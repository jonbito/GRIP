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
      <PageLoader v-if="initialLoading" />
      <router-view v-if="!initialLoading"/>
    </v-main>
  </v-app>
</template>

<script>
import ProjectMenu from "../components/ProjectMenu";
import HeaderUserMenu from "../components/HeaderUserMenu";
import HeaderSearchButton from "../components/HeaderSearchButton";
import SearchDrawer from "../components/SearchDrawer";
import PageLoader from "@/components/PageLoader";
import client from "@/client";

export default {
  name: 'App',
  components: {
    PageLoader,
    ProjectMenu,
    HeaderUserMenu,
    HeaderSearchButton,
    SearchDrawer
  },
  data: () => ({
    initialLoading: true,
    showSearchDrawer: null
  }),
  created() {
    client.get('/org/' + this.$route.params.orgUrl).then(response => {
      this.initialLoading = false;
      console.log(response.data);
    }).catch(err => {
      console.log(err.response);
    })
  }
};
</script>
