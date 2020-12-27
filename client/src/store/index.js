import Vue from 'vue'
import Vuex from 'vuex'
// import axios from '../axios-auth.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
    storeUser (state, user){
      state.user = user
    },
  },
  actions: {
    // signup (authData) {
    //   axios.post('/register', {
    //     email: authData.email,
    //     password: authData.password,
    //     returnSecureToken: true
    //   })
    //     .then(res => {
    //       console.log(res)
    //       commit('authUser', {
    //         token: res.data.idToken,
    //         userId: res.data.localId
    //       })
    //       const now = new Date()
    //       const expirationDate = new Date(now.getTime() + res.data.expiresIn * 1000)
    //       localStorage.setItem('token', res.data.idToken)
    //       localStorage.setItem('userId', res.data.localId)
    //       localStorage.setItem('expirationDate', expirationDate)
    //       dispatch('setLogoutTimer', res.data.expiresIn)
    //       dispatch('storeUser', authData)
    //     })
    //     .catch(error=> console.log(error))
    // },
  },
  modules: {
  }
})
