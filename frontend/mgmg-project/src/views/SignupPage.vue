//SignupPage.vue
<template>
  <div>
    <h1 @click="check">SignupPage입니다.</h1>
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
    <MusicSurvey v-if="signupOrder == 2" @updateMusic="setMusic1" />
    <MusicSurveySecond v-if="signupOrder == 3" @updateMusicSecond="setMusic2" />
    <GiftSurvey v-if="signupOrder == 4" @selectGifts="setGift" @upperPriceSignup="setUpperPrice" @underPriceSignup="setUnderPrice" />
    <customButton btnText="다음" id="page1Next" v-if="signupOrder == 1" @click="signupNext1page"></customButton>
    <customButton btnText="이전" id="page2Before" v-if="signupOrder == 2" @click="signupBefore"></customButton>
    <customButton btnText="다음" id="page2Next" v-if="signupOrder == 2" @click="signupNext2page"></customButton>
    <customButton btnText="이전" id="page3Before" v-if="signupOrder == 3" @click="signupBefore"></customButton>
    <customButton btnText="다음" id="page3Next" v-if="signupOrder == 3" @click="signupNext3page"></customButton>
    <customButton btnText="이전" id="page4Before" v-if="signupOrder == 4" @click="signupBefore"></customButton>
    <customButton btnText="완료" id="page4FNext" v-if="signupOrder == 4" @click="signUp"></customButton>
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
      selectMusic: {},
      selectMusicSecond: {},
      musicTaste: {},
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
      // this.parentValid = this.finalValid;
      this.finalValid = value;
      console.log("최종 유효성 평가", this.finalValid);
    },
    signupBefore() {
      this.signupOrder--;
    },
    signupNext1page() {
      console.log(this.userId, this.userPassword, this.userEmail, this.userName, this.userBirth, this.userGender, this.userRuleCheck, this.userUnderPrice, this.userUpperPrice);
      if (this.finalValid) {
        this.signupOrder++;
      } else {
        Swal.fire({
          text: "입력한 정보를 다시 확인하세요.",
          icon: "warning",
          // iconColor: "#000000",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      }
    },
    signupNext2page() {
      var isAllChecked = true;
      var rep;
      //하나라도 비어있는 항목 있으면 안됨.
      for (rep = 0; rep < 5; rep++) {
        if (typeof this.selectMusic[this.emotionLst1[rep]] == "undefined" || this.selectMusic[this.emotionLst1[rep]].length == 0) {
          isAllChecked = false;
        }
      }
      if (!isAllChecked) {
        Swal.fire({
          text: "감정별로 음악 장르를 1개 이상 선택해야 합니다.",
          icon: "warning",
          // iconColor: "#000000",
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
      //하나라도 비어있는 항목 있으면 안됨.
      for (rep = 0; rep < 5; rep++) {
        console.log(this.emotionLst2[rep]);
        console.log(this.selectMusicSecond[this.emotionLst2[rep]]);
        if (typeof this.selectMusicSecond[this.emotionLst2[rep]] == "undefined" || this.selectMusicSecond[this.emotionLst2[rep]].length == 0) {
          isAllChecked = false;
        }
      }
      if (!isAllChecked) {
        Swal.fire({
          text: "감정별로 음악 장르를 1개 이상 선택해야 합니다.",
          icon: "warning",
          // iconColor: "#000000",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      } else {
        this.signupOrder++;
      }
    },
    setGift(value) {
      this.selectGift = value;
      console.log("부모!!!!!!!", this.selectGift);
    },
    setMusic1(value) {
      this.selectMusic = value;
      var rep;
      for (rep = 0; rep < 5; rep++) {
        console.log(this.emotionLst1[rep]);
        this.musicTaste[this.emotionLst1[rep]] = this.selectMusic[this.emotionLst1[rep]];
      }
      // this.musicTaste = this.musicTaste.push(this.selectMusic);
      console.log(this.musicTaste);
    },
    setMusic2(value) {
      this.selectMusicSecond = value;
      var rep;
      for (rep = 0; rep < 5; rep++) {
        console.log(this.emotionLst2[rep]);
        this.musicTaste[this.emotionLst2[rep]] = this.selectMusicSecond[this.emotionLst2[rep]];
      }
      // this.musicTaste = this.musicTaste.push(this.selectMusic);
      console.log(this.musicTaste);
    },
    setUserid(value) {
      this.userId = value;
      console.log(this.userId);
    },
    setUserpassword(value) {
      this.userPassword = value;
      console.log(this.userPassword);
    },
    setUseremail(value) {
      this.userEmail = value;
      console.log(this.userEmail);
    },
    setUserName(value) {
      this.userName = value;
      console.log(this.userName);
    },
    setUserBirth(value) {
      this.userBirth = value;
      console.log(this.userBirth);
    },
    setUserGender(value) {
      this.userGender = value;
      console.log(this.userGender);
    },
    setRuleCheck(value) {
      this.userRuleCheck = value;
      console.log(this.userRuleCheck);
    },
    setUpperPrice(value) {
      this.userUpperPrice = value;
      console.log(this.userUpperPrice);
    },
    setUnderPrice(value) {
      this.userUnderPrice = value;
      console.log(this.userUnderPrice);
    },
    check() {
      console.log(this.userId, this.userPassword, this.userEmail, this.userName, this.userBirth, this.userGender, this.userRuleCheck, this.userUnderPrice, this.userUpperPrice);
      console.log(this.selectGift, this.selectMusic);
    },
    async signUp() {
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
      console.log(request);

      let response = await signUp(request);
      console.log("응답 데이터", response);
      if (response.statusCode == 200) {
        Swal.fire({
          text: "회원가입에 성공했습니다. 로그인 해주세요.",
          icon: "success",
          // iconColor: "#000000",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
        this.$router.push("/login");
      }
      // signUp;
    },
  },
};
</script>

<style></style>
