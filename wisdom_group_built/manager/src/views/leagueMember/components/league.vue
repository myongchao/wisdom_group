<template>
  <el-form ref="form" :inline="true" :model="entity" label-width="100px">
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
    <el-form-item label="入团时间:" prop="leagueTime">
      <el-date-picker
        v-model="entity.leagueTime"
        type="date"
        placeholder="选择入团日期"/>
    </el-form-item>
    <el-form-item label="QQ号码:" prop="qq">
      <el-input v-model="entity.qq"/>
    </el-form-item>
    <el-form-item label="是否团干:" prop="tuanGan">
      <el-select v-model="entity.tuanGan" placeholder="请选择团干">
        <el-option v-for="(item,index) in istuanGan" :key="index" :label="item.tuanGan" :value="item.tuanGan"/>
      </el-select>
    </el-form-item>
    <el-form-item label="团干性质:" prop="tuanGanXZ">
      <el-select v-model="entity.tuanGanXZ" placeholder="请选择团干性质">
        <el-option v-for="item in tuanGanXZES" :label="item.tuanGanXZ" :value="item.tuanGanXZ" :key="index"/>
      </el-select>
    </el-form-item>
    <el-form-item label="现任职务:" prop="position">
      <el-select v-model="entity.position" placeholder="请选择职务">
        <el-option v-for="(item,index) in positionList" :key="index" :label="item.position" :value="item.position"/>
      </el-select>
    </el-form-item>
    <el-form-item label="是否为党员:" prop="partyMember">
      <el-select v-model="entity.partyMember" placeholder="请选择党员">
        <el-option v-for="(item,index) in istuanGan" :key="index" :label="item.tuanGan" :value="item.tuanGan"/>
      </el-select>
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
      loading: false,
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
      ]
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

