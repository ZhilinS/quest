<template>
  <div id="app">
    <div id="root" class="container">
      <hero-title></hero-title>
      <text-content v-if="!showQuiz"></text-content>
      <quiz v-if="showQuiz"></quiz>
      <prize-preview></prize-preview>
      <footer-content></footer-content>
      <modal :step="this.step" :number="123123"></modal>
    </div>
  </div>
</template>

<script>

  const axios = require('axios');

  import Modal from './components/Modal.vue';
  import HeroTitle from './components/HeroTitle.vue';
  import TextContent from './components/TextContent.vue';
  import PrizePreview from './components/PrizePreview.vue';
  import FooterContent from './components/FooterContent.vue';
  import Quiz from './components/Quiz.vue';

  let stepToTime = {
    1:1531850400,
    2:1531828800,
    3:1532023200,
    4:1532109600
  };

  export default {
    name: 'app',

    data() {
      return {
        step: 1,
        now: Math.trunc((new Date()).getTime() / 1000)
      }
    },

    computed: {
      showQuiz() {
        console.log(this.step);
        console.log(this.now - stepToTime[this.step] > 0)
        return this.step === 2
           &&  this.now - stepToTime[this.step] > 0;
      }
    },

    created() {
      axios.get("http://localhost:8501/api/modal/current")
        .then((response) => {
          this.step = response.data.step
        }).catch((error) => {
        console.log(error);
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

    components: { Modal, HeroTitle, TextContent, PrizePreview, FooterContent, Quiz }
  }
</script>

<style>

  #root {
    min-width: 100%;
  }

  body {}

</style>
