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
    <MusicSurvey v-if="signupOrder == 2" @selectMusics="setMusic" />
    <GiftSurvey v-if="signupOrder == 3" @selectGifts="setGift" @upperPriceSignup="setUpperPrice" @underPriceSignup="setUnderPrice" />
    <customButton btnText="다음" id="page1Next" v-if="signupOrder == 1" @click="signupNext"></customButton>
    <customButton btnText="이전" id="page2Before" v-if="signupOrder == 2" @click="signupBefore"></customButton>
    <customButton btnText="다음" id="page2Next" v-if="signupOrder == 2" @click="signupNext"></customButton>
    <customButton btnText="이전" id="page3Before" v-if="signupOrder == 3" @click="signupBefore"></customButton>
    <customButton btnText="완료" id="page3FNext" v-if="signupOrder == 3"></customButton>
  </div>
</template>

<script>
import SignUp from "@/components/signup/SignupComponent.vue";
import GiftSurvey from "@/components/signup/GiftSurvey.vue";
import MusicSurvey from "@/components/signup/MusicSurvey.vue";
import Swal from "sweetalert2";
export default {
  components: { SignUp, GiftSurvey, MusicSurvey },
  data() {
    return {
      signupOrder: 1,
      selectMusic: [],
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
      parentValid: false,
    };
  },
  methods: {
    finalValidCheck() {
      this.parentValid = this.finalValid;
      console.log(this.parentValid);
    },
    signupBefore() {
      this.signupOrder--;
    },
    signupNext() {
      console.log(this.userId, this.userPassword, this.userEmail, this.userName, this.userBirth, this.userGender, this.userRuleCheck, this.userUnderPrice, this.userUpperPrice);
      if (this.userId && this.userPassword && this.userEmail && this.userName && this.userBirth && this.userGender && this.userRuleCheck) {
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
    setGift(value) {
      this.selectGift = value;
      console.log("부모!!!!!!!", this.selectGift);
    },
    setMusic(value) {
      this.selectMusic = value;
      console.log(this.selectMusic);
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
  },
};
</script>

<style></style>
