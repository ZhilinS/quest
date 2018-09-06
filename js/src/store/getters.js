export default {
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
}