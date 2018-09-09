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
                ты представляешься к награде и можешь выбрать что угодно,
                располагая своим бюджетом
              </p>
            </div>
          </article>
        </div>
      </div>
      <div class="container spaced">
        <div class="item" v-for="itemCart in cart" @click="selectItem(itemCart.id, itemCart.cost)" :id="itemCart.id">
          <sell-item  :itemCart="itemCart"></sell-item>
        </div>
      </div>
      <div class="sum" :class="notEnough">
        {{ currentSum }}
      </div>
      <div class="check-out" @click="processPurchase">
        <a class="button is-primary" :disabled="disabled">Закупить!</a>
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
            cost: 0
          }
        ],
        balance: 0,
        sumSelected: 0,
        itemsSelected: []
      }
    },

    computed: {
      currentSum() {
        return this.balance - this.sumSelected;
      },

      notEnough() {
        if (this.balance - this.sumSelected < 0) {
          return 'not-enough';
        }
      },

      disabled() {
        return this.balance - this.sumSelected < 0;
      }
    },

    methods: {
      selectItem(id, cost) {
        if (this.itemsSelected.indexOf(id) !== -1) {
          this.itemsSelected.splice(this.itemsSelected.indexOf(id), 1);
          this.sumSelected -= cost;

          document.getElementById(id).classList.remove('active')
        } else {
          this.itemsSelected.push(id);
          this.sumSelected += cost;

          document.getElementById(id).classList.add('active');
        }
      },

      processPurchase() {
        if (this.balance - this.sumSelected >= 0) {
          let resultItems = [];

          this.cart.forEach(item => {
            if (this.itemsSelected.includes(item.id)) {
              resultItems.push(
                {
                  id: item.id,
                  description: item.description,
                  cost: item.cost
                }
              )
            }
          });

          Event.$emit('update_score');

          axios.post('http://quiz-backend:8501/api/cart/purchase',
            {
              sum: this.sumSelected,
              items: resultItems
            }
          ).then((response) => {
            console.log(response);
          }).catch((error) => {
            console.log(error)
          });
        }
      }
    },

    mounted() {
      axios.get("http://quiz-backend:8501/api/score/current")
        .then((response) => {
          this.balance = response.data.score;
        }).catch((error) => {
        console.log(error)
      });
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
    background: url("../../assets/tada.png") no-repeat center center fixed;
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

  div.item {
    height: 100%;
    width: 30%;
    cursor: pointer;
    margin: 1em;
  }

  div.item.active {
    border: 3px solid lightseagreen;
  }

  div.sum {
    color: white;
    font-weight: bold;
  }

  div.sum.not-enough {
    color: red;
  }

</style>
