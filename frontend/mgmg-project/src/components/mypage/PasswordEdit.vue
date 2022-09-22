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
      <CustomButton btnText="수정하기" @click="userPasswordEdit" />
    </v-row>
  </v-container>
</template>

<script>
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
  methods: {
    async userPasswordEdit() {
      if (this.pwValidation && this.pwCheckValidation) {
        var new_password = this.userPw;

        const request = {
          newPassword: new_password,
        };

        let response = await editPasswordInfo(request);
        console.log("응답 데이터", response);
        if (response.statusCode == 200) {
          Swal.fire({
            text: "비밀번호가 정상적으로 변경되었습니다.",
            icon: "success",
            // iconColor: "#000000",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
          this.$router.push("/my/myinfo");
        }
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
.hrStyle {
  border: 0.01rem solid #000000;
  width: 80rem;
}
</style>
