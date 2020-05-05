<template>
    <draggable
            tag="div"
            :value="value"
            @input="emitter"
            :group="group"
            @unchoose="choose"
    >
        <v-card
                v-for="element in value"
                :key="element.id"
                outlined
                :class="'d-flex justify-space-between pa-2 issue-card' + (element.selected ? ' issue-card-selected' : '')"
        >
            <div class="blue-grey--text text--darken-4">{{element.operation.name}} {{element.subject.name}}</div>
            <div>
                <span class="mr-4 body-2 blue-grey--text text--darken-2">{{$route.params.projectKey}}-{{element.niceId}}</span>
                <v-chip v-if="showSize" class="px-3" small>{{element.size != null ? element.size : '-'}}</v-chip>
            </div>
        </v-card>
    </draggable>
</template>

<script>
    import draggable from 'vuedraggable';

    export default {
        name: "DraggableGoals",
        components: {
            draggable
        },
        methods: {
            emitter(value) {
                this.$emit("input", value);
            },
            choose(value) {
                this.$emit("choose", this.value[value.oldIndex]);
            }
        },
        props: {
            value: {
                required: false,
                type: Array,
                default: null
            },
            group: {
                required: true,
                type: String
            },
            showSize: {
                required: false,
                type: Boolean,
                default: true
            }
        }
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

    .issue-card-selected {
        background: #DEEBFF !important;
    }

    .sortable-ghost {
        opacity: 0;
        visibility: hidden;
    }
</style>
