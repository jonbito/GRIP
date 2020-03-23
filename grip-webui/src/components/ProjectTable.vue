<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="projects"
                :options.sync="options"
                :server-items-length="totalProjects"
                :loading="loading"
                class="elevation-1"
                show-select
        >
            <template v-slot:item.data-table-select="{ item }">
                <v-checkbox on-icon="md-star" off-icon="md-start-outline" v-model="item.starred" @input="starProject" />
            </template>
        </v-data-table>
    </div>
</template>

<script>
    export default {
        name: "ProjectTable",
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
            totalProjects: 12,
            options: {},
            loading: false,
        }),
        methods: {
            starProject(val) {
                console.log(val);
            }
        },
        created() {
            this.getFrequentProjects();
        }
    }
</script>

<style scoped>

</style>
