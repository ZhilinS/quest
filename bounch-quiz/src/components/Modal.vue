<template>
  <div v-if="showModal">
    <div class="modal is-active">
      <div class="modal-background"></div>
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
      </div>
    </div>
  </div>
</template>

<script>

  const axios = require('axios');

  export default {
    props: ['step', 'number'],

    data() {
      return {
        dataShowModal: false,
        modalStep: this.step,
        rightNumber: this.number,
        num: '',
        danger: ''
      }
    },

    computed: {
      showModal() {
        return this.dataShowModal;
      }
    },

    methods: {
      postNumber() {
        if (this.num === this.rightNumber.toString()) {
          axios.post(
            'http://localhost:8501/api/modal/submit',
            {
              step: this.modalStep,
              entered_number: this.num
            }
          ).then((response) => {
            this.dataShowModal = false;
          }).catch((error) => {
            console.log(error)
          });

          Event.$emit('modal_success')
        } else {
          this.danger = 'is-danger'
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
