<template>
  <div class='main'>
    <el-container>
      <el-header>
        <HeaderBar></HeaderBar>
      </el-header>
    </el-container>
    <div class='view-note'>
      <div class='edit-header'>
        <el-row>
          <span class='title'>{{article.title}}</span>
        </el-row>
        <el-row style="margin-top: 5px;">
          <span class='subtitle'>导语 : {{article.subtitle}}</span>
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
        <el-row>
          <el-divider  content-position="right">
            <span style="color: gray;font-size:larger;"><a href="#" @click="intoUserProfile">{{creator}}</a> </span>
            <span style="color: gray;"> {{gmtCreate}}</span>
          </el-divider>
        </el-row>
      </div>
      <markdown-plugin :subfield='subfield'
                       :toolbarsFlag='false'
                       :editable='editable'
                       defaultOpen='preview'
                       :article='article'/>
    </div>
  </div>
</template>

<script>

  import MarkdownPlugin from '@/components/common/plugins/MarkdownPlugin'
  import HeaderBar from '@/components/common/header/HeaderBar'
  import {findNoteById} from '@/network/note'

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
        id: '',
        subfield: false, // Markdown 编辑/阅览
        toolbarsFlag: false,
        editable: false,

      }
    },
    methods: {
      init(id){
        this.findNoteById(id)
      },
      intoUserProfile(){
        const routeUrl = this.$router.resolve({
          path: '/index/profile',
          query: {id: this.article.creator}
        })
        window.open(routeUrl.href, '_blank')
      },
      findNoteById(id){
        findNoteById(id).then(res =>{
          this.article = res.obj
          
        })
      },
      dateFormat(dateStr){
        let df = new Date(dateStr);
        let year = df.getFullYear();
        let month = df.getMonth()+1;
        let day = df.getDate();
        let hours = df.getHours();
        let minuter = df.getMinutes();
        let second = df.getSeconds().toString().padStart(2,'0');
        //return year+'-'+month+'-'+day;
        return `${year}-${month}-${day}  ${hours}:${minuter}:${second}`;
    }
    },
    computed: {
      //字符串转换为数组
      transformTag(){
        return this.article.tag.split(",")
      },
      //格式化日期
      gmtCreate(){
        return this.dateFormat(this.article.gmtCreate)
      },
      creator(){
        return this.article.creator != null ? this.article.author:'佚名'
      }
    },
    mounted() {
      this.id = this.$route.query.id
      this.init(this.id)
    },

    components: {
      MarkdownPlugin,
      HeaderBar
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
  .view-note{
    width: 70%;
    margin: auto;
    
  }
  .edit-header{
    background-color: white;
  }
  .main{
    background-color:#F7F7F7;
    height: 100%;
  }
  .title{
    font-size: 30px;
    font-family: 'PingFang SC'
  }
  .subtitle{
    margin-top: 40px;
    font-size: 15px;
    color: gray;
  }
</style>
