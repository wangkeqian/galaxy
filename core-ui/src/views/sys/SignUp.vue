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
  <div>
    <span>手机:&nbsp;</span>
    <el-input
    placeholder="请输入手机"
    v-model="phone"
    clearable>
    </el-input>
  </div>
  <div>
    <el-button type="success"  :loading="loginStatus" @click=signup>注册</el-button> 
  </div>
 </div>
</template>

<script>
  import { signup } from '@/network/user'
  export default {
    name: 'Register',
    data() {
      return {
        username: '',
        password: '',
        phone: '',
        loginStatus: false
      }
    },
    methods: {
      signup(){
        this.loginStatus = true
        let signinData = new Object()
        signinData['username'] = this.username
        signinData['password'] = this.password
        signinData['phone']    = this.phone
        signup(signinData).then(res =>{
          this.$message({
              message: '注册成功,请登录',
              type: 'success'
            });
          this.$router.push('/blog/sign_in')
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
