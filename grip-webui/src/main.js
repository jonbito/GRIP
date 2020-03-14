import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import Keycloak from 'keycloak-js';
import axios from 'axios';

const keycloak = Keycloak({
  url: 'http://localhost:8888/auth',
  realm: 'Grip',
  clientId: 'grip-webui'
});

Vue.config.productionTip = false;

keycloak.init({
  onLoad: 'login-required'
}).success(() => {
  setAxiosHeader();
  new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
  }).$mount('#app')
});

keycloak.onTokenExpired = () => {
  keycloak.updateToken(30).success(() => {
    setAxiosHeader();
  });
};

const setAxiosHeader = () => {
  axios.defaults.headers.common['Authorization'] = 'Bearer ' + keycloak.token;
};
