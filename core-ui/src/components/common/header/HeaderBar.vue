<template>
  <div>
    <el-menu :default-active="defaultActive" class="el-menu-demo" mode="horizontal" @select="handleSelect">

      <div>
        <span class='header-font'>Galaxy博客</span>
        <el-submenu index="2" style="float: right;">
          <template slot="title">
            <div class="block"><el-avatar :size="50" :src="circleUrlPhoto" ></el-avatar></div>
          </template>
          <div v-if="showUser">
            <el-menu-item index="2-1" @click="goto('/index/profile?id='+userInfo.id)">
              <el-dropdown-item class="clearfix">
                个人中心
                <!-- <el-badge class="mark" :value="12" /> -->
              </el-dropdown-item></el-menu-item>
            <el-menu-item index="2-2">
              <el-dropdown-item class="clearfix">
                我的关注
                <el-badge class="mark" :value="focusPoint" />
              </el-dropdown-item>
            </el-menu-item>
            <el-menu-item index="2-3">
              <el-dropdown-item class="clearfix">
                我的回复
                <el-badge class="mark" :value="replyPoint" />
              </el-dropdown-item>
            </el-menu-item>
            <el-menu-item index="2-4" @click='logout'>
              <el-dropdown-item class="clearfix">
                退出登录
              </el-dropdown-item>
            </el-menu-item>
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
        defaultActive: '1',
        circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
        showUser: false,
        userInfo:{},
        websock: null,
        focusPoint: '',
        replyPoint: ''
      }
    },
    created() {
      this.getUser()
      this.initWebSocket()
    },
    methods: {
      logout(){
        sessionStorage.removeItem("loginUserId")
        sessionStorage.removeItem("token")
        this.websock.close() //离开路由之后断开websocket连接
        this.$router.push('/blog')
      },
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
      },
      // - - - - - - - - - - - websoket - - - - - - - - - //
      initWebSocket(){ //初始化weosocket
        const uid = sessionStorage.getItem('loginUserId')
        const wsuri = "ws://127.0.0.1:8090/ws/"+uid;
        //const wsuri = "ws://112.74.161.190:8090/ws/"+uid;
        this.websock = new WebSocket(wsuri);
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onopen = this.websocketonopen;
        this.websock.onerror = this.websocketonerror;
        this.websock.onclose = this.websocketclose;
      },
      websocketonopen(){ //连接建立之后执行send方法发送数据
        let actions = {"test":"12345"};
        this.websocketsend(JSON.stringify(actions));
      },
      websocketonerror(){//连接建立失败重连
        this.initWebSocket();
      },
      websocketonmessage(e){ //数据接收
        let data = JSON.parse(e.data)
        console.log(data);
        
        if(data.status == 1){ //弹窗
          this.$notify.success({
            title: data.title,
            message: data.msg
          });
        }
        if(data.type == 1){ //我的回复 +1
          const pointnum = this.replyPoint == '' ? 0:this.replyPoint
          this.replyPoint  = parseInt(pointnum)+1
        }else if(data.type == 2){ //我的关注 +1
          const focus = this.focusPoint == '' ? 0:this.focusPoint
          this.focusPoint  = parseInt(focus)+1
        }
        
        
      },
      websocketsend(Data){//数据发送
        this.websock.send(Data);
      },
      websocketclose(e){  //关闭
        console.log('断开连接',e);
      },
    },
    destroyed() {
      this.websock.close() //离开路由之后断开websocket连接
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
