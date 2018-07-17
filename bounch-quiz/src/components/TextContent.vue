<template>
  <section class="section with-image">
    <div class="container">
      <div class="tile is-ancestor justified-tile">
        <div class="tile is-6">
          <acrticle class="tile is-child">
            <p class="title" style="text-align: center; color: white">Маленькая шалость</p>
            <div class="content">
              <p>
                В конце каждого дня тут будут появляться небольшие задания
                просто для того, чтобы немного отдохнуть от рабочей рутины.
              </p>
              <p>
                Но вот только просто так выполнять задания - этот скучно.
              </p>
              <p>
                Поэтому у тебя есть возможность подзаработать немного и потом
                потратить эти кредиты (поцелуйчики) с умом!
              </p>
              <p>
                По любым вопросам ты знаешь куда обращаться.
                Если закончилась зарядка для мозгов - ты опять знаешь куда идти ;)
              </p>
            </div>
          </acrticle>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
  export default {
    data() {
      return {}
    }
  }
</script>

<style>
  p {
    text-align: justify;
    color: white;
  }

  .justified-tile {
    justify-content: center;
  }

  .tile {
    display: inline;
  }

  .with-image {
    background: url("../assets/chart.png") no-repeat center center fixed;
  }
</style>
