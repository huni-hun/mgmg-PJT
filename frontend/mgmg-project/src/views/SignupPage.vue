//SignupPage.vue
<template>
  <div class="signupTotal">
    <div class="signupTitle"><label for="">회원가입</label></div>
    <div class="signupBody noScrollBox">
      <SignUp
        v-if="signupOrder == 1"
        @useridSignup="setUserid"
        @userpasswordSignup="setUserpassword"
        @useremailSignup="setUseremail"
        @usernameSignup="setUserName"
        @userbirthSignup="setUserBirth"
        @usergenderSignup="setUserGender"
        @ruleCheckSignup="setRuleCheck"
        @finalValidSignup="finalValidCheck"
      />
      <MusicSurvey v-if="signupOrder == 2" :selectMusic="selectMusic" @updateMusic="setMusic1" />
      <MusicSurveySecond v-if="signupOrder == 3" :selectMusicSecond="selectMusicSecond" @updateMusicSecond="setMusic2" />
      <GiftSurvey v-if="signupOrder == 4" @selectGifts="setGift" @upperPriceSignup="setUpperPrice" @underPriceSignup="setUnderPrice" />

      <div class="signupButton1Page" v-if="signupOrder == 1">
        <customButton class="signupButton" btnText="다음" id="page1Next" @click="signupNext1page"></customButton>
      </div>

      <div class="signupButton2Page justify_content_center" v-if="signupOrder == 2">
        <customButton class="signupButton" btnText="이전" id="page2Before" @click="signupBefore"></customButton>
        <customButton class="signupButton" btnText="다음" id="page2Next" @click="signupNext2page"></customButton>
      </div>

      <div class="signupButton3Page justify_content_center" v-if="signupOrder == 3">
        <customButton class="signupButton" btnText="이전" id="page3Before" @click="signupBefore"></customButton>
        <customButton class="signupButton" btnText="다음" id="page3Next" @click="signupNext3page"></customButton>
      </div>

      <div class="signupButton4Page justify_content_center" v-if="signupOrder == 4">
        <customButton class="signupButton" btnText="이전" id="page4Before" @click="signupBefore"></customButton>
        <customButton class="signupButton" btnText="완료" id="page4FNext" @click="signUp"></customButton>
      </div>
    </div>
  </div>
</template>

<script>
import SignUp from "@/components/signup/SignupComponent.vue";
import GiftSurvey from "@/components/signup/GiftSurvey.vue";
import MusicSurvey from "@/components/signup/MusicSurvey.vue";
import MusicSurveySecond from "@/components/signup/MusicSurveySecond.vue";
import Swal from "sweetalert2";
import { signUp } from "@/api/userApi.js";

