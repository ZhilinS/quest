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
            <div class="level-item" @click="openModal">
              <a class="button is-primary">
                <span>Ввести код</span>
              </a>
            </div>
          </div>
        </nav>
    </div>
    <div class="hero-body">
      <div class="container">
        <h1 class="title">
          Йо! Первый квест в интернете! (нет)
        </h1>
        <h2 class="subtitle">
          С этого момента начинается неожиданное путешествие или туда и обратно
        </h2>

        <reverse-timer :date="openDate"></reverse-timer>
      </div>
    </div>
    <div class="hero-foot"></div>
  </section>
</template>

<script>

  const axios = require('axios');

  let stepToTime = {
    1:1531850400,
    2:1531936800,
    3:1532023200,
    4:1532109600
  };

  import Notify from './Notify.vue';
  import ReverseTimer from './ReverseTimer.vue';

  export default {

    data() {
      return {
        score: 0,
        step: 0
      }
    },

    computed: {
      openDate() {
        return stepToTime[this.step]
      }
    },

    methods: {
      openModal() {
        axios.get("http://localhost:8501/api/modal/current")
          .then((response) => {
            Event.$emit('open_modal', response.data.step);
          }).catch((error) => {
            console.log(error);
          })
      }
    },

    mounted() {
      axios.get("http://localhost:8501/api/modal/current")
        .then((response) => {
          this.step = response.data.step
        }).catch((error) => {
          console.log(error);
        });

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
            this.step = response.data.step;
          }).catch((error) => {
            console.log(error)
          });
      })
    },

    components: { Notify, ReverseTimer }
  }

</script>

<style>

  #score {
    margin: 5px;
    font-weight: bold;
  }

  .control-left {
    display: flex;
    align-items: center;
    margin: 10px;
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

  .field.is-grouped {
    justify-content: flex-end;
  }

  .navbar-item {
    justify-content: flex-end;
  }

</style>
