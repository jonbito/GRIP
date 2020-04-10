<template>
    <v-navigation-drawer
            clipped
            app
            permanent
            width="300"
            v-if="show"
    >
        <v-list-item>
            <v-list-item-icon>
                <v-avatar color="#eee" style="border-radius: 15%;">
                    <span class="headline grey--text text--darken-2">G</span>
                </v-avatar>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title class="title">
                    Slic Replatform
                </v-list-item-title>
                <v-list-item-subtitle>
                    Software project
                </v-list-item-subtitle>
            </v-list-item-content>
        </v-list-item>

        <v-divider></v-divider>

        <v-list dense>
            <v-list-item :to="urlPrefix + '/'">
                <v-list-item-action>
                    <v-icon>
                        mdi-format-list-bulleted-triangle
                    </v-icon>
                </v-list-item-action>
                <v-list-item-content>
                    <v-list-item-title>
                        Backlog
                    </v-list-item-title>
                </v-list-item-content>
            </v-list-item>
            <v-list-item link>
                <v-list-item-action>
                    <v-icon>
                        mdi-card-account-mail
                    </v-icon>
                </v-list-item-action>
                <v-list-item-content>
                    <v-list-item-title>
                        Contact
                    </v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
    export default {
        name: "ProjectNavigationDrawer",
        computed: {
            show: function() {
                return !!this.$route.params.projectKey;
            },
            urlPrefix: function() {
                return '/' + this.$route.params.username + '/' + this.$route.params.projectKey;
            }
        }
    }
</script>
