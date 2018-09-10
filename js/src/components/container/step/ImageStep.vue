<template>
    <section class="section with-image">
        <div class="container pointed-container">
            <img src="../../../assets/got_simple.jpg" alt="got_simple" v-if="!clicked" @click="toggle_img">
            <img src="../../../assets/got.jpg" alt="got" v-if="clicked" @click="toggle_img">
        </div>
    </section>
</template>

<script>

    export default {
      data() {
        return {
          clicked_img: false
        }
      },
      computed: {
        clicked() {
          return this.clicked_img;
        }
      },

      methods: {
        toggle_img() {
          this.clicked_img = !this.clicked_img
        }
      }
    }

</script>

<style>
    .with-image {
        background: url("../../../assets/tada.png") no-repeat center center fixed;
        display: flex;
        justify-content: center;
    }

    .pointed-container {
        cursor: pointer;
    }
</style>
