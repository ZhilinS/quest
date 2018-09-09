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
                        <input type="text"
                               class="input modal-input"
                               :class="danger"
                               v-model="num">
                    </div>
                </section>
                <footer class="modal-card-foot">
                    <button class="button is-success"
                            :class="danger"
                            @click="postNumber">Отправить
                    </button>
                </footer>
                <button class="modal-close is-large"
                        aria-label="close"
                        @click="closeModal">
                </button>
            </div>
        </div>
    </div>
</template>

<script>

  const axios = require('axios');

  import {mapGetters} from 'vuex';
  import {TOGGLE_MODAL, TOGGLE_SUCCESS} from "../store/types/mutation_types";
  import {MODAL_SUBMIT} from "../store/types/action_types";

  let stepsToNumbers = {
    1: 629136,
    2: 321321,
    3: 432542,
    4: 123145
  };

  export default {
    data() {
      return {
        num: '',
        danger: '',
        now: Math.trunc((new Date()).getTime() / 1000)
      }
    },

    computed: {
      ...mapGetters([
        'showModal',
        'currentStep'
      ])
    },

    methods: {
      postNumber() {
        if (this.num === stepsToNumbers[this.currentStep].toString()) {
          axios.post(
            '/api/modal/submit',
            {
              step: this.currentStep,
              entered_number: this.num
            }
          ).then((response) => {
            this.num = '';
            this.danger = '';

            this.$store.dispatch(MODAL_SUBMIT, this.currentStep + 1);
            this.$store.commit(TOGGLE_SUCCESS);
          }).catch((error) => {
            console.log(error)
          });

        } else {
          this.danger = 'is-danger'
        }
      },

      closeModal() {
        this.$store.commit(TOGGLE_MODAL);
      }
    },

    mounted() {
      window.setInterval(() => {
        this.now = Math.trunc((new Date()).getTime() / 1000);
      }, 1000);
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
