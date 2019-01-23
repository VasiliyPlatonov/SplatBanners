import Vue from 'vue'
import Vuex from 'vuex'
import banners from './banners'
import shared from './shared'

Vue.use(Vuex)

const drawer = {
  state: {
    shown: null
  },
  getters: {
    isShown(state) {
      return state.shown
    }
  },
  mutations: {
    setShown(state, val) {
      state.shown = val
    },
    toggleShown(state) {
      state.shown = !state.shown
    }
  },
  actions: {
    toggleShown({commit}) {
      commit('toggleShown')
    }
  }
}

export default new Vuex.Store({
  modules: {
    shared,
    drawer,
    banners
  }
})
