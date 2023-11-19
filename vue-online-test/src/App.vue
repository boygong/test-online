<template>
  <!-- <teacher-page></teacher-page> -->
  <router-view></router-view>
</template>

<script>
// import router from './router';
// import TeacherPage from './views/element/teacherPage.vue';

export default {
  name: 'App',
  components: {
    // TeacherPage,
    // router
}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.el-menu-item.is-active {
  background-color: #409EFF; /* 当前路由背景颜色 */
}

.el-menu-item.is-active a {
  color: #fff; /* 当前路由文字颜色 */
}

.el-menu-item, .el-submenu__title {
  text-decoration: none !important; /* 取消下划线 */
}
</style>
