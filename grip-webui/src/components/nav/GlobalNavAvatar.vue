<template>
  <div class="global-nav-icon-container">
    <router-link :to="to" class="global-nav-icon-button">
      <div class="avatar-circle">
            <span class="initials">
              JB
            </span>
      </div>
    </router-link>
  </div>
</template>

<style scoped lang="scss">
  .global-nav-icon-container {
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
    padding: 4px;
  }

  .global-nav-icon-button {
    -webkit-box-align: center;
    align-items: center;
    color: rgb(222, 235, 255);
    cursor: pointer;
    display: flex;
    font-size: inherit;
    -webkit-box-pack: center;
    justify-content: center;
    line-height: 1;
    position: relative;
    height: 32px;
    width: 32px;
    background-color: rgb(7, 71, 166);
    border-width: 0px;
    border-style: initial;
    border-color: initial;
    border-image: initial;
    border-radius: 50%;
    outline: none;
    padding: 0px;
    text-decoration: none;

    .avatar-circle {
      width: 24px;
      height: 24px;
      background-color: #00865A;
      text-align: center;
      border-radius: 50%;
      -webkit-border-radius: 50%;
      -moz-border-radius: 50%;

      .initials {
        position: relative;
        top: 3px; /* 25% of parent */
        font-size: 12px; /* 50% of parent */
        line-height: 12px; /* 50% of parent */
        color: #fff;
        font-weight: bold;
      }
    }
  }

  .global-nav-icon-button:hover {
    background-color: rgb(28, 99, 206);
  }
</style>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class GlobalNavAvatar extends Vue {
  @Prop(String)
  readonly to!: string;
}
</script>
