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
                <img src="../../assets/trubi.png" width="30" height="30" alt="credits" class="kissing">
              </span>
                        <span id="score">{{ currentScore }}</span>
                    </div>
                    <div class="level-item">
                        <a class="button is-primary" :disabled="disabled" @click="openModal">
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
                    Первый квест в интернете! (нет)
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

  import Notify from '../Notify.vue';
  import ReverseTimer from '../ReverseTimer.vue';
  import Vdoh from '../Vdoh.vue';

  import { mapGetters } from 'vuex';
  import { TOGGLE_MODAL } from "../../store/types/mutation_types";

  export default {

    data() {
      return {
        score: 0,
        now: Math.trunc((new Date()).getTime() / 1000)
      }
    },

    computed: {
      blockedTill() {
        return this.stepTill;
      },

      showTimer() {
        return this.now - this.stepTill < 0;
      },

      disabled() {
        return this.now - this.stepTill < 0 || this.currentStep === 5;
      },

      ...mapGetters([
        'currentStep',
        'stepTill',
        'currentScore'
      ])
    },

    methods: {
      openModal() {
        if (this.now - this.stepTill > 0) {
          this.$store.commit(TOGGLE_MODAL, true);
        }
      }
    },

    mounted() {
      window.setInterval(() => {
        this.now = Math.trunc((new Date()).getTime() / 1000);
      }, 1000);
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

    .hero.is-primary.is-medium.is-bold {
        background-image: url("../../assets/cats.png"), linear-gradient(141deg, #009e6c 0%, #00d1b2 71%, #00e7eb 100%);
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
