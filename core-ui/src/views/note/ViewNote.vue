<template>
  <div class='edit-note'>
    <div class='edit-header'>
      <el-row>
        <el-col :span="10">
          <el-input placeholder="请输入标题" v-model="article.title">
            <template slot="prepend">标题:</template>
          </el-input>
        </el-col>
        <el-col :span="14">
          <el-input placeholder="请输入副标题" v-model="article.subtitle">
            <template slot="prepend">副标题:</template>
          </el-input>
        </el-col>
      </el-row>
      <el-row style="margin: 10px 0 10px 0;"> 
        <el-tag
          :key="tag"
          v-for="tag in transformTag"
          :disable-transitions="false"
          @close="handleClose(tag)">
          {{tag}}
        </el-tag>
      </el-row>
    </div>
    <markdown-plugin :subfield='subfield'
                     :toolbarsFlag='toolbarsFlag'
                     :editable='editable'
                     defaultOpen='preview' />
  </div>
</template>

<script>

  import MarkdownPlugin from '@/components/common/plugins/MarkdownPlugin'

  export default {
    name: 'ViewNote',
    data() {
      return {
        article: {
          title: '',
          subtitle: '',
          tag: 'Java,Html,Python',
          content: ''
        },
        dynamicTags: [],
        inputVisible: false,
        inputValue: '',
        subfield: false, // Markdown 编辑/阅览
        toolbarsFlag: false,
        editable: false,

      }
    },
    methods: {

    },
    computed: {
      //字符串转换为数组
      transformTag(){
        return this.article.tag.split(",")
      }
    },
    created() {
      this.dynamicTags = this.article.tag.split(",")
    },

    components: {
      MarkdownPlugin
    }
  }
</script>

<style scoped>

  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
    float: right;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
    float: right;
  }
</style>
