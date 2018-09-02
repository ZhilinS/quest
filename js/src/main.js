import Vue from 'vue'
import Vuex from 'vuex'
import App from './components/App.vue'
import VueYoutube from 'vue-youtube'
import VueRouter from 'vue-router'
import routes from './routes.js'


Vue.use(VueYoutube);
Vue.use(VueRouter);
Vue.use(Vuex);

window.Event = new Vue();

Vue.filter('two_digits', function (value) {
  if(value.toString().length <= 1)
  {
    return "0"+value.toString();
  }
  return value.toString();
});

const router = new VueRouter({
  routes
});

const store = new Vuex.Store({
  state: {
    step: 0
  },
  mutations: {
    UPDATE_STEP (state, step) {
      state.step = step
    }
  },

  getters: {
    currentStep: state => {
      return state.step
    }
  }
});

new Vue({
  router,
  el: '#app',
  store,
  render: h => h(App)
});
