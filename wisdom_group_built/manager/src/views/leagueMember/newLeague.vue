<template>
  <el-form ref="form" :inline="true" :model="entity" class="demo-form-inline">
    <el-form-item label="团员名称:" prop="name">
      <el-input v-model="entity.name"/>
    </el-form-item>
    <el-form-item label="身份证号:" prop="idNumber">
      <el-input v-model="entity.idNumber"/>
    </el-form-item>
    <el-form-item label="民族:" prop="national">
      <el-input v-model="entity.national"/>
    </el-form-item>
    <el-form-item label="政治面貌:" prop="politicalLandscape">
      <el-input v-model="entity.politicalLandscape"/>
    </el-form-item>
    <el-form-item label="文化程度:" prop="education">
      <el-input v-model="entity.education"/>
    </el-form-item>
    <el-form-item label="手机号码：" prop="phone">
      <el-input v-model="entity.phone"/>
    </el-form-item>
    <el-form-item label="入团时间：" prop="leagueTime">
      <el-date-picker
        v-model="entity.leagueTime"
        type="date"
        placeholder="选择入团日期"/>
    </el-form-item>
    <el-form-item label="qq号码：" prop="qq">
      <el-input v-model="entity.qq"/>
    </el-form-item>
    <el-form-item label="是否团干：" prop="tuanGan">
      <el-input v-model="entity.tuanGan"/>
    </el-form-item>
    <el-form-item label="团干性质：" prop="tuanGan">
      <el-input v-model="entity.tuanGanXZ"/>
    </el-form-item>
    <el-form-item label="现任职务：" prop="position">
      <el-input v-model="entity.position"/>
    </el-form-item>
    <el-form-item label="是否为党员：" prop="partyMember">
      <el-input v-model="entity.partyMember"/>
    </el-form-item>
  </el-form>
</template>

<script>
import {
  form
} from '@/mixins/form'
export default {
  mixins: [form],
  props: ['isEdit'],
  data() {
    return {
      loading: false
    }
  },
  created() {
  },
  methods: {
    open() {
      this.dialogVisible = true
    }
  }
}
</script>

<style>

</style>

