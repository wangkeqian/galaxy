<template>
  <div>
    <div class="box-card">
      <el-card  shadow="hover" v-for="(item,index) in list" >
        <div slot="header" class="clearfix">
          <span><a href="#" @click=showNote(item.id)>{{item.title}}</a></span>
          <el-button :ref="'article_'+index" style="float: right; padding: 3px 0" type="text" @click='collct(item,$event,index)' >
            <span v-if="item.collectIs == 0">收藏</span>
            <span v-else >已收藏</span>
          </el-button>
        </div>
        <div>
          <div class="tag">
            <el-tag :type="tagColor" size="mini" v-for=" tag in item.tag.split(',') "> {{tag}}  </el-tag> 
            <a href="#" @click="intoUserProfile(item.creator)">
              <el-tag
              :key="item.id"
              type="warning"
              size="mini"
              style="float: right;"
              effect="plain">
              作者：{{ item.author }}
              </el-tag>
            </a> 
          </div>
        </div>
        <div style="color: #999;">
          <span style="font-size: 14px;">{{item.content | contextSpliter}}</span>
        </div>
        <div class="pvrg">
          <icon class='el-icon-thumb'></icon>: {{item.pageView}}
          <el-rate style="float: left;"
          v-model="item.articleGrade"
          :disabled=true>
        </el-rate>
        </div>
      </el-card>
    </div>
    <div class="ranking-list">
      <el-card  shadow="hover">
        <p>热门排行榜</p>
      </el-card>
      <el-card  shadow="hover" v-for="item in hotRankingList" >
        <span><a href="#" @click=showNote(item.id)>{{item.title | titleSpliter}}</a></span>
      </el-card>
    </div>
  </div>
</template>

<script>
  import {homePageNote, 
          delNote, 
          hotRankingList,
          articleCollect} from '@/network/note'
  import { send } from '@/network/ws'
  export default {
    name: 'Home',
    data() {
      return {
        total: 0,
        currentPage: 1,
        pageSize: 10,
        restaurants: [],
        state: '',
        timeout:  null,
        list: [],
        hotRankingList:[],
        isCollect: false,
        loadAll() {
        return [
          { "value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号" },
        ];
        }
      }
    },
    created() {
      this.findNote()
      this.findHotRankingList()
    },
    methods: {
      findNote(){
        homePageNote(this.currentPage).then(res =>{
          this.list = res.obj.list
        })
      },
      findHotRankingList(){
        hotRankingList().then(res =>{
          this.hotRankingList = res.obj;
          
        })
      },
      querySearchAsync(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          cb(results);
        }, 3000 * Math.random());
      },
      createStateFilter(queryString) {
        return (state) => {
          return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      handleSelect(item) {
        console.log(item);
      },
      showNote(id){
        const routeUrl = this.$router.resolve({
          path: '/note',
          query: {id: id}
        })
        window.open(routeUrl.href, '_blank')
      },
      intoUserProfile(uid){
        const routeUrl = this.$router.resolve({
          path: '/index/profile',
          query: {id: uid}
        })
        window.open(routeUrl.href, '_blank')
      },
      /**
       * 收藏
       */
      collct(item,event,index){
        articleCollect(item.id).then(res =>{
          item.collectIs = !item.collectIs;
          this.$message({
            message: res.msg
        });
        })
      }
    },
    mounted() {
      this.restaurants = this.loadAll();
    },
    computed: {
      tagColor(){
        let randomNum =Math.ceil(Math.random()*10)

        return randomNum <= 3 ? 'success' : randomNum <=6 ? 'danger' : ''
      }
    },
    components: {}
    
  }
</script>

<style scoped>
  .box-card{
    /* margin-top: 20px; */
    width: 60%;
    margin-left: 10%;
    float: left;
  }
  .el-autocomplete{
    margin: 0, auto;
  }
  .box-card a{
    color: black;
    font-size: large;
    text-decoration: none
  }
  .ranking-list{
    width: 200px;
    float: left;
    margin-left: 50px;

  }
  .ranking-list a{
    color: black;
    font-size: small;
    text-decoration: none
  }
  a:hover{
    text-decoration:underline
  }
  .pvrg{
    float: right;
  }
</style>
