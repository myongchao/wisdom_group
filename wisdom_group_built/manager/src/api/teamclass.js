import request from '@/utils/request'

/** 会议信息分页 */
export function pageWithTeamclass(data) {
  return request({
    url: 'teamclass/page',
    method: 'post',
    data
  })
}

/** 删除会议信息 */
export function deleteTeamclass(id) {
  return request({
    url: 'teamclass/del',
    method: 'post',
    params: {
      id
    }
  })
}

/** 添加会议信息 */
export function addTeamclass(data) {
  return request({
    url: 'teamclass/add',
    method: 'post',
    data
  })
}
