<template>
  <div class="teacher-header">
    <div class="components-container">
      <template>
        <el-form ref="form" :inline="true" :model="form" size="small" class="demo-form-inline">
          <!-- <el-form-item label="团干部名称:" prop="name">
            <el-input v-model="form.name" placeholder="输入团干部名称"/>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-search" type="primary" style="height: 75%;" @click="getList()">搜索</el-button>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-refresh" type="primary" style="height: 75%;" @click="resetForm('form')">重置</el-button>
          </el-form-item> -->
          <div class="operation-button">
            <el-form-item>
              <el-button type="primary" style="height: 75%;" @click="gotoManager()">添加管理员</el-button>
            </el-form-item>
          </div>
        </el-form>
      </template>
    </div>
    <div class="manager">
      <el-table :data="tableData">
        <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="160"/>
        <el-table-column prop="name" align="center" label="管理员" width="160"/>
        <el-table-column prop="position" align="center" label="团内职务" width="160"/>
        <el-table-column prop="tuanGanXZ" align="center" label="团干部性质" width="160"/>
        <el-table-column prop="status" align="center" label="状态" width="160"/>
        <el-table-column
          label="操作"
          align="center"
        >
          <template slot-scope="scope">
            <el-button type="info" size="small" icon="el-icon-document" plain @click="detaileLeague(scope.row.id)"/>
            <el-button type="primary" size="small" icon="el-icon-edit" plain @click="editLeague(scope.row.id)"/>
            <el-button type="danger" size="small" icon="el-icon-delete" plain @click="deleteLeague(scope.$index)"/>
          </template>
        </el-table-column>
      </el-table>
      <page :page="form.page" @changed="getList"/>
      <edit-league ref="edit" @success="getList"/>
      <detaile-league ref="detail" @success="getList"/>
    </div>
  </div>
</template>

<script>
import page from '@/components/page'
import editLeague from '../leagueMember/components/editLeague'
import detaileLeague from '../leagueMember/components/detaileLeague'
import {
  pageWithLeagueWithGanbu, deleteLeague } from '@/api/league'
export default {
  components: {
    page,
    editLeague,
    detaileLeague
  },
  data() {
    return {
      // 分页和模糊查询
      form: {
        page: {
          current: 1,
          pageCount: 10,
          total: 0
        },
        isAdmin: null,
        search: '',
        orderBy: 'id desc'

      },
      loading: false, // 加载
      tableData: [],
      searchParams: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.searchParams.page = this.form.page
      this.searchParams.form = {
        isAdmin: '是'
      }
      if (this.form.name !== null) {
        this.searchParams.form = {
          name: this.form.name,
          isAdmin: '是'
        }
      }

      pageWithLeagueWithGanbu(this.searchParams).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
        this.tableData = data
      })
    },
    editLeague(id) {
      this.$refs.edit.open(id)
    },
    detaileLeague(id) {
      this.$refs.detail.open(id)
    },
    addLeague() {
      this.$router.push('addLeague')
    },
    importData() {
      this.$refs.imp.open()
    },
    // 实现导入功能
    onImportSuccess(e) {
      if (e) {
        this.getList()
      }
    },
    deleteLeague(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        deleteLeague(this.tableData[index].id).then(e => {
          if (e.success) {
            this.getList()
            this.form.page.total--
            this.$message({
              type: 'success',
              message: '删除成功！'
            })
          } else {
            this.$message({
              type: 'error',
              message: '删除失败！'
            })
          }
        })
      }).catch(_ => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    resetForm(reformName) {
      this.$refs[reformName].resetFields()
    },
    gotoManager() {
      this.$router.push('admin')
    },
    indexMethod(index) {
      return index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
    }
  }

}
</script>

<style scoped>
   .teacher-header{
       margin-top: 15px;
   }
  .components-container{
    margin-left: 10px;
  }
    .manager{
       /* border: 1px solid seashell; */
       margin: 15px;
       text-align: center
    }
    .el-input {
    width: 100%;
   }
    .operation-button{
      float:right
    }
</style>

