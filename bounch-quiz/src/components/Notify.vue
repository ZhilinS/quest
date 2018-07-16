<template>
  <div v-if="success">
    <div class="notification is-success">
      <button class="delete" @click="closeNotify"></button>
      Ой вей, правильный код! Получите ваши кредиты!
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        success: false
      }
    },

    methods: {
      closeNotify() {
        this.success = false;
      }
    },

    created() {
      Event.$on('modal_success', () => this.success = true);
    }
  }
</script>

<style>

</style>
