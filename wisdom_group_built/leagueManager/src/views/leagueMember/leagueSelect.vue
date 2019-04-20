<template>
  <div class="teacher-header">
    <div>
      <template>
        <div class="leagueTitle">
          &nbsp;
          <img src="../../assets/用户.png">&nbsp;&nbsp;组织成员——团员
        </div>
      </template>
    </div>
    <div class="manager">
      <el-table :data="tableData" header-cell-style="background-color:#FA4A4A;color:#FFFFFF">
        <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="160"/>
        <el-table-column prop="name" align="center" label="姓名"/>
        <el-table-column prop="national" align="center" label="民族"/>
        <el-table-column prop="phone" align="center" label="手机号码"/>
        <el-table-column prop="position" align="center" label="团内职务"/>
      </el-table>
      <page :page="form.page" @changed="getList"/>
    </div>
  </div>
</template>

<script>
import page from '@/components/page';
import { pageWithLeague, deleteLeague } from '@/api/league';
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
      if (this.form.name !== null) {
        this.searchParams.form = {
          name: this.form.name
        }
      }
      pageWithLeague(this.searchParams).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
        this.tableData = data
      })
    },
    indexMethod(index) {
      return (
        index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
      )
    }
  }
}
</script>

<style scoped>
.teacher-header {
  float: left;
  margin-top: 15px;
}
.leagueTitle {
  width: 100%;
  height: 40px;
  padding-top: 10px;
  float: left;
  background-color: #ffffff;
  border-bottom: 2px solid #e4e4e4;
  color: #d72500;
}
.manager {
  border: 1px solid seashell;
  margin-top: 5px;
  text-align: center;
}
.el-input {
  width: 100%;
}
.operation-button {
  float: right;
}
</style>

