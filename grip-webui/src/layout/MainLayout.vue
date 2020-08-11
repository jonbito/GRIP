<template>
  <v-app>

    <SearchDrawer v-model="showSearchDrawer"/>

    <v-app-bar
        app
        clipped-left
        dense
        color="#001628"
        dark
    >
      <div>
        <v-btn
            to="/"
            text
            class="mr-12 ml-4 no-active"
            x-large
            :ripple="false"
            icon
        >
          <img src="../assets/logo.svg" style="height: 24px; width: 24px;margin-right: 5px;"/>
          Grip
        </v-btn>
      </div>

      <ProjectMenu/>

      <v-spacer></v-spacer>

      <HeaderSearchButton
          @click="showSearchDrawer = !showSearchDrawer"
          v-shortkey="['/']"
          @shortkey.native="showSearchDrawer = !showSearchDrawer"
      />
      <HeaderUserMenu/>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
import ProjectMenu from "../components/ProjectMenu";
import HeaderUserMenu from "../components/HeaderUserMenu";
import HeaderSearchButton from "../components/HeaderSearchButton";
import SearchDrawer from "../components/SearchDrawer";

export default {
  name: 'App',
  components: {
    ProjectMenu,
    HeaderUserMenu,
    HeaderSearchButton,
    SearchDrawer
  },
  data: () => ({
    showSearchDrawer: null
  }),
};
</script>
