import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import OnlineTestView from '../views/OnlineTestView.vue'
import FinalCore from '@/components/FinalCore.vue'
import ReadTest from '@/components/ReadTest.vue'
import TestList from '@/components/TestList.vue'
import RecordsTest from '@/components/RecordsTest.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: () => import('../views/AdminView.vue'),
    meta: {
      title: '登录'
    }
  },
  {
      path: '/home',
      name: 'home',
      component: () => import( '../views/HomeView.vue'),
      meta: {
        title: '首页'
      },
      children: [
        {
          path: 'TestList',
          name: 'TestList',
          component: TestList,
          meta: {
            title: '考试列表'
          }
        },
        {
          path:'ReadTest',
          name:'ReadTest',
          component:ReadTest,
          meta: {
            title: '考试须知'
          }
        },
        {
          path: 'OnlineTest',
          name: 'OnlineTestView',
          component: OnlineTestView,
          meta: {
            title: '在线考试'
          }
        },
        {
          path: 'about',
          name: 'about',
          component: () => import('../views/AboutView.vue')
        },
        {
          path: 'recordsTest',
          name: 'RecordsTest',
          component: RecordsTest,
          meta: {
            title: '考试记录'
          }
        },
        {
          path: 'finalcore',
          name:'finalCore',
          component: FinalCore,
           props: true,
           meta: {
            title: '答题记录'
          }
        }
      ]
      
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  const pageTitle = to.meta.title || 'Page'; // 获取目标路由的标题
  document.title = pageTitle; // 更改浏览器标题
  next(); // 继续路由导航
});

export default router
