<template>
    <div>
        <text-content v-if="!showQuiz && !showImage && !showVideo && !showResult"></text-content>
        <quiz v-if="showQuiz"></quiz>
        <image-step v-if="showImage"></image-step>
        <video-step v-if="showVideo"></video-step>
        <result v-if="showResult"></result>
    </div>
</template>

<script>
  import TextContent from './TextContent.vue';
  import Quiz from './Quiz.vue';
  import ImageStep from './ImageStep.vue';
  import VideoStep from './VideoStep.vue';
  import Result from '../../cart/Result.vue';

  import { mapGetters } from 'vuex';

  export default {

    data() {
      return {
        step: 1,
        now: Math.trunc((new Date()).getTime() / 1000)
      }
    },

    computed: {
      showQuiz() {
        return this.currentStep === 2
          &&  this.now - this.stepTill > 0;
      },

      showImage() {
        return this.currentStep === 3
          && this.now - this.stepTill > 0;
      },

      showVideo() {
        return this.currentStep === 4
          && this.now - this.stepTill > 0;
      },

      showResult() {
        return this.currentStep === 5
      },

      ...mapGetters([
        'currentStep',
        'stepTill'
      ])
    },

    mounted() {
      window.setInterval(() => {
        this.now = Math.trunc((new Date()).getTime() / 1000);
      },1000);
    },

    components: { TextContent, Quiz, VideoStep, Result, ImageStep }
  }

</script>

<style>

</style>