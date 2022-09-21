<template>
  <div>
    <v-container>
      <label for="">비밀번호 찾기</label>
      <v-row>
        <v-col>
          <label for="">아이디</label>
        </v-col>
        <v-col>
          <CustomInput v-model="idPasswordFindInput" />
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <label for="">이메일</label>
        </v-col>
        <v-col>
          <CustomInput v-model="emailPasswordFindInput" />
        </v-col>
      </v-row>
      <v-row>
        <CustomButton @click="findPw" btnText="비밀번호 찾기" />
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import swal from "sweetalert2";
import api_url from "@/api/index.js";
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
    findPw() {
      const userId = document.getElementById("idPasswordFindInput").value;
      const userEmail = document.getElementById("emailPasswordFindInput").value;

      axios
        .get(api_url.accounts.find_pw(), {
          userId: userId,
          email: userEmail,
        })
        .then((response) => {
          if (response.data.statusCode == 200) {
            console.log(response.data.message);
            swal.fire({
              // toast: true,
              // title: "ID 누락",
              text: "이메일로 임시 비밀번호를 보냈습니다.",
              icon: "success",
              // iconColor: "#000000",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
              // },
            });
          }
        });
    },
  },
};
</script>

<style></style>
