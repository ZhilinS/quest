<template>
    <div>
        <text-content v-if="!showQuiz && !showVideo && !showResult"></text-content>
        <quiz v-if="showQuiz"></quiz>
        <video-step v-if="showVideo"></video-step>
        <result v-if="showResult"></result>
    </div>
</template>

<script>
  import TextContent from './TextContent.vue';
  import Quiz from './Quiz.vue';
  import VideoStep from './VideoStep.vue';
  import Result from '../../store/Result.vue';

  const axios = require('axios');
  let stepToTime = {
    1: 1532358000,
    2: 1532444400,
    3: 1532530800,
    4: 1532617200,
    5: 1532703600,
  };

  export default {

    data() {
      return {
        step: 1,
        now: Math.trunc((new Date()).getTime() / 1000)
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

    computed: {
      showQuiz() {
        return this.step === 2
          &&  this.now - stepToTime[this.step] > 0;
      },

      showVideo() {
        return this.step === 3
          && this.now - stepToTime[this.step] > 0;
      },

      showResult() {
        return this.step === 5
      }
    },

    mounted() {
      window.setInterval(() => {
        this.now = Math.trunc((new Date()).getTime() / 1000);
      },1000);
    },

    components: { TextContent, Quiz, VideoStep, Result }
  }

</script>

<style>

</style>