import Vue from 'vue'
import App from './App.vue'
import router from './router'
import appSignupRouter from './router/appSignupRoutes'
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
  const accountCreated = keycloak.tokenParsed.accountCreated;
  new Vue({
    router: accountCreated ? router : appSignupRouter,
    store,
    vuetify,
    render: h => h(accountCreated ? App : AppSignup)
  }).$mount('#app')
});

keycloak.onTokenExpired = () => {
  keycloak.updateToken(30).success(() => {
    setAxiosHeader();
  });
};

const setAxiosHeader = () => {
  client.defaults.headers.common['Authorization'] = 'Bearer ' + keycloak.token;
};
