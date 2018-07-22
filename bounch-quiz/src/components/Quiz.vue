<template>
  <section class="section centered">
    <div class="card" v-if="!questFinished">
      <div class="card-image">
        <figure class="image is-4by3">
          <img :src="question.src" :alt="question.alt">
        </figure>
      </div>
      <div class="card-content no-padding-content">
        <div class="content question">{{ question.question }}</div>
        <hr>
        <div class="variant"
             v-for="variant in question.variants"
             @click="selectVariant(variant.correct, variant.id)"
             :id="variant.id"
        >
          {{ variant.text }}
        </div>

        <a class="button is-primary whole-width" @click="sendVariant">
          <span>
            Смазали
          </span>
        </a>
      </div>
    </div>
    <div class="content finished" v-if="questFinished">
      <p class="justified">
        Квест окончен! Теперь ты достойна пройти дальше.
        Введи код 321321 и подожди следующего дня :)
      </p>
    </div>
  </section>
</template>
<script>

  const axios = require('axios');

  let numberToQuestion = {
    1: {
      question: 'Some random question',
      src: './dist/images/friends.png',
      alt: 'friends',
      variants: [
        {
          text: 'First variant',
          correct: false,
          id: 1
        },
        {
          text: 'Second variant',
          correct: false,
          id: 2
        },
        {
          text: 'Third variant',
          correct: false,
          id: 3
        },
        {
          text: 'Fourth variant',
          correct: true,
          id: 4
        }
      ]
    },
    2: {
      question: 'Second question',
      src: './dist/images/car.jpg',
      alt: 'car',
      variants: [
        {
          text: 'First question variant',
          correct: false,
          id: 1
        },
        {
          text: 'Second question variant',
          correct: true,
          id: 2
        },
        {
          text: 'Third question variant',
          correct: false,
          id: 3
        },
        {
          text: 'Fourth question variant',
          correct: false,
          id: 4
        }
      ]
    }
  };

  export default {
    data() {
      return {
        num: 1,
        maxQuestions: 2,
        selectedVariant: 0,
        activeVariants: {
          1: false,
          2: false,
          3: false,
          4: false
        }
      }
    },

    computed: {
      question() {
        return numberToQuestion[this.num];
      },

      questFinished() {
        Event.$emit('quest_finished');

        return this.num > this.maxQuestions;
      },
    },

    methods: {
      selectVariant(correct, id) {
        this.selectedCorrect = correct;
        this.selectedVariant = id;

        Object.keys(this.activeVariants).forEach(id => {
          document.getElementById(id).classList.remove('active')
        });

        document.getElementById(id).classList.add('active');
      },

      sendVariant() {
        Object.keys(this.activeVariants).forEach(id => {
          document.getElementById(id).classList.remove('active')
        });

        axios.post(
          'http://little-magic.me/api/quiz/submit',
          {
            question_number: this.num,
            selected: this.selectedVariant,
            correct: this.selectedCorrect
          })
          .then((response) => {
            console.log(response);
          }).catch((error) => {
            console.log(error)
        });

        this.selectedCorrect = false;
        this.selectedVariant = 0;
        this.num += 1;
      }
    },

    mounted() {
      axios.get('http://little-magic.me/api/quiz/current')
        .then((response) => {
          this.num = response.data.num;
        }).catch((error) => {
          console.log(error);
      })
    }
  }

</script>

<style>
  .card {
    height: 100%;
    width: 40%;
  }

  .centered {
    display: flex;
    justify-content: center;
    background: url("../assets/tada.png") no-repeat center center fixed;
  }

  @media (max-width: 1200px) {
    .card {
      height: 100%;
      width: 50%;
    }
  }

  @media (max-width: 500px) {
    .card {
      height: 100%;
      width: 100%;
    }

    .centered {
      padding: 0;
    }
  }

  .question {
    font-weight: bold;
    padding: 10px 0 0 20px;
  }

  .variant {
    border-bottom: 2px solid #e8ebef;
    cursor: pointer;
    padding: 10px 0 10px 20px;
  }

  .variant:hover {
    background-color: #c0ede9;
  }

  .variant.active {
    background-color: lightseagreen;
  }

  .no-padding-content {
    padding: 0;
  }

  .button.is-primary.whole-width {
    height: 3rem;
    width: 100%;

    -webkit-border-radius: 0;
    -moz-border-radius: 0;
    border-radius: 0;
  }

  .content.finished {
    width: 40%;
  }

  p.justified {
    text-align: justify;
    color: white;
  }

</style>
