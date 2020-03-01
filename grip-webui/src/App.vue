<template>
  <div id="app">
    <Nav></Nav>
    <div class="content"
         :style="{paddingLeft: this.collapsed && !this.hoverWhenCollapsed ? '60px' : '304px'}">
      <div class="content-inner">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
  body {
    background: white;
    font-family: -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,
      Oxygen,Ubuntu, 'Fira Sans','Droid Sans','Helvetica Neue',sans-serif;
    color: #172b4d;
    font-size: 14px;
    font-weight: 400;
    font-style: normal;
    line-height: 20px;
  }

  html, body, h1, h2, h3, h4, h5, h6, ul, ol, dl, img, pre, form, fieldset {
    margin: 0;
    padding: 0;
  }

  h1, h2, h3, h4, h5, h6 {
    color: #172b4d;
  }
  h1 {
    font-size: 24px;
    font-weight: 500;
    line-height: 32px;
    letter-spacing: -0.01em;
    text-overflow: ellipsis;
  }
</style>

<style scoped lang="scss">
  .content {
    box-sizing: border-box;
    will-change: padding-left;
    padding-left: 304px;
    transition: padding-left 300ms cubic-bezier(0.2, 0, 0, 1) 0s;
    -webkit-flex: 1 1 auto;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;

    .content-inner {
      height: 100vh;
      overflow: auto;
      min-width: 800px;
      padding: 24px 40px;
    }
  }
</style>

<script lang="ts">
import Nav from '@/components/nav/Nav.vue';
import { Component, Vue } from 'vue-property-decorator';
import { namespace } from 'vuex-class';

const navbarModule = namespace('Navbar');

@Component({
  components: { Nav },
})
export default class App extends Vue {
  @navbarModule.State
  private collapsed!: boolean;

  @navbarModule.State
  private hoverWhenCollapsed!: boolean;
}
</script>
