<template>
    <div class="editable-input-container" @click="startEditing" :class="{'editable-input-container-editing': editing}">
        <slot v-if="!editing" />
        <form v-else @submit.prevent="submit" style="z-index: 20;position:relative;width:100%;">
            <WysiwgEditor
                    v-model="newValue"
                    @init="initEditor"
            />

            <div class="mt-5">
                <v-btn v-if="!loading" color="primary" type="submit">Save</v-btn>
                <v-btn v-if="!loading" @click="cancelEditing" class="ml-2">Cancel</v-btn>
            </div>
        </form>
        <v-snackbar
                v-model="showError"
                color="error"
                :timeout="6000"
                right
                top
        >
            {{ errorText }}
            <v-btn
                    dark
                    text
                    @click="showError = false"
            >
                Close
            </v-btn>
        </v-snackbar>
    </div>
</template>

<script>
    import client from "../client";
    import WysiwgEditor from "./WysiwgEditor";

    export default {
        name: "EditableWysiwg",
        components: {WysiwgEditor},
        data: () => ({
            newValue: '',
            editing: false,
            loading: false,
            showError: false,
            errorText: '',
            editor: null
        }),
        props: {
            value: {
                required: true
            },
            url: {
                required: true,
                type: String
            },
            jsonKey: {
                required: true,
                type: String
            }
        },
        methods: {
            async startEditing() {
                if(!this.editing) {
                    this.editing = true;
                    this.newValue = "";
                    await this.$nextTick();
                    this.editor.view.dom.focus();
                    this.newValue = this.value;
                }
            },
            async cancelEditing() {
                if(this.editing) {
                    setTimeout(() => {
                        this.loading = false;
                        this.editing = false;
                    }, 100)
                }
            },
            submit() {
                if(this.newValue !== this.value) {
                    console.log(this.newValue);
                    client.patch(this.url, {
                        [this.jsonKey]: this.newValue
                    }).then((response) => {
                        this.$emit('success', response.data);
                        this.$emit('input', this.newValue);
                    }).catch((err) => {
                        if(err.response.status === 400) {
                            this.errorText = err.response.data.errors[0].errors[0];
                            this.showError = true;
                        }
                    }).then(() => {
                        this.editing = false;
                        this.loading = false;
                    });
                } else {
                    this.cancelEditing();
                }
            },
            initEditor(editor) {
                this.editor = editor.editor;
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
