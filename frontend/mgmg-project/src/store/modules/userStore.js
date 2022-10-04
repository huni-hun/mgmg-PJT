import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const userStore = {
  namespaced: true,
  state: () => ({
    userId: "",
    userPw: "",
    userName: "",
    accessToken: "",
    refreshToken: "",
    diaryFont: 0,
    admin: 0,
    isInf: false, // 알림창을 위한 변수
  }),
  getters: {},
  mutations: {
    // 로그인 상태 유지 로그인
    SET_USER_INFO_AUTO(state, data) {
      state.userId = data.userId;
      state.userName = data.userName;
      state.accessToken = data.accessToken;
      state.refreshToken = data.refreshToken;
      state.diaryFont = data.diaryFont;
      console.log(state.accessToken);
      console.log(state.refreshToken);
      state.admin = data.admin;
    },
    // 로그인 상태 유지X 로그인
    SET_USER_INFO_NOT_AUTO(state, data) {
      state.userId = data.userId;
      state.userName = data.userName;
      state.accessToken = data.accessToken;
      state.diaryFont = data.diaryFont;
      state.refreshToken = "";
      console.log(state.accessToken);
      console.log(state.refreshToken);
      state.admin = data.admin;
    },
    IS_INF: (state, isInf) => (state.isInf = isInf),
  },
  actions: {
    setUserInfoAuto({ commit }, data) {
      commit("SET_USER_INFO_AUTO", data);
    },
    setUserInfoNotAuto({ commit }, data) {
      commit("SET_USER_INFO_NOT_AUTO", data);
    },
    setIsInf({ commit }, isInf) {
      commit("IS_INF", isInf);
    },
  },
  modules: {},
};

export default userStore;
