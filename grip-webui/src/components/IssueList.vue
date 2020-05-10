<template>
    <div class="d-flex overflow-hidden">
        <div class="issue-list-container">
            <div class="issue-list-header-container">
                <div class="d-flex justify-space-between align-center" style="width:100%;">
                    <div>
                        <v-select
                                :items="filterDropdownItems"
                                dense
                                solo
                                hide-details
                                background-color="#F4F5F7"
                                style="width:150px;"
                        ></v-select>
                    </div>
                    <div>
                        <v-tooltip bottom open-delay="500">
                            <template v-slot:activator="{ on }">
                                <v-btn icon v-on="on">
                                    <v-icon>mdi-arrow-down</v-icon>
                                </v-btn>
                            </template>
                            <span>Sort ascending</span>
                        </v-tooltip>
                    </div>
                </div>
            </div>

            <StretchHeight :offset="80" v-slot="{height}">
                <div ref="issueListItemsContainer" class="issue-list-items-container" :style="{'height': height}">
                    <ScrollShadow :style="{'height': height}">
                        <a class="issue-list-item" @click="choose(index)" :key="issue.id"
                           :class="{'active': issue.selected}" href="#" v-for="(issue, index) in issues">
                            <div class="flex justify-space-between" style="height:100%;">
                                <div class="subtitle-2">
                                    {{issue.summary}}
                                </div>
                                <div class="caption font-weight-bold grey--text">
                                    {{$route.params.projectKey}}-{{issue.niceId}}
                                </div>
                            </div>
                        </a>
                    </ScrollShadow>
                </div>
            </StretchHeight>

            <div class="issue-list-footer-container">
                <div class="d-flex justify-space-between align-center" style="width:100%;">
                    <div>
                        <v-btn dense color="success" small @click="$emit('createIssue')">Create issue</v-btn>
                    </div>
                    <div class="grey--text text--darken-2">
                        Issue {{selectedIndex}} of {{issues.length}}
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import StretchHeight from "./StretchHeight";
    import ScrollShadow from "./ScrollShadow";

    export default {
        name: "IssueList",
        components: {ScrollShadow, StretchHeight},
        data: () => ({
            selectedIndex: 1,
            filterDropdownItems: [
                {text: 'Priority', value: 'priority'}
            ]
        }),
        methods: {
            choose(index) {
                this.selectedIndex = index + 1;
                this.$emit("choose", this.issues[index]);
            }
        },
        props: {
            issues: {
                required: false,
                type: Array,
                default: null
            },
        }
    }
</script>

<style lang="scss">
    .issue-list-container {
        display: flex;
        flex-direction: column;
        background-color: rgb(244, 245, 247);
        margin-bottom: 16px;
        border-radius: 4px;
        padding: 8px;

        .issue-list-header-container {
            display: flex;
            -webkit-box-flex: 1;
            flex-grow: 1;
            min-height: 32px;
            padding-bottom: 8px;
        }

        .issue-list-items-container {
            min-height: 97px;
            background-color: rgb(244, 245, 247);
            padding: 0px;
            overflow-y: scroll;
            position: relative;
            width: 256px;

            .issue-list-item {
                display: block;
                box-sizing: border-box;
                background-color: rgb(255, 255, 255);
                color: inherit;
                padding: 12px;
                border-top: 0px;
                border-bottom: 1px solid rgb(235, 236, 240);
                border-left: 1px solid rgb(235, 236, 240);
                border-right: 1px solid rgb(223, 225, 230);
                border-radius: 0px;
                height: 71px;
                width: 100%;

                &:first-child {
                    border-radius: 4px 4px 0px 0px;
                    border-top: 1px solid rgb(235, 236, 240);
                }

                &:last-child {
                    border-radius: 0px 0px 4px 4px;
                }

                &.active {
                    color: rgb(0, 82, 204);
                    background-color: rgb(222, 235, 255);

                    &:hover {
                        background-color: rgb(222, 235, 255);
                        color: inherit;
                        text-decoration: none;
                    }
                }

                &:hover {
                    background-color: rgb(250, 251, 252);
                    color: inherit;
                    text-decoration: none;
                }
            }
        }

        .issue-list-footer-container {
            background-color: rgb(244, 245, 247);
            display: flex;
            -webkit-box-pack: start;
            justify-content: flex-start;
            -webkit-box-align: center;
            align-items: center;
            padding: 12px 0px 4px;
        }
    }
</style>
