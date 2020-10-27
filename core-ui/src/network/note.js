import {request} from './request'

export function addNote(article){
  return request({
    method: 'post',
    url: '/article/add',
    data: article
  })
}
export function findNote(article,page){
  article = decodeURI(article)
  return request({
    method: 'get',
    url: '/article/find?'+article+'&',
    params: {
      page
    }
  })
}
export function findNoteById(id){
  return request({
    method: 'get',
    url: '/article/'+id
  })
}