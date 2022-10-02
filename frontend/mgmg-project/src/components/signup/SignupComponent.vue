<template>
  <div id="signupComponentApp">
    <!-- <div></div> -->
    <v-container class="signupContainer" id="signupContainer">
      <!-- <v-col> -->
      <v-row class="signupInputCommon">
        <label class="signupNoDrag signupLabel" for="idSignupInput" id="idSignupLabel">아이디</label>
        <div class="signupInput">
          <CustomInput v-model="idSignupInput" />
        </div>
        <div class="signupButton">
          <custom-button class="signupBtnWidth" btnText="중복확인" @click="idDoubleCheck" />
        </div>
      </v-row>
      <v-row>
        <label class="signupNoDrag signupLabel" for="pwSignupInput" id="pwSignupLabel">비밀번호</label>
        <div class="inputStyle signupInput">
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
            id="pwSignupInput"
          ></v-text-field>
        </div>
        <div class="col-4"></div>
      </v-row>
      <v-row>
        <label class="signupNoDrag signupLabel" for="pwCheckSignupInput" id="pwCheckSignupLabel">비밀번호 확인</label>
        <div class="inputStyle signupInput">
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
            id="pwCheckSignupInput"
          ></v-text-field>
        </div>
        <div class="col-4"></div>
      </v-row>
      <v-row>
        <label class="signupNoDrag signupLabel" for="emailSignupInput" id="emailSignupLabel">이메일</label>
        <div class="signupInput">
          <CustomInput v-model="emailSignupInput" />
        </div>
        <div class="signupButton">
          <custom-button class="signupBtnWidth" btnText="인증하기" @click="emailDubleCheck" />
        </div>
      </v-row>
      <v-row>
        <label class="signupNoDrag signupLabel" for="emailcheckSignupInput" id="emailcheckSignupLabel">인증번호</label>
        <div class="signupInput">
          <CustomInput v-model="emailcheckSignupInput" />
        </div>
        <div class="signupButton">
          <custom-button class="signupBtnWidth" btnText="확인" @click="emailNumCheck" />
        </div>
      </v-row>
      <v-row>
        <label class="signupNoDrag signupLabel" for="nameSignupInput" id="nameSignupLabel">이름</label>
        <div class="signupInput">
          <CustomInput v-model="nameSignupInput" />
        </div>
        <div class="col-4"></div>
      </v-row>
      <v-row>
        <label class="signupNoDrag signupLabel" for="birthSignupInput" id="birthSignupLabel">생년월일</label>
        <div class="signupInput">
          <v-menu v-model="menu2" :close-on-content-click="false" :nudge-right="40" transition="scale-transition" offset-y min-width="auto">
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                single-line
                outlined
                v-model="date"
                class="inputStyle"
                append-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
                id="birthSignupInput"
                :change="birthCheck()"
              ></v-text-field>
            </template>
            <v-date-picker
              v-model="date"
              no-title
              @input="menu2 = false"
              :weekday-format="getDay"
              :month-format="getMonth"
              :title-date-format="getMonth"
              :header-date-format="getHeaderTitleMonth"
            ></v-date-picker>
          </v-menu>
        </div>
        <div class="col-4"></div>
      </v-row>
      <v-row>
        <label class="signupNoDrag signupGenderLabel" for="" id="genderSignupLabel">성별</label>
        <div class="genderInputLine">
          <CustomButton class="signupGenderButton" :class="{ selectedGender: userGenderNum == 1 }" @click="changeGender(1)" btnText="남자" />
          <CustomButton class="signupGenderButton" :class="{ selectedGender: userGenderNum == 2 }" @click="changeGender(2)" btnText="여자" />
        </div>
        <div class="col-4"></div>
      </v-row>
      <v-row class="ruleCheckTitle">
        <label for="" class="signupNoDrag ruleCheckTitleLabel">약관 동의</label>
      </v-row>
      <v-row class="ruleCheckBody">
        <label for="">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Quisquam necessitatibus obcaecati dolore molestiae ad debitis perspiciatis consequuntur doloribus ex ratione consectetur eaque quo
          maiores quas ipsa, nihil, facere saepe maxime.
        </label>
      </v-row>
      <v-row class="ruleCheckBox">
        <v-checkbox hide-details v-model="ruleCheck" :label="`약관 내용에 동의합니다.`" @click="ruleApproveCheck"></v-checkbox>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import { idDoubleCheck } from "@/api/userApi.js";
