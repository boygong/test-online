<template>
  <div>
    <el-container>
    <el-aside width="18%"  style="background-color: rgb(238, 241, 246);border-right: 1px solid;border-radius: 30px;height: 700px;">
          <el-menu>
            <el-menu-item  index="1" >
              <template slot="title"><i class="el-icon-grape"></i>
                <router-link to="/home/about">首页</router-link>
              </template>
            </el-menu-item >
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-pear"></i>
                <span>在线考试</span>
              </template>
              <el-menu-item-group :default-active="[1,2]">
                <template slot="title"></template>
                <el-menu-item index="1">
                  <router-link to="/home/TestList">我的考试</router-link>
                </el-menu-item>

              </el-menu-item-group>
            </el-submenu>
            <el-menu-item  index="3">
              <template slot="title"><i class="el-icon-apple"></i>
                <router-link to="/home/recordsTest">考试记录</router-link>
              </template>
            </el-menu-item >
            <el-menu-item  index="4">
              <template slot="title"><i class="el-icon-pie-chart"></i>
                <router-link to="">成绩分析</router-link>
              </template>
            </el-menu-item >
          </el-menu>
        </el-aside>
      <el-container>
        <el-main >
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default{
  data(){
    return{
      userId:null
    }
  },
}
</script>
<style>
</style>


