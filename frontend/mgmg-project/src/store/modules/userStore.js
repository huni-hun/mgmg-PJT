import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 로그인 시 변경되는 항목
    isLogin: false,
    userId: String,
    userPw: String,
  },
  getters: {},
  mutations: {},
  actions: {},
  modules: {},
});

