<template>
    <div v-if="successSubmit">
        <div class="notification is-success">
            <button class="delete" @click="closeNotify"></button>
            Ой вей, правильный код! Получите ваши кредиты!
        </div>
    </div>
</template>

<script>
  import {mapGetters} from 'vuex';
  import {TOGGLE_SUCCESS} from "../mutation_types";

  export default {
    methods: {
      closeNotify() {
        this.$store.commit(TOGGLE_SUCCESS)
      }
    },

    computed: {
      ...mapGetters([
        'successSubmit'
      ])
    },
  }
</script>

<style>

</style>
