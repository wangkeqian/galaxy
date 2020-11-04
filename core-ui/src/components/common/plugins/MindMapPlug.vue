<template>
  <div>
    <el-row style="margin-bottom: 10px;">
      <el-col :span=14>
        <el-input placeholder="标题" v-model="title" required>
          <template slot="prepend">标题：</template>
        </el-input>
      </el-col>
      
      <el-col :span=2 style="float: right;">
        <el-button :disabled="!isEdit" type="success" plain @click=submit>保存脑图</el-button>
      </el-col>
    </el-row>
    <mind-map v-model="data" :height=height></mind-map>
  
  </div>
</template>

<script>
  import MindMap from '@hellowuxin/mindmap'
  import { addMindMap } from '@/network/mindMap'
  export default {
    name: 'MindMapPlug',
    props: {
      dataVo: Object,
      isEdit: {
        type: Boolean,
        default(){
          return true
        }
      }
    },
    data() {
      return {
        height: 650,
        id: '',
        title: '',
        data: [{
          "name":"如何学习D3",
          "children": [
            {
              "name":"预备知识",
              "children": [
                { "name":"HTML & CSS" },
                { "name":"JavaScript" },
              ]
            },
            {
              "name":"安装",
              "_children": [
                { "name": "折叠节点" }
              ]
            },
            {
              "name":"进阶",
              "left": true
            }
          ]
        }]
      }
    },
    methods: {
      submit(){
        console.log(JSON.stringify(this.data));
        this.addMindMap()
      },
      addMindMap(){
        const data = new Object
        data['id'] = this.id
        data['title'] = this.title
        data['data'] = JSON.stringify(this.data)
        addMindMap(data).then(res =>{
          if(res.status == 200){
            this.$message({
              message: '操作成功',
              type: 'success'
            });
            setTimeout(() =>{
              this.$router.push('/index/mindMapList')
            },500)
          }
        }).catch(res =>{
          this.isLoading = false
          this.$message.error('提交错误,请检查服务器');
        })
      }
    },
    components: {
      MindMap
    },
    watch: {
      'dataVo': {
        immediate:true,
        deep: true,
        handler:function(newVal){
          this.id = newVal.id
          this.title = newVal.title
          this.data = JSON.parse(newVal.data)
        }
      },
      isEdit(newVal){

      }
    },
  }
</script>

<style scoped>

</style>
