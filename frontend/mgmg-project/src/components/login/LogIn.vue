<template>
  <div>
    <v-container>
      <div class="loginTitleLableArea">
        <label for="">로그인</label>
      </div>

      <div class="loginBodyLine">
        <div class="loginBodyLabel">
          <label for="idLoginInput" class="noDrag" id="idLoginLabel">아이디</label>
        </div>
        <div class="loginBodyInput">
          <CustomInput v-model="idLoginInput" />
        </div>
      </div>

      <div class="loginBodyLine">
        <div class="loginBodyLabel">
          <label for="pwLoginInput" class="noDrag" id="pwLoginLabel">비밀번호</label>
        </div>
        <div class="loginBodyInput">
          <div class="inputStyle">
            <v-text-field :rules="[pwRequired]" :type="password" label="비밀번호를 입력하세요." name="input-10-2"
              hint="비밀번호를 입력하세요." value="" class="input-group--focused" single-line outlined id="pwLoginInput"
              @keyup.enter="login"></v-text-field>
          </div>
        </div>
      </div>
      <div class="loginButtonLine">
        <div class="loginCheckBox">
          <v-checkbox v-model="loginNext" :label="`로그인 상태 유지하기`"></v-checkbox>
        </div>
        <div class="loginButton">
          <CustomButton class="loginButtonText" @click="login" btnText="로그인" />
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { autoLogin } from "@/api/userApi.js";
import { logIn } from "@/api/userApi.js";
import Swal from "sweetalert2";

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
  mounted() {
    this.autoLogin();
  },
  methods: {
    ...mapActions("userStore", ["setUserInfoAuto", "setUserInfoNotAuto"]),

    async autoLogin() {
      //쿠키 있는지 확인
      if (this.$cookies.isKey("autoLoginCookie")) {
        var refreshToken = this.$cookies.get("autoLoginCookie");
        var userId = this.$cookies.get("userIdCookie");

        const request = {
          refreshToken: refreshToken,
          userId: userId,
        };

        await autoLogin(request).then((res) => {
          this.loginAuto(res);
          this.$router.push("/main");
        });
      }
    },
    //자동로그인 선택 하고, 안하고 상태관리
    loginAuto(response) {
      this.setUserInfoAuto(response);
      this.$cookies.set("autoLoginCookie", response.refreshToken);
      this.$cookies.set("userIdCookie", response.userId);
    },
    loginNotAuto(response) {
      this.setUserInfoNotAuto(response);
      this.$cookies.remove("autoLoginCookie");
      this.$cookies.remove("userIdCookie");
    },

    //로그인
    async login() {
      const userId = document.getElementById("idLoginInput").value;
      const userPw = document.getElementById("pwLoginInput").value;
      var autoflag = this.loginNext;

      var request = {
        userId: userId,
        password: userPw,
        autoFlag: autoflag,
      };

      await logIn(request)
        .then((res) => {
          res.userId = userId;
          //자동 로그인 선택한 경우
          if (autoflag) {
            this.loginAuto(res);
          } else {
            //자동 로그인 선택 안한 경우
            this.loginNotAuto(res);
          }
          this.$router.push("/main");
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            text: "잠시후 다시 시도해주세요.",
            icon: "warning",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
        });
    },
  },
};
// 회원정보 스토어에 저장
</script>

<style scoped>
.loginTitleLableArea {
  margin: 3% 3% 5% 3%;
  font-size: clamp(1.5rem, 2.5vw, 1.8rem);
}

.loginBodyLine {
  display: flex;
  flex-direction: row;
}

.loginButtonLine {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.loginBodyLabel {
  padding-top: 1%;
  padding-left: 5%;
  width: 30%;
  font-size: clamp(1.1rem, 2vw, 1.2rem);
}

.loginBodyInput {
  padding-right: 5%;
  width: 70%;
}

.loginCheckBox {
  padding-left: 5%;
}

.loginButton {
  padding-right: 5%;
}

.loginButtonText {
  font-size: clamp(0.8rem, 2vw, 1rem);
}

.inputStyle:deep(fieldset) {
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  padding: 0;
}

.noDrag {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

@media (max-width: 375px) {
  .loginCheckBox {
    padding-left: 3%;
  }

  .loginButton {
    padding-right: 3%;
  }

  .loginButtonText {
    padding: 0.4rem 0.7rem 0.4rem 0.7rem;
  }
}

@media (max-width: 300px) {
  .loginCheckBox {
    padding-left: 1%;
  }

  .loginButton {
    padding-right: 1%;
  }

  .loginBodyLabel {
    padding-left: 1%;
  }

  .loginBodyInput {
    padding-right: 1%;
  }

  .loginButtonText {
    padding: 0.4rem 0.5rem 0.4rem 0.5rem;
  }
}
</style>
