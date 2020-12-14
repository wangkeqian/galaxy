<template>
  <div>
    <el-calendar v-model="now" style="float: left;">
    </el-calendar>
    <div class = "main">
      <div class="loginView">
        <div>
          <span>账户:&nbsp;</span>
          <el-input
            placeholder="请输入账户"
            v-model="username"
            clearable>
          </el-input>
        </div>
        <div>
          <span>密码:&nbsp;</span>
          <el-input placeholder="请输入密码" clearable v-model="password" show-password></el-input>
          <el-divider content-position="right">以梦为马，不负韶华</el-divider>
        </div>
        <el-button type="primary" :loading="loginStatus" @click=login>登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>

  import { login } from '@/network/user'
  export default {
    name: 'Login',
    data() {
      return {
      
        now: new Date(),
        username: '',
        password: '',
        loginStatus: false
      }
    },
    methods: {
      login(){
        this.loginStatus = true
        login(this.username,this.password).then(res =>{
          
          sessionStorage.setItem('token',res.obj.token)
          const token = sessionStorage.getItem('token')   
          console.log('token='+token);
          
          this.$router.push('/index/home')
        }).catch(error =>{
          this.loginStatus = false
        })
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
  .el-input{
    vertical-align:middle;
    width: 90%;
  }
  .main{
    float: left;
    width: 50%;
    
  }
</style>
