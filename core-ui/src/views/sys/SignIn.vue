<template>
  <div>
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
   </div>
   <el-divider content-position="right">以梦为马，不负韶华</el-divider>
   <div>
     <el-button type="primary" :loading="loginStatus" @click=login()>登录</el-button> 
   </div>
  </div>
 </template>
 
 <script>
  import { login } from '@/network/user'
  export default {
     name: 'Register',
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
          console.log(res.obj.sysUser);
          sessionStorage.setItem('loginUserId',res.obj.sysUser.id)
          const token = sessionStorage.getItem('token')   
          this.$router.push('/index/home')
        }).catch(error =>{
          this.loginStatus = false
        })
      },
     },
     components: {}
   }
 </script>
 
 <style scoped>
   .el-input{
     vertical-align:middle;
     width: 90%;
   }
 </style>
 