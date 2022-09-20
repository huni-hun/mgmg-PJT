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
// import axios from "axios";
// import swal from "sweetalert2";
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
    test() {
      console.log(process.env.VUE_APP_API_URL);
    },
    findId() {
      const userName = document.getElementById("nameIdFindInput").value;
      const userEmail = document.getElementById("emailIdFindInput").value;
      // const userId = "iddesu";

      var findidLst = { userName: userName, email: userEmail };
      console.log(findidLst);
      this.$store.dispatch("find_id", userName, userEmail);
      // axios
      //   .get(this.$store.state.baseurl + "api/user/findid", {
      //     userName: userName,
      //     email: userEmail,
      //   })
      //   .then((response) => {
      //     if (response.data.statusCode == 200) {
      //       this.getUserId = response.data.userId;
      //       swal.fire({
      //         // toast: true,
      //         // title: "ID 누락",
      //         text: "가입하신 아이디는\n" + userId,
      //         icon: "success",
      //         // iconColor: "#000000",
      //         confirmButtonColor: "#666666",
      //         confirmButtonText: "확인",
      //         // },
      //       });
      //     } else {
      //       swal.fire({
      //         // toast: true,
      //         // title: "ID 누락",
      //         text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
      //         icon: "warning",
      //         // iconColor: "#000000",
      //         confirmButtonColor: "#666666",
      //         confirmButtonText: "확인",
      //         // },
      //       });
      //     }
      //   });
    },
  },
};
</script>

<style></style>
