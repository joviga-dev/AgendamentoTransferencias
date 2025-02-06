import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import PrimeVue from 'primevue/config';
import Aura from '@primevue/themes/aura';
import App from './App.vue'
import router from './router'
import 'primeflex/primeflex.css';
import '/node_modules/primeflex/primeflex.css';
import axios from "axios";
import VueAxios from 'vue-axios'
import ToastService from 'primevue/toastservice';

const app = createApp(App);

app.use(createPinia())
app.use(router)
app.use(VueAxios,axios);
app.use(PrimeVue, {
  theme: {
    preset: Aura,
    options: {
      prefix: 'p',
      darkModeSelector: 'false',
    }
  }
});
app.use(ToastService);
app.mount('#app')


