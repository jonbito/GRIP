import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import {TiptapVuetifyPlugin} from 'tiptap-vuetify';
import 'tiptap-vuetify/dist/main.css'

const vuetify = new Vuetify();

Vue.use(Vuetify);
Vue.use(TiptapVuetifyPlugin, {
    vuetify,
    iconsGroup: 'mdi'
});

export default vuetify;
