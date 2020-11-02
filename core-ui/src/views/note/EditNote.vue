<template>
  <div class='edit-note'>
    <div class='edit-header'>
      <el-row>
        <el-col :span="9">
          <el-input placeholder="请输入标题" v-model="article.title">
            <template slot="prepend">标题:</template>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-input placeholder="请输入副标题" v-model="article.subtitle">
            <template slot="prepend">副标题:</template>
          </el-input>
        </el-col>
        <el-col :span="3">
          <el-button style="float: right;" type="primary" @click='submitNote' :loading="isLoading">提交</el-button>
        </el-col>
      </el-row>
      <el-row style="margin: 10px 0 10px 0;"> 
        <el-tag
          :key="tag"
          v-for="tag in transformTag"
          closable
          :disable-transitions="false"
          @close="handleClose(tag)">
          {{tag}}
        </el-tag>
        <el-input
          class="input-new-tag"
          v-if="inputVisible"
          v-model="inputValue"
          ref="saveTagInput"
          size="small"
          @keyup.enter.native="handleInputConfirm"
          @blur="handleInputConfirm"
        >
        </el-input>
        <el-button v-else class="button-new-tag" size="medium" @click="showInput" >+ 新标签</el-button>
      </el-row>
    </div>
    <markdown-plugin ref="context" 
                     :subfield='subfield'
                     :article='article'
                     />
  </div>
</template>

<script>
  import { addNote } from '@/network/note'

  import MarkdownPlugin from '@/components/common/plugins/MarkdownPlugin'

  export default {
    name: 'EditNote',
    data() {
      return {
        article: {
          title: '',
          subtitle: '',
          tag: 'Java,Html,Python',
          content: ''
        },
        isLoading: false,
        dynamicTags: [],
        inputVisible: false,
        inputValue: '',
        subfield: true // Markdown 编辑/阅览
      }
    },
    methods: {
      handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        this.article.tag = this.dynamicTags.join(",")
      },

      showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },

      handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.dynamicTags.push(inputValue);
          this.article.tag = this.dynamicTags.join(",")
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
      //提交
      submitNote(){
        this.isLoading = true
        this.article.content = this.content
        this.addNote(this.article)
      },
      addNote(article){
        addNote(article).then(res =>{
          if(res.status == 200){
            this.$message({
              message: '操作成功',
              type: 'success'
            });
            setTimeout(() =>{
              this.$router.push('/index/noteList')
            },500)
          }
        }).catch(res =>{
          this.isLoading = false
          this.$message.error('提交错误,请检查服务器');
        })
      },
      // 绑定@imgAdd event
      $imgAdd(pos, $file){
        console.log('imgAdd');
        
      // 第一步.将图片上传到服务器.
        var formdata = new FormData();
        formdata.append('image', $file);
        this.uploadFile(formdata)
      },
      uploadFile(file){
        console.log('uploadFile');
        uploadFile(file).then(res =>{
          console.log('res='+res);
          
        })
      }
    },
    computed: {
      content(){
        return this.$refs.context.returnVal
      },
      //字符串转换为数组
      transformTag(){
        return this.article.tag.split(",")
      }
    },
    created() {
      this.dynamicTags = this.article.tag.split(",")
      const data = this.$route.query.data
      if(data != null){
        this.article = data
      }
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
