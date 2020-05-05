<template>
    <div>
        <div class="d-flex justify-space-between mt-3">
            <h1>Projects</h1>
            <v-btn color="success" to="/-/projects/new">New Project</v-btn>
        </div>
        <v-divider class="mt-3 mb-10"/>
        <v-row
            align="end"
        >
            <v-col cols="8" class="pb-0">
                <v-tabs v-model="currentTab" class="project-tabs">
                    <v-tab>Your Projects</v-tab>
                    <v-tab>Starred Projects</v-tab>
                </v-tabs>
            </v-col>
            <v-col cols="4">
                <v-text-field
                        v-model="search"
                        outlined
                        dense
                        hide-details
                        placeholder="Search projects"
                        @input="searchProjects"
                        clearable
                >
                    <template v-slot:append v-if="!search">
                        <v-icon>mdi-magnify</v-icon>
                    </template>
                </v-text-field>
            </v-col>
        </v-row>
        <v-divider />
        <v-data-table
                :headers="headers"
                :items="projects"
                :options.sync="options"
                :server-items-length="totalProjects"
                :loading="loading"
                class="elevation-1 project-table"
                show-select
                single-select
                item-key="key"
                :search="search"
        >
            <template v-slot:item.name="{ item }">
                <router-link :to="item.url" class="mr-1">
                    <v-avatar color="#eee" style="border-radius: 15%;">
                        <span v-if="!item.avatar" class="headline grey--text text--darken-2">{{item.name.charAt(0).toUpperCase()}}</span>
                    </v-avatar>
                </router-link>&nbsp;
                <router-link :to="item.url" class="link">{{item.group}} / <strong>{{item.name}}</strong></router-link>
            </template>
            <template v-slot:item.data-table-select="{ item }">
                <v-checkbox on-icon="mdi-star" off-icon="mdi-star-outline" v-model="item.starred" @change="starProject($event, item)"/>
            </template>
            <template v-slot:item.lead="{ item }">
                <router-link :to="'/people/' + item.leadId" class="mr-1">
                    <v-avatar size="40px" color="blue">
                        <img v-if="item.leadAvatar" :src="item.leadAvatar"/>
                        <span v-if="!item.leadAvatar" class="white--text">{{getLeadAvatarInitials(item.leadName)}}</span>
                    </v-avatar>
                </router-link>&nbsp;
                <router-link :to="'/people/' + item.leadId" class="link">{{ item.leadName }}</router-link>
            </template>
            <template v-slot:no-data>
                <div class="text-center mt-8" v-if="!search && currentTab === 0">
                    <v-icon size="128">mdi-folder</v-icon>
                    <h2 class="mb-2 black--text">You currently have no projects</h2>
                    <p class="mb-6 black--text">Let's create your first project in Grip</p>
                    <v-btn color="primary" to="/-/projects/new" class="mb-10">Create project</v-btn>
                </div>
                <div class="text-center mt-3" v-if="!search && currentTab === 1">
                    <p>No projects found</p>
                </div>
                <div class="text-center mt-3" v-if="search">
                    <p>No projects found</p>
                    <v-btn class="mb-5" @click="clearSearch">Clear Search</v-btn>
                </div>
            </template>
        </v-data-table>
    </div>
</template>

<script>
    import client from "../client";
    import {debounce} from "lodash"

    export default {
        name: 'BrowseProjects',
        components: {},
        data: () => ({
            headers: [
                {
                    text: 'Name',
                    value: 'name'
                },
                {
                    text: 'Key',
                    value: 'key'
                },
                {
                    text: 'Lead',
                    value: 'lead'
                }
            ],
            projects: [],
            totalProjects: 0,
            options: {},
            loading: false,
            projectsLoadedOnce: false,
            search: '',
            currentTab: 0
        }),
        methods: {
            starProject(checked, item) {
                client.post('/project/star/' + item.id + '/' + checked).then(() => {
                });

                if(!checked && this.currentTab === 1) {
                    this.projects.forEach((p, i) => {
                        if(p.id === item.id) {
                            this.projects.splice(i, 1);
                            --this.totalProjects;
                        }
                    });
                }
            },
            searchProjects() {
                this.loading = true;
                this.searchTypeDebounce();
            },
            searchTypeDebounce: debounce(function() {
                const { sortBy, sortDesc, page, itemsPerPage } = this.options;

                const urlParams = new URLSearchParams();
                urlParams.append('sortBy', sortBy);
                urlParams.append('sortDesc', sortDesc);
                urlParams.append('page', page);
                urlParams.append('itemsPerPage', itemsPerPage);
                urlParams.append('search', this.search ? this.search : '');
                urlParams.append('starred', (this.currentTab === 1).toString());

                client.get('/project/list?' + urlParams.toString()).then((response) => {
                    this.projects = response.data.contents;
                    this.totalProjects = response.data.total;
                }).then(() => {
                    this.loading = false;
                    this.projectsLoadedOnce = true;
                })
            }, 500),
            getLeadAvatarInitials(name) {
                const nameSplit = name.split(" ");
                return nameSplit[0].charAt(0) + nameSplit[1].charAt(0);
            },
            clearSearch() {
                this.search = "";
                this.searchProjects();
            }
        },
        watch: {
            options: {
                handler() {
                    this.searchProjects();
                }
            },
            currentTab: function(val) {
                this.$router.push('/' + (val === 1 ? '?starred=true' : '')).catch(() => {});
            },
            '$route.query.starred': function(val) {
                this.currentTab = val ? 1 : 0;
                this.searchProjects();
            }
        },
        created() {
            if(this.$route.query.starred) {
                this.currentTab = 1;
            }
        }
    }
</script>
