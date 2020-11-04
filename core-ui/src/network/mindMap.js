import {request} from './request'
//添加思维脑图
export function addMindMap(mindMap){
  return request({
    method: 'post',
    url: '/mindMap/add',
    data: mindMap
  })
}
//查询
export function findMindMap(queryVo){
  return request({
    method: 'post',
    url: '/mindMap/find',
    data: queryVo
  })
}