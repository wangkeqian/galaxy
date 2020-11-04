<template>
  <div>
    <div style="display: flex;justify-content: space-between">
      <div>
          <el-input :placeholder="getPlaceholder" prefix-icon="el-icon-search"
                    clearable
                    @clear="search"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                    @keydown.enter.native="search" :disabled="showAdvanceSearchView"></el-input>
          <el-button icon="el-icon-search" type="primary" @click="search" :disabled="showAdvanceSearchView">
              搜索
          </el-button>
          <el-button type="primary" @click="showAdvanceSearchView = !showAdvanceSearchView">
              <i :class="showAdvanceSearchView?'fa fa-angle-double-up':'fa fa-angle-double-down'"
                 aria-hidden="true"></i>
              高级搜索
          </el-button>
      </div>
      <div>
          <el-upload 
                    action="/employee/basic/import"
                    style="display: inline-flex;margin-right: 8px">
              <el-button   type="success" :icon="importDataBtnIcon">
                  {{importDataBtnText}}
              </el-button>
          </el-upload>
          <el-button type="success" icon="el-icon-download">
              导出数据
          </el-button>
          <el-button type="primary" icon="el-icon-plus" @click="optionClick">
              {{optionTypeName}}
          </el-button>
          
      </div>
  </div>
  <transition name="slide-fade">
    <div v-show="showAdvanceSearchView"
         style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
        <el-row>
            <el-col :span="5">
                政治面貌:
                <el-select v-model="searchValue.politicId" placeholder="政治面貌" size="mini"
                           style="width: 130px;">
                    <el-option
                            v-for="item in politicsstatus"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="4">
                民族:
                <el-select v-model="searchValue.nationId" placeholder="民族" size="mini"
                           style="width: 130px;">
                    <el-option
                            v-for="item in nations"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="4">
                职位:
                <el-select v-model="searchValue.posId" placeholder="职位" size="mini" style="width: 130px;">
                    <el-option
                            v-for="item in positions"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="4">
                职称:
                <el-select v-model="searchValue.jobLevelId" placeholder="职称" size="mini"
                           style="width: 130px;">
                    <el-option
                            v-for="item in joblevels"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="7">
                聘用形式:
                <el-radio-group v-model="searchValue.engageForm">
                    <el-radio label="劳动合同">劳动合同</el-radio>
                    <el-radio label="劳务合同">劳务合同</el-radio>
                </el-radio-group>
            </el-col>
        </el-row>
        <el-row style="margin-top: 10px">
            <el-col :span="5">
                所属部门:
                <el-popover
                        placement="right"
                        title="请选择部门"
                        width="200"
                        trigger="manual"
                        v-model="popVisible2">
                    <el-tree default-expand-all :data="allDeps" :props="defaultProps"
                             @node-click="searvhViewHandleNodeClick"></el-tree>
                    <div slot="reference"
                         style="width: 130px;display: inline-flex;font-size: 13px;border: 1px solid #dedede;height: 26px;border-radius: 5px;cursor: pointer;align-items: center;padding-left: 8px;box-sizing: border-box;margin-left: 3px"
                         @click="showDepView2">{{inputDepName}}
                    </div>
                </el-popover>
            </el-col>
            <el-col :span="10">
                入职日期:
                <el-date-picker
                        v-model="searchValue.beginDateScope"
                        type="daterange"
                        size="mini"
                        unlink-panels
                        value-format="yyyy-MM-dd"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                </el-date-picker>
            </el-col>
            <el-col :span="5" :offset="4">
                <el-button size="mini">取消</el-button>
                <el-button size="mini" icon="el-icon-search" type="primary" @click="initEmps('advanced')">搜索</el-button>
            </el-col>
        </el-row>
    </div>
  </transition>

  </div>
</template>

<script>

  
  export default {
    name: 'SearchBar',
    props: {
      nations: Array,
      joblevels: Array,
      politicsstatus: Array,
      positions: Array,
      editData: Object,
      placeholder:String,
      optionTypeName: {
        type: String,
        default(){
          return '添加'
        }
      },
      optionRoutePath: String
    },
    data() {
      return {
        showAdvanceSearchView: false,
        importDataBtnIcon: 'el-icon-upload2',
        keyword: "",
        importDataBtnText: '',
        searchValue: {
                    politicId: null,
                    nationId: null,
                    jobLevelId: null,
                    posId: null,
                    engageForm: null,
                    departmentId: null,
                    beginDateScope: null
        },
        title: '新增奖惩记录',
        dialogVisible:false,
        popVisible2: false,
        tiptopDegrees: ['本科', '大专', '硕士', '博士', '高中', '初中', '小学', '其他'],
        allDeps: [],
        inputDepName: '所属部门',
        defaultProps: {
                    children: 'children',
                    label: 'name'
        },
      }
    },
    created() {
      this.initEmps()
    },
    methods: {
      initEmps(){
        this.search()
      },
      search(){

        this.$emit('search',this.keyword)

      },
      searvhViewHandleNodeClick(){

      },
      showDepView2(){

      },
      setDialogVisible(){
        this.dialogVisible = true
      },
      exitDetail(elm){
        this.dialogVisible = false
        this.search()
      },
      optionClick(){
        this.$router.push({
         path: this.optionRoutePath,
         // name: 'mallList',
         query: {
        
         }
        })
      }
    },
    computed: {
      getPlaceholder(){
        return this.placeholder
      }
    },
    components: {
      
      
    },
    watch: {
      'editData':function(newVal){
        this.dialogVisible = true
      }
    },
  }
</script>

<style scoped>
</style>
