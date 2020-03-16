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
                            :loading="rolesLoading"
                    ></v-select>

                    <h3 class="mb-2 mt-3 black--text">Who will be using Grip?</h3>
                    <v-radio-group v-model="whosUsing" :mandatory="true" row>
                        <v-radio label="My company or team" value="company"></v-radio>
                        <v-radio label="Just me" value="me"></v-radio>
                    </v-radio-group>

                    <v-btn color="primary" block class="mt-4" @click="submit">Get Started</v-btn>
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
            items: [
                {
                    text: 'Software Developer',
                    value: 'SOFTWARE_DEVELOPER'
                }
            ],
            role: 'SOFTWARE_DEVELOPER',
            whosUsing: 'company',
            loading: false,
            rolesLoading: false,
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
                }).then(() => {
                    this.loading = false;
                })
            }
        },
        created() {
            this.rolesLoading = true;
            client.get('/account/roles').then((response) => {
                this.items = response.data;
                this.role = response.data[0].value;
            }).then(() => {
                this.rolesLoading = false;
            })
        }
    }
</script>
