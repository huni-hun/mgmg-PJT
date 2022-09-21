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
    />
    <MusicSurvey v-if="signupOrder == 2" @selectMusics="setMusic" />
    <GiftSurvey v-if="signupOrder == 3" @selectGifts="setGift" />
    <customButton btnText="이전" id="signupBeforeButton" v-if="signupOrder > 1" @click="signupBefore"></customButton>
    <customButton btnText="다음" id="signupNextButton" v-if="signupOrder < 3" @click="signupNext"></customButton>
    <customButton btnText="완료" id="signupFinish" v-if="signupOrder == 3"></customButton>
  </div>
</template>

<script>
import SignUp from "@/components/signup/SignupComponent.vue";
import GiftSurvey from "@/components/signup/GiftSurvey.vue";
import MusicSurvey from "@/components/signup/MusicSurvey.vue";
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
    };
  },
  methods: {
    signupBefore() {
      this.signupOrder--;
    },
    signupNext() {
      this.signupOrder++;
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
    check() {
      console.log(this.userId, this.userPassword, this.userEmail, this.userName, this.userBirth, this.userGender, this.userRuleCheck);
      console.log(this.selectGift, this.selectMusic);
    },
  },
};
</script>

<style></style>
