import axios from 'axios'

export default {
  state: {
    banners: null,
    isTableView: false
  },
  // todo: Change the payload to a more understandable word
  mutations: {
    setBanners(state, payload) {
      state.banners = payload
    },
    setTableView(state, payload) {
      state.isTableView = payload
    },
    toggleTableView(state) {
      state.isTableView = !state.isTableView
    },
    updateBanner(state, payload) {
      let banner = state.banners.find(b => {
        return b.id === payload.id
      })
      banner.width = payload.width
      banner.height = payload.height
      banner.language = payload.language
      banner.imgSrc = payload.imgSrc
      banner.targetUrl = payload.targetUrl
      banner.langId = payload.langId
    },
    deleteBanner(state, banner) {
      const index = state.banners.indexOf(banner)
      state.banners.splice(index, 1)
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
    },
    updateBanner({commit}, banner) {
      commit('clearError')
      commit('setLoading', true)

      axios.put('/banner/' + banner.id, {
        id: banner.id,
        width: banner.width,
        height: banner.height,
        langId: banner.langId,
        imgSrc: banner.imgSrc,
        targetUrl: banner.targetUrl
      })
        .then(response => {
          const banner = response.data
          commit('updateBanner', banner)
          commit('setLoading', false)
        })
        .catch(error => {
          commit('setLoading', false)
          commit('setError', error.message)
          throw error
        })
    },
    deleteBanner({commit}, banner) {
      commit('clearError')
      commit('setLoading', true)

      axios.delete('/banner/' + banner.id)
        .then(() => {
          commit('deleteBanner', banner)
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
