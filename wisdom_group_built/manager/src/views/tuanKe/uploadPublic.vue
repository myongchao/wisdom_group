<template>
  <div class="publish">
    <div class="publish-page">
      <el-form ref="form" :model="form" class="demo-form-inline" size="small">
        <el-form-item label="团课时间:" class="dateSelect" prop="tuanKeDate">
          <el-date-picker
            v-model="form.tuanKeDate"
            type="datetime"
            placeholder="选择团课召开时间"/>
        </el-form-item>
        <div class="buttom">
          <el-form-item label="团课学时:" prop="title">
            <el-select v-model="form.title" placeholder="请选择团课学时">
              <el-option
                v-for="(item,index) in schedules"
                :key="index"
                :label="item.title"
                :value="item.title"/>
            </el-select>
          </el-form-item>
          <el-form-item label="团课地点:" prop="destination">
            <el-input v-model="form.destination" placeholder="请输入4-24有效字数"/>
          </el-form-item>
          <el-form-item label="团课主题:" prop="theme">
            <el-input v-model="form.theme" placeholder="请输入12-24有效字数"/>
          </el-form-item>
          <el-form-item label="团课内容:" prop="textarea">
            <el-input
              :rows="5"
              v-model="form.textarea"
              class="text"
              type="textarea"
              placeholder="请输入24-8000有效字数"/>
          </el-form-item>
          <el-form-item class="btn">
            <el-button :plain="true" type="primary" @click="submitForm('form')">提交</el-button>
            <el-button class="btn" @click="resetForm('form')">重置</el-button>
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        title: undefined,
        tuanKeDate: undefined,
        destination: undefined,
        theme: undefined,
        textarea: undefined
      },
      value1: '',
      schedules: [
        { id: 1, title: '1学时' },
        { id: 2, title: '2学时' },
        { id: 3, title: '3学时' },
        { id: 4, title: '4学时' }
      ]
    }
  },
  methods: {
    submitForm(formName) {
      this.$message({
        message: '提交成功',
        type: 'success'
      })
      this.close()
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    close() {
      this.$refs.form.resetFields()
    }
  }
}
</script>

<style scoped>
  .publish{
      width: 100%;
      height: 500px;
      margin: 20px;
      background-color: #fff;
  }
  .publish-page{
      padding-top:40px;
      padding-left: 100px;
      /* padding-right: 50px; */
  }
  .el-select{
      width: 80%
  }
  .dateSelect{
    width: 80%;
  }
  .buttom,.el-input{
      width: 80%;
  }
  .text{
      width: 80%;
  }
  .btn{
    margin-top:35px;
    margin-left: 160px;
  }
</style>
