<template>
    <v-alert
            dense
            outlined
            type="error"
            v-if="error"
    >
        {{error}}
    </v-alert>
</template>

<script>
    export default {
        name: "FormError",
        props: ['error']
    }
</script>
