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

  import { mapGetters } from 'vuex';
  import { UPDATE_STEP, UPDATE_SCORE } from "../../store/types/mutation_types";

  export default {

    data() {
      return {
        cart: [
          {
            id: 1,
            src: './images/trex_costume.jpg',
            alt: 'trex',
            description: 'Костюм тиранозвара',
            cost: 200
          },
          {
            id: 2,
            src: './images/shoot.jpg',
            alt: 'shoot',
            description: 'Стрельба по мишеням с фотографиями',
            cost: 150
          },
          {
            id: 3,
            src: './images/disco.jpg',
            alt: 'disco',
            description: 'Дискотека',
            cost: 75
          },
          {
            id: 4,
            src: './images/hug.gif',
            alt: 'coupon',
            description: 'Купон 2000 часов обнимашек',
            cost: 350
          },
          {
            id: 5,
            src: './images/balloons.jpg',
            alt: 'baloons',
            description: 'Миллиард воздашных шариков',
            cost: 200
          },
          {
            id: 6,
            src: './images/hottabich.jpg',
            alt: 'nothing',
            description: 'Да что хотите! Хотя я сама скажу',
            cost: 0
          },
          {
            id: 7,
            src: './images/stars.jpg',
            alt: 'stars',
            description: 'Годовой запас блесток и звездочек',
            cost: 140
          },
          {
            id: 8,
            src: './images/dna.jpg',
            alt: 'dna',
            description: 'Ебовейший тест днк',
            cost: 290
          },
          {
            id: 9,
            src: './images/sbp4.jpg',
            alt: 'sbp4',
            description: 'СБПЧ',
            cost: 100
          },
          {
            id: 10,
            src: './images/merch.jpg',
            alt: 'merch',
            description: 'Именной мерч',
            cost: 350
          },
          {
            id: 11,
            src: './images/postcard.jpg',
            alt: 'postcard',
            description: 'Внезапные открытки!',
            cost: 100
          },
          {
            id: 12,
            src: './images/keywords.jpg',
            alt: 'keywrods',
            description: 'Миллион! Нет, два миллиона кейвордов!',
            cost: 300
          }
        ],
        sumSelected: 0,
        itemsSelected: []
      }
    },

    computed: {
      currentSum() {
        return this.currentScore - this.sumSelected;
      },

      notEnough() {
        if (this.currentScore - this.sumSelected < 0) {
          return 'not-enough';
        }
      },

      disabled() {
        return this.currentScore - this.sumSelected < 0;
      },

      ...mapGetters([
        'currentStep',
        'currentScore'
      ])
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
        if (this.currentScore - this.sumSelected >= 0) {
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

          axios.post(
            '/api/modal/submit',
            {
              step: this.currentStep
            }
          ).then((response) => {
            this.$store.commit(UPDATE_SCORE, (this.currentScore - this.sumSelected));
            this.$store.commit(UPDATE_STEP, (this.currentStep + 1));
          }).catch((error) => {
            console.log(error)
          });

          axios.post('/api/cart/purchase',
            {
              sum: this.sumSelected,
              items: resultItems
            }
          ).catch((error) => {
            console.log(error)
          });

        }
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
