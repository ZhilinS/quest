<template>
  <section class="section centered">
    <div class="content" v-if="!questFinished">
    </div>
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
      question: 'Чем знаменит город Выборг?',
      src: './images/vyborg.png',
      alt: 'vyborg',
      variants: [
        {
          text: 'Старинными зданиями',
          correct: false,
          id: 1
        },
        {
          text: 'Культурой',
          correct: false,
          id: 2
        },
        {
          text: 'Корсетом',
          correct: true,
          id: 3
        },
        {
          text: 'Молодежными движениями',
          correct: false,
          id: 4
        }
      ]
    },
    2: {
      question: 'Продолжаем тему Финляндии. Как, согласно финскому поверью, был создан мир?',
      src: './images/god.png',
      alt: 'god',
      variants: [
        {
          text: '7 дней, немного майнкрафта и змеюка с яблоком',
          correct: false,
          id: 1
        },
        {
          text: 'Глазояйцевый вуайерист помог членожопу',
          correct: true,
          id: 2
        },
        {
          text: 'Никто ничего не создавал. И вообще, мы в матрице',
          correct: false,
          id: 3
        },
        {
          text: 'Смазали и наляпали',
          correct: false,
          id: 4
        }
      ]
    },
    3: {
      question: 'Мода за 300. Коко Шанель была известна своим безупречным вкусом. Что, согласно её высказыванию, рождает настоящую женщину?',
      src: './images/coco.png',
      alt: 'coco',
      variants: [
        {
          text: 'Два правила женщины: обувь — на размер больше, бюстгальтер — на размер меньше',
          correct: true,
          id: 1
        },
        {
          text: 'Шуба. Охуенная розовая шуба',
          correct: true,
          id: 2
        },
        {
          text: 'Третье правило женщины: туфли - обязательно с носками',
          correct: false,
          id: 3
        },
        {
          text: 'Другая настоящая женщина',
          correct: false,
          id: 4
        }
      ]
    },
    4: {
      question: 'Чуть посложнее. Что в итоге сделал Хармс со старухой в своем одноименном рассказе?',
      src: './images/harms.png',
      alt: 'harms',
      variants: [
        {
          text: 'Оставил в комнате',
          correct: false,
          id: 1
        },
        {
          text: 'Попросил Сакирдона Михайловича отвезти тело в лес',
          correct: false,
          id: 2
        },
        {
          text: 'Запихнул в чемодан и повез топить в болото',
          correct: true,
          id: 3
        },
        {
          text: 'Попил c ней воды и лег спать',
          correct: false,
          id: 4
        }
      ]
    },
    5: {
      question: 'Ах, какая женщина, какая женщина',
      src: './images/woman.png',
      alt: 'woman',
      variants: [
        {
          text: 'Мне б такую',
          correct: true,
          id: 1
        },
        {
          text: 'Дякую',
          correct: false,
          id: 2
        },
        {
          text: 'Какая женщина?',
          correct: false,
          id: 3
        },
        {
          text: 'Мущина, вы что, не видите?',
          correct: false,
          id: 4
        }
      ]
    },
    6: {
      question: 'Поговорим об истории архитектуры. С какого здания началась история Санкт-Петербурга',
      src: './images/piter.png',
      alt: 'piter',
      variants: [
        {
          text: 'Мега Парк на Заставской',
          correct: false,
          id: 1
        },
        {
          text: 'Домик Петра I',
          correct: true,
          id: 2
        },
        {
          text: 'Адмиралтейство',
          correct: false,
          id: 3
        },
        {
          text: 'Зимний дворец',
          correct: false,
          id: 4
        }
      ]
    },
    7: {
      question: 'Продолжаем архитектурную тему. Какой день считается днем рождения архитектурного клуба?',
      src: './images/architecture.png',
      alt: 'architecture',
      variants: [
        {
          text: '3-4 августа. Вместе с семрашиком основали',
          correct: false,
          id: 1
        },
        {
          text: '1 апреля. Шутка',
          correct: false,
          id: 2
        },
        {
          text: '11 сентября. Совпадение?',
          correct: false,
          id: 3
        },
        {
          text: '1-2 декабря',
          correct: true,
          id: 4
        }
      ]
    },
    8: {
      question: 'Что, согласно данному мему, можно получить ',
      src: './images/memes.png',
      alt: 'memes',
      variants: [
        {
          text: '2 года условно',
          correct: false,
          id: 1
        },
        {
          text: '',
          correct: false,
          id: 2
        },
        {
          text: '',
          correct: true,
          id: 3
        },
        {
          text: '',
          correct: false,
          id: 4
        }
      ]
    },
    9: {
      question: 'Кто, согласно легендам, вылупится из яйца в комнате руби?',
      src: './images/ruby.png',
      alt: 'ruby',
      variants: [
        {
          text: 'Человек-малекула',
          correct: false,
          id: 1
        },
        {
          text: 'Лунтик',
          correct: false,
          id: 2
        },
        {
          text: 'la Sicario',
          correct: true,
          id: 3
        },
        {
          text: 'Новый фронт',
          correct: true,
          id: 4
        }
      ]
    },
    10: {
      question: 'Где собрались люди, для которых ты безумно дорогой человек?',
      src: './images/all.png',
      alt: 'all',
      variants: [
        {
          text: 'Прямо рядом с тобой',
          correct: true,
          id: 1
        }
      ]
    },
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
          '/api/quiz/submit',
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
      axios.get('/api/quiz/current')
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
    background: url("../../../assets/tada.png") no-repeat center center fixed;
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
