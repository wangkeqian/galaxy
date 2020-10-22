<template>
  <div>
    <el-container>
      <el-header>
        <HeaderBar></HeaderBar>
      </el-header>
    </el-container>
    <el-container>
      <left-menu/>
      <el-main style="background-color: #288b49;">
        <h3>你你你你</h3>
      </el-main>
    </el-container> 
  </div>
</template>

<script>
  import LeftMenu from '@/components/common/leftMenu/LeftMenu'
  import HeaderBar from '@/components/common/header/HeaderBar'
  export default {
    name: 'Home',
    data() {
      return {
        
      }
    },
    methods: {

    },
    components: {
      LeftMenu,
      HeaderBar
    }
  }
</script>

<style scoped>
  .el-header{
    padding: 0;
  }

</style>
