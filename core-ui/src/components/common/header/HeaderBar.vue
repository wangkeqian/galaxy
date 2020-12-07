<template>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">

      <div>
        <span class='header-font'>Galaxy博客</span>
        <el-submenu index="2" style="float: right;">
          <template slot="title">我的工作台</template>
          <el-menu-item index="2-1">选项1</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
          <el-menu-item index="2-3">选项3</el-menu-item>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项1</el-menu-item>
            <el-menu-item index="2-4-2">选项2</el-menu-item>
            <el-menu-item index="2-4-3">选项3</el-menu-item>
          </el-submenu>
        </el-submenu>
      </div>
    </el-menu>
  </div>
</template>

<script>
  import { loginStatus } from '@/network/user'
  export default {
    name: 'HeaderBar',
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      }
    },
    created() {
      this.getUser()
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      getUser(){
        var token = this.getCookie('userToken')
        if(token != null ){
          const tokenData = new FormData();
          tokenData.append("token", token);
          console.log(loginStatus(tokenData));
        }
      },
       //获取cookie
      getCookie (name) {
          //获取当前所有cookie
          var strCookies = document.cookie;
          //截取变成cookie数组
          var array = strCookies.split(';');
          //循环每个cookie
          for (var i = 0; i < array.length; i++) {
              //将cookie截取成两部分
              var item = array[i].split("=");
              //判断cookie的name 是否相等
              if (item[0] == name) {
                  return item[1];
              }
          }
          return null;
      }
    },
    components: {}
  }
</script>

<style scoped>
  .el-menu{
    background-color: #f2f2f2;
  }
  .header-font{
    width: 100px;
    font-size: 25px;
  }
</style>
