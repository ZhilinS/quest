import {UPDATE_STEP, TOGGLE_MODAL, UPDATE_SCORE} from "./types/mutation_types";

const axios = require('axios');

export default {
  MODAL_SUBMIT({commit}, step) {
    commit(UPDATE_STEP, step);
    commit(TOGGLE_MODAL);

    axios.get("http://quiz-backend:8501/api/score/current")
      .then((response) => {
        commit(UPDATE_SCORE, response.data.score);
      }).catch((error) => {
      console.log(error)
    });
  },

  INIT_APP({commit}) {
    axios.get("http://quiz-backend:8501/api/modal/current")
      .then((response) => {
        commit(UPDATE_STEP, response.data.step);
      }).catch((error) => {
      console.log(error);
    });
    axios.get("http://quiz-backend:8501/api/score/current")
      .then((response) => {
        commit(UPDATE_SCORE, response.data.score);
      }).catch((error) => {
      console.log(error)
    });
  }
}