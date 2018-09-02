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
  require.context('../assets');

  const axios = require('axios');

  import Container from './container/Container.vue';
  import Modal from './Modal.vue';
  import FooterContent from './FooterContent.vue';
  import { UPDATE_STEP } from "../mutation_types";

  export default {
    name: 'app',

    mounted() {
      axios.get("http://localhost:8501/api/modal/current")
        .then((response) => {
          console.log("STEP SOOQA: " + response.data.step);
          this.$store.commit(UPDATE_STEP, response.data.step)
        }).catch((error) => {
        console.log(error);
      });
    },

    components: { Modal, FooterContent, Container }
  }
</script>

<style>

  #root {
    min-width: 100%;
  }

  body {}

</style>
