<template>
  <div>
    <el-aside width=null>
      <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
        <el-radio-button :label="false">展开</el-radio-button>
        <el-radio-button :label="true">收起</el-radio-button>
      </el-radio-group>
      <el-menu default-active="1-4-1" class="el-menu-vertical-demo" 
               @open="handleOpen" @close="handleClose" :collapse="isCollapse"
               router @select='selectHandle'>
        <el-menu-item index="home">
          <i class="el-icon-s-home"></i>
          <span slot="title">首页</span>
        </el-menu-item>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span slot="title">博客</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="editNote" >写博客</el-menu-item>
            <el-menu-item index="noteList">博客列表</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <span slot="title">文章分析</span>
            <el-menu-item index="1-4-1">选项1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <span slot="title">相册</span>
        </el-menu-item>
        <el-menu-item index="3" >
          <i class="el-icon-document"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span slot="title">系统设置</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
  </div>
</template>

<script>
  export default {
    name: 'LeftMenu',
    props: {
      currentStation: {
        type: String,
        required: true
      }
    },
    data() {
      return {
        isCollapse: true
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      selectHandle(key, keyPath){
        this.$emit('update:currentStation', key)
        
      }
    },

    components: {}
  }
</script>

<style scoped>

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>
