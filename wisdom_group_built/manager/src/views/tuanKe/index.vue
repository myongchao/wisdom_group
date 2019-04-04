<template>
  <div>
    <div class="btn">
      <el-button size="small" type="primary" style="height: 75%;" @click="submitForm()">团课上传</el-button>
    </div>
    <div class="tuanke">
      <el-table :data="tableData">
        <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="100"/>
        <el-table-column
          prop="tuanKeDate"
          align="center"
          value-format="yyyy-MM-dd HH:mm:ss"
          label="团课时间"
          width="160"
        />
        <el-table-column prop="classHour" align="center" label="团课学时" width="140"/>
        <el-table-column prop="place" align="center" label="团课地点" width="140"/>
        <el-table-column prop="theme" align="center" label="团课主题" width="160"/>
        <el-table-column prop="content" align="center" label="团课内容" width="160"/>
        <el-table-column label="操作" align="center" width="160">
          <template slot-scope="scope">
            <!-- <el-button type="info" size="small" icon="el-icon-document" plain @click="detaileLeague(scope.row.id)"/> -->
            <el-button
              type="danger"
              size="small"
              icon="el-icon-delete"
              plain
              @click="deleteTeamclass(scope.$index)"
            />
          </template>
        </el-table-column>
      </el-table>
      <page :page="form.page" @changed="getList"/>
    </div>
  </div>
</template>

<script>
import page from '@/components/page'
import { pageWithTeamclass, deleteTeamclass } from '@/api/teamclass'
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
      pageWithTeamclass(this.searchParams).then(e => {
        if (e.data) {
          this.tableData = e.data.records
          this.form.page.total = e.data.total
        }
      })
    },
    deleteTeamclass(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(_ => {
          deleteTeamclass(this.tableData[index].id).then(e => {
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
      this.$router.push('uploadPublic')
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
.tuanke {
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
