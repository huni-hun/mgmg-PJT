<template>
  <div>
    <img class="titleLogoMG" src="../../src/assets/logo/logo_mg.png" alt="" />
    <img class="titleLogoText" src="../../src/assets/logo/logo_only_text.png" alt="" />

    <router-view></router-view>
    <!-- <LogIn v-if="loginOrder == 1" />
    <IdFind v-if="loginOrder == 2" />
    <PasswordFind v-if="loginOrder == 3" /> -->
    <v-container>
      <v-row>
        <div v-if="loginOrder != 1" @click="moveToLogIn">
          <router-link to="/login" class="noDrag">
            <div><label for="">로그인</label></div>
          </router-link>
        </div>

        <div v-if="loginOrder != 2" @click="moveToIdFind">
          <router-link to="/login/findid" class="noDrag">
            <div><label for="">아이디 찾기</label></div>
          </router-link>
        </div>

        <div v-if="loginOrder != 3" @click="moveToPasswordFind">
          <router-link to="/login/findpw" class="noDrag">
            <div><label for="">비밀번호 찾기</label></div>
          </router-link>
        </div>

        <label for="signup" class="noDrag" @click="moveToSignupPage">회원가입</label>

        <!-- <div class="verticalLine" v-if="loginOrder != 1"></div>
        <label for="findId" class="noDrag" @click="moveToIdFind" v-if="loginOrder != 2">아이디 찾기</label>
        <div class="verticalLine" v-if="loginOrder != 2"></div>
        <label for="findPw" class="noDrag" @click="moveToPasswordFind" v-if="loginOrder != 3">비밀번호 찾기</label>
        <div class="verticalLine" v-if="loginOrder != 3"></div> -->
      </v-row>
    </v-container>
  </div>
</template>

<script>
// import LogIn from "@/components/login/LogIn.vue";
// import IdFind from "@/components/login/IdFind.vue";
// import PasswordFind from "@/components/login/PasswordFind.vue";

export default {
  // components: { LogIn, IdFind, PasswordFind },
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
      this.$router.push("/signup");
    },
  },
};
</script>

<style scoped>
.titleLogoMG {
  width: 7vw;
}
.titleLogoText {
  width: 18vw;
}
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
