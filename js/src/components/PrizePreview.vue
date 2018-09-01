<template>
  <section class="section">
    <div class="tile is-ancestor">
      <div class="tile is-vertical">
        <div class="tile is-parent justified-tile">
          <p class="title" style="text-align: center">Ух, что скоро будет!</p>
        </div>
        <div class="tile is-parent justified-tile">
          <div class="tile is-child is-2" v-for="variant in this.imgs">
            <figure class="image is-4by3">
              <img :src="variant.src" :alt="variant.alt">
            </figure>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>

  export default {
    data() {
      return {
        imgs: [
          {
            src: './dist/images/lake_house.png',
            alt: 'lake-house'
          },
          {
            src: './dist/images/concert.png',
            alt: 'concert'
          },
          {
            src: './dist/images/hackathon.png',
            alt: 'hackathon'
          }
        ]
      }
    }
  }

</script>

<style>
  .justified-tile {
    justify-content: center;
  }

  @media (max-width: 800px) {

  }
</style>
