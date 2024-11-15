import request from '@/utils/request'

export function getUserMenus() {
  return request({
    url: '/menus/user',
    method: 'get'
  })
}
