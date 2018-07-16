<template>
  <div id="app">
    <div id="root" class="container">
      <modal :step="1" :number="123123"></modal>
    </div>
  </div>
</template>

<script>

  import Modal from './components/Modal.vue';

  export default {
    name: 'app',

    data() {
      return {
      }
    },

    components: { Modal }
  }
</script>

<style>

  #root {
    background-size: cover;
    background: url('./assets/landscape2.jpg') no-repeat fixed center;
    min-height: 100vh;
  }

</style>
