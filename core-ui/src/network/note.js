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
export function uploadFile(file){
  return request({
    url: '/file/upload',
    method: 'post',
    data: file,
    headers: { 'Content-Type': 'multipart/form-data' },
  })
}
export function delFile(filePath){
  return request({
    url: '/file/del',
    method: 'delete',
    data : filePath
  })
}