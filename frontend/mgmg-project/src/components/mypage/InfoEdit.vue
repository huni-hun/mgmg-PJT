<template>
  <div class="infoEditTotalBody">
    <div class="titleLabel">
      <label for="" @click="firstProcess">회원 정보 변경</label>
    </div>

    <div>
      <hr />
    </div>

    <div class="infoEditBody">
      <div class="infoEditLine">
        <div class="infoEditBodyLabel"><label for="">이름</label></div>
        <div class="infoEditBodyContent">
          <CustomInput v-model="nameInfoEditInput" class="" />
        </div>
      </div>

      <div class="infoEditLine">
        <div class="infoEditBodyLabel"><label for="">아이디</label></div>
        <div>
          <label class="infoEditBodyContent" for="">{{ userId }}</label>
        </div>
      </div>

      <div class="infoEditLine">
        <div class="infoEditBodyLabel"><label for="">이메일</label></div>
        <div>
          <label class="infoEditBodyContent" for="">{{ userEmail }}</label>
        </div>
      </div>

      <div class="infoEditLine">
        <div class="infoEditBodyLabel"><label for="">생년월일</label></div>
        <div class="infoEditBodyContent">
          <v-menu v-model="menu2" :close-on-content-click="false" :nudge-right="40" transition="scale-transition" offset-y min-width="auto">
            <template v-slot:activator="{ on, attrs }">
              <v-text-field single-line outlined v-model="date" class="inputStyle" append-icon="mdi-calendar" readonly v-bind="attrs" v-on="on" id="birthSignupInput"></v-text-field>
            </template>
            <v-date-picker
              :weekday-format="getDay"
              :month-format="getMonth"
              :title-date-format="getMonth"
              :header-date-format="getHeaderTitleMonth"
              v-model="date"
              no-title
              @input="menu2 = false"
            ></v-date-picker>
          </v-menu>
        </div>
      </div>

      <div class="infoEditLine">
        <div class="infoEditBodyLabel">
          <label for="" id="genderInfoEditLabel">성별</label>
        </div>
        <div class="infoEditGenderContent">
          <CustomButton class="genderButton" :class="{ selectedGender: userGenderNum == 1 }" @click="changeGender(1)" btnText="남자" />
          <CustomButton class="genderButton" :class="{ selectedGender: userGenderNum == 2 }" @click="changeGender(2)" btnText="여자" />
        </div>
      </div>

      <div class="infoEditButtonLine">
        <CustomButton class="infoEditButton" btnText="수정" @click="userInfoEdit" />
      </div>
    </div>
  </div>
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
      userId: "",
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
    };
  },
  mounted() {
    // this.test();
    this.firstProcess();
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
    test() {
      this.nameInfoEditInput.input = "kimminyoung";
      this.userEmail = "min4849@naver.com";
      this.date = "1222-02-22";
      this.userName = "민영김";
      this.changeGender(1);
      this.userId = "qwer";
    },
    async firstProcess() {
      let response = await showUserInfo(this.accessToken);
      console.log(response);
      this.date = response.birth;
      this.userBirth = response.birth;
      this.userEmail = response.email;
      this.userId = response.userId;
      this.userName = response.userName;

      this.genToNum(response.gender);
      this.nameInfoEditInput.input = this.userName;
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
      if (this.nameValidation) {
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

        await editUserInfo(request)
          .then((res) => {
            console.log(res);
            Swal.fire({
              text: "회원 정보가 정상적으로 변경되었습니다.",
              icon: "success",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
            });
            this.$router.push("/mypage/myinfo");
          })
          .catch((err) => {
            console.log(err);
            Swal.fire({
              text: "잠시후 다시 시도해주세요.",
              icon: "warning",
              confirmButtonColor: "#666666",
              confirmButtonText: "확인",
            });
          });
      }
    },
  },
};
</script>

<style scoped>
.infoEditTotalBody {
  width: 100%;
  padding: 7% 10%;
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 0px 20px 20px rgba(0, 0, 0, 0.2);
}

.titleLabel {
  font-size: clamp(1.5rem, 5vw, 2.2rem);
  margin: 3% 0% 0.5% 0%;
}

.infoEditBody {
  padding: 5% 5% 0% 5%;
}

.infoEditLine {
  display: flex;
  flex-direction: row;
  margin-bottom: 2%;
}

.infoEditBodyLabel {
  width: 20%;
}

.infoEditBodyContent {
  width: 80%;
}

.infoEditButtonLine {
  width: 100%;
  margin-top: 10%;
  display: flex;
  justify-content: center;
}

.infoEditButton {
  padding-right: 9%;
  padding-left: 9%;
  /* width: 50%; */
}

.infoEditGenderContent {
  width: 80%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.genderButton {
  width: 49%;
  background-color: rgb(191, 191, 191);
}
.genderButton:hover {
  background-color: rgb(111, 111, 111);
}

.inputStyle:deep(fieldset) {
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
  padding: 0;
}

.selectedGender {
  background-color: rgb(111, 111, 111);
  color: white;
}

@media (max-width: 639px) {
  .infoEditTotalBody {
    margin-top: 0;
  }
}
</style>
