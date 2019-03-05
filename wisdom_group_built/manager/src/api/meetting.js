import request from '@/utils/request'

/** 添加huiyi信息 */
export function addLeague(data) {
  return request({
    url: 'league/add',
    method: 'post',
    data
  })
}

/** 会议信息分页 */
export function pageWithMeeting(data) {
  return request({
    url: 'meetting/page',
    method: 'post',
    data
  })
}
