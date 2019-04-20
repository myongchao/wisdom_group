import request from '@/utils/request'

/** 会议信息分页 */
export function pageWithMeeting(data) {
  return request({
    url: 'meetting/page',
    method: 'post',
    data
  })
}

/** 删除会议信息 */
export function deleteMeet(id) {
  return request({
    url: 'meetting/del',
    method: 'post',
    params: {
      id
    }
  })
}

/** 添加会议信息 */
export function addMeet(data) {
  return request({
    url: 'meetting/add',
    method: 'post',
    data
  })
}

/** 会议信息列表 */
export function getMeetingList(data) {
  return request({
    url: 'meetting/list',
    method: 'get',
    data
  })
}
