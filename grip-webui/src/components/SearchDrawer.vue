<template>
    <v-navigation-drawer
            absolute
            temporary
            :width="450"
            :value="value"
            @input="(newVal) => $emit('input', newVal)"
            @transitionend="$refs.search.focus()"
    >
        <v-container>
            <v-row>
                <v-col cols="auto">
                    <v-btn icon large @click="$emit('input', false)"><v-icon>mdi-close</v-icon></v-btn>
                </v-col>
                <v-col>
                    <v-text-field
                            ref="search"
                            autofocus
                            placeholder="Search Grip"
                            height="32"
                            style="font-size:18px;"
                            hide-details
                            class="pt-0 mt-2"
                    />
                </v-col>
            </v-row>
        </v-container>
    </v-navigation-drawer>
</template>

<script>
    export default {
        name: "SearchDrawer",
        props: ['value']
    }
</script>
