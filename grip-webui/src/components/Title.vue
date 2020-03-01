<template>
  <div class="title-container">
    <h1>{{title}}</h1>
    <div class="buttons-wrapper">
      <slot></slot>
    </div>
  </div>
</template>

<style lang="scss">
  .title-container {
    align-items: flex-start;
    display: flex;
    margin-bottom: 15px;

    h1 {
      margin-bottom: 8px;
      max-width: 100%;
      min-width: 0px;
      flex: 1 1 auto;
      display: block;
    }

    .buttons-wrapper {
      margin-bottom: 8px;
      margin-left: auto;
      max-width: 100%;
      padding-left: 32px;
      text-align: right;
      white-space: nowrap;
      flex: 0 0 auto;

      button {
        -webkit-align-items: baseline;
        -webkit-box-align: baseline;
        -ms-flex-align: baseline;
        align-items: baseline;
        border-width: 0;
        box-sizing: border-box;
        display: inline-flex;
        font-size: inherit;
        font-style: normal;
        font-weight: normal;
        max-width: 100%;
        outline: none !important;
        text-align: center;
        -webkit-text-decoration: none;
        text-decoration: none;
        white-space: nowrap;
        background: #0052CC;
        border-radius: 3px;
        box-shadow: 0 0 0 2px inherit;
        cursor: pointer;
        color: #FFFFFF !important;
        height: 2.2857142857142856em;
        line-height: 2.2857142857142856em;
        padding: 0 10px;
        -webkit-transition: background 0.1s ease-out,
          box-shadow 0.15s cubic-bezier(0.47,0.03,0.49,1.38);
        transition: background 0.1s ease-out,box-shadow 0.15s cubic-bezier(0.47,0.03,0.49,1.38);
        -webkit-transition-duration: 0.1s,0.15s;
        transition-duration: 0.1s,0.15s;
        vertical-align: middle;
        width: auto;

        &:hover {
          background: lighten(#0052CC, 10%);
        }
      }
    }
  }
</style>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class Title extends Vue {
  @Prop(String)
  readonly title!: string;
}
</script>
