<template>
  <div>
    <el-autocomplete
      v-model="state"
      :fetch-suggestions="querySearchAsync"
      placeholder="请输入内容"
      @select="handleSelect"
    />
    <el-card class="box-card" shadow="hover" v-for="item in list" >
      <div slot="header" class="clearfix">
        <span><a href="#" @click=showNote(item.id)>{{item.title}}</a></span>
        <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
      </div>
      <div>
        <div class="tag">
          <el-tag type="danger" size="mini" v-for=" tag in item.tag.split(',') ">{{tag}}</el-tag>
        </div>
      </div>
      <div style="color: #999;">
        <span style="font-size: 14px;">{{item.content | contextSpliter}}</span>
      </div>
    </el-card>
  </div>
</template>

<script>
  import {findNote, delNote} from '@/network/note'

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
        loadAll() {
        return [
          { "value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号" },
        ];
        }
      }
    },
    created() {
      this.findNote()
    },
    methods: {
      findNote(param){
        findNote(param, this.currentPage).then(res =>{
          
          this.list = res.obj.list
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
      }
    },
    mounted() {
      this.restaurants = this.loadAll();
    },
    computed: {
      tags(data){
        console.log(data);
        
        return "JJSS"
      }
    },
    components: {}
    
  }
</script>

<style scoped>
  .box-card{
    margin-top: 20px;
    width: 70%;
    margin: auto;
  }
  .el-autocomplete{
    margin: 0, auto;
  }
  a{
    color: black;
    font-size: large;
    text-decoration: none
  }
  a:hover{
    text-decoration:underline
  }
</style>
