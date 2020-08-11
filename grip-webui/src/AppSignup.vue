<template>
  <v-app>
    <v-app-bar
      app
      dense
      flat
      color="#001628"
      dark
    >
      <div class="d-flex justify-center align-center" style="width:100%;">
        <img style="width:30px; height: 30px;" src="./assets/logo.svg" />
      </div>
    </v-app-bar>

    <v-main>
      <v-container fluid style="max-width:580px;margin:0 auto;">
        <router-view />
      </v-container>
    </v-main>
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
