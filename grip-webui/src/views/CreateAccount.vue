<template>
    <v-row>
        <v-col>
            <h1 class="text-center mb-5 mt-6">Welcome to Grip<br/>@{{firstName}}!</h1>
            <p class="text-center">In order to personalize your experience with Grip we would like to know a bit more
                about you.</p>
            <v-card>
                <v-card-text>
                    <validation-observer v-slot="{ handleSubmit }">
                        <form @submit.prevent="handleSubmit(submit)">
                            <h3 class="mb-2 black--text">Role</h3>
                            <validation-provider v-slot="{ errors }" name="Role" rules="required">
                                <v-select
                                        :items="items"
                                        outlined
                                        :error-messages="errors"
                                        v-model="role"
                                        hint="This will help us personalize your onboarding experience."
                                        persistent-hint
                                ></v-select>
                            </validation-provider>

                            <h3 class="mb-2 mt-3 black--text">Who will be using Grip?</h3>
                            <validation-provider v-slot="{ errors }" name="Field" rules="required">
                                <v-radio-group
                                        v-model="whosUsing"
                                        :mandatory="true"
                                        row
                                        :error-messages="errors"
                                >
                                    <v-radio label="My company or team" value="company"></v-radio>
                                    <v-radio label="Just me" value="me"></v-radio>
                                </v-radio-group>
                            </validation-provider>

                            <form-error :error="errorMessage" />

                            <v-btn
                                    color="primary"
                                    block
                                    class="mt-4"
                                    type="submit"
                                    :loading="loading"
                            >
                                Get Started
                            </v-btn>
                        </form>
                    </validation-observer>
                </v-card-text>
            </v-card>
        </v-col>
    </v-row>
</template>

<script>
    import keycloak from "../keycloak";
    import client from '../client';
    import FormError from "../components/FormError";

    export default {
        name: "CreateAccount",
        components: {
            FormError
        },
        data: () => ({
            items: ['Software Developer', 'Development Team Lead', 'Devops Engineer', 'Systems Administrator', 'Security Analyst', 'Data Analyst', 'Product Manager', 'Product Designer', 'Other'],
            role: 'Software Developer',
            username: '',
            whosUsing: 'company',
            loading: false,
            usernameExistsLoading: false,
            usernameValid: false,
            errorMessage: ''
        }),
        computed: {
            firstName: {
                get() {
                    return keycloak.tokenParsed.given_name;
                }
            },
        },
        methods: {
            submit() {
                this.loading = true;
                client.post('/account', {
                    role: this.role,
                    whosUsing: this.whosUsing
                }).then(() => {
                    window.location.href = '/';
                }).catch((error) => {
                    this.errorMessage = error.response.data.message;
                }).then(() => {
                    this.loading = false;
                })
            }
        }
    }
</script>
