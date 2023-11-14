import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import axios from 'axios';

// 假设你已经从后端获取了令牌，并存储在名为token的变量中

// 在发送请求时，将令牌放入请求头中
axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');


Vue.config.productionTip = false
Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
