import axios from 'axios'

export default {
  state: {
    banners: null
  },
  mutations: {
    setBanners(state, payload) {
      state.banners = payload
    }
  },
  getters: {
    getBanners(state) {
      return state.banners
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
    }
  }
}
