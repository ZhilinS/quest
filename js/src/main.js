import Vue from 'vue'
import App from './components/App.vue'
import VueYoutube from 'vue-youtube'
import VueRouter from 'vue-router'
import routes from './routes.js'
import store from './store'


Vue.use(VueYoutube);
Vue.use(VueRouter);

window.Event = new Vue();

Vue.filter('two_digits', function (value) {
  if (value.toString().length <= 1) {
    return "0" + value.toString();
  }
  return value.toString();
});

const router = new VueRouter({
  routes
});

new Vue({
  router,
  el: '#app',
  store,
  render: h => h(App)
});
