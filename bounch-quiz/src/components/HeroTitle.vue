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
                <img src="../assets/kissing_hearth.png" width="30" height="30" alt="credits" class="kissing">
              </span>
              <span id="score">{{ score }}</span>
            </div>
            <div class="level-item" >
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
    1:1532368800,
    2:1532444400,
    3:1532530800,
    4:1532617200,
    5:1532703600,
  };

  import Notify from './Notify.vue';
  import ReverseTimer from './ReverseTimer.vue';
  import Vdoh from './Vdoh.vue';

  export default {

    data() {
      return {
        score: 0,
        step: 1,
        now: Math.trunc((new Date()).getTime() / 1000)
      }
    },

    computed: {
      blockedTill() {
        return stepToTime[this.step];
      },

      showTimer() {
        return this.now - stepToTime[this.step] < 0;
      },

      disabled() {
        return this.now - stepToTime[this.step] < 0 || this.step === 5;
      }
    },

    methods: {
      openModal() {
        if (this.now - stepToTime[this.step] > 0) {
          axios.get("http://little-magic.me/api/modal/current")
            .then((response) => {
              Event.$emit('open_modal', response.data.step);
            }).catch((error) => {
            console.log(error);
          })
        }
      }
    },

    mounted() {
      window.setInterval(() => {
        this.now = Math.trunc((new Date()).getTime() / 1000);
      },1000);

      axios.get("http://little-magic.me/api/modal/current")
        .then((response) => {
          this.step = response.data.step
        }).catch((error) => {
          console.log(error);
        });

      axios.get("http://little-magic.me/api/score/current")
        .then((response) => {
          this.score = response.data.score;
        }).catch((error) => {
          console.log(error)
        });

      Event.$on('update_score', () => {
        axios.get("http://little-magic.me/api/score/current")
          .then((response) => {
            this.score = response.data.score;
          }).catch((error) => {
            console.log(error)
          });
      });

      Event.$on('update_timer', () => {
        axios.get("http://little-magic.me/api/modal/current")
          .then((response) => {
            this.step = response.data.step;
          }).catch((error) => {
            console.log(error)
          });
      });
    },

    components: { Notify, ReverseTimer, Vdoh }
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