import { emailDoubleCheck } from "@/api/userApi.js";
import { emailNumCheck } from "@/api/userApi.js";

export default {
  props: ["userid", "userpassword", "useremail", "username", "userbirth", "usergender", "userrulecheck"],
  computed: {
    testComputed() {
      return this.userId;
    },
  },
  data() {
    return {
      // 비밀번호인풋
      showPw: false,
      showPwCheck: false,
      password: "Password",
      rules: {
        pwRequired: (v) => this.pwValidationCheck(v) || "영어, 숫자, 특수문자 포함 8자 이상 16자 이하",
        pwCheckRequired: (v) => this.pwCheckValidationCheck(v) || "비밀번호가 일치하지 않습니다.",
      },
      // 달력
      date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000).toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      // 커스텀 인풋
      idSignupInput: {
        labelText: "아이디를 입력하세요.",
        rules: [(v) => !!v || "아이디는 필수값입니다.", (v) => /^[A-Za-z]/.test(v) || "아이디는 영어로 시작해야 합니다.", (v) => this.idValidationCheck(v) || "영어, 숫자 포함 6자 이상 16자 이하"],
        hint: "아이디를 입력하세요.",
        id: "idSignupInput",
      },
      emailSignupInput: {
        labelText: "이메일을 입력하세요.",
        rules: [(v) => !!v || "이메일은 필수값입니다.", (v) => this.emailValidationCheck(v) || "이메일 형식을 확인해주세요."],
        hint: "이메일을 입력하세요.",
        id: "emailSignupInput",
      },
      emailcheckSignupInput: {
        labelText: "인증번호를 입력하세요.",
        rules: [(v) => !!v || "인증번호는 필수값입니다."],
        hint: "이메일로 전송된 인증번호를 입력하세요.",
        id: "emailcheckSignupInput",
      },
      nameSignupInput: {
        labelText: "이름을 입력하세요.",
        rules: [
          (v) => !!v || "이름은 필수값입니다.",
          (v) => /^[ㄱ-ㅎ|가-힣|a-z|A-Z|]+$/.test(v) || "이름은 한글과 영어만 입력 받습니다.",
          (v) => this.nameValidationCheck(v) || "이름은 한 글자 이상입니다.",
        ],
        hint: "이름을 입력하세요.",
        id: "nameSignupInput",
      },
      //인풋 값 가져오기
      userId: "",
      userPw: "",
      userEmail: "",
      userEmailCheck: "",
      userName: "",
      userBirth: "",
      userGenderNum: 0,
      userGender: "",
      //각 항목 정규식 유효성 검사 (boolean 값으로)
      idValidation: false, //
      pwValidation: false,
      emailValidation: false,
      nameValidation: false,
      GenderValidation: false,
      // 중복 검사
      idDuplicated: false,
      emailDuplicated: false,
      //이메일 인증 여부
      emailAuthentication: false,
      // 약관 동의 여부
      ruleCheck: false,
      // 한 항목이라도 false면 다음 버튼 비활성화하기
    };
  },
  methods: {
    getDay(date) {
      const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
      let i = new Date(date).getDay(date);
      return daysOfWeek[i];
    },
    getMonth(date) {
      const monthName = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];

      let i = new Date(date).getMonth(date);
      return monthName[i];
    },
    getHeaderTitleMonth(date) {
      const monthName = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
      let i = new Date(date).getMonth(date);
      const year = new Date(date).getFullYear(date);
      const month = monthName[i];
      return `${year}년 ${month}`;
    },
    finalValidSignup() {
      var finalValidation =
        this.idValidation &&
        this.pwValidation &&
        this.emailValidation &&
        this.nameValidation &&
        this.GenderValidation &&
        this.idDuplicated &&
        this.emailDuplicated &&
        this.emailAuthentication &&
        this.ruleCheck;
      this.$emit("finalValidSignup", finalValidation);

      console.log("자식 최종 유효성 평가", finalValidation);
      console.log("아이디유효성", this.idValidation);
      console.log("비밀번호유효성", this.pwValidation);
      console.log("이메일유효성", this.emailValidation);
      console.log("이름유효성", this.nameValidation);
      console.log("성별유효성", this.GenderValidation);
      console.log("아이디중복", this.idDuplicated);
      console.log("이메일중복", this.emailDuplicated);
      console.log("약관동의", this.ruleCheck);
    },
    //테스트용 메소드
    test() {
      console.log("결과");
    },
    // 아이디 정규식 검사
    idValidationCheck(user_id) {
      const regId = /^[A-Za-z](?=.*?[A-Za-z])(?=.*?[0-9])[a-zA-Z0-9]{5,15}$/;
      if (regId.test(user_id)) {
        this.userId = user_id;
        console.log(user_id);
        this.$emit("useridSignup", user_id);
        this.idValidation = true;
        this.finalValidSignup();
        return true;
      } else {
        this.idValidation = false;
        this.finalValidSignup();
      }
    },
    // 비밀번호 정규식 검사
    pwValidationCheck(user_pw) {
      const regPw = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-])[a-zA-Z0-9#?!@$ %^&*-]{8,16}$/;
      if (regPw.test(user_pw)) {
        this.userPw = user_pw;
        this.$emit("userpasswordSignup", user_pw);
        this.finalValidSignup();
        return true;
      } else {
        this.pwValidation = false;
        this.finalValidSignup();
      }
    },
    // 비밀번호체크 - 비밀번호 일치여부 검사
    pwCheckValidationCheck(user_pwcheck) {
      if (this.userPw == user_pwcheck) {
        this.pwValidation = true;
        this.finalValidSignup();
        return true;
      } else {
        this.pwCheckValidation = false;
        this.finalValidSignup();
      }
    },
    // 이메일 정규식 검사
    emailValidationCheck(user_email) {
      const regEmail = /^[0-9a-zA-Z]([_]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      if (regEmail.test(user_email)) {
        this.$emit("useremailSignup", user_email);
        this.userEmail = user_email;
        this.emailValidation = true;
        return true;
      } else {
        this.emailValidation = false;
        this.finalValidSignup();
      }
    },
    // 이름 정규식 검사
    nameValidationCheck(user_name) {
      const regName = /^\D{1,}$/;
      if (regName.test(user_name)) {
        this.userName = user_name;
        this.nameValidation = true;
        this.finalValidSignup();
        this.$emit("usernameSignup", user_name);
        return true;
      } else {
        this.nameValidation = false;
        this.finalValidSignup();
      }
    },
    // 아이디 api 중복검사
    async idDoubleCheck() {
      if (this.idValidation) {
        var user_id = this.userId;

        const params = {
          userId: user_id,
        };

        await idDoubleCheck(params)
          .then((res) => {
            console.log(res);
            Swal.fire({
              text: "사용 가능한 아이디 입니다.",
              icon: "success",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
            });
            this.idDuplicated = true;
            this.finalValidSignup();
          })
          .catch((err) => {
            console.log(err);
            Swal.fire({
              text: "사용 불가능한 아이디입니다.",
              icon: "warning",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
            });
            this.idDuplicated = false;
            this.finalValidSignup();
          });
        console.log("아이디 중복검사결과", this.idDuplicated);
      }
    },
    // 이메일 api 전송
    async emailDubleCheck() {
      console.log(this.emailValidation);
      console.log(this.userEmail);
      if (this.emailValidation) {
        var user_email = this.userEmail;
        Swal.fire({
          text: "이메일로 인증 번호를 보냈습니다.",
          icon: "success",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });

        const params = {
          email: user_email,
        };
        await emailDoubleCheck(params)
          .then((res) => {
            console.log(res);
            this.emailDuplicated = true;
            this.finalValidSignup();
          })
          .catch((err) => {
            console.log(err);
            Swal.fire({
              text: "사용 불가능한 이메일입니다.",
              icon: "warning",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
            });
            this.emailDuplicated = false;
            this.finalValidSignup();
          });
      }
    },

    // 인증번호 일치여부 확인- api 가져오기 (확인 되면 emailValidation true로 변경) 일치하지 않으면 정규식 사용해서 인증번호가 일치하지 않습니다(가능하면)
    async emailNumCheck() {
      if (this.emailValidation && this.emailDuplicated) {
        var user_email = this.userEmail;
        var user_emailNum = document.getElementById("emailcheckSignupInput").value;

        const params = {
          user_email: user_email,
          user_emailNum: user_emailNum,
        };
        await emailNumCheck(params)
          .then((res) => {
            console.log(res);
            this.emailAuthentication = true;
            this.finalValidSignup();
            Swal.fire({
              text: "인증번호가 확인되었습니다.",
              icon: "success",
              // iconColor: "#000000",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
            });
          })
          .catch((err) => {
            console.log(err);
            Swal.fire({
              text: "인증번호가 틀렸습니다.",
              icon: "warning",
              // iconColor: "#000000",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
              // },
            });
            this.emailAuthentication = false;
            this.finalValidSignup();
          });
      }
    },

    //생일 날짜 emit
    birthCheck() {
      this.$emit("userbirthSignup", this.date);
      // console.log(this.date);
    },
    // 성별 선택
    changeGender(gender) {
      this.userGenderNum = gender;
      if (this.userGenderNum == 1) {
        this.userGender = "남자";
      } else if (this.userGenderNum == 2) {
        this.userGender = "여자";
      }
      this.$emit("usergenderSignup", this.userGender);
      this.GenderValidation = true;
      this.finalValidSignup();
    },
    // 약관 동의 체크 확인
    ruleApproveCheck() {
      this.$emit("ruleCheckSignup", this.ruleCheck);
      this.finalValidSignup();
    },
  },
};
</script>

