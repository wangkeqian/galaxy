<template>
  <div class='main'>
    <el-divider content-position="left"><i class="el-icon-user"></i> 基本档案</el-divider>
    <div class='baseInfo'>
      <div class='headerImg'>
        <el-avatar :size="100" :src="user.headPhoto"></el-avatar>
      </div>
      <div class="innerInfo">
        <h1>
          {{user.name}} 
          <template v-if='isMyself'>
            <el-button style="float: right;" 
              type='primary'
              size="medium" 
              icon='el-icon-circle-plus-outline'
              @click="focus"
              v-if="!followeStatus">
              关注
            </el-button>
            <el-popconfirm
              v-else
              confirm-button-text='确定'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="确定要取消关注吗？"
              @onConfirm="focus"
            >
              <el-button style="float: right;" 
              type='success'
              size="medium" 
              icon='el-icon-circle-check'
              
              slot="reference"
              >
              已关注
            </el-button>
            </el-popconfirm>
          </template>
          </h1>
        <h4>{{user.position}} | {{user.birthday | dateFormat}}</h4>
        <h4>
          <span><i class="el-icon-phone-outline"></i> {{user.phone}}</span> <span><i class="el-icon-link"></i>{{user.email}}</span>
        </h4>
      </div>
    </div>
    <div class="feature">
      <div class='personalProfile'>
        <el-divider content-position="left"><i class="el-icon-news"></i> 个人简介</el-divider>
        <el-input
          type="textarea"
          :rows="3"
          placeholder="请输入内容"
          v-model="user.intro"
          :readonly=true>
        </el-input>
      </div>
    </div>
  </div>
</template>

<script>
  import 
  { 
    getUserProfile,
    addFollowing,
    isFollowing,
    cancelFollowing
  } 
  from '@/network/user'
  export default {
    name: 'Profile',
    data() {
      return {
        circleUrl: "http://112.74.161.190:80/group1/M00/00/00/rBIJrV_SRaaAAvS5AAIwONXIlTI877.png",
        id:'',
        user: {},
        followeStatus: false,
        icon: 'el-icon-circle-check'
      }
    },
    created() {
      this.id = this.$route.query.id
      this.isFollowing()
      this.getSysUser(this.id)

    },
    methods: {
      getSysUser(id){
        getUserProfile(id).then(res =>{
          this.user = res.obj
        })
      },
      focus(){
        const id = this.id
        if(this.followeStatus){
          //取消关注
          cancelFollowing(id).then(res =>{
            this.followeStatus = false
          })
        }else{
          //关注
          addFollowing(id).then(res =>{
          this.followeStatus = true
        })
        }
      },
      isFollowing(){
        isFollowing(this.id).then(res =>{
          if(res.obj == true){
            this.followeStatus = true
          }
        })
      },
    },
    computed:{
      isMyself(){
        const id = sessionStorage.getItem('loginUserId')
        return id != this.id
      }
    },
    components: {}
  }
</script>

<style scoped>
  .main{
    width: 70%;
    margin: auto;
  }
  .baseInfo{
    background-color: #f8f8f8;
    /* width: 70%; */
    margin: auto;
  }
  .headerImg{
    width: 100px;
    margin-left: 50px;
    margin-top: 10px;
    float: left;
  }
  .innerInfo{
    
    margin-top: 20px;
    margin-left:180px;
  }
  .el-divider{
    margin-top: 30px;
  }
  .el-avatar.img{
    margin: auto;
  }
</style>
