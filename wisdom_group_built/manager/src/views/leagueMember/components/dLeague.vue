<template>
  <el-form ref="form" :inline="true" :model="entity" label-width="100px">
    <el-form-item label="团员名称:" prop="name">
      <el-input :disabled="true" v-model="entity.name"/>
    </el-form-item>
    <el-form-item label="身份证号:" prop="idNumber">
      <el-input :disabled="true" v-model="entity.idNumber"/>
    </el-form-item>
    <el-form-item label="民族:" prop="national">
      <el-input :disabled="true" v-model="entity.national"/>
    </el-form-item>
    <el-form-item label="政治面貌:" prop="politicalLandscape">
      <el-input :disabled="true" v-model="entity.politicalLandscape"/>
    </el-form-item>
    <el-form-item label="文化程度:" prop="education">
      <el-input :disabled="true" v-model="entity.education"/>
    </el-form-item>
    <el-form-item label="手机号码：" prop="phone">
      <el-input :disabled="true" v-model="entity.phone"/>
    </el-form-item>
    <el-form-item label="入团时间：" prop="leagueTime">
      <el-input :disabled="true" v-model="entity.leagueTime"/>
    </el-form-item>
    <el-form-item label="qq号码：" prop="qq">
      <el-input :disabled="true" v-model="entity.qq"/>
    </el-form-item>
    <el-form-item label="是否团干：" prop="tuanGan">
      <el-input :disabled="true" v-model="entity.tuanGan"/>
    </el-form-item>
    <el-form-item label="团干性质：" prop="tuanGan">
      <el-input :disabled="true" v-model="entity.tuanGanXZ"/>
    </el-form-item>
    <el-form-item label="现任职务：" prop="position">
      <el-input :disabled="true" v-model="entity.position"/>
    </el-form-item>
    <el-form-item label="是否为党员：" prop="partyMember">
      <el-input :disabled="true" v-model="entity.partyMember"/>
    </el-form-item>
  </el-form>
</template>

<script>
import {
  form
} from '@/mixins/form'
import { fetchOne } from '@/api/league'
export default {
  mixins: [form],
  props: ['eid', 'isEdit'],
  data() {
    return {
      loading: false
    }
  },
  created() {
  },
  methods: {
    load() {
      if (this.isEdit && this.eid) {
        fetchOne(this.eid).then(e => {
          this.entity = e.data
        })
      }
    },
    open() {
      this.dialogVisible = true
    }
  }
}
</script>

<style>

</style>

