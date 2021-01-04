<template>
  <div>
    <el-calendar v-model="now" style="float: left;">
    </el-calendar>
    <div class = "main">
      <div class="loginView">
        <div class='navi'>
          <router-link to="/blog/sign_in" tag='el-button' :style="selectedLoginBtn">登录</router-link>
          <router-link to="/blog/sign_up" tag='el-button' :style="selectedSignUpBtn">注册</router-link>  
        </div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: '',
    data() {
      return {
        now: new Date()
      }
    },
    methods: {
    },
    computed:{
      selectedLoginBtn(){
        const path = this.$route.path
        console.log('path='+path);
        
        return path == '/blog/sign_in' ? 'background-color: #e9f0fd':''
      },
      selectedSignUpBtn(){
        const path = this.$route.path
        console.log('path='+path);
        
        return path == '/blog/sign_up' ? 'background-color: #e9f0fd':''
      }
    },
    components: {}
  }
</script>

<style scoped>
  .el-calendar{
    /* line-height: 40%; */
    width: 50%;
  }
  .loginView{
    width: 70%;
    line-height: 100px;
    margin: auto;
    margin-top: 20%;
  }
  .main{
    float: left;
    width: 50%;
  }
  .navi{
    margin-left: 35%;
  }
</style>
