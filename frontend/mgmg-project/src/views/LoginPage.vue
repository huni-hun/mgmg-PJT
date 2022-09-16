<template>
  <div>
    <h1>LoginPage입니다.</h1>
    <LogIn v-if="loginOrder == 1" />
    <IdFind v-if="loginOrder == 2" />
    <PasswordFind v-if="loginOrder == 3" />
    <v-container>
      <v-row>
        <label for="login" class="noDrag" @click="moveToLogIn" v-if="loginOrder != 1">로그인</label>
        <div class="verticalLine" v-if="loginOrder != 1"></div>
        <label for="findId" class="noDrag" @click="moveToIdFind" v-if="loginOrder != 2">아이디 찾기</label>
        <div class="verticalLine" v-if="loginOrder != 2"></div>
        <label for="findPw" class="noDrag" @click="moveToPasswordFind" v-if="loginOrder != 3">비밀번호 찾기</label>
        <div class="verticalLine" v-if="loginOrder != 3"></div>
        <label for="signup" class="noDrag" @click="moveToSignupPage">회원가입</label>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import LogIn from "@/components/login/LogIn.vue";
import IdFind from "@/components/login/IdFind.vue";
import PasswordFind from "@/components/login/PasswordFind.vue";

export default {
  components: { LogIn, IdFind, PasswordFind },
  data() {
    return {
      loginOrder: 1,
    };
  },
  methods: {
    moveToLogIn() {
      this.loginOrder = 1;
    },
    moveToIdFind() {
      this.loginOrder = 2;
    },
    moveToPasswordFind() {
      this.loginOrder = 3;
    },
    moveToSignupPage() {
      // 회원가입으로 페이지 이동
    },
  },
};
</script>

<style scoped>
.noDrag {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.verticalLine {
  background-color: black;
  /* border: 5px solid black; */
  width: 0.1rem;
  height: 2em;
}
</style>
