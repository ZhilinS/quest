<template>
  <section class="section centered">
    <div class="card">
      <div class="card-image">
        <figure class="image is-4by3">
          <img :src="question.src" alt="friends">
        </figure>
      </div>
      <div class="card-content">
        <div class="content question">{{ question.question }}</div>
        <hr>
        <div class="answer" v-for="variant in question.variants">
          <div class="variant" @click="selectVariant(variant.correct)">
            {{ variant.text }}
          </div>
        </div>

        <a class="button is-primary" @click="checkVariant">
          <span>
            Смазали
          </span>
        </a>
      </div>
    </div>
  </section>
</template>

<script>

  const axios = require('axios');

  let numberToQuestion = {
    1: {
      question: 'Some random question',
      src: './dist/images/friends.png',
      variants: [
        {
          text: 'First variant',
          correct: false
        },
        {
          text: 'Second variant',
          correct: false
        },
        {
          text: 'Third variant',
          correct: false
        },
        {
          text: 'Fourth variant',
          correct: true
        }
      ]
    },
    2: {
      question: 'Second question',
      src: '../assets/car.jpg',
      variants: [
        {
          text: 'First question variant',
          correct: false
        },
        {
          text: 'Second question variant',
          correct: true
        },
        {
          text: 'Third question variant',
          correct: false
        },
        {
          text: 'Fourth question variant',
          correct: false
        }
      ]
    }
  };

  export default {
    data() {
      return {
        num: 1,
        selectedCorrect: false
      }
    },

    computed: {
      question() {
        return numberToQuestion[this.num]
      }
    },

    methods: {
      selectVariant(correct) {
        this.selectedCorrect = correct;
      },

      checkVariant() {
        axios.post(
          'http://localhost:8501/api/quiz/submit',
          {
            question_number: this.num,
            correct: this.selectedCorrect
          })
          .then((response) => {
            console.log(response);
          }).catch((error) => {
            console.log(error)
        });

        this.selectedCorrect = false;
        this.num += 1;
      }
    },

    mounted() {
      axios.get('http://localhost:8501/api/quiz/current')
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
  }

  .variant {
    border: 1px solid black;
    cursor: pointer;
  }

</style>
