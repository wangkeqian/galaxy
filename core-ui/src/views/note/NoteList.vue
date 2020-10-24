<template>
  <div>
    <search-bar/>
    <div>
      <el-table
      :data="list"
      stripe
      style="width: 100%">
      <el-table-column
        prop="title"
        label="标题"
        width="180">
      </el-table-column>
      <el-table-column
        prop="subtitle"
        label="副标题"
        width="180">
      </el-table-column>
      <el-table-column
        prop="tag"
        label="标签">
      </el-table-column>
      <el-table-column
        prop="gmtCreate"
        label="创建时间">
        <template slot-scope="scope">
          {{scope.row.gmtCreate | dateFormat}}
        </template>
      </el-table-column>
      <el-table-column
        prop="gmtModified"
        label="修改时间">
        <template slot-scope="scope">
          {{scope.row.gmtModified | dateFormat}}
        </template>
    </el-table-column>
      <el-table-column

      width="200"
      align="center"
      fixed="right"
      label="操作">
  <template slot-scope="scope">
      <el-button style="padding: 3px" size="mini">编辑</el-button>
      <el-button style="padding: 3px" size="mini" type="primary" @click=showNote(scope.row)>查看</el-button>
      <el-button style="padding: 3px" size="mini" type="danger">删除</el-button>
  </template>
</el-table-column>
    </el-table>
    </div>
  </div>
</template>

<script>
  import {findNote} from '@/network/note'

  import SearchBar from '@/components/common/searchBar/SearchBar';

  export default {
    name: 'NoteList',
    data() {
      return {
        list: []
      }
    },
    created() {
      this.findNote()
    },
    methods: {
      showNote(data){
        console.log(data);
      },
      findNote(){
        const param = new Object
        param['title'] = ''
        findNote(param).then(res =>{
          this.list.push(...res.obj)
        })
      }
    },
    components: {
      SearchBar
    },
    filters:{
      dateFormat:function(dateStr){
        let df = new Date(dateStr);
        let year = df.getFullYear();
        let month = df.getMonth()+1;
        let day = df.getDate();
        let hours = df.getHours();
        let minuter = df.getMinutes();
        let second = df.getSeconds().toString().padStart(2,'0');
        //return year+'-'+month+'-'+day;
        return `${year}-${month}-${day}  ${hours}:${minuter}:${second}`;
    },
      tofixed(price){
        return '￥'+price.toFixed(2);
      }
  }
  }
</script>

<style scoped>
</style>
