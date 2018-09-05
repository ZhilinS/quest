import Vue from 'vue'
import Vuex from 'vuex'
import App from './components/App.vue'
import VueYoutube from 'vue-youtube'
import VueRouter from 'vue-router'
import routes from './routes.js'
import {TOGGLE_MODAL, UPDATE_SCORE, UPDATE_STEP} from "./mutation_types";

const axios = require('axios');


Vue.use(VueYoutube);
Vue.use(VueRouter);
Vue.use(Vuex);

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

const store = new Vuex.Store({
  state: {
    step: 0,
    score: 0,
    showModal: false,
    success: false,
    stepToTime: {
      1: 1532358000,
      2: 1532444400,
      3: 1532530800,
      4: 1532617200,
      5: 1532703600,
    },
  },
  mutations: {
    UPDATE_STEP(state, step) {
      state.step = step
    },
    TOGGLE_MODAL(state) {
      state.showModal = !state.showModal
    },
    UPDATE_SCORE(state, score) {
      state.score = score
    },
    TOGGLE_SUCCESS(state) {
      state.success = !state.success
    }
  },

  getters: {
    currentStep: state => {
      return state.step
    },
    currentScore: state => {
      return state.score
    },
    showModal: state => {
      return state.showModal
    },
    stepTill: state => {
      return state.stepToTime[state.step]
    },
    successSubmit: state => {
      return state.success
    }
  },

  actions: {
    MODAL_SUBMIT({commit}, step) {
      commit(UPDATE_STEP, step);
      commit(TOGGLE_MODAL);

      axios.get("http://localhost:8501/api/score/current")
        .then((response) => {
          commit(UPDATE_SCORE, response.data.score);
        }).catch((error) => {
        console.log(error)
      });
    },
    INIT_APP({commit}) {
      axios.get("http://localhost:8501/api/modal/current")
        .then((response) => {
          commit(UPDATE_STEP, response.data.step);
        }).catch((error) => {
        console.log(error);
      });
      axios.get("http://localhost:8501/api/score/current")
        .then((response) => {
          commit(UPDATE_SCORE, response.data.score);
        }).catch((error) => {
        console.log(error)
      });
    }
  }
});

new Vue({
  router,
  el: '#app',
  store,
  render: h => h(App)
});
