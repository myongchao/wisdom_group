<template>
  <div>
    <div class="heager">添加管理员</div>
    <div class="admin-page">
      <div ref="form" :v-model="form">
        <table class="table-page">
          <tr>
            <td class="admin">管理员变更的组织</td>
            <td class="admin-text">2015级计算机科学与技术1班团支部</td>
            <td class="admin">添加管理员*</td>
            <td>
              <el-select v-model="form.id" placeholder="请选择" @change="changeName">
                <el-option v-for="(item,index) in leagueManagers" :key="index" :label="item.name" :value="item.id" />
              </el-select>
            </td>
          </tr>
          <tr class="tuangan">
            <td class="admin">团内现任职务</td>
            <td class="admin-text">{{ form.position }}</td>
            <td class="admin">团干部性质</td>
            <td class="admin-text">
              {{ form.tuanGanXZ }}
            </td>
          </tr>
          <tr>
            <td colspan="4">
              <div class="btn">
                <el-button size="small" type="primary" style="height: 75%;" @click="submitForm()">提交</el-button>
                <el-button size="small" type="danger" style="height: 75%;" @click="goBack()">返回</el-button>
              </div>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { getManagerList, fetchOne, addManager } from '@/api/league'
export default {
  data() {
    return {
      form: {
        id: undefined,
        position: undefined,
        tuanGanXZ: undefined
      },
      loading: false,
      leagueManagers: [],
      entity: undefined
    }
  },
  created() {
    this.getManagerList()
  },
  methods: {
    getManagerList() {
      getManagerList().then(e => {
        this.leagueManagers = e.data
      })
    },
    changeName() {
      fetchOne(this.form.id).then(e => {
        this.entity = e.data
        this.form.position = this.entity.position
        this.form.tuanGanXZ = this.entity.tuanGanXZ
        console.log(this.entity)
      })
    },
    submitForm() {
      addManager(this.form).then(e => {
        if (e.success) {
          this.$message({
            type: 'success',
            message: '添加成功！'
          })
        }
      })
    },
    goBack() {
      this.$router.push('changeAdmin')
    }
  }
}
</script>

<style scoped>
.heager{
 position: relative;
 text-align: center;
 top: 70px;
 /* transform: translate3d(-50%,-50%,0); */
 color: red;
 font-size: 20px;
}
.admin-page{
    margin-top: 120px;
    margin-left: 80px;
    background-color: #fff;
    width: 85%;
    height: 300px;
}
.table-page{
    margin-left: 80px;
    padding: 50px;
    font-size: 14px;
}
.admin{
    width: 200px;
    text-align: center;
    background-color: #F3F3F3;
    border: 1px solid #F3F3F3;
}
.admin-text{
    border: 1px solid #F3F3F3;
    text-align: center;
    width: 300px;
    height: 40px;
}
 .el-row {
    margin-top:20px;
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
    /* height: 140px; */
  }
  .grid{
      background-color: #F3F3F3;
      line-height: 45px;
      text-align: center;
      border-radius: 4px;
      height: 45px;
  }
  .grid-content {
    height:10px;
    text-align: center;
    font-size: 10px;
  }
  .tuangan{
      height: 40px;
      text-align: center
  }
 .btn{
     margin-top: 50px;
     margin-left: 260px;
 }
</style>
