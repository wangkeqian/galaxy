import axios from 'axios'
export function request(config){
  const instance = axios.create({
    // baseURL: 'http://152.136.185.210:8000/api/w6',
    baseURL: 'http://localhost:8090/',
    timeout: 5000
  })
  //1.请求拦截
  instance.interceptors.request.use(config => {
    return config
  }, err => {

  })
  //2.响应拦截
  instance.interceptors.response.use(res =>{
    return res.data
  }, err =>{
    console.log(err);
    
  })
  return instance(config)
}