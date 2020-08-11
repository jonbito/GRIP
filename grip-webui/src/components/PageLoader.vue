<template>
  <div class="loader-container">
    <transition name="fade">
      <v-progress-circular
          indeterminate
          color="primary"
          :size="50"
          v-if="show"
      ></v-progress-circular>
    </transition>
  </div>
</template>

<script>
export default {
  name: "PageLoader",
  data: () => ({
    show: false
  }),
  created() {
    setTimeout(() => {
      this.show = true;
    }, 1000);
  },
}
</script>

<style scoped>
  .loader-container {
    margin-top: 150px;
    text-align: center;
  }

  .fade-enter-active {
    transition: opacity 3s;
  }
  .fade-enter {
    opacity: 0;
  }
  .fade-enter-to {
    opacity: 1;
  }
</style>
