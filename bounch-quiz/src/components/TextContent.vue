<template>
  <section class="section with-image">
    <div class="container">
      <div class="tile is-ancestor justified-tile">
        <div class="tile is-6">
          <article class="tile is-child">
            <div class="content">
              <p class="text-content">
                Зачем хоть что-то делать, если в твоем мире все просто остается словами?
              </p>
              <p class="text-content">
                Звал, вытаскивал, получал отказы, это значит что просто "говорил, но не делал". Л - логика
              </p>
              <p class="text-content">
                Вечер у меня, свидание, этот подарок, который я бы сам даже хотел получить, все тебе не нужно. Зато нужно что-то эдакое. Пока непонятно что, но чтобы легче стало.
              </p>
              <p class="text-content">
                А хотя кому я это говорю, все равно как горох об стену.
              </p>
              <p class="text-content">
                Крч, искренне желаю разоброаться в себе.
              </p>

            </div>
            <!--<p class="title" style="text-align: center; color: white">Маленькая шалость</p>-->
            <!--<div class="content">-->
              <!--<p class="text-content">-->
                <!--В конце каждого дня тут будут появляться небольшие задания-->
                <!--просто для того, чтобы немного отдохнуть от рабочей рутины.-->
              <!--</p>-->
              <!--<p class="text-content">-->
                <!--Но вот только просто так выполнять задания - это скучно.-->
              <!--</p>-->
              <!--<p class="text-content">-->
                <!--Поэтому у тебя есть возможность подзаработать немного и потом-->
                <!--потратить эти кредиты (поцелуйчики) с умом!-->
              <!--</p>-->
              <!--<p class="text-content">-->
                <!--По любым вопросам ты знаешь куда обращаться.-->
                <!--Если закончилась зарядка для мозгов - ты опять знаешь куда идти ;)-->
              <!--</p>-->
            <!--</div>-->
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
    background: url("../assets/tada.png") no-repeat center center fixed;
  }
</style>
