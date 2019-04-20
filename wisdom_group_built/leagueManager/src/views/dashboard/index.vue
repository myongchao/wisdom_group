<template>
  <div class="manager">
    <div class="left_box">
      <div class="usertitle">
        <span class="titleStyle">团员基本资料：</span>
        <span class="look" style="float:right;">查看</span>
      </div>
      <div class="infolist">
        <div class="lineinfos">
          <div class="user_info">姓名</div>
          <span class="usercontent" style="height:28px; line-height:28px">{{ leagueData.name }}</span>
        </div>
        <div class="lineinfos">
          <div class="user_info">性别</div>
          <span class="usercontent" style="height:28px; line-height:16px;">男</span>
        </div>
        <div class="lineinfos">
          <div class="user_info">民族</div>
          <span class="usercontent" style="height:28px; line-height:28px">汉族</span>
        </div>
        <div class="lineinfos">
          <div class="user_info">年龄</div>
          <span class="usercontent" style="height:28px; line-height:28px">22</span>
        </div>
        <div class="lineinfos">
          <div class="user_info">所在团组织</div>
          <span class="usercontent" style="height:28px; line-height:28px">2015级计算机科学与技术1班团支部</span>
        </div>
      </div>
    </div>
    <div class="right_box">
      <div class="usertitle">
        <span class="titleStyle">工作通知：</span>
        <span class="look" style="float:right;margin-right:15px">查看</span>
      </div>
      <div>
        <ul>
          <li>
            <a
              href="https://b.xiumi.us/board/v5/3qTJy/101392066"
              target="_blank"
            >“智慧团建”系统全团推广工作常见问题解答（持续更新中）</a>
          </li>
          <li>
            <a
              href="http://www.gqt.org.cn/documents/zqbf/201712/P020171207616840064337.pdf"
              target="_blank"
            >共青团中央办公厅关于做好 “智慧团建冶 系统组织树建立阶段工作的通知</a>
          </li>
          <li>
            <a href="http://c.xiumi.us/board/v5/3cCN6/70680482" target="_blank">“智慧团建”系统操作常见问题解答</a>
          </li>
          <li>
            <a
              href="https://zhtj.youth.cn/zhtj/help/%E5%85%B3%E4%BA%8E%E7%94%B3%E6%8A%A52018%E5%B9%B4%E5%BA%A6%E5%85%A8%E5%9B%BD%E2%80%9C%E4%B8%A4%E7%BA%A2%E2%80%9D%E2%80%9C%E4%B8%A4%E4%BC%98%E2%80%9D%E7%9A%84%E9%80%9A%E7%9F%A5.pdf"
              target="_blank"
            >
              共青团中央办公厅关于申报 2018 年度
              “全国优秀共青团员冶 “全国优秀共青团干部冶
              “全国五四红旗团委 (团支部) 冶 的通知
            </a>
          </li>
          <li>
            <a
              href="https://zhtj.youth.cn/zhtj/help/P020181026.pdf"
              target="_blank"
            >关于做好“智慧团建”系统的团员团干部信息采集录入工作的通知(持续更新中)</a>
          </li>
          <li>
            <a
              href="https://zhtj.youth.cn/zhtj/help/index_czzn.html"
              target="_blank"
            >“智慧团建”系统组织树建立操作指南</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="content">
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="grid-content bg-purple">
            <div class="title">
              <span>&nbsp;&nbsp;我的三会</span>
              <span style="float:right;margin-right:10px;">
                <a href="../..#/sanHui/zhiBuTuanYuan">查看</a>
              </span>
            </div>
            <div>
              <ul>
                <li
                  v-for="(item,index) in meetingList"
                  :key="index"
                  style="width:100%"
                >{{ item.title }}</li>
              </ul>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="grid-content bg-purple">
            <div class="title">
              <span>&nbsp;&nbsp;我的团课</span>
              <span style="float:right;margin-right:10px;">
                <a href="../..#/tuanKe/index">查看</a>
              </span>
            </div>
            <div>
              <ul>
                <li
                  v-for="(item,index) in activityList"
                  :key="index"
                  style="width:100%"
                >{{ item.name }}</li>
              </ul>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="grid-content bg-purple">
            <div class="title">
              <span>&nbsp;&nbsp;我的团日活动</span>
              <span style="float:right;margin-right:10px;">
                <a href="../..#/tuanActivity/index">查看</a>
              </span>
            </div>
            <div>
              <ul>
                <li
                  v-for="(item,index) in teamClassList"
                  :key="index"
                  style="width:100%"
                >{{ item.theme }}</li>
              </ul>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { getActivityList } from '@/api/activity';
