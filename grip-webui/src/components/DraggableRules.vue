<template>
    <draggable
            tag="div"
            :value="value"
            @input="emitter"
            :group="group"
            @unchoose="choose"
            handle=".move-handle"
    >
        <v-card
                v-for="element in value"
                :key="element.id"
                outlined
                class="d-flex justify-space-between pa-2 subgoal-card"
        >
            <v-icon class="move-handle">mdi-format-align-justify</v-icon>
            <div class="blue-grey--text text--darken-4"><a href="#" @click.prevent="choose(element)">{{element.name}}</a></div>
            <div>
                <span class="mr-4 body-2 blue-grey--text text--darken-2"><a href="#" @click.prevent="choose(element)">{{$route.params.projectKey}}-R{{element.niceId}}</a></span>
            </div>
        </v-card>
    </draggable>
</template>

<script>
    import draggable from 'vuedraggable';

    export default {
        name: "DraggableRules",
        components: {
            draggable
        },
        methods: {
            emitter(value) {
                this.$emit("input", value);
            },
            choose(value) {
                this.$emit("choose", value);
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
            }
        }
    }
</script>

<style lang="scss"></style>
