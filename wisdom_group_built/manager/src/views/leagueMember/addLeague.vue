<template>
  <div class="header">
    <div class="components-container">
      <div class="submit">
        <template>
          <el-form ref="form" :model="form" :rules="rules" class="demo-form-inline" size="small">
            <el-form-item label="团员名称:" prop="name">
              <el-input v-model="form.name"/>
            </el-form-item>
            <el-form-item label="身份证号:" prop="idNumber">
              <el-input v-model="form.idNumber"/>
            </el-form-item>
            <el-form-item label="所属民族:" prop="national">
              <el-input v-model="form.national"/>
            </el-form-item>
            <el-form-item label="政治面貌:" prop="politicalLandscape">
              <el-input v-model="form.politicalLandscape"/>
            </el-form-item>
            <el-form-item label="文化程度:" prop="education">
              <el-input v-model="form.education"/>
            </el-form-item>
            <el-form-item label="手机号码:" prop="phone">
              <el-input v-model="form.phone"/>
            </el-form-item>
            <el-form-item label="入团时间:" prop="leagueTime">
              <el-date-picker
                v-model="form.leagueTime"
                type="date"
                placeholder="选择入团日期"/>
            </el-form-item>
            <el-form-item label="QQ号码:" prop="qq">
              <el-input v-model="form.qq"/>
            </el-form-item>
            <el-form-item label="是否团干:" prop="tuanGan">
              <el-select v-model="form.tuanGan" placeholder="请选择团干">
                <el-option v-for="(item,index) in istuanGan" :key="index" :label="item.tuanGan" :value="item.tuanGan"/>
              </el-select>
            </el-form-item>
            <el-form-item label="团干性质:" prop="tuanGanXZ">
              <el-select v-model="form.tuanGanXZ" placeholder="请选择团干性质">
                <el-option v-for="item in tuanGanXZES" :label="item.tuanGanXZ" :value="item.tuanGanXZ" :key="index"/>
              </el-select>
            </el-form-item>
            <el-form-item label="现任职务:" prop="position">
              <el-select v-model="form.position" placeholder="请选择职务">
                <el-option v-for="(item,index) in positionList" :key="index" :label="item.position" :value="item.position"/>
              </el-select>
            </el-form-item>
            <el-form-item label="是否为党员:" prop="partyMember">
              <el-select v-model="form.partyMember" placeholder="请选择党员">
                <el-option v-for="(item,index) in istuanGan" :key="index" :label="item.tuanGan" :value="item.tuanGan"/>
              </el-select>
            </el-form-item>
            <el-form-item class="btn">
              <el-button :plain="true" type="primary" @click="submitForm('form')">提交</el-button>
              <el-button @click="resetForm('form')">重置</el-button>
            </el-form-item>
          </el-form>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import { addLeague } from '@/api/league'
export default {
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
      istuanGan: [
        { tuanGan: '是' },
        { tuanGan: '否' }
      ],
      tuanGanXZES: [
        { tuanGanXZ: '专职团干' },
        { tuanGanXZ: '兼职团干' },
        { tuanGanXZ: '挂职团干' }
      ],
      positionList: [
        { id: 1, position: '书记' },
        { id: 2, position: '副书记' },
        { id: 3, position: '组织委员' },
        { id: 4, position: '宣传委员' },
        { id: 5, position: '文体委员' },
        { id: 6, position: '无职务' }
      ],
      rules: {
        name: [
          { required: true, message: '请输入班级名称', trigger: 'blur' }
        ],
        idNumber: [
          { required: true, message: '请输入身份证号码', trigger: 'blur' }
        ],
        national: [
          { required: true, message: '请输入民族', trigger: 'blur' }
        ],
        politicalLandscape: [
          { required: true, message: '请输入政治面貌', trigger: 'blur' }
        ],
        education: [
          { required: true, message: '请输入文化程度', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入联系方式', trigger: 'blur' }
        ],
        leagueTime: [
          { required: true, message: '请输入入团时间', trigger: 'blur' }
        ],
        qq: [
          { required: true, message: '请输入qq号码', trigger: 'blur' }
        ],
        tuanGan: [
          { required: true, message: '请输入是否团干', trigger: 'blur' }
        ],
        // position: [
        //   { required: true, message: '请输入现任职务', trigger: 'blur' }
        // ],
        partyMember: [
          { required: true, message: '请输入是否为党员', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addLeague(this.form).then(e => {
            if (e.success && e.data === '') {
              this.$message({
                type: 'success',
                message: '添加班级信息成功！'
              })
              this.$refs.form.resetFields()
            } else {
              this.$message({
                type: 'error',
                message: '添加失败!'
              })
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    close() {
    },
    indexMethod(index) {
      return index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
    }
  }

}
</script>

<style  scoped>
  .header{
    margin-top: 10px;
  }
  .el-input {
    width: 15%;
  }
  .el-option{
    width: 15%;
  }
  .components-container{
    width: 100%;
    height: 680px;
    margin:20px;
    background-color: #fff;
  }
  .submit{
    margin: 35px;
    margin-top: 10px;
    padding-top: 10px
  }
  .btn{
    margin-left: 90px;
  }
</style>
