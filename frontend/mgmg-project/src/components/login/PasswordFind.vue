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

      swal.fire("Title", "Sub Title", "success");
      axios
        .get(this.$store.state.baseurl + "api/user/findid", {
          userId: userId,
          email: userEmail,
        })
        .then((response) => {
          if (response.data.statusCode == 200) {
            console.log(response.data.message);
          }
        });
    },
  },
};
</script>

<style></style>
