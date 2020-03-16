<template>
    <v-row>
        <v-col>
            <h1 class="text-center mb-5 mt-6">Welcome to Grip<br />@{{firstName}}!</h1>
            <p class="text-center">In order to personalize your experience with Grip we would like to know a bit more about you.</p>
            <v-card>
                <v-card-text>
                    <h3 class="mb-2 black--text">Role</h3>
                    <v-select
                            :items="items"
                            outlined
                            v-model="role"
                            hint="This will help us personalize your onboarding experience."
                            persistent-hint
                    ></v-select>

                    <h3 class="mb-2 mt-3 black--text">Who will be using Grip?</h3>
                    <v-radio-group v-model="whosUsing" :mandatory="true" row>
                        <v-radio label="My company or team" value="company"></v-radio>
                        <v-radio label="Just me" value="me"></v-radio>
                    </v-radio-group>

                    <v-btn
                            color="primary"
                            block
                            class="mt-4"
                           @click="submit"
                           :loading="loading"
                    >
                        Get Started
                    </v-btn>
                </v-card-text>
            </v-card>
        </v-col>
    </v-row>
</template>

<script>
    import keycloak from "../keycloak";
    import client from "../client";

    export default {
        name: "CreateAccount",
        data: () => ({
            items: ['Software Developer', 'Development Team Lead', 'Devops Engineer', 'Systems Administrator', 'Security Analyst', 'Data Analyst', 'Product Manager', 'Product Designer', 'Other'],
            role: 'Software Developer',
            whosUsing: 'company',
            loading: false,
        }),
        computed: {
            firstName: {
                get() {
                    return keycloak.tokenParsed.given_name;
                }
            }
        },
        methods: {
            submit() {
                this.loading = true;
                client.post('/account', {
                    role: this.role,
                    whosUsing: this.whosUsing
                }).then((response) => {
                    console.log(response);
                }).catch((error) => {
                   console.log(error);
                }).then(() => {
                    this.loading = false;
                })
            }
        }
    }
</script>
