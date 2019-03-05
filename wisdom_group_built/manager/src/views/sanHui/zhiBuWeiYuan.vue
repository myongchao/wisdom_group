<template>
  <div>
    <div class="btn"><el-button size="small" type="primary" style="height: 75%;" @click="submitForm()">会议发布</el-button></div>
    <div class="zhiBuTuanYuan"><el-table
      :data="tableData"
    >
      <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="160"/>
      <el-table-column prop="title" align="center" label="会议类型" width="160"/>
      <el-table-column prop="theme" align="center" label="会议主题" width="160"/>
      <el-table-column prop="meetDate" align="center" label="会议时间" width="160"/>
      <el-table-column prop="place" align="center" label="会议地点" width="160"/>
      <el-table-column prop="content" align="center" label="会议内容" width="160"/>
      <el-table-column
        label="操作"
        align="center"
      >
        <template slot-scope="scope">
          <!-- <el-button type="info" size="small" icon="el-icon-document" plain @click="detaileLeague(scope.row.id)"/> -->
          <el-button type="danger" size="small" icon="el-icon-delete" plain @click="deleteMeet(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
      <page :page="form.page" @changed="getList"/>
    </div>
  </div>
</template>

<script>
import page from '@/components/page'
import {
  pageWithMeeting, deleteMeet } from '@/api/meetting'
export default {
  components: {
    page
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
        search: '',
        orderBy: 'id desc'
      },
      loading: false, // 加载
      tableData: [],
      multipleSelection: [], // 选中的id
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
        type: 2
      }
      pageWithMeeting(this.searchParams).then(e => {
        if (e.data) {
          this.tableData = e.data.records
          this.form.page.total = e.data.total
        }
      })
    },
    deleteMeet(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        deleteMeet(this.tableData[index].id).then(e => {
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
    submitForm() {
      this.$router.push('publish')
    }
  }
}
</script>

<style scoped>
 .btn{
   position: relative;
   left: 80px;
   top: 30px;
 }
 .zhiBuTuanYuan{
     width: 88%;
     margin-left: 80px;
     margin-top: 50px;
     text-align: center;
     font-size: 20px;
     font-family: inherit;
     color: rgb(131, 117, 117);
     background-color: #fff;
 }
</style>
