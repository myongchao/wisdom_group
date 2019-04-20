import request from '@/utils/request'

/** 团课分页 */
export function pageWithTeamclass(data) {
  return request({
    url: 'teamclass/page',
    method: 'post',
    data
  })
}

/** 删除团课信息 */
export function deleteTeamclass(id) {
  return request({
    url: 'teamclass/del',
    method: 'post',
    params: {
      id
    }
  })
}

/** 添加团课信息 */
export function addTeamclass(data) {
  return request({
    url: 'teamclass/add',
    method: 'post',
    data
  })
}

/** 团课列表 */
export function getTeamClassList(data) {
  return request({
    url: 'teamclass/list',
    method: 'get',
    data
  })
}
