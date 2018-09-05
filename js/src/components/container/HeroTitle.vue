<template>
    <section class="hero is-primary is-medium is-bold">
        <notify></notify>
        <div class="hero-head">
            <nav class="level">
                <div class="level-left">
                    <div class="level-item">
                    </div>
                </div>
                <div class="level-right">
                    <div class="level-item">
              <span>
                <img src="../../assets/kissing_hearth.png" width="30" height="30" alt="credits" class="kissing">
              </span>
                        <span id="score">{{ score }}</span>
                    </div>
                    <div class="level-item">
                        <a class="button is-primary" :disabled="disabled" @click="openModal(currentStep)">
                            <span>Ввести код</span>
                        </a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="hero-body">
            <div class="container">
                <h1 class="title">
                    <vdoh></vdoh>
                    <br>
                    Йо! Первый квест в интернете! (нет)
                </h1>
                <h2 class="subtitle">
                    С этого момента начинается неожиданное путешествие или туда и обратно
                </h2>

                <reverse-timer :date="blockedTill" v-if="showTimer"></reverse-timer>
            </div>
        </div>
        <div class="hero-foot"></div>
    </section>
</template>

<script>

  const axios = require('axios');

  let stepToTime = {
    1: 1532358000,
    2: 1532444400,
    3: 1532530800,
    4: 1532617200,
    5: 1532703600,
  };

  import Notify from '../Notify.vue';
  import ReverseTimer from '../ReverseTimer.vue';
  import Vdoh from '../Vdoh.vue';

  import { mapGetters } from 'vuex';
  import { TOGGLE_MODAL } from "../../mutation_types";

  export default {

    data() {
      return {
        score: 0,
        now: Math.trunc((new Date()).getTime() / 1000)
      }
    },

    computed: {
      blockedTill() {
        return stepToTime[this.currentStep];
      },

      showTimer() {
        return this.now - stepToTime[this.currentStep] < 0;
      },

      disabled() {
        return this.now - stepToTime[this.currentStep] < 0 || this.currentStep === 5;
      },

      ...mapGetters([
        'currentStep'
      ])
    },

    methods: {
      openModal(step) {
        if (this.now - stepToTime[step] > 0) {
          console.log("CURRENT STEP: " + step);
          this.$store.commit(TOGGLE_MODAL, true);
          // Event.$emit('open_modal', step);
        }
      }
    },

    mounted() {
      window.setInterval(() => {
        this.now = Math.trunc((new Date()).getTime() / 1000);
      }, 1000);

      // todo: Score into store as well
      axios.get("http://localhost:8501/api/score/current")
        .then((response) => {
          this.score = response.data.score;
        }).catch((error) => {
        console.log(error)
      });

      Event.$on('update_score', () => {
        axios.get("http://localhost:8501/api/score/current")
          .then((response) => {
            this.score = response.data.score;
          }).catch((error) => {
          console.log(error)
        });
      });

      Event.$on('update_timer', () => {
        axios.get("http://localhost:8501/api/modal/current")
          .then((response) => {
            this.$store.commit(UPDATE_STEP, response.data.step)
          }).catch((error) => {
          console.log(error)
        });
      });
    },

    components: {Notify, ReverseTimer, Vdoh}
  }

</script>

<style>

    #score {
        margin: 5px;
        font-weight: bold;
    }

    .level-item {
        margin: 10px 0 20px 20px;
    }

    .kissing {
        width: 30px;
        height: 30px;
    }

    .hero-head {
        padding: 10px 20px 0 20px;
    }

    .hero-body {
        padding-top: 3rem;
        padding-bottom: 3rem;
    }

    @media screen and (min-width: 769px) {
        .hero.is-medium .hero-body {
            padding-top: 6rem;
            padding-bottom: 3rem;
        }
    }

</style>
