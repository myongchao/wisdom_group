import request from '@/utils/request'

/** 添加团员信息 */
export function addLeague(data) {
  return request({
    url: 'league/add',
    method: 'post',
    data
  })
}

/** 修改团员信息 */
export function addManager(data) {
  return request({
    url: 'league/addManager',
    method: 'post',
    data
  })
}

/** 批量添加团员信息 */
export function insertBatches(list) {
  return request({
    url: 'league/multipleAdd',
    method: 'post',
    data: {
      data: list
    }
  })
}
/** 导出 */
export function exportByIds(data) {
  return request({
    url: '/league/export',
    method: 'post',
    data,
    responseType: 'blob'
  })
}

/** 下载 */
export function downTemplate(data) {
  return request({
    url: 'league/download/template',
    method: 'get',
    data
  })
}

/** 团员信息分页 */
export function pageWithLeague(data) {
  return request({
    url: 'league/page',
    method: 'post',
    data
  })
}

/** 团干部信息列表非管理 */
export function getManagerList(data) {
  return request({
    url: 'league/listTuangan',
    method: 'get',
    data
  })
}

/** 团员信息列表 */
export function getLeagueList(data) {
  return request({
    url: 'league/list',
    method: 'get',
    data
  })
}

/** 团员信息分页 */
export function pageWithLeagueWithGanbu(data) {
  return request({
    url: 'league/pageWithGanbu',
    method: 'post',
    data
  })
}

/** 获取单个团员信息 */
export function fetchOne(id) {
  return request({
    url: 'league/get',
    method: 'get',
    params: {
      id
    }
  })
}

/** 修改团员信息 */
export function editLeague(data) {
  return request({
    url: 'league/edit',
    method: 'post',
    data
  })
}

/** 修改团干部信息 */
export function editLeagueGanbu(data) {
  return request({
    url: 'league/editGanbu',
    method: 'post',
    data
  })
}

/** 删除团员信息 */
export function deleteLeague(id) {
  return request({
    url: 'league/del',
    method: 'post',
    params: {
      id
    }
  })
}
