import request from '@/utils/request'

/** 会议信息分页 */
export function pageWithActivity(data) {
  return request({
    url: 'activity/page',
    method: 'post',
    data
  })
}

/** 删除会议信息 */
export function deleteActivity(id) {
  return request({
    url: 'activity/del',
    method: 'post',
    params: {
      id
    }
  })
}

/** 添加会议信息 */
export function addActivity(data) {
  return request({
    url: 'activity/add',
    method: 'post',
    data
  })
}
