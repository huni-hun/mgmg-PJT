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
    isInf: false,
  }),
  mutations: {
    SET_USER_INFO_AUTO(state, data) {
      state.userId = data.userId;
      state.userName = data.userName;
      state.accessToken = data.accessToken;
      state.refreshToken = data.refreshToken;
      state.diaryFont = data.diaryFont;
      state.admin = data.admin;
    },
    SET_USER_INFO_NOT_AUTO(state, data) {
      state.userId = data.userId;
      state.userName = data.userName;
      state.accessToken = data.accessToken;
      state.diaryFont = data.diaryFont;
      state.refreshToken = "";
      state.admin = data.admin;
    },
    IS_INF: (state, isInf) => (state.isInf = isInf),
    SET_FONT: (state, fontNum) => {
      state.diaryFont = fontNum;
      console.log("폰트 저장됨", fontNum);
    },
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
    setFont({ commit }, fontNum) {
      commit("SET_FONT", fontNum);
    },
  },
};

export default userStore;
