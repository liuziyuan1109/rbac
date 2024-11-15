import Vue from 'vue';
import ElementUI from 'element-ui';
import App from './App.vue';
import router from './router';
import store from './store';
import './permission' 
import './utils/request'
import 'element-ui/lib/theme-chalk/index.css';

store.dispatch('permission/generateRoutes').then(() => {
  // 动态添加路由
  store.state.permission.menus.forEach(menu => {
    // 动态添加路由的逻辑
  })
})


Vue.use(ElementUI);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
