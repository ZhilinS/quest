<template>
  <div id="app">
    <div id="root" class="container">
      <hero-title></hero-title>
      <text-content></text-content>
      <prize-preview></prize-preview>
      <modal :step="1" :number="123123"></modal>
    </div>
  </div>
</template>

<script>

  import Modal from './components/Modal.vue';
  import HeroTitle from './components/HeroTitle.vue';
  import TextContent from './components/TextContent.vue';
  import PrizePreview from './components/PrizePreview.vue';

  export default {
    name: 'app',

    data() {
      return {
      }
    },

    components: { Modal, HeroTitle, TextContent, PrizePreview }
  }
</script>

<style>

  #root {
    min-width: 100%;
  }

  body {}

</style>
