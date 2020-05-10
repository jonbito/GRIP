<template>
    <div class="editable-input-container" @click="startEditing" :class="{'editable-input-container-editing': editing}">
        <slot v-if="!editing" />
        <form v-else @submit.prevent="submit" style="z-index: 20;position:relative;width:100%;">
            <v-text-field
                    outlined
                    dense
                    :loading="loading"
                    @focusout="wait(submit)"
                    :value="value"
                    @input="val => this.newValue = val"
                    hide-details
                    style="width: 100%;"
                    ref="field"
            />
            <v-btn tile absolute style="right:40px;top:50px;padding:0 5px;min-width: auto;" small><v-icon>mdi-check</v-icon></v-btn>
            <v-btn @click="cancelEditing" tile absolute style="right:0px;top:50px;padding:0 5px;min-width: auto;" small><v-icon>mdi-close</v-icon></v-btn>
        </form>
    </div>
</template>

<script>
    var waitTimeout = null;

    export default {
        name: "EditableInput",
        data: () => ({
            newValue: '',
            editing: false,
            loading: false
        }),
        props: ['value'],
        methods: {
            async startEditing() {
                if(!this.editing) {
                    this.editing = true;
                    await this.$nextTick();
                    this.$refs.field.focus();
                }
            },
            wait(callback) {
                this.loading = true;
                waitTimeout = setTimeout(() => {
                    waitTimeout = null;
                    callback();
                }, 200);
            },
            async cancelEditing() {
                if(this.editing) {
                    this.loading = false;
                    if(waitTimeout) {
                        clearTimeout(waitTimeout);
                        waitTimeout = null;
                    }
                    setTimeout(() => {
                        this.editing = false;
                    }, 100);
                }
            },
            submit() {
                this.editing = false;
                this.loading = false;
            },
        },
        beforeDestroy() {
            if(waitTimeout) {
                clearTimeout(waitTimeout);
                waitTimeout = null;
            }
        }
    }
</script>

<style lang="scss">
    .editable-input-container {
        -webkit-box-align: center;
        align-items: center;
        background-color: transparent;
        box-sizing: border-box;
        color: rgb(9, 30, 66);
        display: flex;
        font-size: 14px;
        -webkit-box-pack: justify;
        justify-content: space-between;
        line-height: 1.42857;
        max-width: 100%;
        overflow-wrap: break-word;
        border-color: transparent;
        border-radius: 3px;
        border-style: solid;
        flex: 1 0 auto;
        transition: background-color 0.2s ease-in-out 0s, border-color 0.2s ease-in-out 0s;
        border-width: 2px;
        padding: 6px;

        &:hover {
            background-color: rgb(235, 236, 240);
        }
    }
    .editable-input-container-editing {
        &:hover {
            background-color: transparent !important;
        }
    }
</style>
