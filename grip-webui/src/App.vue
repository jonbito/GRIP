<template>
  <v-app>

    <v-navigation-drawer
      clipped
      app
      v-if="false"
    >
      <v-list dense>
        <v-list-item link>
          <v-list-item-action>
            <v-icon>
              mdi-home
            </v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>
              Home
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-action>
            <v-icon>
              mdi-card-account-mail
            </v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>
              Contact
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      clipped-left
      dense
      flat
      color="primary"
      dark
    >
      <v-btn
              href="/"
              target="_blank"
              text
              class="mr-2"
      >
        <v-icon class="mr-2">mdi-home</v-icon>
        <span>Grip</span>
      </v-btn>

        <ProjectMenu />

      <v-spacer></v-spacer>

      <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">Latest Release</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-content>
      <v-container fluid style="max-width:1280px;margin:0 auto;">
        <router-view />
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
  import ProjectMenu from "./components/ProjectMenu";

  export default {
  name: 'App',

  components: {
    ProjectMenu,
  },

  data: () => ({
  }),
};
</script>
