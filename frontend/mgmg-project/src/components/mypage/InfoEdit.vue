<template>
  <v-container>
    <v-row>
      <label for="" @click="firstProcess">회원 정보 변경</label>
    </v-row>
    <v-row>
      <hr class="hrStyle" />
    </v-row>
    <v-row>
      <v-col><label for="">이름</label></v-col>
      <CustomInput v-model="nameInfoEditInput" class="" />
    </v-row>
    <v-row>
      <v-col><label for="">아이디</label></v-col>
      <label for="">{{ userId }}</label>
    </v-row>
    <v-row>
      <v-col><label for="">이메일</label></v-col>
      <CustomInput v-model="emailInfoEditInput" class="" />
    </v-row>
    <v-row>
      <v-col><label for="">생년월일</label></v-col>
      <div class="">
        <v-menu v-model="menu2" :close-on-content-click="false" :nudge-right="40" transition="scale-transition" offset-y min-width="auto">
          <template v-slot:activator="{ on, attrs }">
            <v-text-field single-line outlined v-model="date" class="inputStyle" append-icon="mdi-calendar" readonly v-bind="attrs" v-on="on" id="birthSignupInput"></v-text-field>
          </template>
          <v-date-picker v-model="date" no-title @input="menu2 = false"></v-date-picker>
        </v-menu>
      </div>
    </v-row>
    <v-row>
      <label class="col-4" for="" id="genderInfoEditLabel">성별</label>
      <CustomButton class="col-3" :class="{ selectedGender: userGenderNum == 1 }" @click="changeGender(1)" btnText="남자" />
      <CustomButton class="col-3" :class="{ selectedGender: userGenderNum == 2 }" @click="changeGender(2)" btnText="여자" />
      <div class="col-4"></div>
    </v-row>
    <v-row>
      <CustomButton btnText="수정하기" @click="userInfoEdit" />
    </v-row>
  </v-container>
</template>

<script>
import { showUserInfo } from "@/api/userApi.js";
import { editUserInfo } from "@/api/userApi.js";
import Swal from "sweetalert2";

