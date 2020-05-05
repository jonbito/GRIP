<template>
    <div class="detail-column" style="width:600px;left:-21px;" v-if="goal">
        <div class="flexContainer">
            <div class="resizer"></div>
            <div class="content">
                <v-toolbar flat>
                    <v-toolbar-title style="font-size:12px;">{{$route.params.projectKey}}-{{goal.niceId}}
                    </v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-btn icon>
                        <v-icon>mdi-dots-horizontal</v-icon>
                    </v-btn>
                    <v-btn icon @click="close">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-toolbar>
                <p class="headline mb-6">
                    As a <strong>{{goal.agent.name}}</strong>, <strong>{{goal.operation.name}}</strong> the <strong>{{goal.subject.name}}</strong>
                </p>
                <v-skeleton-loader
                        type="article, sentences, actions"
                        class="mx-auto"
                        :loading="loading"
                >
                    <div class="mb-8">
                        <v-btn class="mr-4">
                            <v-icon left>mdi-paperclip</v-icon>
                            Attach
                        </v-btn>
                        <v-btn @click="createSubgoalDialogOpen = true">
                            <v-icon left>mdi-check-box-multiple-outline</v-icon>
                            Create subgoal
                        </v-btn>
                    </div>
                    <div class="subtitle-1 font-weight-bold mb-2">
                        Rules
                    </div>

                    <DraggableRules @choose="chooseRule" v-model="rules" group="rules" />
                    <v-card v-if="rules.length === 0" outlined class="flex justify-center pa-2 text-center grey--text"
                            style="border: 2px dashed #ccc;">
                        Rules are empty
                    </v-card>
                    <v-btn class="mt-1 mb-5 grey--text text--darken-1" text @click="createRuleDialogOpen = true">
                        + Create rule
                    </v-btn>

                    <div class="subtitle-1 font-weight-bold my-3" v-if="goal.children.length > 0">
                        Subgoals
                    </div>
                    <DraggableSubgoals @choose="chooseSubgoal" v-model="goal.children" group="subgoals" />
                    <v-btn class="mt-1 mb-5 grey--text text--darken-1" text @click="createSubgoalDialogOpen = true" v-if="goal.children.length > 0">
                        + Create subgoal
                    </v-btn>
                </v-skeleton-loader>
            </div>
        </div>

        <v-dialog v-model="createRuleDialogOpen" persistent max-width="700">
            <CreateRuleCard :goal-id="goal.id" @cancel="createRuleDialogOpen = false" @created="ruleCreated" :opened="createRuleDialogOpen"/>
        </v-dialog>
        <v-dialog v-model="createSubgoalDialogOpen" persistent max-width="700">
            <CreateGoalCard :project-id="1" :goal-id="goal.id" @cancel="createSubgoalDialogOpen = false" :opened="createSubgoalDialogOpen"/>
        </v-dialog>
    </div>
</template>

<script>
    import DraggableSubgoals from "./DraggableSubgoals";
    import DraggableRules from "./DraggableRules";
    import client from "../client";
    import CreateRuleCard from "./CreateRuleCard";
    import CreateGoalCard from "./CreateGoalCard";

    export default {
        name: "GoalDetailColumn",
        components: {
            CreateGoalCard,
            CreateRuleCard,
            DraggableSubgoals,
            DraggableRules
        },
        props: ['goal'],
        data: () => ({
            loading: true,
            rules: [],
            ruleTableHeaders: [
                {
                    text: 'Name',
                    value: 'name'
                },
                {
                    text: '',
                    value: 'actions'
                }
            ],
            createRuleDialogOpen: false,
            createSubgoalDialogOpen: false
        }),
        methods: {
            close() {
                this.$emit('close');
            },
            completeRule(checked, rule) {
                client.post('/rule/' + rule.id + '/complete/' + (checked || false)).then(() => {
                });
            },
            chooseSubgoal(value) {
                console.log(value);
            },
            chooseRule(value) {
                console.log(value);
            },
            ruleCreated(rule) {
                this.rules.push(rule);
                this.createRuleDialogOpen = false;
            }
        },
        watch: {
            goal: function (val) {
                if (val) {
                    this.loading = true;
                    client.get('/rule?goalId=' + val.id).then(response => {
                        this.rules = response.data;
                    }).then(() => {
                        this.loading = false;
                    })
                }
            }
        }
    }
</script>

<style lang="scss">
    .detail-column {
        display: table-cell;
        height: inherit;
        vertical-align: top;

        .flexContainer {
            display: flex;
        }

        .resizer {
            width: 8px;
            cursor: ew-resize;
            border-left-style: solid;
            border-left-width: 2px;
            border-left-color: transparent;

            &:hover {
                border-left-color: rgb(38, 132, 255);
            }

            &:before {
                margin-left: -2px;
                content: " ";
                display: block;
                height: 99%;
                background-image: url(data:image/svg+xml;/resizer.svg);
                background-repeat: no-repeat;
                background-position: left center;
            }
        }

        .content {
            overflow-y: auto;
            width: 100%;
            padding: 0px 16px 0px 6px;
        }
    }
</style>
