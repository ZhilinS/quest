<template>
  <div v-if="showModal">
    <div class="modal is-active">
      <div class="modal-background" @click="closeModal"></div>
      <div class="modal-card">
        <header class="modal-card-head">
          <div class="header-margin">
            <p class="modal-card-title">Очень важный код</p>
          </div>
        </header>
        <section class="modal-card-body">
          <div class="input-margin">
            <input type="text" class="input modal-input" :class="danger" v-model="num">
          </div>
        </section>
        <footer class="modal-card-foot">
          <button class="button is-success" :class="danger" @click="postNumber">Отправить</button>
        </footer>
        <button class="modal-close is-large" aria-label="close" v-if="ableToClose" @click="closeModal"></button>
      </div>
    </div>
  </div>
</template>

<script>

  const axios = require('axios');

  let stepsToNumbers = {
    1: 123123,
    2: 321321,
    3: 432542,
    4: 123145
  };

  export default {
    props: ['step'],

    data() {
      return {
        dataShowModal: false,
        modalStep: this.step,
        num: '',
        danger: ''
      }
    },

    computed: {
      showModal() {
        return this.dataShowModal;
      },
      ableToClose() {
        return this.modalStep !== 1;
      }
    },

    methods: {
      postNumber() {
        if (this.num === stepsToNumbers[this.modalStep].toString()) {
          axios.post(
            'http://localhost:8501/api/modal/submit',
            {
              step: this.modalStep,
              entered_number: this.num
            }
          ).then((response) => {
            this.dataShowModal = false;
            this.num = '';
            this.danger = '';

            Event.$emit('update_score');
            Event.$emit('update_timer');
          }).catch((error) => {
            console.log(error)
          });

          Event.$emit('modal_success')
        } else {
          this.danger = 'is-danger'
        }
      },

      closeModal() {
        if (this.modalStep !== 1) {
          this.dataShowModal = false;
        }
      }
    },

    mounted() {
      axios.get('http://localhost:8501/api/modal/step/' + this.modalStep)
        .then((response) => {
          this.dataShowModal = !response.data.success;
        }).catch((error) => {
          console.log(error);
      });

      Event.$on('open_modal', (id) => {
        console.log('GOT EVENT: ' + id);
        this.dataShowModal = true;
        this.modalStep = id;
      })
    },
  }

</script>

<style>

  .modal-card-head {
    border-bottom: none;
    justify-content: center;
  }

  .modal-card-foot {
    border-top: none;
    justify-content: center;
  }

  .modal-input {
    max-width: 200px;
  }

  .input-margin {
    display: flex;
    justify-content: center;
  }

  .modal-card {
    max-width: 300px;
  }

</style>
