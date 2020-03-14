<template>
    <v-menu
            v-model="shown"
            offset-y
            :close-on-content-click="false"
            :nudge-width="350"
    >
        <template v-slot:activator="{ on }">
            <v-btn
                    target="_blank"
                    text
                    v-on="on"
            >
                <span class="mr-1">Projects</span>
                <v-icon>mdi-chevron-down</v-icon>
            </v-btn>
        </template>

        <v-card>
            <v-row no-gutters>
                <v-col cols="4" style="border-right: 1px solid #ddd">
                    <v-list>
                        <v-list-item-group>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        Your projects
                                    </v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        Starred projects
                                    </v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        Explore projects
                                    </v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list-item-group>
                    </v-list>
                </v-col>
                <v-col cols="8" class="pa-4">
                    <v-text-field
                        label="Search your projects"
                        outlined
                    />
                    <v-list>
                        <v-subheader>
                            Frequently visited
                        </v-subheader>
                        <v-list-item-group>
                            <v-list-item
                                v-for="(project, i) in frequentProjects"
                                :key="i"
                            >
                                <v-list-item-icon>
                                    <v-icon v-text="project.icon" />
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title v-text="project.text" />
                                </v-list-item-content>
                            </v-list-item>
                        </v-list-item-group>
                    </v-list>
                </v-col>
            </v-row>
        </v-card>
    </v-menu>
</template>

<script>
    import {createNamespacedHelpers} from 'vuex';

    const { mapActions, mapState } = createNamespacedHelpers('project-menu');

    export default {
        name: "ProjectMenu",
        computed: {
            ...mapState({
                frequentProjects: state => state.frequentProjects,
            })
        },
        data: () => ({
            shown: false
        })
    }
</script>

<style scoped>

</style>
