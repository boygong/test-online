import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import axios from 'axios';
import router from './router';

// 在发送请求时，将令牌放入请求头中
axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');
Vue.use(ElementUI);
Vue.prototype.axios = axios;

new Vue({
  el: '#app',
  render: h => h(App),
  router, // 正确挂载 Vue Router
});
