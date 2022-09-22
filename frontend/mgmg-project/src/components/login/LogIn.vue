<template>
  <div>
    <v-container>
      <v-row>
        <v-col> <label for="idLoginInput" class="noDrag" id="idLoginLabel">아이디</label></v-col>
        <v-col>
          <CustomInput v-model="idLoginInput" />
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <label for="pwLoginInput" class="noDrag" id="pwLoginLabel">비밀번호</label>
        </v-col>
        <v-col>
          <div class="inputStyle">
            <v-text-field
              :rules="[pwRequired]"
              :type="password"
              label="비밀번호를 입력하세요."
              name="input-10-2"
              hint="비밀번호를 입력하세요."
              value=""
              class="input-group--focused"
              single-line
              outlined
              id="pwLoginInput"
              @keyup.enter="login"
            ></v-text-field>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-checkbox v-model="loginNext" :label="`로그인 상태 유지하기`"></v-checkbox>
        </v-col>
        <v-col>
          <CustomButton @click="login" btnText="로그인" />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import api_url from "@/api/index.js";
import Swal from "sweetalert2";
// import axios from "axios";
// import api from "@/api/index.js";
// import

export default {
  data() {
    return {
      loginNext: false,
      //커스텀 인풋
      idLoginInput: {
        labelText: "아이디를 입력하세요.",
        rules: [(v) => !!v || "아이디는 필수값입니다."],
        hint: "아이디를 입력하세요.",
        id: "idLoginInput",
      },
      // 비밀번호 인풋
      password: "Password",
      pwRequired: (v) => !!v || "비밀번호는 필수값입니다.",
    };
  },
  methods: {
    test() {
      console.log(this.loginNext);
    },
    loginAuto(data) {
      this.$store.commit("SET_USER_INFO_AUTO", data);
    },
    loginNotAuto(data) {
      this.$store.commit("SET_USER_INFO_NOT_AUTO", data);
    },
    login() {
      const userId = document.getElementById("idLoginInput").value;
      const userPw = document.getElementById("pwLoginInput").value;
      var autoflag = this.loginNext;

      let loginLst = {
        userId: userId,
        password: userPw,
        autoFlag: this.loginNext,
      };
      console.log(loginLst);
      console.log(api_url.accounts.login());
      // 여기서 자동로그인 분기하기
      // 자동로그인 하는 경우
      if (this.loginNext) {
        axios
          .post(api_url.accounts.login(), {
            userId: userId,
            password: userPw,
            autoFlag: this.loginNext,
          })
          .then((response) => {
            if (response.data.statusCode == 200) {
              console.log(response.data);
              this.loginAuto(response.data);
              this.$router.push("/main");
            }
          })
          .catch((err) => {
            console.log(err);
            Swal.fire({
              text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
              icon: "warning",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
            });
          });
      } else {
        // 자동로그인 하지 않는 경우
        axios
          .post(api_url.accounts.login(), {
            userId: userId,
            password: userPw,
            autoFlag: autoflag,
          })
          .then((response) => {
            if (response.data.statusCode == 200) {
              console.log(response.data);
              this.loginAuto(response.data);
              this.$router.push("/main");
            }
          })
          .catch((err) => {
            console.log(err);
            Swal.fire({
              text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
              icon: "warning",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
            });
          });
      }

      // console.log(this.$store.dispatch("set_user", loginLst));
      // axios
      //   .post(api.accounts.login, {
      //     userId: userId,
      //     password: userPw,
      //     autoflag: this.loginNext,
      //   })
      //   .then((response) => {
      //     if (response.data.statusCode == 200) {
      //       // this.$Store.state.userStore.isLogin = true;
      //       console.log(userId);
      //       // 사용자 정보 받아서 저장해두고, 메인페이지로 이동
      //     } else {
      //       console.log("error");
      //     }
      //   });
    },
  },
};
// 회원정보 스토어에 저장
</script>

<style scoped>
.inputStyle:deep(fieldset) {
  /* border-color: rgb(255, 250, 250); */
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  padding: 0;
}
.noDrag {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
</style>
