<template>
    <div id="app">
        <div id="root" class="container">
            <router-view></router-view>
            <footer-content></footer-content>
            <modal></modal>
            <giphy v-if="final"></giphy>
        </div>
    </div>
</template>

<script>
  require.context('../assets');

  import Container from './container/Container.vue';
  import Modal from './Modal.vue';
  import FooterContent from './FooterContent.vue';
  import Giphy from './Giphy.vue';

  import { mapGetters } from 'vuex';

  import { INIT_APP } from "../store/types/action_types";

  export default {
    name: 'app',

    mounted() {
      this.$store.dispatch(INIT_APP)
    },

    computed: {
      final() {
        return this.currentStep === 6;
      },

      ...mapGetters([
        'currentStep',
      ]),
    },


    components: {Modal, FooterContent, Container, Giphy}
  }
</script>

<style>

    #root {
        min-width: 100%;
    }

    body {
    }

</style>
