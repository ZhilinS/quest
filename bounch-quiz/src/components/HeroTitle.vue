<template>
  <section class="hero is-primary is-medium is-bold">
    <notify></notify>
    <div class="hero-head">
      <div class="navbar-item">
        <div class="field is-grouped flex-end">
          <div class="control-left">
            <span>
              <img src="../assets/kissing_hearth.png" width="30" height="30" alt="credits" class="kissing">
            </span>
            <span id="score">{{ score }}</span>
          </div>
          <div class="control-left" @click="openModal">
            <a class="button is-primary">
              <span>Ввести код</span>
            </a>
          </div>
        </div>
      </div>
    </div>
    <div class="hero-body">
      <div class="container">
        <h1 class="title">
          Йо! Первый квест в интернете! (нет)
        </h1>
        <h2 class="subtitle">
          С этого момента начинается неожиданное путешествие или туда и обратно
        </h2>
      </div>
    </div>
  </section>
</template>

<script>

  const axios = require('axios');

  import Notify from './Notify.vue';

  export default {

    data() {
      return {
        score: 0
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
      })
    },

    components: { Notify }
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

  .kissing {
    width: 30px;
    height: 30px;
  }

  .field.is-grouped {
    justify-content: flex-end;
  }

</style>
