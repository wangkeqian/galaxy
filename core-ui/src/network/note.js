import {request} from './request'

export function addNote(article){
  return request({
    method: 'post',
    url: '/article/add',
    data: article
  })
}
export function findNote(article){
  return request({
    method: 'get',
    url: '/article/find',
    params: article
  })
}
export function findNoteById(id){
  return request({
    method: 'get',
    url: '/article/'+id
  })
}