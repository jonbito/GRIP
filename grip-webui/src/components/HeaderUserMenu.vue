<template>
    <v-menu
            v-model="shown"
            offset-y
            :close-on-content-click="false"
    >
        <template v-slot:activator="{ on }">
            <v-btn
                    target="_blank"
                    v-on="on"
                    text
                    class="px-1"
            >
                <v-avatar color="blue" size="32">
                    <span class="white--text">JB</span>
                </v-avatar>
                <v-icon>mdi-chevron-down</v-icon>
            </v-btn>
        </template>

        <v-card>
            <v-list two-line>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title><strong>Jonathan Bishop</strong></v-list-item-title>
                        <v-list-item-subtitle>@{{username}}</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
            <v-divider />
                    <v-list>
                        <v-list-item-group>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        Set Status
                                    </v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        Profile
                                    </v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        Settings
                                    </v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>

                            <v-divider class="my-2" />

                            <v-list-item @click="signOut">
                                <v-list-item-content>
                                    <v-list-item-title>
                                        Sign out
                                    </v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>

                        </v-list-item-group>
                    </v-list>
        </v-card>
    </v-menu>
</template>

<script>
    import keycloak from "../keycloak";

    export default {
        name: "HeaderUserMenu",
        data: () => ({
            shown: false
        }),
        computed: {
            username: {
                get() {
                    return keycloak.tokenParsed.preferred_username
                }
            }
        },
        methods: {
            signOut() {
                keycloak.logout();
            }
        }
    }
</script>

<style scoped>

</style>
