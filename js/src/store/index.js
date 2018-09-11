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
    quest_finished: false,
    stepToTime: {
      1: 1536516000,
      2: 1536555600,
      3: 1536591600,
      4: 1536661800,
      5: 1536602400,
    },
  },

  mutations,
  getters,
  actions
});