<template>
  <div>
    <div class="zhiBuTuanYuan">
      <el-table :data="tableData">
        <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="100"/>
        <el-table-column prop="name" align="center" label="活动类型"/>
        <el-table-column prop="theme" align="center" label="活动主题"/>
        <el-table-column prop="activityDate" align="center" label="活动时间"/>
        <el-table-column prop="place" align="center" label="活动地点"/>
        <el-table-column prop="content" align="center" label="活动内容"/>
      </el-table>
      <page :page="form.page" @changed="getList"/>
    </div>
  </div>
</template>

<script>
import page from '@/components/page';
import { pageWithActivity, deleteActivity } from '@/api/activity';
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
      searchParams: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.searchParams.page = this.form.page
      pageWithActivity(this.searchParams).then(e => {
        if (e.data) {
          this.tableData = e.data.records
          this.form.page.total = e.data.total
          for (var i = 0; i < this.tableData.length; i++) {
            this.tableData[i].activityDate = timeFormat(
              this.tableData[i].activityDate
            )
          }
        }
      })
    },

    deleteActivity(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(_ => {
          deleteActivity(this.tableData[index].id).then(e => {
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
        })
        .catch(_ => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    submitForm() {
      this.$router.push('activity')
    }
  }
}
</script>

<style scoped>
.btn {
  position: relative;
  left: 80px;
  top: 30px;
}
.zhiBuTuanYuan {
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
