<template>
  <div class="changing-header">
    <div>
      Ну здравствуй,
      <span v-if="!motion">
        {{ current }}
      </span>
      <span v-if="motion">
        {{ changing }}
      </span>
    </div>
    <div>
      Соскучилась? Да я знаю!
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        texts: [
          'родная', 'солнце', 'МАХАРЕП',
          'красавица', 'вселенная',
          'принцесса', 'ну привет', 'дорогая',
          'работница', 'комсомолка', 'чудо ПО',
          'заводная', 'смешная', 'amigo!'
        ],
        currentText: '',
        changingText: '',
        inMotion: true
      }
    },

    computed: {
      current() {
        return this.currentText;
      },

      changing() {
        return this.changingText;
      },

      motion() {
        return this.inMotion;
      }
    },

    mounted() {
      window.setInterval(() => {
        this.changingText = this.texts[Math.floor(Math.random() * this.texts.length)]
      }, 70);

      window.setInterval(() => {
        this.inMotion = !this.inMotion;
        this.currentText = this.texts[Math.floor(Math.random() * this.texts.length)]
      }, 2000);
    }
  }
</script>

<style>
  /*@media all and (max-width: 700px){*/
    /*.changing-header {*/
      /*font-size: 18px;*/
      /*background-color: yellow;*/
    /*}*/
  /*}*/
  .changing-header {
    font-size: 18px;
  }
</style>
