<template>
  <div>
    <v-container>
      <div class="loginTitleLableArea">
        <label for="">아이디 찾기</label>
      </div>

      <div class="loginBodyLine">
        <div class="loginBodyLabel">
          <label class="noDrag" for="">이름</label>
        </div>
        <div class="loginBodyInput">
          <CustomInput v-model="nameIdFindInput" />
        </div>
      </div>

      <div class="loginBodyLine">
        <div class="loginBodyLabel">
          <label for="">이메일</label>
        </div>
        <div class="loginBodyInput">
          <CustomInput v-model="emailIdFindInput" />
        </div>
      </div>

      <div class="loginButtonLine">
        <div class="loginButton">
          <CustomButton class="loginButtonText" @click="findId" btnText="아이디 찾기" />
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import { findId } from "@/api/userApi.js";

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
    async findId() {
      const userName = document.getElementById("nameIdFindInput").value;
      const userEmail = document.getElementById("emailIdFindInput").value;

      const params = {
        userName: userName,
        userEmail: userEmail,
      };

      await findId(params)
        .then((res) => {
          this.getUserId = res.userId;
          Swal.fire({
            text: "가입하신 아이디는\n" + res.userId + "입니다.",
            icon: "success",
            // iconColor: "#000000",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
            // },
          });
        })
        .catch((err) => {
          console.log(err);
          if (err.response.status == 401) {
            Swal.fire({
              text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
              icon: "warning",
              // iconColor: "#000000",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
              // },
            });
          }
        });
    },
    // findId() {
    //   const userName = document.getElementById("nameIdFindInput").value;
    //   const userEmail = document.getElementById("emailIdFindInput").value;
    //   // const userId = "iddesu";

    //   // var findidLst = { userName: userName, email: userEmail };
    //   // console.log(findidLst);
    //   // this.$store.dispatch("find_id", userName, userEmail);
    //   console.log(userName, userEmail);
    //   axios
    //     .get(api_url.accounts.find_id(), {
    //       params: {
    //         userName: userName,
    //         email: userEmail,
    //       },
    //     })
    //     .then((response) => {
    //       if (response.data.statusCode == 200) {
    //         this.getUserId = response.data.userId;
    //         Swal.fire({
    //           // toast: true,
    //           // title: "ID 누락",
    //           text: "가입하신 아이디는\n" + response.data.userId + "입니다.",
    //           icon: "success",
    //           // iconColor: "#000000",
    //           confirmButtonColor: "#666666",
    //           confirmButtonText: "확인",
    //           // },
    //         });
    //       } else if (response.data.statusCode == 401) {
    //         Swal.fire({
    //           // toast: true,
    //           // title: "ID 누락",
    //           text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
    //           icon: "warning",
    //           // iconColor: "#000000",
    //           confirmButtonColor: "#666666",
    //           confirmButtonText: "확인",
    //           // },
    //         });
    //       }
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //       Swal.fire({
    //         // toast: true,
    //         // title: "ID 누락",
    //         text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
    //         icon: "warning",
    //         // iconColor: "#000000",
    //         confirmButtonColor: "#666666",
    //         confirmButtonText: "확인",
    //         // },
    //       });
    //     });
    // },
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
