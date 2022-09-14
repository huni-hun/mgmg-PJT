<template>
  <div>
    <label for="idLoginInput" id="idLoginLabel">아이디</label>
    <input type="text" id="idLoginInput" placeholder="아이디를 입력하세요." />
    <label for="pwLoginInput" id="pwLoginLabel">비밀번호</label>
    <input type="password" id="pwLoginInput" placeholder="비밀번호를 입력하세요." />
    <button id="loginBtn" @click="login">로그인</button>
    <input type="checkbox" v-model="loginNextCheck" value="true" id="loginNextCheck" />
    <label for="loginNextCheck" id="loginNextLabel">로그인 상태 유지하기</label>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      loginNext: false,
    };
  },
  methods: {
    login() {
      let userId = document.getElementById("idLoginInput");
      let userPw = document.getElementById("pwLoginInput");

      axios
        .post(this.$store.state.baseurl + "auth/login/normal", {
          userId: userId,
          password: userPw,
        })
        .then((response) => {
          if (response.data.statusCode == 200) {
            this.$Store.state.userStore.isLogin = true;
          }
        });
    },
  },
};
// 회원정보 스토어에 저장
</script>

<style></style>

