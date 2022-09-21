// import api_url from "@/api/index.js";
import Vue from "vue";
import Vuex from "vuex";
// import { API } from "@/store";
// import Swal from "sweetalert2";

Vue.use(Vuex);

const userStore = {
  state: {
    // 로그인 시 변경되는 항목
    isLogin: false,
    userId: "",
    userPw: "",
    userName: "",
    accessToken: "",
    refreshToken: "", // persistent cookies에 저장하기 @@@@@@@@@@@@@
    diaryFont: 0,
    admin: 0,
  },
  getters: {},
  mutations: {
    // 로그인 상태 유지 로그인
    SET_USER_INFO_AUTO(state, data) {
      // state.userId.push(data);
      // state.userName.push(data.userName);
      state.userName = data.userName;
      state.accessToken = data.accessToken;
      state.refreshToken = data.refreshToken;
      console.log(state.accessToken);
      console.log(state.refreshToken);
    },
    // 로그인 상태 유지X 로그인
    SET_USER_INFO_NOT_AUTO(state, data) {
      state.userName = data.userName;
      state.accessToken = data.accessToken;
      state.refreshToken = ""; // persistent cookies에 저장하기 @@@@@@@@@@@@@@
      console.log(state.accessToken);
      console.log(state.refreshToken);
    },
  },
  actions: {
    // 로그인
    // login_auto(data) {
    //     commit("SET_USER_INFO_AUTO", data);
    // }
    // set_user({ commit }, params) {
    //   API({
    //     // url: "http://localhost:8080/api/user/login",
    //     // url: `/user/login`,
    //     url: api_url.accounts.login(),
    //     method: "POST",
    //     data: params,
    //   })
    //     .then(({ data }) => {
    //       // console.log(data);
    //       if (data.autoFlag) {
    //         commit("SET_USER_INFO_AUTO", data);
    //       } else {
    //         commit("SET_USER_INFO_NOT_AUTO", data);
    //       }
    //     })
    //     .catch((err) => {
    //       console.log("에러내용" + err);
    //     });
    // },
  },
  modules: {},
};

export default userStore;
