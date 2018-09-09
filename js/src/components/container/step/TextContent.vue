<template>
  <section class="section with-image">
    <div class="container">
      <div class="tile is-ancestor justified-tile">
        <div class="tile is-6">
          <article class="tile is-child">
            <p class="title" style="text-align: center; color: white">ИТАК</p>
            <div class="content">
              <p class="text-content">
                В определенное время тут будут появляться испытания воли и ума.
                Никто не знает <s>почему едет он, а не я</s> откуда и почему,
                но, надеемся, это поможет отвелечься от рабочей рутины и отдохнуть.
              </p>
              <p class="text-content">
                Но вот только просто так выполнять задания - это скучно.
              </p>
              <p class="text-content">
                Поэтому у тебя есть возможность подзаработать немного и потом
                потратить бабосики с умом!
              </p>
              <p class="text-content">
                И даже сейчас ты можешь найти что нам сказать через "Ввести код"!
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
