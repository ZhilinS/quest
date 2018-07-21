<template>
  <div class="card sell-item">
    <div class="card-image">
      <figure class="image is-4by3">
        <img :src="item.src" :alt="item.alt">
      </figure>
    </div>
    <div class="card-content sell">
      <div class="content description">
        {{ item.description }}
      </div>

      <div class="content price">
        {{ item.cost }}
      </div>
    </div>
  </div>
</template>

<script>

  export default {
    props: ["itemCart"],

    data() {
      return {
        item: this.itemCart
      }
    }
  }

</script>

<style>

  .card.sell-item {
    /*height: 100%;*/
    /*width: 30%;*/
    /*cursor: pointer;*/
    /*margin: 1em;*/
  }

  .content.description {
    font-weight: bold;
    display: flex;
    justify-content: center;

    margin-bottom: 0.5em;
  }

  div.content.price {
    font-size: large;

    display: flex;
    justify-content: center;
  }

  .card-content.sell {
    padding: 0.5em;
  }

</style>
