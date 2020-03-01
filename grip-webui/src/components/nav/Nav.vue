<template>
  <div class="nav-container">
    <div class="nav-inner">
      <div class="global-nav-right-separator"></div>
      <div class="global-nav-bg">
        <div class="global-nav-top">
          <GlobalNavIconLink to="/"
                             :src="require('../../assets/global-nav-favorites-icon.svg')" />
          <GlobalNavIconLink to="/"
                             :src="require('../../assets/global-nav-search-icon.svg')" />
          <GlobalNavIconLink to="/"
                             :src="require('../../assets/global-nav-add-icon.svg')" />
        </div>
        <div class="global-nav-bottom">
          <GlobalNavIconLink to="/"
                             :src="require('../../assets/global-nav-notification-icon.svg')" />
          <GlobalNavIconLink to="/"
                             :src="require('../../assets/global-nav-apps-icon.svg')" />
          <GlobalNavIconLink to="/"
                             :src="require('../../assets/global-nav-help-icon.svg')" />
          <GlobalNavIconLink to="/"
                             :src="require('../../assets/global-nav-settings-icon.svg')" />
          <GlobalNavAvatar to="/" />

        </div>
      </div>

      <div class="contextual-nav-container"
           v-bind:style="{width: this.collapsed && !this.hoverWhenCollapsed ? '20px' : '240px'}"
           @mouseenter="this.hoveredTrue"
           @mouseleave="this.hoveredFalse">
        <div class="contextual-nav">
          <div class="contextual-nav-flex-container">
            <div class="contextual-nav-logo">
              <img src="../../assets/contextual-nav-logo.svg" />
            </div>

            <div class="contextual-nav-items"
                 v-bind:style="{display: this.collapsed && !this.hoverWhenCollapsed ? 'none' : ''}">
              <div class="contextual-nav-items-flex">
                <div class="contextual-nav-item">
                  <a href="#">
                    <div class="contextual-nav-item-icon">
                      <img src="../../assets/contextual-nav-your-work-icon.svg" />
                    </div>
                    <div class="contextual-nav-item-text-container">
                      <div class="contextual-nav-item-text">
                        Your work
                      </div>
                    </div>
                  </a>
                </div>


                <div class="contextual-nav-item">
                  <router-link to="/">
                    <div class="contextual-nav-item-icon">
                      <img src="../../assets/contextual-nav-projects-icon.svg" />
                    </div>
                    <div class="contextual-nav-item-text-container">
                      <div class="contextual-nav-item-text">
                        Projects
                      </div>
                    </div>
                  </router-link>
                </div>

                <div class="contextual-nav-item">
                  <a href="#">
                    <div class="contextual-nav-item-icon">
                      <img src="../../assets/contextual-nav-people-icon.svg" />
                    </div>
                    <div class="contextual-nav-item-text-container">
                      <div class="contextual-nav-item-text">
                        People
                      </div>
                    </div>
                  </a>
                </div>

                <div class="contextual-nav-item">
                  <a href="#">
                    <div class="contextual-nav-item-icon">
                      <img src="../../assets/global-nav-settings-icon.svg" />
                    </div>
                    <div class="contextual-nav-item-text-container">
                      <div class="contextual-nav-item-text">
                        Settings
                      </div>
                    </div>
                    <div class="contextual-nav-item-right-icon">
                      <img src="../../assets/right-arrow-icon.svg" />
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="nav-right-bar-container">
      <button class="collapse-button"
              v-bind:style="{
                transform: this.collapsed ? 'rotate(180deg) translate(50%)': '',
                opacity: this.collapsed ? '1' : ''
              }"
              type="button" aria-expanded="true" aria-label="Toggle navigation"
              v-on:click="this.collapse">
        <svg width="24" height="24" viewBox="0 0 24 24" focusable="false" role="presentation">
          <path d="M13.706 9.698a.988.988 0 0 0 0-1.407 1.01 1.01 0 0 0-1.419 0l-2.965
          2.94a1.09 1.09 0 0 0 0 1.548l2.955 2.93a1.01 1.01 0 0 0 1.42 0 .988.988 0 0 0
          0-1.407l-2.318-2.297 2.327-2.307z" fill="currentColor"
                fill-rule="evenodd"></path></svg>
      </button>
    </div>
  </div>
