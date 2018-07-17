<template>
  <div id="app">
    <div id="root" class="container">
      <hero-title></hero-title>
      <text-content v-if="!showQuiz && !showVideo"></text-content>
      <quiz v-if="showQuiz"></quiz>
      <video-step v-if="showVideo"></video-step>
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
  import VideoStep from './components/VideoStep.vue';

  let stepToTime = {
    1:1531850400,
    2:1532015180,
    3:1531843050,
    4:1532015180
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
        return this.step === 2
           &&  this.now - stepToTime[this.step] > 0;
      },

      showVideo() {
        return this.step === 3
          && this.now - stepToTime[this.step] > 0;
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
      });
    },

    mounted() {
      window.setInterval(() => {
        this.now = Math.trunc((new Date()).getTime() / 1000);
      },1000);
    },

    components: { Modal, HeroTitle, TextContent, PrizePreview, FooterContent, Quiz, VideoStep }
  }
</script>

<style>

  #root {
    min-width: 100%;
  }

  body {}

</style>
