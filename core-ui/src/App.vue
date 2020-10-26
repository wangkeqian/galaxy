<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  import IndexPage from '@/views/IndexPage'
  export default {
    name: 'app',
    data() {
      return {

      }
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
