<template>
  <div>
    <search-bar :placeholder='placeholder'
                @search="search"/>
    <div>
      <el-table
        :data="list"
        stripe
        border
        v-loading="loading"
        
        element-loading-text="正在加载..."
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        style="width: 100%">
      <el-table-column
        fixed
        align="center"
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
      prop="subtitle"
      label="副标题"
      width="180">
    </el-table-column>
      <el-table-column
        prop="tag"
        label="标签"
        width="150">
      </el-table-column>
      <el-table-column
        prop="creator"
        label="作者"
        width="100">
      </el-table-column>
      <el-table-column
        prop="gmtCreate"
        label="创建时间"
        width="170">
        <template slot-scope="scope">
          {{scope.row.gmtCreate | dateFormat}}
        </template>
      </el-table-column>
      <el-table-column
        prop="gmtModified"
        label="修改时间"
        width="170">
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
        <el-button style="padding: 3px" size="mini" type="primary" @click=showNote(scope.row)>查看</el-button>
        <el-button style="padding: 3px" size="mini">编辑</el-button>
        <el-button style="padding: 3px" size="mini" type="danger">删除</el-button>
      </template>
      </el-table-column>
    </el-table>
    <div style="display: flex;justify-content: flex-end">
      <el-pagination style="float:right"
              background
              @current-change="currentChange"
              @size-change="sizeChange"
              layout="sizes, prev, pager, next, jumper, ->, total, slot"
              :total="total">
      </el-pagination>
  </div>
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
        list: [],
        total: 0,
        currentPage: 1,
        pageSize: 10,
        loading: false,
        placeholder: '请输入标题关键字'
      }
    },
    created() {
      this.findNote()
    },
    methods: {
      search(key){
        this.findNote(key)
      },
      showNote(data){
        const routeUrl = this.$router.resolve({
          path: '/note',
          query: {id: data.id}
        })
        window.open(routeUrl.href, '_blank')
      },
      findNote(param){
        if(typeof(param) == 'string'){
          param = 'title='+param
        }
        findNote(param, this.currentPage).then(res =>{
          const obj = res.obj
          this.list = obj.list
          this.total = obj.total
          this.currentPage = obj.pageNum
        })
      },
      sizeChange(){
        console.log('sizeChange');
      },
      currentChange(currentPage){
        this.currentPage = currentPage
        console.log('currentPage='+currentPage);
        this.findNote()
      },
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
