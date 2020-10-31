<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  
  export default {
    name: 'app',
    data() {
      return {

      }
    },
    created() {
      console.log('这里是App.vue');
      
    },
    methods: {},
    components: {}
  }
</script>
<style>
  html,body,#app{
 		height: 100%;
 		width: 100%;
	}
</style>
