import Vue from 'vue'
import Main from '../src/sections/Main.vue'
import vuetify from './plugins/vuetify'
import router from './index.js'
import store from './store'

Vue.config.productionTip = false


new Vue({
  vuetify,
  router,
  store,
  render: h => h(Main)
}).$mount('#app')