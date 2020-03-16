<template>
  <v-app>
    <v-app-bar
      app
      dense
      flat
      color="primary"
      dark
    >
      <div class="text-center" style="width:100%">Grip</div>
    </v-app-bar>

    <v-content>
      <v-container fluid style="max-width:580px;margin:0 auto;">
        <router-view />
      </v-container>
    </v-content>
  </v-app>
</template>

<script>

  export default {
  name: 'App',

  components: {
  },

  data: () => ({
  }),
};
</script>
