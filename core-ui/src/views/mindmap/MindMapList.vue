<template>
  <div>
    <search-bar :placeholder='placeholder'
                @search="search"
                :optionTypeName='optionTypeName'
                :optionRoutePath='optionRoutePath'/>
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
      prop="gmtCreate"
      label="创建时间"
      width="170">
      <template slot-scope="scope">
        {{scope.row.gmtCreate | dateFormat}}
      </template>
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
        <el-button style="padding: 3px" size="mini" type="primary" @click=showMindMap(scope.row,false)>查看</el-button>
        <el-button style="padding: 3px" size="mini" @click=showMindMap(scope.row,true)>编辑</el-button>
        <el-button style="padding: 3px" slot="reference" size="mini" type="danger"  @click=delNote(scope.row.id,scope.$index)>删除</el-button>
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
  import { findMindMap } from '@/network/mindMap'
  import SearchBar from '@/components/common/searchBar/SearchBar';
  export default {
    name: 'MindMapList',
    data() {
      return {
        list: [],
        total: 0,
        currentPage: 1,
        pageSize: 10,
        loading: false,
        placeholder: '请输入标题关键字',
        optionTypeName: '添加思维导图',
        optionRoutePath: '/index/mindMapEdit'
      }
    },
    created() {

    },
    methods: {
      currentChange(){

      },
      sizeChange(){

      },
      search(){
        this.findMindMap()
      },
      findMindMap(){
        const queryVo = new Object()
        queryVo['title'] = this.title
        queryVo['page'] = this.currentPage
        queryVo['size'] = this.pageSize
        findMindMap(queryVo).then(res =>{
          if(res.status == 200){
            this.list = res.obj.list
          }
          
        })
      },
      showMindMap(data,isEdit){
        this.$router.push({
         path: '/index/mindMapEdit',
         // name: 'mallList',
         query: {
            data,
            isEdit
         }
        })
      },
    
    },
    components: {
      SearchBar
    }
  }
</script>

<style scoped>
</style>
