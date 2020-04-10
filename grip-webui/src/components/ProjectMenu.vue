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
                <v-col cols="4" style="border-right: 1px solid #ddd; min-height: 350px;">
                    <v-list>
                            <v-list-item @click="goto('/')">
                                <v-list-item-content>
                                    <v-list-item-title>
                                        Your projects
                                    </v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                            <v-list-item @click="goto('/?starred=true')">
                                <v-list-item-content>
                                    <v-list-item-title>
                                        Starred projects
                                    </v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                    </v-list>
                </v-col>
                <v-col cols="8" class="pa-4">
                    <v-text-field
                        label="Search your projects"
                        outlined
                        :value="searchText"
                        @input="type"
                        :loading="searchLoading"
                        hide-details
                    />
                    <div v-if="searchText.length === 0">
                        <div class="mt-4">
                            <strong class="grey--text text--darken-2">Frequent projects</strong>
                        </div>
                        <v-list
                                two-line
                        >
                            <v-list-item-group v-if="frequentProjects.length > 0">
                                <v-list-item
                                        v-for="(project) in frequentProjects"
                                        :key="project.id"
                                >
                                    <v-list-item-icon>
                                        <v-icon v-text="project.icon" />
                                    </v-list-item-icon>
                                    <v-list-item-content>
                                        <v-list-item-title v-text="project.name" />
                                        <v-list-item-subtitle>Software Project</v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list-item-group>
                            <div class="text-center" v-if="frequentProjectsLoading">
                                <v-progress-circular indeterminate color="primary" />
                            </div>
                            <div v-if="frequentProjects.length === 0 && !frequentProjectsLoading">
                                Projects you visit often will appear here
                            </div>
                        </v-list>
                    </div>
                    <v-list v-if="projectsSearchResults.length > 0" two-line>
                        <v-list-item-group>
                            <v-list-item
                                v-for="(project) in projectsSearchResults"
                                :key="project.id"
                            >
                                <v-list-item-icon>
                                    <v-icon v-text="project.icon" />
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title v-text="project.name" />
                                    <v-list-item-subtitle>Software project</v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list-item-group>
                    </v-list>
                    <div
                        v-if="projectsSearchResults.length === 0 && !searchLoading && searchText.length > 0"
                        class="mt-4"
                    >
                        Sorry, no projects matched your search
                    </div>
                </v-col>
            </v-row>
        </v-card>
    </v-menu>
</template>

<script>
    import {createNamespacedHelpers} from 'vuex';

    const { mapActions, mapState } = createNamespacedHelpers('ProjectMenu');

    export default {
        name: "ProjectMenu",
        data: () => ({
            shown: false
        }),
        computed: {
            ...mapState({
                searchText: state => state.searchText,
                searchLoading: state => state.searchLoading,
                frequentProjects: state => state.frequentProjects,
                frequentProjectsLoading: state => state.frequentProjectsLoading,
                projectsSearchResults: state => state.projectsSearchResults
            })
        },
        methods: {
            ...mapActions({
                type: 'type',
                getFrequentProjects: 'getFrequentProjects'
            }),
            goto(path) {
                this.shown = false;
                this.$router.push(path);
            }
        },
        created() {
            this.getFrequentProjects();
        }
    }
</script>

<style scoped>

</style>
