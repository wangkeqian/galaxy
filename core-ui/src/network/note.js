import {request} from './request'

//添加文章
export function addNote(article){
  return request({
    method: 'post',
    url: '/article/add',
    data: article
  })
}
//博客管理查询
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
/**
 * 首页文章
 * @param {*} key 
 * 
 */
export function homePageNote(page){
  return request({
    method: 'get',
    url: '/article/homePageArticleQuery',
    params: {
      page
    }
  })
}
//根据标题查询->走的redis
export function findNoteByTitle(key){
  key = decodeURI(key)
  return request({
    method: 'get',
    url: '/article/find?'+key+'&',
    params: {
      page
    }
  })
}
//根据id查文章
export function findNoteById(id){
  return request({
    method: 'get',
    url: '/article/'+id
  })
}
//删除文章
export function delNote(id){
  return request({
    method: 'delete',
    url: '/article/del/'+id
  })
}
//上传文件
export function uploadFile(file){
  return request({
    url: '/file/upload',
    method: 'post',
    data: file,
    headers: { 'Content-Type': 'multipart/form-data' },
  })
}
//删除照片
export function delFile(filePath){
  return request({
    url: '/file/del',
    method: 'delete',
    data : filePath
  })
}