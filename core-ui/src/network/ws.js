import {request} from './request'
//注册用户
export function send(uid,message){
  return request({
    method: 'get',
    url: '/ws/sendOne',
    params:{
      uid,
      message
    }
  })
}