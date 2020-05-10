<template>
    <div>
        <ProjectBreadcrumbs :items="breadcrumb"/>
        <h1>Issues</h1>
        <v-row>
            <v-col cols="12" md="3">
                <v-text-field
                        dense
                        outlined
                        clearable
                        v-model="search"
                >
                    <template v-slot:append v-if="!search">
                        <v-icon>mdi-magnify</v-icon>
                    </template>
                </v-text-field>
            </v-col>
        </v-row>

        <div class="d-flex">
            <IssueList @choose="issueChosen" :issues="issues" @createIssue="createDialogOpen = true" />
            <IssueDetails :issue-id="selectedIssueId" />
        </div>


        <v-dialog v-model="createDialogOpen" persistent max-width="700">
            <CreateIssueCard :project-id="projectId" @cancel="createDialogOpen = false" :opened="createDialogOpen"/>
        </v-dialog>

    </div>

</template>

<style lang="scss">
</style>

<script>
    import ProjectBreadcrumbs from "../components/ProjectBreadcrumbs";
    import client from "../client";
    import CreateIssueCard from "../components/CreateIssueCard";
    import IssueList from "../components/IssueList";
    import IssueDetails from "../components/IssueDetails";

    export default {
        name: 'ProjectIssues',
        components: {
            IssueDetails,
            IssueList,
            CreateIssueCard,
            ProjectBreadcrumbs,
        },
        props: {
            projectId: {
                type: Number,
                required: true
            }
        },
        data: () => ({
            breadcrumb: [
                {
                    to: '/asdf',
                    text: 'Jonathan Bishop'
                }
            ],
            issues: [],
            selectedIssueId: null,
            search: '',
            createDialogOpen: false,
            loading: true
        }),
        created() {
            client.get('/issue?projectId=' + this.projectId).then(response => {
                this.issues = response.data;
                if (this.issues && this.issues.length > 0) {
                    this.issueChosen(this.issues[0]);
                }
            }).then(() => {
                this.loading = false;
            })
        },
        methods: {
            issueChosen(value) {
                this.issues.forEach(i => {
                    if (i.selected) {
                        this.$set(i, 'selected', false);
                    }
                });
                this.$set(value, 'selected', true);
                this.selectedIssueId = value.id;
            },
        }
    }

</script>
