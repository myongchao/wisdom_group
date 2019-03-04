<template>
  <div class="publish">
    <div class="publish-page">
      <el-form ref="form" :model="form" class="demo-form-inline" size="small">
        <el-form-item label="活动类型:" prop="name">
          <el-select v-model="form.name" placeholder="请选择主题团日活动">
            <el-option
              v-for="(item,index) in activities"
              :key="index"
              :label="item.name"
              :value="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="活动时间:" class="dateSelect" prop="tuanKeDate">
          <el-date-picker
            v-model="form.tuanKeDate"
            type="datetime"
            placeholder="选择活动开展时间"/>
        </el-form-item>
        <div class="buttom">
          <el-form-item label="活动地点:" prop="destination">
            <el-input v-model="form.destination" placeholder="请输入4-24有效字数"/>
          </el-form-item>
          <el-form-item label="活动主题:" prop="theme">
            <el-input v-model="form.theme" placeholder="请输入12-24有效字数"/>
          </el-form-item>
          <el-form-item label="活动内容:" prop="textarea">
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
        name: undefined,
        tuanKeDate: undefined,
        destination: undefined,
        theme: undefined,
        textarea: undefined
      },
      value1: '',
      activities: [
        { id: 1, name: '五四主题团日活动' },
        { id: 2, name: '七一主题团日活动' },
        { id: 3, name: '十一主题团日活动' },
        { id: 4, name: '自定义主题团日活动' }
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
