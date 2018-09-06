<template>
  <section class="section with-image">
    <div class="container">
      <div class="tile is-ancestor justified-tile">
        <div class="tile is-6">
          <article class="tile is-child">
            <p class="title" style="text-align: center; color: white">ИТАК</p>
            <div class="content">
              <p class="text-content">
                В конце каждого дня тут будут появляться небольшие задания
                просто для того, чтобы немного отдохнуть от рабочей рутины.
              </p>
              <p class="text-content">
                Но вот только просто так выполнять задания - это скучно.
              </p>
              <p class="text-content">
                Поэтому у тебя есть возможность подзаработать немного и потом
                потратить эти кредиты с умом!
              </p>
            </div>
          </article>
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
  p.text-content {
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
    background: url("../../../assets/tada.png") no-repeat center center fixed;
  }
</style>