export default {
  components: { SignUp, GiftSurvey, MusicSurvey, MusicSurveySecond },
  data() {
    return {
      emotionLst1: ["피곤", "평온", "짜증", "기쁨", "사랑"],
      emotionLst2: ["기대", "슬픔", "창피", "화", "공포"],

      signupOrder: 1,

      selectMusic: {
        피곤: ["", "", "", "", "", "", "", ""],
        평온: ["", "", "", "", "", "", "", ""],
        짜증: ["", "", "", "", "", "", "", ""],
        기쁨: ["", "", "", "", "", "", "", ""],
        사랑: ["", "", "", "", "", "", "", ""],
      },
      selectMusicSecond: {
        기대: ["", "", "", "", "", "", "", ""],
        슬픔: ["", "", "", "", "", "", "", ""],
        창피: ["", "", "", "", "", "", "", ""],
        화: ["", "", "", "", "", "", "", ""],
        공포: ["", "", "", "", "", "", "", ""],
      },
      musicTaste: {
        피곤: [],
        평온: [],
        짜증: [],
        기쁨: [],
        사랑: [],
        기대: [],
        슬픔: [],
        창피: [],
        화: [],
        공포: [],
      },
      selectGift: [],
      userId: "",
      userPassword: "",
      userEmail: "",
      userName: "",
      userBirth: "",
      userGender: "",
      userRuleCheck: "",
      userUnderPrice: 0,
      userUpperPrice: 0,
      finalValid: false,
    };
  },

  methods: {
    finalValidCheck(value) {
      this.finalValid = value;
    },
    signupBefore() {
      this.signupOrder--;
    },
    signupNext1page() {
      if (this.finalValid) {
        this.signupOrder++;
      } else {
        Swal.fire({
          text: "입력한 정보를 다시 확인하세요.",
          icon: "warning",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      }
    },
    signupNext2page() {
      var isAllChecked = true;
      var rep;
      var gen;
      for (rep = 0; rep < 5; rep++) {
        if (typeof this.selectMusic[this.emotionLst1[rep]] == "undefined") {
          isAllChecked = false;
          break;
        }

        var isNotEmpty = false;
        for (gen = 0; gen < 8; gen++) {
          if (this.selectMusic[this.emotionLst1[rep]][gen]) {
            isNotEmpty = true;
            break;
          }
        }
        if (isNotEmpty == false) {
          isAllChecked = false;
        }
      }
      if (!isAllChecked) {
        Swal.fire({
          text: "감정별로 음악 장르를 1개 이상 선택해야 합니다.",
          icon: "warning",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      } else {
        this.signupOrder++;
      }
    },
    signupNext3page() {
      var isAllChecked = true;
      var rep;
      var gen;
      for (rep = 0; rep < 5; rep++) {
        if (typeof this.selectMusicSecond[this.emotionLst2[rep]] == "undefined") {
          isAllChecked = false;
          break;
        }

        var isNotEmpty = false;
        for (gen = 0; gen < 8; gen++) {
          if (this.selectMusicSecond[this.emotionLst2[rep]][gen]) {
            isNotEmpty = true;
            break;
          }
        }
        if (isNotEmpty == false) {
          isAllChecked = false;
        }
      }
      if (!isAllChecked) {
        Swal.fire({
          text: "감정별로 음악 장르를 1개 이상 선택해야 합니다.",
          icon: "warning",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      } else {
        this.signupOrder++;
      }
    },
    setGift(value) {
      this.selectGift = value;
    },
    setMusic1() {
      var emoRep;
      for (emoRep = 0; emoRep < 5; emoRep++) {
        var genRep;
        for (genRep = 0; genRep < 8; genRep++) {
          if (this.selectMusic[this.emotionLst1[emoRep]][genRep] != "") {
            this.musicTaste[this.emotionLst1[emoRep]].push(this.selectMusic[this.emotionLst1[emoRep]][genRep]);
          }
        }
      }
    },
    setMusic2() {
      var emoRep;
      for (emoRep = 0; emoRep < 5; emoRep++) {
        var genRep;
        for (genRep = 0; genRep < 8; genRep++) {
          if (this.selectMusicSecond[this.emotionLst2[emoRep]][genRep] != "") {
            this.musicTaste[this.emotionLst2[emoRep]].push(this.selectMusicSecond[this.emotionLst2[emoRep]][genRep]);
          }
        }
      }
    },
    setUserid(value) {
      this.userId = value;
    },
    setUserpassword(value) {
      this.userPassword = value;
    },
    setUseremail(value) {
      this.userEmail = value;
    },
    setUserName(value) {
      this.userName = value;
    },
    setUserBirth(value) {
      this.userBirth = value;
    },
    setUserGender(value) {
      this.userGender = value;
    },
    setRuleCheck(value) {
      this.userRuleCheck = value;
    },
    setUpperPrice(value) {
      this.userUpperPrice = value;
    },
    setUnderPrice(value) {
      this.userUnderPrice = value;
    },
    async signUp() {
      this.setMusic1();
      this.setMusic2();

      var email = this.userEmail;
      var password = this.userPassword;
      var userId = this.userId;
      var birth = this.userBirth;
      var name = this.userName;
      var gender = this.userGender;
      var giftTaste = this.selectGift;
      var musicTaste = this.musicTaste;
      var lowPrice = this.userUnderPrice;
      var highPrice = this.userUpperPrice;

      const request = {
        email: email,
        password: password,
        userId: userId,
        birth: birth,
        name: name,
        gender: gender,
        giftTaste: giftTaste,
        musicTaste: musicTaste,
        lowPrice: lowPrice,
        highPrice: highPrice,
      };
      await signUp(request)
        .then((res) => {
          console.log(res);
          Swal.fire({
            text: "회원가입에 성공했습니다. 로그인 해주세요.",
            icon: "success",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
          this.$router.push("/login");
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            text: "회원가입에 실패했습니다. 잠시후 다시 시도해주세요.",
            icon: "warning",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
        });
    },
  },
};
</script>

<style scoped>
.signupTotal {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.signupTitle {
  font-size: clamp(1.8rem, 2.5vw, 2.1rem);
  margin: 1% 0;
  color: white;
}

.signupBody {
  box-shadow: 0px 0px 20px 20px rgba(23, 50, 97, 0.2);
  border-radius: 15px;
  width: 100%;
  background-color: white;
  padding: 5% 10%;
  height: 75vh;
  /* 내부 스크롤 */
  overflow: auto;
}

.signupButton1Page {
  display: flex;
  justify-content: center;
  margin-top: 3%;
}

.signupButton2Page {
  display: flex;
  justify-content: center;
  margin-top: 3%;
}

.signupButton3Page {
  display: flex;
  justify-content: center;
  margin-top: 3%;
}

.signupButton4Page {
  display: flex;
  justify-content: center;
  margin-top: 3%;
}

.signupButton {
  width: 20%;
  margin: 0 2% 3% 2%;
}

.justify_content_center {
  display: flex;
  justify-content: center;
}

.noScrollBox {
  -ms-overflow-style: none;
  scrollbar-width: none;
}

.noScrollBox::-webkit-scrollbar {
  display: none;
}

@media (max-width: 575px) {
  .signupBody {
    background-color: white;
    padding: 5% 10%;
  }

  .signupButton {
    width: 30%;
    padding: 1% 0;
  }
}

@media (max-width: 330px) {
  .signupBody {
    padding: 5% 2%;
  }
}
</style>
