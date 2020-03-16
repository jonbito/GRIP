import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import client from './client';
import AppSignup from "./AppSignup";
import keycloak from "./keycloak";

Vue.config.productionTip = false;

keycloak.init({
  onLoad: 'login-required'
}).success(() => {
  setAxiosHeader();
  if(keycloak.tokenParsed.accountCreated) {
    new Vue({
      router,
      store,
      vuetify,
      render: h => h(App)
    }).$mount('#app')
  } else {
    new Vue({
      router,
      store,
      vuetify,
      render: h => h(AppSignup)
    }).$mount('#app')

    if(router.currentRoute.path !== '/create-account') {
      router.replace('/create-account');
    }
  }
});

keycloak.onTokenExpired = () => {
  keycloak.updateToken(30).success(() => {
    setAxiosHeader();
  });
};

const setAxiosHeader = () => {
  client.defaults.headers.common['Authorization'] = 'Bearer ' + keycloak.token;
};
