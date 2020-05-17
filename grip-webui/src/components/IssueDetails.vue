<template>
    <div class="flex-grow-1 ml-10">
        <v-skeleton-loader
                type="article, sentences, actions"
                v-if="loading"
        />
        <div class="flex" v-else>
            <div class="content">
                <v-toolbar flat>
                    <v-toolbar-title style="font-size:12px;">{{$route.params.projectKey}}-{{issue.niceId}}
                    </v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-btn icon>
                        <v-icon>mdi-dots-horizontal</v-icon>
                    </v-btn>
                </v-toolbar>

                <EditableInput class="headline mb-3" v-model="issue.summary" json-key="summary" :url="'/issue/' + issue.id">
                    {{issue.summary}}
                </EditableInput>
                <div class="mb-8">
                    <v-btn class="mr-4">
                        <v-icon left>mdi-paperclip</v-icon>
                        Attach
                    </v-btn>
                    <v-btn>
                        <v-icon left>mdi-check-box-multiple-outline</v-icon>
                        Create subgoal
                    </v-btn>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import client from "../client";
    import EditableInput from "./EditableInput";

    export default {
        name: "IssueDetails",
        components: {EditableInput},
        props: ['issueId'],
        data: () => ({
            loading: true,
            issue: null
        }),
        methods: {},
        watch: {
            issueId: function (val) {
                if (val) {
                    this.loading = true;
                    client.get('/issue/' + val).then(response => {
                        this.issue = response.data
                    }).then(() => {
                        this.loading = false;
                    })
                }
            }
        }
    }
</script>
