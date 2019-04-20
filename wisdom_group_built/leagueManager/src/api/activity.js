import request from '@/utils/request'

/** 团体活动分页 */
export function pageWithActivity(data) {
  return request({
    url: 'activity/page',
    method: 'post',
    data
  })
}

/** 删除团体活动信息 */
export function deleteActivity(id) {
  return request({
    url: 'activity/del',
    method: 'post',
    params: {
      id
    }
  })
}

/** 添加团体活动信息 */
export function addActivity(data) {
  return request({
    url: 'activity/add',
    method: 'post',
    data
  })
}

/** 团体活动信息列表 */
export function getActivityList(data) {
  return request({
    url: 'activity/list',
    method: 'get',
    data
  })
}
