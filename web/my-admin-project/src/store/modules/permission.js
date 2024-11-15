import { getUserMenus } from '@/api/menu'
import router from '@/router'

const state = {
  menus: []
}

const mutations = {
  SET_MENUS(state, menus) {
    state.menus = menus
  }
}

const actions = {
  generateRoutes({ commit }) {
    return new Promise((resolve, reject) => {
      getUserMenus().then(response => {
        if (response === undefined) {
          return resolve(response); // 提前退出
        }
        commit('SET_MENUS', response)
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
