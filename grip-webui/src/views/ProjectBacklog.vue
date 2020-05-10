<template>
    <div>
        <ProjectBreadcrumbs :items="breadcrumb"/>
        <h1>Backlog</h1>
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

        <v-dialog v-model="createDialogOpen" persistent max-width="700">
            <CreateGoalCard :project-id="projectId" @cancel="createDialogOpen = false" :opened="createDialogOpen"/>
        </v-dialog>

        <div class="plan-group">

            <div class="backlog-column">

                <h3 class="mb-3">Milestone 1</h3>
                <v-card v-if="goals.length === 0" outlined class="d-flex justify-center pa-2 text-center grey--text"
                        style="border: 2px dashed #ccc;">
                    Plan a release by dragging issues here.
                </v-card>

                <h3 class="mt-6 mb-3">Backlog</h3>
                <v-card v-if="goals.length === 0" outlined class="d-flex justify-center pa-2 text-center grey--text"
                        style="border: 2px dashed #ccc;">
                    Your backlog is empty
                </v-card>
                <DraggableGoals v-model="goals" group="goals" @choose="goalChosen"/>

                <v-btn class="mt-1 grey--text text--darken-1" text @click="createDialogOpen = true">+ Create issue
                </v-btn>

            </div>

            <GoalDetailColumn :goal="selectedGoal" @close="closeDetailColumn" />

        </div>
    </div>
</template>

<style lang="scss">
    .plan-group {
        display: table;
        table-layout: fixed;
        width: 100%;

        .backlog-column {
            display: table-cell;
            height: inherit;
            vertical-align: top;
            padding-right: 20px;
        }
    }
</style>

<script>
    import ProjectBreadcrumbs from "../components/ProjectBreadcrumbs";
    import CreateGoalCard from "../components/CreateGoalCard";
    import DraggableGoals from "../components/DraggableGoals";
    import client from "../client";
    import GoalDetailColumn from "../components/GoalDetailColumn";

    export default {
        name: 'ProjectBacklog',
        components: {
            ProjectBreadcrumbs,
            CreateGoalCard,
            DraggableGoals,
            GoalDetailColumn
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
            goals: [],
            selectedGoal: null,
            selectedRule: null,
            search: '',
            createDialogOpen: false,
            loading: true
        }),
        created() {
            client.get('/goal?projectId=' + this.projectId).then(response => {
                this.goals = response.data;
            }).then(() => {
                this.loading = false;
            })
        },
        methods: {
            goalChosen(value) {
                // deselect all
                this.goals.forEach(g => {
                    if(g.selected) {
                        this.$set(g, 'selected', false);
                    }
                });
                this.$set(value, 'selected', true);
                this.selectedGoal = value;
            },
            ruleChosen(value) {
                this.selectedGoal = null;
                this.selectedRule = value;
            },
            closeDetailColumn() {
                this.goals.forEach(g => {
                    if(g.selected) {
                        this.$set(g, 'selected', false);
                    }
                });
                this.selectedGoal = null;
            }
        }
    }

</script>