export default {
  data() {
    return {
      nameValidation: false,
      userName: "",
      emailValidation: false,
      userEmail: "",
      userBirth: "",
      userGender: "",
      userGenderNum: 0,
      // 달력 마운트로 기존 유저 값 넣어주기
      date: new Date("2000-01-01").toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      //이름 커스텀 인풋
      nameInfoEditInput: {
        labelText: "이름을 입력하세요.",
        rules: [
          (v) => !!v || "이름은 필수값입니다.",
          (v) => /^[ㄱ-ㅎ|가-힣|a-z|A-Z|]+$/.test(v) || "이름은 한글과 영어만 입력 받습니다.",
          (v) => this.nameValidationCheck(v) || "이름은 한 글자 이상입니다.",
        ],
        hint: "이름을 입력하세요.",
        id: "nameInfoEditInput",
        input: "",
      },
      // 이메일 커스텀 인풋
      emailInfoEditInput: {
        labelText: "이메일을 입력하세요.",
        rules: [(v) => !!v || "이메일은 필수값입니다.", (v) => this.emailValidationCheck(v) || "이메일 형식을 확인해주세요."],
        hint: "이메일을 입력하세요.",
        id: "emailInfoEditInput",
        input: "",
      },
    };
  },
  // async created() {
  //   let response = await showUserInfo();
  //   console.log(response);
  //   this.date = response.birth;
  //   this.userBirth = response.birth;
  //   this.userEmail = response.email;
  //   this.userGender = response.gender;
  //   this.userId = response.userId;
  //   this.userName = response.userName;
  // },
  // mounted: {
  //   defGenderNum() {
  //     if (this.userGender == "여자") {
  //       this.userGenderNum = 2;
  //     } else if (this.userGender == "남자") {
  //       this.userGenderNum = 1;
  //     }
  //   },
  // },
  mounted() {
    // this.test();
    this.firstProcess();
  },
  methods: {
    test() {
      this.nameInfoEditInput.input = "kimminyoung";
      this.emailInfoEditInput.input = "min4849@com";
      this.date = "1222-02-22";
      this.userName = "민영김";
      this.changeGender(1);
      this.userId = "qwer";
      console.log(this.userGender, this.userGenderNum);
    },
    async firstProcess() {
      let response = await showUserInfo();
      console.log(response);
      this.date = response.birth;
      this.userBirth = response.birth;
      this.userEmail = response.email;
      this.userId = response.userId;
      this.userName = response.userName;

      this.genToNum(response.gender);
      this.nameInfoEditInput.input = this.userName;
      this.emailInfoEditInput.input = this.userEmail;
    },
    // 이름 정규식 검사
    nameValidationCheck(user_name) {
      const regName = /^\D{1,}$/;
      if (regName.test(user_name)) {
        this.userName = user_name;
        this.nameValidation = true;
        return true;
      } else {
        this.nameValidation = false;
      }
    },
    // 이메일 정규식 검사
    emailValidationCheck(user_email) {
      const regEmail = /^[0-9a-zA-Z]([_]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      if (regEmail.test(user_email)) {
        this.userEmail = user_email;
        this.emailValidation = true;
        return true;
      } else {
        this.emailValidation = false;
      }
    },
    // 성별 선택
    changeGender(gender) {
      this.userGenderNum = gender;
      if (this.userGenderNum == 1) {
        this.userGender = "남자";
      } else if (this.userGenderNum == 2) {
        this.userGender = "여자";
      }
      this.GenderValidation = true;
    },
    genToNum(gender) {
      if (gender == "여자") {
        this.userGender = "여자";
        this.userGenderNum = 2;
      } else if (gender == "남자") {
        this.userGender = "남자";
        this.userGenderNum = 1;
      }
    },

    async userInfoEdit() {
      if (this.nameValidation && this.emailValidation) {
        var user_email = this.userEmail;
        var user_birth = this.date;
        var user_name = this.userName;
        var user_gender = this.userGender;

        const request = {
          email: user_email,
          birth: user_birth,
          name: user_name,
          gender: user_gender,
        };

        let response = await editUserInfo(request);
        console.log("응답 데이터", response);
        if (response.statusCode == 200) {
          Swal.fire({
            text: "회원 정보가 정상적으로 변경되었습니다.",
            icon: "success",
            // iconColor: "#000000",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
          this.$router.push("/my/myinfo");
        }
      }
    },
    // // 버튼 누르면, 이름 이메일 값 확인해서 axios 실행
    // userInfoEdit() {
    //   if (this.nameValidation && this.emailValidation) {
    //     console.log("valid");
    //     console.log(this.userName, this.userEmail, this.date, this.userGender);
    //     axios
    //       .put(api_url.accounts.mypage_show_edit_delete(), {
    //         headers: {
    //           //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 헤더에 토큰
    //           Authorization: `Bearer ${this.$store.state.userStore.accessToken}`,
    //         },
    //         email: this.userEmail,
    //         birth: this.date,
    //         name: this.userName,
    //         gender: this.userGender,
    //       })
    //       .then((response) => {
    //         console.log(response);
    //         Swal.fire({
    //           text: "회원 정보가 정상적으로 변경되었습니다.",
    //           icon: "success",
    //           // iconColor: "#000000",
    //           confirmButtonColor: "#666666",
    //           confirmButtonText: "확인",
    //         });
    //       })
    //       .catch((err) => {
    //         console.log(err);
    //         Swal.fire({
    //           text: "회원 정보 변경에 실패했습니다.",
    //           icon: "warning",
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
.inputStyle:deep(fieldset) {
  /* border-color: rgb(255, 250, 250); */
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
  padding: 0;
}
.selectedGender {
  background-color: black;
  color: white;
}
</style>
