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
    step: 0,
    showModal: false,
    stepToTime: {
      1: 1532358000,
      2: 1532444400,
      3: 1532530800,
      4: 1532617200,
      5: 1532703600,
    }
  },
  mutations: {
    UPDATE_STEP (state, step) {
      state.step = step
    },
    TOGGLE_MODAL (state) {
      state.showModal = !state.showModal
    }
  },

  getters: {
    currentStep: state => {
      return state.step
    },
    showModal: state => {
      return state.showModal
    }
  }
});

new Vue({
  router,
  el: '#app',
  store,
  render: h => h(App)
});
