<template>
    <v-card>
        <validation-observer ref="observer">
            <form @submit.prevent="submit">
                <v-card-title class="headline">Create Rule</v-card-title>
                <v-card-text>
                    <validation-provider v-slot="{ errors }" name="Name" rules="required">
                        <v-text-field
                                label="Name"
                                outlined
                                :error-messages="errors"
                                v-model="name"
                                dense
                                ref="nameField"
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
        name: "CreateRuleCard",
        components: {WysiwgEditor},
        props: ['goalId', 'opened'],
        data: () => ({
            loading: false,
            name: 'Should ',
            description: '<strong>Given</strong> <br /><strong>When</strong> <br /><strong>Then</strong>&nbsp;'
        }),
        methods: {
            submit() {
                client.post('/rule', {
                    goalId: this.goalId,
                    name: this.name,
                    description: this.description
                }).then((response) => {
                    this.$emit('created', response.data);
                }).then(() => {
                    this.loading = false;
                });
            },
            init() {
                this.name = 'Should ';
                this.description = '<strong>Given</strong> <br /><strong>When</strong> <br /><strong>Then</strong>&nbsp;';
                setTimeout(() => {
                    this.$refs.nameField.focus();
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

