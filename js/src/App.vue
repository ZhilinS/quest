<template>
  <div id="app">
    <div id="root" class="container">
      <hero-title></hero-title>
      <step></step>
      <prize-preview></prize-preview>
      <footer-content></footer-content>
      <!--<modal :step="this.step"></modal>-->
    </div>
  </div>
</template>

<script>

  require.context('./assets');

  const axios = require('axios');

  import Modal from './components/Modal.vue';
  import HeroTitle from './components/HeroTitle.vue';
  import PrizePreview from './components/PrizePreview.vue';
  import FooterContent from './components/FooterContent.vue';
  import Step from './components/Step.vue';

  export default {
    name: 'app',

    components: { Modal, HeroTitle, PrizePreview, FooterContent, Step }
  }
</script>

<style>

  #root {
    min-width: 100%;
  }

  body {}

</style>
