<template>
  <div>
    <v-container>
      <label for="">아이디 찾기</label>
      <v-row>
        <v-col>
          <label for="">이름</label>
        </v-col>
        <v-col>
          <CustomInput v-model="nameIdFindInput" />
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <label for="">이메일</label>
        </v-col>
        <v-col>
          <CustomInput v-model="emailIdFindInput" />
        </v-col>
      </v-row>
      <v-row>
        <CustomButton @click="findId" btnText="아이디 찾기" />
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
      // api로 받아올 유저 아이디
      getUserId: "",
      // 커스텀 인풋
      nameIdFindInput: {
        labelText: "아이디를 입력하세요.",
        rules: [(v) => !!v || "아이디는 필수값입니다."],
        hint: "아이디를 입력하세요.",
        id: "nameIdFindInput",
      },
      emailIdFindInput: {
        labelText: "이메일을 입력하세요.",
        rules: [(v) => !!v || "이메일은 필수값입니다."],
        hint: "이메일을 입력하세요.",
        id: "emailIdFindInput",
      },
    };
  },
  methods: {
    test() {},
    findId() {
      const userName = document.getElementById("nameIdFindInput").value;
      const userEmail = document.getElementById("emailIdFindInput").value;
      swal.fire("Title", "Sub Title", "success");
      axios
        .get(this.$store.state.baseurl + "api/user/findid", {
          userName: userName,
          email: userEmail,
        })
        .then((response) => {
          if (response.data.statusCode == 200) {
            this.getUserId = response.data.userId;
          }
        });
    },
  },
};
</script>

<style></style>
