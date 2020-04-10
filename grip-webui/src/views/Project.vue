<template>
    <div>
        <ProjectBreadcrumbs :items="items" />
        <div class="pl-6">
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

            <h3 class="mb-3">Milestone 1</h3>
            <v-card v-if="list1.length === 0" outlined class="d-flex justify-center pa-2 text-center grey--text" style="border: 2px dashed #ccc;">
                Plan a milestone by dragging issues here.
            </v-card>
            <draggable empty-insert-threshold="100" :list="list1" group="issues">
                <v-card v-for="(element, index) in list1" :key="element.name" outlined class="d-flex justify-space-between pa-2 issue-card">
                    <div>{{element.name}}</div>
                    <div>{{index}}</div>
                </v-card>
            </draggable>
            <v-btn class="mt-1 grey--text text--darken-1" text>+ Create issue</v-btn>



            <h3 class="mt-6 mb-3">Backlog</h3>
            <v-card v-if="list2.length === 0" outlined class="d-flex justify-center pa-2 text-center grey--text" style="border: 2px dashed #ccc;">
                Your backlog is empty
            </v-card>
            <draggable empty-insert-threshold="100" :list="list2" group="issues">
                <v-card v-for="(element, index) in list2" :key="element.name" outlined class="d-flex justify-space-between pa-2 issue-card">
                    <div>{{element.name}}</div>
                    <div>{{index}}</div>
                </v-card>
            </draggable>
            <v-btn class="mt-1 grey--text text--darken-1" text>+ Create issue</v-btn>
        </div>
    </div>
</template>

<script>
    import ProjectBreadcrumbs from "../components/ProjectBreadcrumbs";
    import draggable from 'vuedraggable';

    export default {
        name: 'Project',
        components: {
            ProjectBreadcrumbs,
            draggable
        },
        data: () => ({
            items: [
                {
                    text: 'Jonathan Bishop',
                    to: '/asdf',
                },
                {
                    text: 'Grip',
                    to: '/asdf/SP2',
                }
            ],
            list1: [
                { name: "John", id: 1 },
                { name: "Joao", id: 2 },
                { name: "Jean", id: 3 },
                { name: "Gerard", id: 4 }
            ],
            list2: [
                { name: "Juan", id: 5 },
                { name: "Edgard", id: 6 },
                { name: "Johnson", id: 7 }
            ],
            search: '',
        })
    }

</script>

<style lang="scss">
    .issue-card {
        border-radius: 0 !important;
        &:hover {
            background: #eee;
            cursor: move;
        }
    }
    .issue-card-ghost {
        visibility: hidden;
        opacity: 0;
    }
    .sortable-ghost {
        opacity: 0;
    }
    .flip-list-move {
        transition: transform 0.5s;
    }
</style>
