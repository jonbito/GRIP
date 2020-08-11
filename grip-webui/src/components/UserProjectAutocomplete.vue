<template>
    <v-autocomplete
            :value="value"
            @input="(val) => $emit('input', val)"
            :items="items"
            :loading="loading"
            :search-input.sync="search"
            hide-details
            hide-no-data
            outlined
            dense
            item-text="name"
            item-value="id"
    />
</template>

<script>
    import {debounce} from "lodash"

    export default {
        name: "UserProjectAutocomplete",
        props: ['value', 'url', 'jsonKey'],
        data: () => ({
            loading: false,
            items: [
                {
                    id: null,
                    name: "Unassigned"
                }
            ],
            search: ""
        }),
        methods: {
            searchDebounce: debounce(function () {

                this.loading = false;
            }, 500)
        },
        watch: {
            search() {
                this.loading = true;
                this.searchDebounce();
            }
        }
    }
</script>

<style scoped>

</style>
