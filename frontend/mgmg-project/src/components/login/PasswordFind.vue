<template>
  <div>
    <v-container>
      <div class="loginTitleLableArea">
        <label for="">비밀번호 찾기</label>
      </div>

      <div class="loginBodyLine">
        <div class="loginBodyLabel">
          <label for="">아이디</label>
        </div>
        <div class="loginBodyInput">
          <CustomInput v-model="idPasswordFindInput" />
        </div>
      </div>

      <div class="loginBodyLine">
        <div class="loginBodyLabel">
          <label for="">이메일</label>
        </div>
        <div class="loginBodyInput">
          <CustomInput v-model="emailPasswordFindInput" />
        </div>
      </div>

      <div class="loginButtonLine">
        <div class="loginButton">
          <CustomButton class="loginButtonText" @click="findPw" btnText="비밀번호 찾기" />
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import { findpw } from "@/api/userApi.js";
export default {
  data() {
    return {
      // 커스텀 인풋
      idPasswordFindInput: {
        labelText: "아이디를 입력하세요.",
        rules: [(v) => !!v || "아이디는 필수값입니다."],
        hint: "아이디를 입력하세요.",
        id: "idPasswordFindInput",
      },
      emailPasswordFindInput: {
        labelText: "이메일을 입력하세요.",
        rules: [(v) => !!v || "이메일은 필수값입니다."],
        hint: "이메일을 입력하세요.",
        id: "emailPasswordFindInput",
      },
    };
  },
  methods: {
    async findPw() {
      const userId = document.getElementById("idPasswordFindInput").value;
      const userEmail = document.getElementById("emailPasswordFindInput").value;

      const params = {
        userId: userId,
        userEmail: userEmail,
      };

      await findpw(params)
        .then((res) => {
          console.log(res);
          Swal.fire({
            text: "이메일로 임시 비밀번호를 보냈습니다.",
            icon: "success",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
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

.loginButtonLine {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-bottom: 6%;
  width: 100%;
}

.loginButton {
  width: 80%;
}

.loginButtonText {
  font-size: clamp(0.8rem, 2vw, 1rem);
  width: 100%;
}
</style>
