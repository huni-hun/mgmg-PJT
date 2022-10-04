<template>
  <div class="loginTotalBody">
    <div class="titleBody" v-if="loginOrder == 1">
      <div class="titleLogoMGArea">
        <img class="titleLogoMG" src="../../src/assets/logo/logo_mg.png" alt="" />
      </div>
      <div class="titleLogoTextArea">
        <img class="titleLogoText" src="../../src/assets/logo/logo_only_text.png" alt="" />
      </div>
    </div>
    <div class="dummyTitleMargin" v-if="loginOrder != 1"></div>

    <div class="loginBody">
      <router-view></router-view>
    </div>
    <div class="loginFooterArea">
      <div class="footerLabel" v-if="loginOrder != 1" @click="moveToLogIn">
        <router-link to="/login" class="noDrag footerLabelDecoration">
          <div><label for="">로그인</label></div>
        </router-link>
      </div>

      <div class="footerLabel" v-if="loginOrder != 2" @click="moveToIdFind">
        <router-link to="/login/findid" class="noDrag footerLabelDecoration">
          <div><label for="">아이디 찾기</label></div>
        </router-link>
      </div>

      <div class="footerLabel" v-if="loginOrder != 3" @click="moveToPasswordFind">
        <router-link to="/login/findpw" class="noDrag footerLabelDecoration">
          <div><label for="">비밀번호 찾기</label></div>
        </router-link>
      </div>

      <div class="footerLabel">
        <label for="signup" class="noDrag" @click="moveToSignupPage">회원가입</label>
      </div>
    </div>
  </div>
</template>

<script>
export default {
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
.loginTotalBody {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.loginBody {
  background-color: white;
  width: 40%;
  box-shadow: 0px 0px 20px 20px rgba(23, 50, 97, 0.2);
  border-radius: 15px;
}

.dummyTitleMargin {
  height: 20vh;
}

.titleBody {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 10vh;
  margin-bottom: 1%;
  width: 100%;
}

.titleLogoMGArea {
  width: 7%;
}

.titleLogoMG {
  width: 100%;
}

.titleLogoTextArea {
  width: 15%;
}

.titleLogoText {
  width: 100%;
}

.loginFooterArea {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.footerLabel {
  display: block;
  margin: 1% 1% 1% 0;
  text-align: center;
}

.footerLabelDecoration {
  color: black;
  text-decoration: none;
}

.noDrag {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.verticalLine {
  background-color: black;
  width: 0.1rem;
  height: 2em;
}

@media (max-width: 991px) {
  .dummyTitleMargin {
    height: 15vh;
  }

  .loginBody {
    width: 80%;
  }

  .footerLabel {
    width: 13%;
  }

  .titleLogoMGArea {
    width: 8%;
  }

  .titleLogoTextArea {
    width: 18%;
  }
}

@media (max-width: 767px) {
  .loginBody {
    width: 80%;
  }

  .footerLabel {
    width: 16%;
  }

  .titleLogoMGArea {
    width: 10%;
  }

  .titleLogoTextArea {
    width: 20%;
  }
}

@media (max-width: 575px) {
  .loginBody {
    width: 100%;
  }

  .footerLabel {
    width: 30%;
  }

  .titleLogoMGArea {
    width: 20%;
  }

  .titleLogoTextArea {
    width: 40%;
  }
}
</style>
