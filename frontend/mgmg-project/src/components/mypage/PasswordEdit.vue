<template>
  <v-container>
    <v-row>
      <label for="">비밀번호 수정</label>
    </v-row>
    <v-row>
      <hr class="hrStyle" />
    </v-row>
    <v-row>
      <label class="col-4 PasswordEditNoDrag" for="pwPasswordEditInput" id="pwPasswordEditLabel">비밀번호</label>
      <div class="inputStyle col-4">
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
      <div class="col-4"></div>
    </v-row>
    <v-row>
      <label class="col-4 PasswordEditNoDrag" for="pwCheckPasswordEditInput" id="pwCheckPasswordEditLabel">비밀번호 확인</label>
      <div class="inputStyle col-4">
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
      <div class="col-4"></div>
    </v-row>
    <v-row>
      <CustomButton btnText="수정하기" />
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      userPw: "",
      pwValidation: false,
      // 비밀번호인풋
      showPw: false,
      showPwCheck: false,
      password: "Password",
      rules: {
        pwRequired: (v) => this.pwValidationCheck(v) || "영어, 숫자, 특수문자 포함 8자 이상 16자 이하",
        pwCheckRequired: (v) => this.pwCheckValidationCheck(v) || "비밀번호가 일치하지 않습니다.",
      },
      // 비밀번호 정규식 검사
      pwValidationCheck(user_pw) {
        const regPw = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-])[a-zA-Z0-9#?!@$ %^&*-]{8,16}$/;
        if (regPw.test(user_pw)) {
          this.userPw = user_pw;
          return true;
        } else {
          this.pwValidation = false;
        }
      },
      // 비밀번호체크 - 비밀번호 일치여부 검사
      pwCheckValidationCheck(user_pwcheck) {
        if (this.userPw == user_pwcheck) {
          this.pwValidation = true;
          return true;
        } else {
          this.pwCheckValidation = false;
        }
      },
    };
  },
};
</script>

<style scoped>
.hrStyle {
  border: 0.01rem solid #000000;
  width: 80rem;
}
</style>
