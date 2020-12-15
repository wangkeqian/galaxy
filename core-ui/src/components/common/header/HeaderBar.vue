<template>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">

      <div>
        <span class='header-font'>Galaxy博客</span>
        <el-submenu index="2" style="float: right;">
          <template slot="title">
            <div class="block"><el-avatar :size="50" :src="circleUrlPhoto" ></el-avatar></div>
          </template>
          <div v-if="showUser">
            <el-menu-item index="2-1" @click="goto('/index/profile?id='+userInfo.id)">个人中心</el-menu-item>
            <el-menu-item index="2-2">我的关注</el-menu-item>
            <el-menu-item index="2-3">我的回复</el-menu-item>
            <el-menu-item index="2-4">退出登录</el-menu-item>
          </div>
          <div v-else>
            <el-menu-item index="2-1" @click="goto('/login')">登录</el-menu-item>
          </div>
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
        activeIndex2: '1',
        //circleUrl: "http://112.74.161.190:80/group1/M00/00/00/rBIJrV_SRaaAAvS5AAIwONXIlTI877.png",
        //https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png
        circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
        squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
        sizeList: ["large", "medium", "small"],
        showUser: false,
        userInfo:{}
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
        var token = this.getStorage('token')
        if(token != null ){
          const tokenData = new FormData();
          tokenData.append("token", token);
          this.loginStatus(tokenData)
        }
      },
       //获取cookie
      getStorage(name) {
        const token = sessionStorage.getItem(name)
        return token
      },
      loginStatus(data){
        loginStatus(data).then(res =>{
          if(res.status == 200){
            this.showUser = true
            this.userInfo = res.obj
          }
          
        })
      },
      goto(url){
        this.$router.push(url)
      }
    },
    computed: {
      circleUrlPhoto(){
        if(this.userInfo != null && this.userInfo.headPhoto != null){
          return this.userInfo.headPhoto
        }
        return this.circleUrl;
      }
    },
    components: {
   
    }
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
  .el-avatar img{
    margin: auto;
  }

</style>
