import axios from 'axios'
import ElementUI from 'element-ui';
import Vue from 'vue'
import router from '../router'
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

axios.defaults.withCredentials = true
export function request(config){
  const vue =new Vue({
    router
  })
  const instance = axios.create({
    baseURL: process.env.VUE_APP_axios_baseURL,
    timeout: 5000
  })
  //1.请求拦截
  instance.interceptors.request.use(config => {
    var token = sessionStorage.getItem('token')
    config.headers['token'] = token
    console.log('当前的环境是',process.env.VUE_APP_axios_baseURL);
    
    return config
  }, err => {

  })
  //2.响应拦截
  instance.interceptors.response.use(res =>{
    if(res.data.status > 9000){
      vue.$notify.error({
        title: '请求失败',
        message: res.data.msg
      });
      if(res.data.status == 9003 || res.data.status == 9002){
        setTimeout(() => {
          vue.$router.push('/blog')    
        }, 1000);
      
      }
    }
    return res.data
  }, err =>{
    vue.$notify.error({
      title: '请求失败',
      message: err.response.data.message
    });
  })
  return instance(config)
}