<style scoped>
.signupContainer {
  width: inherit;
  height: inherit;
}
/* .signupInputCommon {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  height: 10%;
} */
.signupLabel {
  display: block;
  width: 30%;
  padding-left: 10%;
}
.genderInputLine {
  width: 70%;
}
.signupGenderLabel {
  display: block;
  width: 25%;
  padding-left: 10%;
}
.signupInput {
  width: 40%;
}
.signupButton {
  width: 30%;
  padding-left: 3%;
}
.signupBtnWidth {
  width: 50%;
}
.signupGenderButton {
  width: 33%;
  margin-left: 1%;
  margin-right: 1%;
}
/* 약관동의 */
.ruleCheckTitle {
  margin-top: 5%;
  margin-bottom: 1%;
}
.ruleCheckTitleLabel {
  font-size: 1.5rem;
}
.ruleCheckBox {
  display: flex;
  justify-content: flex-end;
}

@media (max-width: 1199px) {
  .ruleCheckTitle {
    margin-top: 8%;
  }
}
@media (max-width: 767px) {
  .signupLabel {
    width: 100%;
    padding-left: 0%;
  }
  .signupGenderLabel {
    width: 100%;
    padding-left: 0%;
  }
  /* .signupInput {
    width: 80%;
  } */
  .signupInput {
    width: 80%;
  }
  .signupButton {
    width: 20%;
    padding-left: 0%;
  }
  .genderInputLine {
    width: 100%;
  }
  .signupBtnWidth {
    width: 100%;
  }
  .signupGenderButton {
    width: 45%;
    margin-left: 1%;
  }
}

