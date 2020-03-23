<template>
    <div>
        <div class="d-flex justify-space-between mt-3">
            <h1>Projects</h1>
            <v-btn color="success" v-if="projects.length > 0" to="/-/projects/new">New Project</v-btn>
        </div>
        <v-divider class="mt-3 mb-10" />
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
                <v-checkbox on-icon="mdi-star" off-icon="mdi-star-outline" v-model="item.starred" @input="starProject" />
            </template>
            <template v-slot:item.lead="{ item }">
                <router-link :to="'/people/' + item.leadId" class="mr-1">
                    <v-avatar size="40px">
                        <img :src="item.leadAvatar || '/default-avatar.png'" />
                    </v-avatar>
                </router-link>&nbsp;
                <router-link :to="'/people/' + item.leadId" class="link">{{ item.leadName }}</router-link>
            </template>
            <template v-slot:no-data>
                <div class="text-center mt-8" v-if="projects.length === 0">
                    <v-icon size="128">mdi-folder</v-icon>
                    <h2 class="mb-2 black--text">You currently have no projects</h2>
                    <p class="mb-6 black--text">Let's create your first project in Grip</p>
                    <v-btn color="primary" to="/-/projects/new" class="mb-10">Create project</v-btn>
                </div>
            </template>
        </v-data-table>
    </div>
</template>

<script>
    import client from "../client";

    export default {
  name: 'BrowseProjects',
  components: {
  },
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
    }),
    methods: {
        starProject(val) {
            console.log(val);
        },
    },
    watch: {
      options: {
          handler() {
              this.loading = true;
              client.get('/project/list').then((response) => {
                  console.log(response.data);
                  this.projects = response.data.contents;
                  this.totalProjects = response.data.total;
              }).then(() => {
                  this.loading = false;
              })
          }
      }
    },
}
</script>

<style>
    .project-table tbody tr:hover {
        background: none !important;
    }
</style>
