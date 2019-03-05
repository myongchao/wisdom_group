<template>
  <div class="manager">
    <div class="left_box">
      <div class="usertitle">
        <span class="titleStyle">基本资料：</span>
        <span class="look" style="float:right;margin-right:15px">查看</span>
      </div>
      <div class="infolist">
        <div class="lineinfos">
          <div class="user_info">组织简称</div>
          <span class="usercontent" style="height:28px; line-height:28px">2015级计算机科学与技术1班团支部</span>
        </div>
        <div class="lineinfos">
          <div class="user_info">组织全称</div>
          <span
            class="usercontent"
            style="height:28px; line-height:16px;"
          >河南省郑州市市属学校郑州师范学院郑州师范学院信息科学与技术学院2015级计算机科学与技术1班团支部</span>
        </div>
        <div class="lineinfos">
          <div class="user_info">组织类别</div>
          <span class="usercontent" style="height:28px; line-height:28px">团支部</span>
        </div>
        <div class="lineinfos">
          <div class="user_info">团员人数</div>
          <span class="usercontent" style="height:28px; line-height:28px">46</span>
        </div>
        <div class="lineinfos">
          <div class="user_info">上级组织</div>
          <span class="usercontent" style="height:28px; line-height:28px">郑州师范学院信息科学与技术学院团总支</span>
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
          <li>“智慧团建”系统操作常见问题解答</li>
          <li>“智慧团建”系统全团推广工作常见问题解答（持续更新中）</li>
          <li>共青团中央办公厅关于做好 “智慧团建冶 系统组织树建立阶段工作的通知</li>
          <li>“智慧团建”系统操作常见问题解答</li>
          <li>“智慧团建”系统全团推广工作常见问题解答（持续更新中）</li>
          <li>共青团中央办公厅关于做好 “智慧团建冶 系统组织树建立阶段工作的通知</li>
        </ul>
      </div>
    </div>
    <div class="content">
      <div class="nav">
        <a href="../..#/leagueMember/addLeague">
          <div class="each_menu">
            <img src="../../assets/images/add.png">
          </div>
          <p class="nav_title">录入团员信息</p>
        </a>
      </div>
      <div class="nav">
        <a href="../..#/leagueMember/leagueSelect">
          <div class="each_menu">
            <img src="../../assets/images/mtasks.png">
          </div>
          <p class="nav_title">团员信息</p>
        </a>
      </div>
      <div class="nav">
        <a href="../..#/tuanGan/leagueOfficer">
          <div class="each_menu">
            <img src="../../assets/images/team.png">
          </div>
          <p class="nav_title">团干部信息</p>
        </a>
      </div>
      <div class="nav">
        <a href="../..#/sanHui/publish">
          <div class="each_menu">
            <img src="../../assets/images/edit.png">
          </div>
          <p class="nav_title">会议活动</p>
        </a>
      </div>
      <!-- <div class="nav">
        <div class="each_menu">
          <img src="../../assets/images/task.png">
        </div>
        <p class="nav_title">团员教育评议</p>
      </div>
      <div class="nav">
        <div class="each_menu">
          <img src="../../assets/images/aixin.png">
        </div>
        <p class="nav_title">年度团籍注册</p>
      </div>-->
      <div class="nav">
        <a href="../..#/businessManager/examination">
          <div class="each_menu">
            <img src="../../assets/images/ceshi.png">
          </div>
          <p class="nav_title">团员注册审批</p>
        </a>
      </div>
      <div class="nav">
        <a href="../..#/tuanGan/changePosition">
          <div class="each_menu">
            <img src="../../assets/images/mtasks.png">
          </div>
          <p class="nav_title">团干部职务变更</p>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false, // 加载
      tableData: []
    }
  },
  created() {},
  methods: {
    addLeague() {
      this.$router.push('addLeague')
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
  background: white;
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
  margin-left: 20px;
  margin-right: 18px;
  margin-bottom: 22px;
  cursor: pointer;
}
.nav_title {
  text-align: center;
  margin-top: 12px;
  font-size: 16px;
  font-weight: 600;
  color: #4e4e4e;
  transition: color 1s;
  -webkit-transition: color 1s;
}
img {
  width: 30px;
  height: 30px;
  color: #d72500;
}
</style>