import { getMeetingList } from '@/api/meetting';
import { getTeamClassList } from '@/api/teamclass';
import { get } from '@/api/login';
import store from '@/store';
export default {
  data() {
    return {
      loading: false, // 加载
      tableData: [],
      leagueData: null,
      activityList: [],
      meetingList: [],
      teamClassList: []
    }
  },
  created() {
    this.getLeague()
    this.getList()
  },
  methods: {
    addLeague() {
      this.$router.push('addLeague')
    },
    getLeague() {
      get(store.getters.token).then(e => {
        debugger
        this.leagueData = e.data
      })
    },
    getList() {
      getActivityList().then(e => {
        this.activityList = e.data
      })
      getMeetingList().then(e => {
        this.meetingList = e.data
      })
      getTeamClassList().then(e => {
        this.teamClassList = e.data
      })
    }
  }
}
</script>

<style scoped>
.manager {
  border: 1px solid seashell;
  margin: 15px;
}
.left_box {
  width: 49%;
  float: left;
  height: 260px;
  background: white;
  border: 1px solid #e1e1e1;
}
.right_box {
  width: 49%;
  float: right;
  height: 260px;
  background: white;
  border: 1px solid #e1e1e1;
}
.titleStyle {
  float: left;
  height: 44px;
  float: left;
  margin-left: 6px;
  line-height: 44px;
  font-size: 16px;
  color: #d72500;
}
.look {
  float: right;
  font-size: 14px;
  color: #888888;
  margin-top: 10px;
  margin-right: 18px;
}
.usertitle {
  width: 100%;
  height: 44px;
  margin: 0 auto;
  overflow: hidden;
  position: relative;
  border-bottom: 1px solid #dcdcdc;
}
.infolist {
  width: 570px;
  padding: 10px;
  height: auto;
  margin: 20px auto;
  margin-left: 0px;
  margin-top: -0.05px;
  background-color: #fff;
}
.lineinfos {
  width: 100%;
  height: 26px;
  margin: 0 auto 12px;
}
.user_info {
  background: #e4e4e4 none repeat scroll 0 0;
  border-radius: 4px;
  color: #000;
  float: left;
  height: 26px;
  line-height: 26px;
  text-align: center;
  width: 92px;
  font-size: 14px;
}
.usercontent {
  float: left;
  margin-left: 20px;
  margin-top: -2px;
  color: #000;
  width: 420px;
}
.right {
  position: absolute;
  float: right;
}
li {
  float: left;
}
.content {
  width: 100%;
  height: auto;
  margin: 20px auto 0;
  overflow: hidden;
  border: 1px solid #e1e1e1;
  padding: 20px;
  padding-bottom: 0;
}
.each_menu {
  width: 124px;
  height: 124px;
  border-radius: 50%;
  background: #ffe5e6;
  transition: background 1s;
  margin: auto;
  -webkit-transition: background 1s;
  line-height: 142px;
  text-align: center;
}
.nav {
  float: left;
  width: 140px;
  height: auto;
  margin-left: 14px;
  margin-right: 0;
  margin-bottom: 22px;
  cursor: pointer;
}
.el-row {
  margin-bottom: 20px;
  /* &:last-child {
    margin-bottom: 0;
  } */
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #ffffff;
}
.bg-purple {
  background: #ffffff;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 200px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.title {
  color: #d72500;
  height: 36px;
  padding-top: 8px;
  border-bottom: 2px solid #e4e7ed;
}
</style>

