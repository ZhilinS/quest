<template>
  <section class="section with-image">
    <div class="container column-container">
      <div class="tile is-ancestor justified-tile">
        <div class="tile is-6">
          <article class="tile is-child">
            <p class="title" style="text-align: center; color: white">
              Магазин на диване
            </p>

            <div class="content">
              <p class="text-content">
                За твои заслуги перед отечественным производителем квестов
                ты предоставляешься к награде и можешь выбрать что угодно,
                располагая своим бюджетом
              </p>
            </div>
          </article>
        </div>
      </div>
      <div class="container spaced">
        <sell-item v-for="itemCart in cart" :itemCart="itemCart" @click="selectItem(itemCart.id)"></sell-item>
      </div>
      <div class="check-out" @click="processPurchase">
        <a class="button is-primary">Закупить!</a>
      </div>
    </div>
  </section>
</template>

<script>

  const axios = require('axios');

  import SellItem from './SellItem.vue'

  export default {

    data() {
      return {
        selectedItemId: 0,
        cart: [
          {
            id: 1,
            src: '',
            alt: 'aqua',
            description: 'Крутой вечер в аквапарке',
            cost: 300
          },
          {
            id: 2,
            src: '',
            alt: 'hunt',
            description: 'Охота на горилл',
            cost: 350
          },
          {
            id: 3,
            src: '',
            alt: 'disco',
            description: 'Дискотека',
            cost: 300
          },
          {
            id: 4,
            src: '',
            alt: 'coupon',
            description: 'Купон 2000 часов обнимашек',
            cost: 350
          },
          {
            id: 5,
            src: '',
            alt: 'voodoo',
            description: 'Кукла вуду человека на выбор',
            cost: 300
          },
          {
            id: 6,
            src: '',
            alt: 'nothing',
            description: 'Ничего',
            cost: 400
          }
        ]
      }
    },

    methods: {
      selectItem(id) {
        this.selectedItemId = id;
      },

      processPurchase() {
        Event.$emit('update_score');

        axios.post('http://localhost:8501/api/cart/purchase',
          {
            id: this.selectedItemId
          }
        ).then((response) => {
          console.log(response);
        }).catch((error) => {
          console.log(error)
        });
      }
    },

    components: {SellItem}
  }

</script>

<style>

  .tile.is-ancestor.justified-tile {
    justify-content: center;
    margin-bottom: 3em;
  }

  .with-image {
    background: url("../assets/tada.png") no-repeat center center fixed;
  }

  .spaced {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
  }

  .column-container {
    display: flex;
    flex-flow: column;
    align-items: center;
  }

  .check-out {
    margin: 3em;
  }

</style>
