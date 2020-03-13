import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import Keycloak from 'keycloak-js';

const keycloak = Keycloak({
  url: 'http://localhost:8888/auth',
  realm: 'Grip',
  clientId: 'grip-webui'
});

Vue.config.productionTip = false

keycloak.init({
  onLoad: 'login-required'
}).success((auth) => {
  if(!auth) {
    window.location.reload();
  }

  new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
  }).$mount('#app')

  localStorage.setItem('token', keycloak.token);
  localStorage.setItem('refresh-token', keycloak.refreshToken);

  setTimeout(() => {
    keycloak.updateToken(70);
  }, 60000)
});
