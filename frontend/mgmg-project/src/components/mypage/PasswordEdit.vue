<template>
  <div class="passwordEditTotalBody">
    <div class="titleLabel">
      <label for="">비밀번호 수정</label>
    </div>

    <div>
      <hr class="hrStyle" />
    </div>

    <div class="passwordEditBody">
      <div class="passwordEditLine">
        <div class="passwordEditBodyLabel">
          <label class="PasswordEditNoDrag" for="pwPasswordEditInput" id="pwPasswordEditLabel">새 비밀번호</label>
        </div>

        <div class="passwordEditBodyContent">
          <div class="inputStyle">
            <v-text-field
              :append-icon="showPw ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.pwRequired]"
              :type="showPw ? 'text' : 'password'"
              label="비밀번호를 입력하세요."
              name="input-10-2"
              hint="비밀번호를 입력하세요."
              value=""
              class="input-group--focused"
              @click:append="showPw = !showPw"
              single-line
              outlined
              id="pwPasswordEditInput"
            ></v-text-field>
          </div>
        </div>
      </div>
      <!-- <div class=""></div> -->
      <!-- </div>

      <div class="passwordEditBody"> -->
      <div class="passwordEditLine">
        <div class="passwordEditBodyLabel">
          <label class="PasswordEditNoDrag" for="pwCheckPasswordEditInput" id="pwCheckPasswordEditLabel">비밀번호 확인</label>
        </div>

        <div class="passwordEditBodyContent">
          <div class="inputStyle">
            <v-text-field
              :append-icon="showPwCheck ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.pwCheckRequired]"
              :type="showPwCheck ? 'text' : 'password'"
              label="비밀번호를 다시 입력하세요."
              name="input-10-2"
              hint="비밀번호를 다시 입력하세요."
              value=""
              class="input-group--focused"
              @click:append="showPwCheck = !showPwCheck"
              single-line
              outlined
              id="pwCheckPasswordEditInput"
            ></v-text-field>
          </div>
        </div>
      </div>
    </div>

    <div class="passwordEditButtonLine">
      <CustomButton class="passwordEditButton" btnText="수정하기" @click="userPasswordEdit" />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Swal from "sweetalert2";
import { editPasswordInfo } from "@/api/userApi.js";

export default {
  data() {
    return {
      userPw: "",
      pwValidation: false,
      pwCheckValidation: false,
      // 비밀번호인풋
      showPw: false,
      showPwCheck: false,
      password: "Password",
      rules: {
        pwRequired: (v) => this.pwValidatedCheck(v) || "영어, 숫자, 특수문자 포함 8자 이상 16자 이하",
        pwCheckRequired: (v) => this.pwCheckValidatedCheck(v) || "비밀번호가 일치하지 않습니다.",
      },
      // 비밀번호 정규식 검사
      pwValidatedCheck(user_pw) {
        const regPw = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-])[a-zA-Z0-9#?!@$ %^&*-]{8,16}$/;
        if (regPw.test(user_pw)) {
          this.userPw = user_pw;
          this.pwValidation = true;
          return true;
        } else {
          this.pwValidation = false;
        }
      },
      // 비밀번호체크 - 비밀번호 일치여부 검사
      pwCheckValidatedCheck(user_pwcheck) {
        if (this.userPw != user_pwcheck) {
          this.pwCheckValidation = false;
        } else {
          this.pwCheckValidation = true;
          return true;
        }
      },
    };
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    async userPasswordEdit() {
      console.log(this.userPw);
      if (this.pwValidation && this.pwCheckValidation) {
        var new_password = this.userPw;

        const request = {
          newPassword: new_password,
        };
        await editPasswordInfo(this.accessToken, request)
          .then((res) => {
            console.log(res);
            Swal.fire({
              text: "비밀번호가 정상적으로 변경되었습니다.",
              icon: "success",
              // iconColor: "#000000",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
            });
            this.$router.push("/my/myinfo");
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
      }
    },
    // userPasswordEdit() {
    //   console.log(this.pwValidation, this.pwCheckValidation, this.userPw);
    //   if (this.pwValidation && this.pwCheckValidation) {
    //     axios
    //       .put(api_url.accounts.password_edit(), {
    //         headers: {
    //           //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 헤더에 토큰
    //           Authorization: `Bearer ${this.$store.state.userStore.accessToken}`,
    //         }, //@@@@@@@@@@@@@@@@@@ 기존 pw는 왜 받지?
    //         newPassword: this.userPw,
    //       })
    //       .then((response) => {
    //         console.log(response);
    //         Swal.fire({
    //           text: "비밀번호가 정상적으로 변경되었습니다.",
    //           icon: "success",
    //           // iconColor: "#000000",
    //           confirmButtonColor: "#666666",
    //           confirmButtonText: "확인",
    //         });
    //       });
    //   } else {
    //     Swal.fire({
    //       text: "입력한 정보를 다시 확인해주세요.",
    //       icon: "warning",
    //       // iconColor: "#000000",
    //       confirmButtonColor: "#666666",
    //       confirmButtonText: "확인",
    //     });
    //   }
    // },
  },
};
</script>

<style scoped>
.passwordEditTotalBody {
  width: 100%;
  padding: 7% 10%;
  display: flex;
  flex-direction: column;
  background-color: white;
}

.titleLabel {
  font-size: clamp(1.2rem, 2.5vw, 1.8rem);
  margin: 3% 0% 0.5% 0%;
}

.passwordEditBody {
  padding: 5% 3% 0% 2%;
}
.passwordEditLine {
  display: flex;
  flex-direction: row;
  margin-bottom: 2%;
}
.passwordEditBodyLabel {
  width: 25%;
}
.passwordEditBodyContent {
  width: 75%;
}
.passwordEditButtonLine {
  width: 100%;
  margin-top: 5%;
  display: flex;
  justify-content: center;
}
.passwordEditButton {
  width: 50%;
}
.passwordEditGenderContent {
  width: 80%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.genderButton {
  width: 49%;
}
.inputStyle:deep(fieldset) {
  /* border-color: rgb(255, 250, 250); */
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
  padding: 0;
}
.hrStyle {
  border: 0.01rem solid #000000;
}
</style>
