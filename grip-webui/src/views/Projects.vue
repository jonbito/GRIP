<template>
  <div>
    <Title title="Projects">
      <button>Create project</button>
    </Title>
    <div class="recent-text">
      Recents
    </div>
  </div>
</template>

<style scoped lang="scss">
  .recent-text {
    padding-bottom: 12px;
    color: rgb(94, 108, 132);
    font-size: 12px;
    font-weight: 600;
  }
</style>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { namespace } from 'vuex-class';
import Title from '@/components/Title.vue';

const navbarModule = namespace('Navbar');

@Component({
  components: {
    Title,
  },
})
export default class Projects extends Vue {
  @navbarModule.State
  private collapsed!: boolean;
}
</script>
