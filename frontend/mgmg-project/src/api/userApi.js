import { API } from "@/store";
import store from "@/store/modules/userStore";

const ACCOUNT = "/user";

async function myCheck(request) {
  let response = await API.post(`${ACCOUNT}` + "/pwcheck", request, {
    headers: {
      // "Content-Type": "multipart/form-data",
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}
async function showUserInfo() {
  let response = await API.get(`${ACCOUNT}` + "/mypage", {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}
async function editUserInfo(request) {
  let response = await API.put(`${ACCOUNT}` + "/mypage", request, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}

export { myCheck, showUserInfo, editUserInfo };

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
