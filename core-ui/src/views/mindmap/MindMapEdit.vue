<template>
  <div class='mind-map' >
    <mind-map-plug :height=height
                   :dataVo=dataVo
                   :isEdit=isEdit />
  </div>
</template>

<script>
  import MindMapPlug from '@/components/common/plugins/MindMapPlug'
  export default {
    name: 'MindMapEdit',
    data() {
      return {
        height: 600,
        dataVo: {},
        isEdit: true
        
      }
    },
    created() {
      const data = this.$route.query.data     
      const isEdit = this.$route.query.isEdit
      
      if(data != null){
        this.dataVo = data
        this.isEdit = JSON.parse(isEdit)
      }
    },
    methods: {},
    components: {
      MindMapPlug
    }
  }
</script>

<style scoped>
  .mind-map{
    height: 650px;
  }
</style>
