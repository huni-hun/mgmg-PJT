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
import Swal from "sweetalert2";
import api_url from "@/api/index.js";

export default {
  data() {
    return {
      // api로 받아올 유저 아이디
      getUserId: "",
      // 커스텀 인풋
      nameIdFindInput: {
        labelText: "이름을 입력하세요.",
        rules: [(v) => !!v || "이름은 필수값입니다."],
        hint: "이름을 입력하세요.",
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
    test() {
      console.log(process.env.VUE_APP_API_URL);
    },
    findId() {
      const userName = document.getElementById("nameIdFindInput").value;
      const userEmail = document.getElementById("emailIdFindInput").value;
      // const userId = "iddesu";

      // var findidLst = { userName: userName, email: userEmail };
      // console.log(findidLst);
      // this.$store.dispatch("find_id", userName, userEmail);
      console.log(userName, userEmail);
      axios
        .get(api_url.accounts.find_id(), {
          params: {
            userName: userName,
            email: userEmail,
          },
        })
        .then((response) => {
          if (response.data.statusCode == 200) {
            this.getUserId = response.data.userId;
            Swal.fire({
              // toast: true,
              // title: "ID 누락",
              text: "가입하신 아이디는\n" + response.data.userId + "입니다.",
              icon: "success",
              // iconColor: "#000000",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
              // },
            });
          } else if (response.data.statusCode == 401) {
            Swal.fire({
              // toast: true,
              // title: "ID 누락",
              text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
              icon: "warning",
              // iconColor: "#000000",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
              // },
            });
          }
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            // toast: true,
            // title: "ID 누락",
            text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
            icon: "warning",
            // iconColor: "#000000",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
            // },
          });
        });
    },
  },
};
</script>

<style></style>
