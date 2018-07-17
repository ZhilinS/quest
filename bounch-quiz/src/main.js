import Vue from 'vue'
import App from './App.vue'

window.Event = new Vue();

Vue.filter('two_digits', function (value) {
  if(value.toString().length <= 1)
  {
    return "0"+value.toString();
  }
  return value.toString();
});

new Vue({
  el: '#app',
  render: h => h(App)
});
