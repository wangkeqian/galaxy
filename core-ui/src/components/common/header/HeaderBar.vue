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
  export default {
    name: 'HeaderBar',
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
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
