import {
  Action, Module, Mutation, VuexModule,
} from 'vuex-module-decorators';

@Module({
  namespaced: true,
})
export default class Navbar extends VuexModule {
  public collapsed = false;

  public hoverWhenCollapsed = false;

  private hoverTimeout = -1;

  @Mutation
  setCollapsed(collapse: boolean) {
    this.collapsed = collapse;
    this.hoverWhenCollapsed = false;
    clearTimeout(this.hoverTimeout);
    localStorage.setItem('navbarCollapsed', collapse.toString());
  }

  @Mutation
  setHoverWhenCollapsed(hovered: boolean) {
    if (this.collapsed) {
      // delay the close if they are leaving the hover state
      clearTimeout(this.hoverTimeout);
      if (!hovered) {
        this.hoverTimeout = setTimeout(() => {
          this.hoverWhenCollapsed = hovered;
        }, 800);
      } else {
        this.hoverTimeout = setTimeout(() => {
          this.hoverWhenCollapsed = hovered;
        }, 400);
      }
    }
  }

  @Action({ commit: 'setCollapsed' })
  loadCollapsedFromLocalStorage() {
    return localStorage.getItem('navbarCollapsed') === 'true';
  }

  @Action({ commit: 'setCollapsed' })
  toggleCollapsed() {
    return !this.collapsed;
  }
}
