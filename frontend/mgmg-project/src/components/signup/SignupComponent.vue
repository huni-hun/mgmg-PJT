<template>
  <div id="signupComponentApp">
    <!-- <div></div> -->
    <v-container class="signupContainer" id="signupContainer">
      <!-- <v-col> -->
      <v-row @click="pwCheckValidation">회원가입</v-row>
      <v-row>
        <label class="col-4" for="idSignupInput" id="idSignupLabel">아이디</label>
        <CustomInput v-model="idSignupInput" class="col-4" id="idSignupInput" />
        <div class="col-4"><custom-button btnText="중복확인" /></div>
      </v-row>
      <v-row>
        <label class="col-4" for="pwSignupInput" id="pwSignupLabel">비밀번호</label>
        <div class="inputStyle col-4">
          <v-text-field
            :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required, rules.min]"
            :type="show3 ? 'text' : 'password'"
            label="비밀번호를 입력하세요."
            name="input-10-2"
            hint="영어, 숫자, 특수기호 포함 8자 이상, 16자 이하"
            value=""
            class="input-group--focused"
            @click:append="show3 = !show3"
            single-line
            outlined
          ></v-text-field>
        </div>
        <div class="col-4"></div>
      </v-row>
      <v-row>
        <label class="col-4" for="pwcheckSignupInput" id="pwcheckSignupLabel">비밀번호 확인</label>
        <CustomInput v-model="pwcheckSignupInput" class="col-4" id="pwcheckSignupInput" />
        <div class="col-4"></div>
      </v-row>
      <v-row>
        <label class="col-4" for="emailSignupInput" id="emailSignupLabel">이메일</label>
        <CustomInput v-model="emailSignupInput" class="col-4" id="emailSignupInput" />
        <div class="col-4">
          <custom-button btnText="인증하기" />
        </div>
      </v-row>
      <v-row>
        <label class="col-4" for="emailcheckSignupInput" id="emailcheckSignupLabel">인증번호</label>
        <CustomInput v-model="emailcheckSignupInput" class="col-4" id="emailcheckSignupInput" />
        <div class="col-4">
          <custom-button btnText="확인" />
        </div>
      </v-row>
      <v-row>
        <label class="col-4" for="nameSignupInput" id="nameSignupLabel">이름</label>
        <CustomInput v-model="nameSignupInput" class="col-4" id="nameSignupInput" />
        <div class="col-4"></div>
      </v-row>
      <v-row>
        <label class="col-4" for="birthSignupInput" id="birthSignupLabel">생년월일</label>
        <div class="col-4">
          <v-menu v-model="menu2" :close-on-content-click="false" :nudge-right="40" transition="scale-transition" offset-y min-width="auto">
            <template v-slot:activator="{ on, attrs }">
              <v-text-field single-line outlined v-model="date" class="inputStyle" append-icon="mdi-calendar" readonly v-bind="attrs" v-on="on"></v-text-field>
            </template>
            <v-date-picker v-model="date" @input="menu2 = false"></v-date-picker>
          </v-menu>
        </div>
        <!-- <CustomInput v-model="birthSignupInput" class="col-4" id="birthSignupInput" /> -->
        <div class="col-4"></div>
      </v-row>
      <v-row>
        <label class="col-4" for="" id="genderSignupLabel">성별</label>
        <CustomButton class="col-3" :class="{ selectedGender: userGender == 1 }" @click="changeGender(1)" btnText="남성" />
        <CustomButton class="col-3" :class="{ selectedGender: userGender == 2 }" @click="changeGender(2)" btnText="여성" />
        <div class="col-4"></div>
      </v-row>
      <v-row>
        <label for="">약관 동의</label>
      </v-row>
      <v-row>
        <label for="">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Quisquam necessitatibus obcaecati dolore molestiae ad debitis perspiciatis consequuntur doloribus ex ratione consectetur eaque quo
          maiores quas ipsa, nihil, facere saepe maxime.
        </label>
      </v-row>
      <v-row>
        <v-col>
          <input type="checkbox" id="ruleCheck" />
          <label for="ruleCheck"> 약관 내용에 동의합니다.</label>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 비밀번호인풋
      show1: false,
      show2: true,
      show3: false,
      show4: false,
      password: "Password",
      rules: {
        // required: (value) => !!value || "Required.",
        min: (v) => v.length >= 8 || "영어, 숫자, 특수문자 포함 8자 이상 16자 이하",
        // emailMatch: () => `The email and password you entered don't match`,
      },
      // 달력
      date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000).toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      userId: document.getElementById("idSignupInput"),
      userPw: document.getElementById("pwSignupInput"),
      userEmail: document.getElementById("emailSignupInput"),
      userEmailCheck: document.getElementById("emailcheckSignupInput"),
      userName: document.getElementById("nameSignupInput"),
      userbirth: document.getElementById("birthSignupInput"),
      userGender: "",
      idSignupInput: {
        labelText: "아이디를 입력하세요.",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "영어로 시작, 영어 숫자 포함 6자 이상 16자 이하",
      },
      pwSignupInput: {
        labelText: "비밀번호를 입력하세요.",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "영어, 숫자, 특수문자 포함 8자 이상 16자 이하",
      },
      pwcheckSignupInput: {
        labelText: "비밀번호를 다시 한 번 입력하세요.",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "비밀번호가 일치하지 않습니다.",
      },
      emailSignupInput: {
        labelText: "이메일을 입력하세요.",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "이메일 형식을 확인해주세요.",
      },
      emailcheckSignupInput: {
        labelText: "인증번호를 입력하세요.",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "이메일로 전송된 인증번호를 입력하세요.",
      },
      nameSignupInput: {
        labelText: "이름을 입력하세요.",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "이름은 한 글자 이상입니다.",
      },
      birthSignupInput: {
        labelText: "생일 입력하세요.",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "생일 입력해주세요",
      },
      // datepickerSignup: {
      //   value: "2022-09-14",
      //   // color: "#123122",
      // },
    };
  },
  methods: {
    // 성별 선택
    changeGender(gender) {
      //남자는 1, 여자는 2 저장해놓음
      this.userGender = gender;
      // if (gender == 1) {
      //   this.userGender = 1;
      // } else if (gender == 2) {
      //   this.userGender = 2;
      // }
      console.log(this.userGender);
    },
    // 비밀번호 두 개 같은지 확인
    pwCheckValidation() {
      if (document.getElementById("pwSignupInput").value == document.getElementById("pwcheckSignupInput").value) {
        console.log("비번 같음");
      }
    },
  },
};
</script>

<style scoped>
.signupContainer {
  width: inherit;
  height: inherit;
}
.inputStyle >>> fieldset {
  /* border-color: rgb(255, 250, 250); */
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
  padding: 0;
}
.selectedGender {
  background-color: black;
  color: white;
}
/* .datepicker {
  width: 5rem;
  height: 5rem;
} */
/* .v-messages.theme--light {
  height: 0;
} */
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
