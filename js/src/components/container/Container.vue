<template>
    <div id="root" class="container">
        <hero-title></hero-title>
        <router-view></router-view>
        <prize-preview></prize-preview>
    </div>
</template>

<script>
  import HeroTitle from './HeroTitle.vue';
  import PrizePreview from './PrizePreview.vue';
  import Step from './step/Step.vue';

  export default {
    components: {HeroTitle, PrizePreview, Step}
  }
</script>

<style>

</style>