<template>
  <div class="changePosition">
    <div class="contain">
      <el-form :inline="true" :model="form" size="small" class="demo-form-inline">
        <el-form-item label="委派职务:">
          <el-select v-model="form.position" placeholder="请选择职务">
            <el-option v-for="(item,index) in positionList" :key="index" :label="item.position" :value="item.position"/>
          </el-select>
        </el-form-item><br>
        <el-form-item label="拟任职人:">
          <el-select v-model="form.name" placeholder="请选择任职人">
            <el-option v-for="(item,index) in leagueList" :key="index" :label="item.name" :value="item.name"/>
          </el-select>
        </el-form-item><br>
        <div class="btn">
          <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button type="danger" @click="goBack">返回</el-button>
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { getLeagueList, editLeagueGanbu
} from '@/api/league'
export default {
  data() {
    return {
      form: {
        name: '', // 姓名
        position: '' // 职务
      },
      leagueList: [], // 团员列表
      positionList: [
        { id: 1, position: '书记' },
        { id: 2, position: '副书记' },
        { id: 3, position: '组织委员' },
        { id: 4, position: '宣传委员' },
        { id: 5, position: '文体委员' },
        { id: 6, position: '无职务' }
      ]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      getLeagueList().then(e => {
        this.leagueList = e.data
      })
    },
    onSubmit() {
      editLeagueGanbu(this.form).then(e => {
        if (e.success) {
          this.$message({
            message: '团干部职务变更成功',
            type: 'success'
          })
          this.close()
        }
      })
    },
    goBack() {
      this.$router.push('/')
    },
    close() {
      this.$refs.form.reset()
    }
  }
}
</script>

<style scoped>
  .changePosition{
    width: 100%;
    height: 300px;
    margin: 10px;
    background-color: #fff;
  }
  .contain{
      padding: 20px;
  }
  .btn{
      margin-top: 30px;
      margin-left: 30px;
  }
</style>
