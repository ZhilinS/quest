<template>
  <div id="app">
    <div id="root" class="container">
      <router-view></router-view>
      <footer-content></footer-content>
      <!--<modal :step="this.step"></modal>-->
    </div>
  </div>
</template>

<script>
  require.context('./assets');

  import Container from './components/container/Container.vue';
  import Modal from './components/Modal.vue';
  import FooterContent from './components/FooterContent.vue';

  export default {
    name: 'app',

    components: { Modal, FooterContent, Container }
  }
</script>

<style>

  #root {
    min-width: 100%;
  }

  body {}

</style>
