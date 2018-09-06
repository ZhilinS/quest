<template>
    <div id="app">
        <div id="root" class="container">
            <router-view></router-view>
            <footer-content></footer-content>
            <modal></modal>
        </div>
    </div>
</template>

<script>
  require.context('../assets');

  import Container from './container/Container.vue';
  import Modal from './Modal.vue';
  import FooterContent from './FooterContent.vue';

  import { INIT_APP } from "../store/types/action_types";

  export default {
    name: 'app',

    mounted() {
      this.$store.dispatch(INIT_APP)
    },

    components: {Modal, FooterContent, Container}
  }
</script>

<style>

    #root {
        min-width: 100%;
    }

    body {
    }

</style>
