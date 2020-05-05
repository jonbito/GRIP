<template>
    <v-card>
        <validation-observer ref="observer">
            <form @submit.prevent="submit">
                <v-card-title class="headline">Create Goal</v-card-title>
                <v-card-text>
                    <v-skeleton-loader
                            type="card"
                            :loading="initialLoading"
                    >
                        <validation-provider v-slot="{ errors }" name="Agent" rules="required">
                            <v-combobox
                                    :items="agents"
                                    item-text="name"
                                    return-object
                                    label="Agent"
                                    outlined
                                    hide-no-data
                                    :error-messages="errors"
                                    v-model="selectedAgent"
                                    dense
                            ></v-combobox>
                        </validation-provider>
                        <validation-provider v-slot="{ errors }" name="What Action" rules="required">
                            <v-combobox
                                    :items="operations"
                                    item-text="name"
                                    hide-no-data
                                    return-object
                                    label="What Action"
                                    :error-messages="errors"
                                    outlined
                                    v-model="selectedOperation"
                                    dense
                            >
                            </v-combobox>
                        </validation-provider>
                        <validation-provider v-slot="{ errors }" name="What Thing" rules="required">
                            <v-combobox
                                    :items="subjects"
                                    item-text="name"
                                    hide-no-data
                                    return-object
                                    label="What Thing"
                                    :error-messages="errors"
                                    outlined
                                    v-model="selectedSubject"
                                    dense
                            ></v-combobox>
                        </validation-provider>
                        <validation-provider v-slot="{ errors }" name="Size" rules="numeric">
                            <v-combobox
                                    :items="[0,1,2,3,5,8,13,21]"
                                    hide-no-data
                                    label="Size"
                                    :error-messages="errors"
                                    outlined
                                    dense
                                    v-model="size"
                            >
                            </v-combobox>
                        </validation-provider>
                    </v-skeleton-loader>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn type="submit" :loading="loading" color="primary">Create</v-btn>
                    <v-btn text @click="$emit('cancel')">Cancel</v-btn>
                </v-card-actions>
            </form>
        </validation-observer>
    </v-card>
</template>

<script>
    import client from "../client";

    export default {
        name: "CreateGoalCard",
        props: ['projectId', 'goalId', 'opened'],
        data: () => ({
            agents: [],
            subjects: [],
            operations: [],
            selectedAgent: null,
            selectedSubject: null,
            selectedOperation: null,
            initialLoading: true,
            loading: false,
            size: null
        }),
        methods: {
            replaceComboboxValueWithExisting(val, dataString, list) {
                if (!val || typeof val === 'object') {
                    return;
                }

                for (let a of list) {
                    if (a.name.toLowerCase() === val.toLowerCase()) {
                        this[dataString] = a;
                        break;
                    }
                }
            },
            submit() {
                this.loading = true;
                setTimeout(this.submitTimeout, 500);
            },
            async submitTimeout() {
                if(!await this.$refs.observer.validate()) {
                    this.loading = false;
                    return;
                }

                let agent = this.selectedAgent;
                let subject = this.selectedSubject;
                let operation = this.selectedOperation;
                if(typeof agent !== 'object') {
                    const response = await client.post('/agent', {
                        projectId: this.projectId,
                        name: agent
                    });
                    agent = response.data;
                }
                if(typeof subject !== 'object') {
                    const response = await client.post('/subject', {
                        projectId: this.projectId,
                        name: subject
                    });
                    subject = response.data;
                }
                if(typeof operation !== 'object') {
                    const response = await client.post('/operation', {
                        projectId: this.projectId,
                        name: operation
                    });
                    operation = response.data;
                }

                await client.post('/goal', {
                    projectId: this.projectId,
                    goalId: this.goalId,
                    agentId: agent.id,
                    subjectId: subject.id,
                    operationId: operation.id,
                    size: this.size
                });
                this.loading = false;
                this.$emit('cancel');
            },
            init() {
                this.initialLoading = true;
                this.selectedAgent = null;
                this.selectedSubject = null;
                this.selectedOperation = null;
                this.size = null;
                const getAgents = client.get('/agent?projectId=' + this.projectId);
                const getSubjects = client.get('/subject?projectId=' + this.projectId);
                const getOperations = client.get('/operation?projectId=' + this.projectId);
                Promise.all([getAgents, getSubjects, getOperations]).then((values) => {
                    this.agents = values[0].data;
                    this.subjects = values[1].data;
                    this.operations = values[2].data;
                    this.initialLoading = false;
                });
            },
        },
        created() {
            this.init();
        },
        watch: {
            selectedAgent: function (val) {
                this.replaceComboboxValueWithExisting(val, 'selectedAgent', this.agents);
            },
            selectedSubject: function (val) {
                this.replaceComboboxValueWithExisting(val, 'selectedSubject', this.subjects);
            },
            selectedOperation: function (val) {
                this.replaceComboboxValueWithExisting(val, 'selectedOperation', this.operations);
            },
            opened: function (val) {
                if(val) {
                    this.init();
                }
            },
        },
    }
</script>

