<template>
  <div>
    <div class="btn"><el-button size="small" type="primary" style="height: 75%;" @click="submitForm()">会议发布</el-button></div>
    <div class="zhiBuTuanYuan">当前组织没有录入会议！</div>
  </div>
</template>

<script>
export default {
  data() {
    return {

    }
  },
  methods: {
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
     height: 200px;
     margin-left: 80px;
     margin-top: 50px;
     text-align: center;
     line-height: 200px;
     font-size: 20px;
     font-family: inherit;
     color: rgb(131, 117, 117);
     background-color: #fff;
 }
</style>
