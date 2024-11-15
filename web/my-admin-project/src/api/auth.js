import request from '@/utils/request';

// 用户登录
export function login(username, password) {
  return request({
    url: '/auth/login',
    method: 'post',
    data: { username, password },
  });
}

// 获取用户信息
export function getInfo() {
  return request({
    url: '/auth/getInfo',
    method: 'get',
  });
}

// 用户退出
export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post',
  });
}
