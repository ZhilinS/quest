export default {
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
  },
  FINISH_QUEST(state) {
    state.quest_finished = true
  }
}