<template>
  <v-container>
    <v-row>
      <label for="">회원 정보 변경</label>
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
      <label for="">min4849</label>
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
      <CustomButton class="col-3" :class="{ selectedGender: userGenderNum == 1 }" @click="changeGender(1)" btnText="남성" />
      <CustomButton class="col-3" :class="{ selectedGender: userGenderNum == 2 }" @click="changeGender(2)" btnText="여성" />
      <div class="col-4"></div>
    </v-row>
    <v-row>
      <CustomButton btnText="수정하기" />
    </v-row>
  </v-container>
</template>

<script>
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
      date: new Date("1998-08-26").toISOString().substr(0, 10),
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
      },
      // 이메일 커스텀 인풋
      emailInfoEditInput: {
        labelText: "이메일을 입력하세요.",
        rules: [(v) => !!v || "이메일은 필수값입니다.", (v) => this.emailValidationCheck(v) || "이메일 형식을 확인해주세요."],
        hint: "이메일을 입력하세요.",
        id: "emailInfoEditInput",
      },
    };
  },
  methods: {
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
        this.userGender = "남성";
      } else if (this.userGenderNum == 2) {
        this.userGender = "여성";
      }
      this.GenderValidation = true;
    },
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
