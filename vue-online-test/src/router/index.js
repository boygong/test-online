import VueRouter from "vue-router";
import Vue from 'vue'
import AppView from '../views/element/teacherPage.vue'
import OnlineTestView from '../views/OnlineTestView.vue'
import FinalCore from '@/components/FinalCore.vue'
import ReadTest from '@/components/ReadTest.vue'
import TestList from '@/components/TestList.vue'
import RecordsTest from '@/components/RecordsTest.vue'
import TestAbout from '@/components/TestAbout.vue'

Vue.use(VueRouter)

const routes = [
  // {
  //     path: '/',
  //     redirect:'/teacherPage'
  // },
  {
    path:'/',
    name:'loginHome',
    component:()=>import('../views/element/loginHome.vue')
  },
  {
    path:'/teacherLogin',
    name:'teacherLogin',
    component:()=>import('../views/element/teacherLogin.vue')
  },
  {
    path: '/studentLogin',
    name: 'studentLogin',
    component: () => import('../views/AdminView.vue'),
    meta: {
      title: '登录'
    }
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/HomeView.vue'),
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
        path: 'ReadTest',
        name: 'ReadTest',
        component: ReadTest,
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
        name: 'finalCore',
        component: FinalCore,
        props: true,
        meta: {
          title: '答题记录'
        }
      },
      {
        path: 'testAbout',
        name: 'TestAbout',
        component: TestAbout
      }
    ]

  },
  {
    path: '/teacherPage',
    name: 'teacherPage',
    component: AppView
  },
  {
    path: '/questionPage',
    name: 'questionPage',
    component: () => import('../views/element/questionPage.vue')
  },
  {
    path: '/examPage',
    name: 'examPage',
    component: () => import('../views/element/examPage')
  },
  {
    path: '/insertExamPage',
    name: 'insertExamPage',
    component: () => import('../views/element/insertExamPage')
  }
]


const router = new VueRouter({
  routes
})

// 全局导航守卫
router.beforeEach((to, from, next) => {
  const activeMenu = to.meta.activeMenu;
  if (activeMenu) {
    // 移除之前的激活样式
    document.querySelectorAll('.el-menu-item').forEach(item => {
      item.classList.remove('is-active');
    });
    // 根据 meta 信息设置当前激活样式
    const activeMenuItem = document.querySelector(`.el-menu-item[data-menu-index="${activeMenu}"]`);
    if (activeMenuItem) {
      activeMenuItem.classList.add('is-active');
    }
  }
  next();
});






export default router