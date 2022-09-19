import api_url from "@/api/index.js";
import Vue from "vue";
import Vuex from "vuex";
import { API } from "@/store";

Vue.use(Vuex);

const userStore = {
  state: {
    // 로그인 시 변경되는 항목
    isLogin: false,
    userId: "",
    userPw: "",
    userName: "",

    // 회원가입 시 잠시 저장
    interestMusicLstSignup: [],
    interestGiftLstSignup: [],
  },
  getters: {},
  mutations: {
    SET_USER_INFO(state, data) {
      // state.userId.push(data);
      // state.userName.push(data.userName);
      state.userName = data.userName;
    },
  },
  actions: {
    // 로그인
    set_user({ commit }, params) {
      API({
        // url: "http://localhost:8080/api/user/login",
        // url: `/user/login`,
        url: api_url.accounts.login(),
        method: "POST",
        data: params,
      })
        .then(({ data }) => {
          console.log(data);
          commit("SET_USER_INFO", data);
        })
        .catch((err) => {
          console.log("에러내용" + err);
        });
    },
    // 이메일 인증
    // send_email_code(params) {
    //   API({
    //     url: api_url.accounts.accounts.email,
    //     metho
    //   })
    // }
  },
  modules: {},
};

export default userStore;
