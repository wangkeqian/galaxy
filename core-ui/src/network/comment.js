import {request} from './request'
//添加评论
export function addComment(comment){
  return request({
    method: 'post',
    url: '/comment/addComment',
    data: comment
  })
}
//加载评论
export function loadComment(id){
  return request({
    method: 'get',
    url: '/comment/loadComment/'+id,
  })
}