import api_url from "@/api/index.js";
import Vue from "vue";
import Vuex from "vuex";
import { API } from "@/store";
import Swal from "sweetalert2";

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
    },
    // 로그인 상태 유지X 로그인
    SET_USER_INFO_NOT_AUTO(state, data) {
      state.userName = data.userName;
      state.accessToken = data.accessToken;
      state.refreshToken = ""; // persistent cookies에 저장하기 @@@@@@@@@@@@@@
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
          // console.log(data);
          if (data.autoFlag) {
            commit("SET_USER_INFO_AUTO", data);
          } else {
            commit("SET_USER_INFO_NOT_AUTO", data);
          }
        })
        .catch((err) => {
          console.log("에러내용" + err);
        });
    },
    // 아이디 찾기
    // find_id(params) {
    //   console.log(params);
    //   API({
    //     url: api_url.accounts.find_id(),
    //     method: "GET",
    //     userName: params.userName,
    //     email: params.email,
    //   })
    //     .then(({ data }) => {
    //       console.log(data);
    //       if (data.statusCode == 200) {
    //         // this.foundId = data.userId;
    //         Swal.fire({
    //           text: "가입하신 아이디는\n" + data.userId,
    //           icon: "success",
    //           confirmButtonColor: "#666666",
    //           confirmButtonText: "확인",
    //         });
    //       } else {
    //         Swal.fire({
    //           text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
    //           icon: "warning",
    //           confirmButtonColor: "#666666",
    //           confirmButtonText: "확인",
    //         });
    //       }
    //     })
    //     .catch((err) => {
    //       console.log("에러내용" + err);
    //     });
    // },

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
