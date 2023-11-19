<template>
    <div>
      <h1>登录页面</h1>
      <button @click="redirectToStudentPage">学生登录</button>
      <button @click="redirectToTeacherPage">教师登录</button>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      redirectToStudentPage() {
        // 使用 Vue Router 的编程式导航跳转到学生登录页面
        this.$router.push('/studentLogin');
      },
      redirectToTeacherPage() {
        // 使用 Vue Router 的编程式导航跳转到教师登录页面
        this.$router.push('/teacherLogin');
      },
    },
  };
  </script>
  
  <style scoped>
  /* 样式可以根据需要进行自定义 */
  h1 {
    text-align: center;
  }
  button {
    margin: 10px;
  }
  </style>
  