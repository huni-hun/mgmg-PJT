import { API } from "@/store";
import store from "@/store/modules/userStore";

const ACCOUNT = "/user";

//아이디 중복 검사
async function idDoubleCheck(params) {
  let response = await API.get(`${ACCOUNT}` + "/idcheck", {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
    params: {
      userId: params.userId,
    },
  });
  return response.data;
}
//이메일 중복 검사
async function emailDoubleCheck(request) {
  let response = await API.post(`${ACCOUNT}` + "/email", request, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}
//이메일 인증번호 검사
async function emailNumCheck(params) {
  let response = await API.get(`${ACCOUNT}` + "/emailcheck", {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
    params: {
      email: params.user_email,
      emailNum: params.user_emailNum,
    },
  });
  return response.data;
}
//회원가입
async function signUp(request) {
  let response = await API.post(`${ACCOUNT}` + "/regist", request, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}

//마이페이지 본인인증
async function myCheck(request) {
  let response = await API.post(`${ACCOUNT}` + "/pwcheck", request, {
    headers: {
      // "Content-Type": "multipart/form-data",
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}
//마이페이지 내 정보 확인
async function showUserInfo() {
  let response = await API.get(`${ACCOUNT}` + "/mypage", {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}
//마이페이지 회원 정보 수정
async function editUserInfo(request) {
  let response = await API.put(`${ACCOUNT}` + "/mypage", request, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}
//마이페이지 비밀번호 수정
async function editPasswordInfo(request) {
  let response = await API.put(`${ACCOUNT}` + "/mypage/password", request, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}
//마이페이지 회원 탈퇴
async function deleteUser(request) {
  let response = await API.delete(`${ACCOUNT}` + "/mypage", request, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}
//일반로그인
async function logIn(request) {
  let response = await API.post(`${ACCOUNT}` + "/login", request);
  return response.data;
}
//자동 로그인
async function autoLogin(request) {
  let response = await API.post(`${ACCOUNT}` + "/autologin", request);
  return response.data;
}

export { idDoubleCheck, emailDoubleCheck, emailNumCheck, signUp, myCheck, showUserInfo, editUserInfo, editPasswordInfo, deleteUser, logIn, autoLogin };

//----------------------------------------------------------------
// import axios from "axios";
// const baseURL = process.env.VUE_APP_API_URL + "api/user/";

// const methods = {
//   idDoubleCheck(user_id) {
//     console.log(baseURL + "idcheck");
//     console.log(user_id);
//     var response = "";
//     axios
//       .get(baseURL + "idcheck", {
//         params: {
//           userId: user_id,
//         },
//       })
//       .then((response) => {
//         //   if (response.data.statusCode == "200") {
//         //     console.log(response.data);
//         //     return response.data;
//         //   } else {
//         //     return 0;
//         //   }
//         // this.response = response.data;
//         // console.log(response.data);
//         // console.log("here", response.data.message);
//         return response.data;
//       });
//     // console.log("igo..." + this.response.data.message);
//     return response.data;
//   },
// };
// // const methods = {
// // };

// export default {
//   // idDoubleCheck,
//   install(Vue) {
//     Vue.prototype.$idDoubleCheck = methods.idDoubleCheck;
//   },
// };
