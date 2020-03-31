<template>
    <v-tooltip bottom>
        <template v-slot:activator="{ on }">
            <v-btn
                    icon
                    v-on="on"
                    @click.stop="$emit('click')"
            >
                <v-icon>mdi-magnify</v-icon>
            </v-btn>
        </template>
        <span>Search "/"</span>
    </v-tooltip>
</template>

<script>
    export default {
        name: "HeaderSearchButton",
    }
</script>
