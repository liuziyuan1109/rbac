import axios from 'axios';
import { getAccessToken } from './auth';
import { removeToken } from './auth';
import { Message } from 'element-ui';
import router from '@/router';


const service = axios.create({
  baseURL: '/api',
  timeout: 5000
});

service.interceptors.request.use(
  config => {
    // 在请求发送之前对请求数据进行处理
    if (getAccessToken()) {
      config.headers['Authorization'] = 'Bearer ' + getAccessToken() // 携带token
    }
    return config;
  },
  error => {
    // 对请求错误做些什么
    console.log(error);
    return Promise.reject(error);
  }
);

service.interceptors.response.use(
  response => {
    return response.data
  },
  error => {
    if (error.response) {
      if (error.response.status === 401) {
        // 未授权，跳转到登录页面
        removeToken()
        router.push('/login')
        Message.error('未授权，请重新登录')
        return Promise.resolve();
      } else if (error.response.status === 403) {
        // 无权限，显示错误信息
        Message.error('无权限访问')
      } else {
        // 其他错误
        Message.error(error.response.data.message || '请求错误')
      }
    }
    return Promise.reject(error)
  }
);

export default service;