/* .inputStyle:deep(v-input__slot) {
  min-height: 10%;
} */
/* .v-text-field--filled > .v-input__control > .v-input__slot,
.v-text-field--full-width > .v-input__control > .v-input__slot,
.v-text-field--outlined > .v-input__control > .v-input__slot {
  min-height: 10px;
} */
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
.v-date-picker-header {
  display: none;
}
.v-picker__title {
  display: none;
}
.signupNoDrag {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.col-xl,
.col-xl-auto,
.col-xl-12,
.col-xl-11,
.col-xl-10,
.col-xl-9,
.col-xl-8,
.col-xl-7,
.col-xl-6,
.col-xl-5,
.col-xl-4,
.col-xl-3,
.col-xl-2,
.col-xl-1,
.col-lg,
.col-lg-auto,
.col-lg-12,
.col-lg-11,
.col-lg-10,
.col-lg-9,
.col-lg-8,
.col-lg-7,
.col-lg-6,
.col-lg-5,
.col-lg-4,
.col-lg-3,
.col-lg-2,
.col-lg-1,
.col-md,
.col-md-auto,
.col-md-12,
.col-md-11,
.col-md-10,
.col-md-9,
.col-md-8,
.col-md-7,
.col-md-6,
.col-md-5,
.col-md-4,
.col-md-3,
.col-md-2,
.col-md-1,
.col-sm,
.col-sm-auto,
.col-sm-12,
.col-sm-11,
.col-sm-10,
.col-sm-9,
.col-sm-8,
.col-sm-7,
.col-sm-6,
.col-sm-5,
.col-sm-4,
.col-sm-3,
.col-sm-2,
.col-sm-1,
.col,
.col-auto,
.col-12,
.col-11,
.col-10,
.col-9,
.col-8,
.col-7,
.col-6,
.col-5,
.col-4,
.col-3,
.col-2,
.col-1 {
  padding: 0px;
}
</style>
