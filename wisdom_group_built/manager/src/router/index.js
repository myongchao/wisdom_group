import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  // {
  //   path: '/',
  //   component: Layout,
  //   redirect: '/dashboard',
  //   name: 'Dashboard',
  //   meta: { title: '组织首页', icon: 'dashboard' },
  //   hidden: true,
  //   children: [{
  //     path: 'dashboard',
  //     component: () => import('@/views/dashboard/index')
  //   }]
  // },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    meta: { title: '组织首页', icon: 'home' },
    children: [
      {
        path: '/dashboard',
        name: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '组织首页', icon: 'home' }
      }
    ]
  },

  {
    path: '/leagueMember',
    component: Layout,
    redirect: '/leagueMember/leagueSelect',
    name: 'leagueSelect',
    meta: { title: '团员管理', icon: 'yonghu' },
    children: [
      {
        path: 'leagueSelect',
        name: 'LeagueSelect',
        component: () => import('@/views/leagueMember/leagueSelect'),
        meta: { title: '团员列表', icon: 'yonghu' }
      },
      {
        path: 'addLeague',
        name: 'AddLeague',
        component: () => import('@/views/leagueMember/addLeague'),
        meta: { title: '录入团员团干部', icon: 'yonghu' }
      }
    ]
  },

  {
    path: '/tuanGan',
    component: Layout,
    redirect: '/tuanGan/leagueOfficer',
    name: 'leagueOfficer',
    meta: { title: '团干部管理', icon: 'qunzu' },
    children: [
      {
        path: 'leagueOfficer',
        name: 'LeagueOfficer',
        component: () => import('@/views/tuanGan/leagueOfficer'),
        meta: { title: '团干部列表', icon: 'qunzu' }
      },
      {
        path: 'changePosition',
        name: 'ChangePosition',
        component: () => import('@/views/tuanGan/changePosition'),
        meta: { title: '团干部职务变更', icon: 'qunzu' }
      }
    ]
  },

  {
    path: '/businessManager',
    component: Layout,
    redirect: '/businessManager/changeAdmin',
    name: 'businessManager',
    meta: { title: '业务办理', icon: 'menu' },
    children: [
      {
        path: 'examination',
        name: 'Examination',
        component: () => import('@/views/businessManager/examination'),
        meta: { title: '团员团干部注册审批', icon: 'menu' }
      },
      {
        path: 'changeAdmin',
        name: 'ChangeAdmin',
        component: () => import('@/views/businessManager/changeAdmin'),
        meta: { title: '管理员变更', icon: 'menu' }
      },
      {
        path: 'admin',
        name: 'Admin',
        hidden: true,
        component: () => import('@/views/businessManager/admin'),
        meta: { title: '管理员变更', icon: 'menu' }
      }
    ]
  },

  {
    path: '/sanHui',
    component: Layout,
    redirect: '/sanHui/zhiBuTuanYuan',
    name: 'sanHui',
    meta: { title: '三会', icon: 'bianji' },
    children: [
      {
        path: 'zhiBuTuanYuan',
        name: 'ZhiBuTuanYuan',
        component: () => import('@/views/sanHui/zhiBuTuanYuan'),
        meta: { title: '支部团员大会', icon: 'bianji' }
      },
      {
        path: 'zhiBuWeiYuan',
        name: 'ZhiBuWeiYuan',
        component: () => import('@/views/sanHui/zhiBuWeiYuan'),
        meta: { title: '支部委员会', icon: 'bianji' }
      },
      {
        path: 'group',
        name: 'Group',
        component: () => import('@/views/sanHui/group'),
        meta: { title: '团小组会', icon: 'bianji' }
      },
      {
        path: 'publish',
        name: 'Publish',
        hidden: true,
        component: () => import('@/views/sanHui/publish'),
        meta: { title: '会议发布', icon: 'bianji' }
      }
    ]
  },

  {
    path: '/tuanKe',
    component: Layout,
    redirect: '/tuanKe/tuanKe',
    name: 'tuanKe',
    meta: { title: '团课', icon: 'folder' },
    children: [
      {
        path: 'index',
        name: 'index',
        component: () => import('@/views/tuanKe/index'),
        meta: { title: '团课', icon: 'folder' }
      },
      {
        path: 'http://gktk.zhtj.youth.cn/',
        name: 'PublicTuanKe',
        // component: () => import('@/views/tuanKe/publicTuanKe'),
        meta: { title: '公开团课', icon: 'link' }
      },
      {
        path: 'uploadPublic',
        name: 'uploadPublic',
        hidden: true,
        component: () => import('@/views/tuanKe/uploadPublic'),
        meta: { title: '团课上传', icon: 'folder' }
      }
    ]
  },

  {
    path: '/tuanActivity',
    component: Layout,
    redirect: '/tuanActivity/index',
    name: 'tuanActivity',
    meta: { title: '团日活动', icon: 'example' },
    children: [
      {
        path: 'index',
        name: 'index',
        component: () => import('@/views/tuanActivity/index'),
        meta: { title: '团日活动', icon: 'example' }
      },
      {
        path: 'activity',
        name: 'activity',
        hidden: true,
        component: () => import('@/views/tuanActivity/activity'),
        meta: { title: '团日活动上传', icon: 'example' }
      }
    ]
  },

  {
    path: '/personal',
    component: Layout,
    redirect: '/personal',
    name: 'personal',
    meta: { title: '个人信息管理', icon: '设置' },
    children: [{
      path: 'userInfo',
      name: 'userInfo',
      component: () => import('@/views/personal/userInfo'),
      meta: { title: '个人信息', icon: 'form' }
    },
    {
      path: 'resetPwd',
      name: 'resetPwd',
      component: () => import('@/views/personal/resetPwd'),
      meta: { title: '密码管理', icon: 'form' }
    }
    ]
  },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
