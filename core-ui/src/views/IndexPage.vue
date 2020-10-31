<template>
  <div>
    <el-container>
      <el-header>
        <HeaderBar></HeaderBar>
      </el-header>
    </el-container>
    <el-container>
      <left-menu :currentStation.sync='currentStation'/>
      <el-main>
        <router-view ></router-view>
      </el-main>
    </el-container> 
  </div>
</template>

<script>
  import LeftMenu from '@/components/common/leftMenu/LeftMenu'
  import HeaderBar from '@/components/common/header/HeaderBar'
  import EditNote from '@/views/note/EditNote'
  export default {
    name: 'IndexPage',
    data() {
      return {
        currentStation: 'editNote'
      }
    },
    methods: {

    },
    created() {
      console.log('这里是IndexPage');
      
    },
    components: {
      LeftMenu,
      HeaderBar,
      EditNote
    },
    watch: {
      currentStation(newVal){
        this.$router.push(this.currentStation)
      }
    },
  }
</script>

<style scoped>
  .el-header{
    padding: 0;
  }

</style>
