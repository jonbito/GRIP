<template>
  <component :is="this.$route.meta.layout || 'MainLayout'">
    <router-view />
  </component>
</template>

<script>
import MainLayout from "@/layout/MainLayout";

export default {
  name: 'App',
  components: { MainLayout }
};
</script>

<style lang="scss">
a {
  text-decoration: none;
}

a.link {
  text-decoration: none;
}

a.link:hover {
  text-decoration: underline;
}

.v-btn-plain:before {
  display: none !important;
}

.v-btn {
  text-transform: none;
}

.v-tab {
  letter-spacing: .05em;
  text-transform: none;
  justify-content: flex-start;
}

.theme--light.v-data-table {
  th.sortable:hover {
    background: #f5f5f5;
  }

  tbody tr:hover:not(.v-data-table__expanded__content):not(.v-data-table__empty-wrapper) {
    background: #fcfcfc !important;
  }
}

.no-active::before {
  opacity: 0 !important;
}

.v-toolbar--flat {
  .v-toolbar__content {
    padding-left: 0;
  }
}
</style>
