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
    method: 'get',
    url: '/user/login',
    params: {
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
//用户信息
export function getUserProfile(id){
  return request({
    method: 'get',
    url: '/user/getSysUser/'+id
  })
}
//关注用户
export function addFollowing(id){
  return request({
    method: 'get',
    url: '/user/addFollower/'+id
  })
}
//是否为关注用户
export function isFollowing(id){
  return request({
    method: 'get',
    url: '/user/isFollowing/'+id
  })
}
//取消关注用户