import axios from 'axios'

export default {
  state: {
    banners: null,
    isTableView: false
  },
  mutations: {
    setBanners(state, payload) {
      state.banners = payload
    },
    setTableView(state, payload) {
      state.isTableView = payload
    },
    toggleTableView(state) {
      state.isTableView = !state.isTableView
    }
  },
  getters: {
    getBanners(state) {
      return state.banners
    },
    getTableView(state) {
      return state.isTableView
    }
  },
  actions: {
    setBanners({commit}) {
      commit('clearError')
      commit('setLoading', true)

      axios.get('/banner')
        .then(response => {
          const banners = response.data
          commit('setBanners', banners)
          commit('setLoading', false)
        })
        .catch(error => {
          commit('setLoading', false)
          commit('setError', error.message)
          throw error
        })
    },
    setTableView({commit}, val) {
      commit('setTableView', val)
    },
    toggleTableView({commit}) {
      commit('toggleTableView')
    }
  }
}
