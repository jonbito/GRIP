<template>
    <v-breadcrumbs :items="items" class="pl-0">
        <template v-slot:divider>
            <v-icon>mdi-chevron-right</v-icon>
        </template>
        <template v-slot:item="{ item }">
            <v-breadcrumbs-item
                    :to="item.to"
                    exact
            >
                {{ item.text }}
            </v-breadcrumbs-item>
        </template>
    </v-breadcrumbs>
</template>

<script>
    export default {
        name: "ProjectNavigationDrawer",
        props: ['items']
    }
</script>
