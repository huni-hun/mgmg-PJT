import axios from "axios";
const baseURL = process.env.VUE_APP_API_URL + "api/user/";

const methods = {
  idDoubleCheck(user_id) {
    console.log(baseURL + "idcheck");
    console.log(user_id);
    var response = "";
    axios
      .get(baseURL + "idcheck", {
        params: {
          userId: user_id,
        },
      })
      .then((response) => {
        //   if (response.data.statusCode == "200") {
        //     console.log(response.data);
        //     return response.data;
        //   } else {
        //     return 0;
        //   }
        // this.response = response.data;
        // console.log(response.data);
        // console.log("here", response.data.message);
        return response.data;
      });
    // console.log("igo..." + this.response.data.message);
    return response.data;
  },
};
// const methods = {
// };

export default {
  // idDoubleCheck,
  install(Vue) {
    Vue.prototype.$idDoubleCheck = methods.idDoubleCheck;
  },
};

// import axios from "./index.js";
// const DIARY = "diary";
// function diaryWrite(userData) {
//   return axios.post(DIARY, userData);
// }
// function monthlyDiaryList(pid) {
//   return axios.get(`${DIARY}/month/${pid}`);
// }
// export { diaryWrite, monthlyDiaryList };
