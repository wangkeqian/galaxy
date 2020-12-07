import {request} from './request'
//注册用户
export function register(userInfo){
  return request({
    method: 'post',
    url: '/user/register',
    data: userInfo
  })
}
//校验用户
export function login(username,password){
  return request({
    method: 'post',
    url: '/user/login',
    data: {
      username,
      password
    }
  })
}
//登录状态
export function loginStatus(token){
  return request({
    method: 'post',
    url: '/user/loginStatus',
    data: token
  })
}