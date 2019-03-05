<template>
  <div class="publish">
    <div class="publish-page">
      <el-form ref="form" :model="form" class="demo-form-inline" size="small">
        <el-form-item label="会议类型:" prop="title">
          <el-select v-model="form.title" placeholder="请选择">
            <el-option
              v-for="(item,index) in meetinges"
              :key="index"
              :label="item.title"
              :value="item.title"/>
          </el-select>
        </el-form-item>
        <el-form-item label="会议时间:" class="dateSelect" prop="meetDate">
          <el-date-picker
            v-model="form.meetDate"
            type="datetime"
            placeholder="选择日期"/>
        </el-form-item>
        <div class="buttom">
          <el-form-item label="会议地点:" prop="place">
            <el-input v-model="form.place" placeholder="请输入4-24有效字数"/>
          </el-form-item>
          <el-form-item label="会议主题:" prop="theme">
            <el-input v-model="form.theme" placeholder="请输入12-24有效字数"/>
          </el-form-item>
          <el-form-item label="会议内容:" prop="content">
            <el-input
              :rows="5"
              v-model="form.content"
              class="text"
              type="textarea"
              placeholder="请输入24-8000有效字数"/>
          </el-form-item>
          <el-form-item class="btn">
            <el-button :plain="true" type="primary" @click="submitForm()">提交</el-button>
            <el-button class="btn" @click="resetForm('form')">重置</el-button>
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import {
  addMeet } from '@/api/meetting'
export default {
  data() {
    return {
      form: {
        title: undefined,
        huiYiDate: undefined,
        destination: undefined,
        theme: undefined,
        textarea: undefined
      },
      value1: '',
      meetinges: [
        { id: 1, title: '支部团员大会' },
        { id: 2, title: '支部委员会大会' },
        { id: 3, title: '团小组会' }
      ]
    }
  },
  methods: {
    submitForm() {
      addMeet(this.form).then(e => {
        if (e.success) {
          this.$message({
            message: '提交成功',
            type: 'success'
          })
          this.close()
        }
      })
    },
    resetForm(formName) {
      debugger
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
      width: 64%
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
