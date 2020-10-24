import {request} from './request'
import qs from 'qs';
export function getHomeMultidata(){
  return request({
    url: '/home/multidata'
  })
}
export function getHomeGoods(type,page){
  return request({
    url: '/home/data',
    params: {
      type,
      page
    }
  })
}
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