import Vue from 'vue';
import Vuex from 'vuex';
import mutations from './mutations'
import getters from './getters'
import actions from './actions'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    step: 1,
    score: 0,
    showModal: false,
    success: false,
    stepToTime: {
      1: 1536505200,
      2: 1536516000,
      3: 1536570000,
      4: 1536591600,
      5: 1536602400,
    },
  },

  mutations,
  getters,
  actions
});