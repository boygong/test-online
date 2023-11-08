
import router from './router';
<template>
  <div id="app">
        <router-view/>
  </div>
</template>
<script>
export default{
  data(){

  },
  mounted()
  {
    this.$router.push('/');
  }
}
</script>
<style>
  .el-aside {
    color: #333;
    height: 700px;
  }
  a{
    text-decoration: none; 
    color: inherit; 
  }
</style>
