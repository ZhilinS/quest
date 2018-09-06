import Vue from 'vue';
import Vuex from 'vuex';
import mutations from './mutations'
import getters from './getters'
import actions from './actions'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    step: 0,
    score: 0,
    showModal: false,
    success: false,
    stepToTime: {
      1: 1536240031,
      2: 1532444400,
      3: 1532530800,
      4: 1532617200,
      5: 1532703600,
    },
  },

  mutations,
  getters,
  actions
});