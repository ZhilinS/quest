<template>
  <div id="app">
    <div id="root" class="container">
      <hero-title></hero-title>
      <modal :step="1" :number="123123"></modal>
    </div>
  </div>
</template>

<script>

  import Modal from './components/Modal.vue';
  import HeroTitle from './components/HeroTitle.vue';

  export default {
    name: 'app',

    data() {
      return {
      }
    },

    components: { Modal, HeroTitle }
  }
</script>

<style>

  #root {
    min-width: 100%;
  }

</style>
