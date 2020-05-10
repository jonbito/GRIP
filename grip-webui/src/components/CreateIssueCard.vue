<template>
    <v-card>
        <validation-observer ref="validationObserver">
            <form @submit.prevent="submit">
                <v-card-title class="headline">Create Issue</v-card-title>
                <v-card-text>
                    <validation-provider v-slot="{ errors }" name="Summary" rules="required">
                        <v-text-field
                                label="Summary"
                                outlined
                                :error-messages="errors"
                                v-model="summary"
                                dense
                                ref="summaryField"
                        />
                    </validation-provider>

                    <h3 class="mt-3 mb-3">Description</h3>
                    <WysiwgEditor v-model="description" />
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
    import WysiwgEditor from "./WysiwgEditor";

    export default {
        name: "CreateIssueCard",
        components: {WysiwgEditor},
        props: ['projectId', 'opened'],
        data: () => ({
            loading: false,
            summary: '',
            description: ''
        }),
        methods: {
            submit() {
                client.post('/rule', {
                    projectId: this.projectId,
                    summary: this.summary,
                    description: this.description
                }).then((response) => {
                    this.$emit('created', response.data);
                }).then(() => {
                    this.loading = false;
                });
            },
            init() {
                this.summary = '';
                this.description = '';
                this.$refs.validationObserver.reset();

                setTimeout(() => {
                    this.$refs.summaryField.focus();
                }, 100);
            },
        },
        mounted() {
            this.init();
        },
        watch: {
            opened: function (val) {
                if(val) {
                    this.init();
                }
            },
        },
    }
</script>