</template>

<style scoped lang="scss">
  .nav-container {
    bottom: 0;
    left: 0;
    position: fixed;
    top: 0;
    z-index: 200;

    &:hover {
      .collapse-button {
        opacity: 1;
      }
    }
  }

  .nav-inner {
    display: flex;
    flex-direction: row;
    height: 100%;
    overflow: hidden;
  }

  #global-nav {

  }

  .global-nav-right-separator {
    bottom: 0px;
    left: -6px;
    opacity: 0.5;
    pointer-events: none;
    position: absolute;
    top: 0px;
    transition-duration: 0.22s;
    transition-property: left, opacity, width;
    transition-timing-function: cubic-bezier(0.2, 0, 0, 1);
    width: 6px;
    background: linear-gradient(to left, rgba(0, 0, 0, 0.2) 0px,
      rgba(0, 0, 0, 0.2) 1px, rgba(0, 0, 0, 0.1) 1px, rgba(0, 0, 0, 0) 100%);
    margin-left: 64px;
  }

  .global-nav-bg {
    -webkit-align-items: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    box-sizing: border-box;
    display: flex;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    -webkit-flex-shrink: 0;
    -ms-flex-negative: 0;
    flex-shrink: 0;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    -ms-flex-pack: justify;
    justify-content: space-between;
    padding-bottom: calc(20px);
    padding-top: 24px;
    -webkit-transition: background-color 0.3s cubic-bezier(0.2,0,0,1),
      color 0.3s cubic-bezier(0.2,0,0,1);
    transition: background-color 0.3s cubic-bezier(0.2,0,0,1),
      color 0.3s cubic-bezier(0.2,0,0,1);
    width: 64px;
    height: calc(100vh - 0px);
    background-color: #0747A6;
    color: #DEEBFF;
    fill: #0747A6;
  }

  .global-nav-top {
    -webkit-box-align: center;
    align-items: center;
    display: flex;
    flex-direction: column;
    -webkit-box-pack: justify;
    justify-content: space-between;
    width: 100%;
    padding-bottom: 16px;
  }

  .global-nav-bottom {
    -webkit-box-align: center;
    align-items: center;
    display: flex;
    flex-direction: column;
    -webkit-box-pack: justify;
    justify-content: space-between;
    width: 100%;
    padding-top: 8px;
  }

  .contextual-nav-container {
    will-change: width;
    width: 240px;
    transition: width 300ms cubic-bezier(0.2, 0, 0, 1) 0s;
    height: 100%;
    position: relative;
  }

  .contextual-nav {
    box-sizing: border-box;
    height: 100%;
    left: 0px;
    min-width: 240px;
    overflow-x: hidden;
    position: absolute;
    top: 0px;
    width: 100%;
    background-color: rgb(7, 71, 166);
    color: rgb(222, 235, 255);
  }

  .contextual-nav-flex-container {
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    height: 100%;
    overflow-x: hidden;
    overflow-y: auto;
    width: 100%;
  }

  .contextual-nav-logo {
    box-sizing: border-box;
    line-height: 0;
    padding: 28px 16px 26px 32px;

    img {
      display: inline-block;
      height: 32px;
      position: relative;
      user-select: none;
    }
  }

  .contextual-nav-items {
    position: relative;
    -webkit-flex: 1 1 100%;
    -ms-flex: 1 1 100%;
    flex: 1 1 100%;
    height: 100%;
    width: 100%;
    overflow: hidden;
  }

  .contextual-nav-items-flex {
    display: flex;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    -webkit-flex-basis: 100%;
    -ms-flex-preferred-size: 100%;
    flex-basis: 100%;
    height: 100%;
    -webkit-box-pack: start;
    -webkit-justify-content: flex-start;
    -ms-flex-pack: start;
    justify-content: flex-start;
    overflow-y: auto;
    position: relative;
    box-sizing: border-box;
    padding-left: 16px;
    padding-right: 16px;
    padding-bottom: 12px;
  }

  .contextual-nav-item {
    width: 100%;
    -webkit-align-items: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    display: flex;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    -ms-flex-pack: justify;
    justify-content: space-between;

    a {
      -webkit-box-align: center;
      align-items: center;
      box-sizing: border-box;
      color: inherit;
      cursor: pointer;
      display: flex;
      flex-shrink: 0;
      font-size: inherit;
      height: 40px;
      text-align: left;
      width: 100%;
      padding-left: 12px;
      padding-right: 12px;
      background-color: rgb(7, 71, 166);
      fill: rgb(7, 71, 166);
      border-width: initial;
      border-style: none;
      border-color: initial;
      border-image: initial;
      border-radius: 3px;
      outline: none;
      text-decoration: none;

      &.router-link-active {
        background-color: rgb(5, 54, 127) !important;
        fill: rgb(5, 54, 127) !important;
      }

      &:hover {
        background-color: rgb(28, 99, 206);
        fill: rgb(28, 99, 206);

        .contextual-nav-item-right-icon {
          display: flex;
        }
      }

      .contextual-nav-item-icon {
        -webkit-align-items: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        display: flex;
        -webkit-flex-shrink: 0;
        -ms-flex-negative: 0;
        flex-shrink: 0;
        margin-right: 16px;
        color: #DEEBFF;

        img {
          display: inline-block;
          flex-shrink: 0;
          line-height: 1;
          height: 24px;
        }
      }

      .contextual-nav-item-text-container {
        display: flex;
        -webkit-flex-direction: column;
        -ms-flex-direction: column;
        flex-direction: column;
        -webkit-box-flex: 1;
        -webkit-flex-grow: 1;
        -ms-flex-positive: 1;
        flex-grow: 1;
        overflow-x: hidden;

        .contextual-nav-item-text {
          -webkit-flex: 1 1 auto;
          -ms-flex: 1 1 auto;
          flex: 1 1 auto;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
          line-height: 1.1428571428571428;
          color: #DEEBFF;
        }
      }

      .contextual-nav-item-right-icon {
        display:none;
        webkit-align-items: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        -webkit-flex-shrink: 0;
        -ms-flex-negative: 0;
        flex-shrink: 0;
        margin-left: 16px;
        color: #DEEBFF;

        img {
          height: 24px;
        }
      }
    }
  }

  .nav-right-bar-container {
    bottom: 0px;
    left: 100%;
    position: absolute;
    top: 0px;
    transform: translateZ(0px);
    width: 24px;
    z-index: 3;

    .collapse-button {
      box-shadow: rgba(9, 30, 66, 0.08) 0px 0px 0px 1px, rgba(9, 30, 66, 0.08) 0px 2px 4px 1px;
      color: rgb(107, 119, 140);
      cursor: pointer;
      height: 24px;
      opacity: 0;
      position: absolute;
      top: 32px;
      transform: translate(-50%);
      width: 24px;
      background: 0px center white;
      border-width: 0px;
      border-style: initial;
      border-color: initial;
      border-image: initial;
      border-radius: 50%;
      outline: 0px;
      padding: 0px;
      transition: background-color 100ms linear 0s, color 100ms linear 0s,
      opacity 300ms cubic-bezier(0.2, 0, 0, 1) 0s,
      transform 300ms cubic-bezier(0.2, 0, 0, 1) 0s;

      &:hover {
        background-color: rgb(76, 154, 255);
        color: white;
      }
    }
  }

</style>

<script lang="ts">
import GlobalNavIconLink from '@/components/nav/GlobalNavIconLink.vue';
import GlobalNavAvatar from '@/components/nav/GlobalNavAvatar.vue';
import { Component, Vue } from 'vue-property-decorator';
import { namespace } from 'vuex-class';

const navbarModule = namespace('Navbar');

@Component({
  components: { GlobalNavIconLink, GlobalNavAvatar },
})
export default class Nav extends Vue {
  @navbarModule.State
  private collapsed!: boolean;

  @navbarModule.State
  private hoverWhenCollapsed!: boolean;

  @navbarModule.Action
  private toggleCollapsed!: () => void;

  @navbarModule.Action
  private loadCollapsedFromLocalStorage!: () => void;

  @navbarModule.Mutation
  private setHoverWhenCollapsed!: (hovered: boolean) => void;

  collapse() {
    this.toggleCollapsed();
  }

  hoveredTrue() {
    this.setHoverWhenCollapsed(true);
  }

  hoveredFalse() {
    this.setHoverWhenCollapsed(false);
  }

  mounted() {
    this.loadCollapsedFromLocalStorage();
  }
}
</script>
