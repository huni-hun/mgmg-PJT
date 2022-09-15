//SignupPage.vue
<template>
  <div>
    <h1 @click="check">SignupPage입니다.</h1>
    <SignUp v-if="signupOrder == 1" />
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
    check() {
      console.log(this.selectGift, this.selectMusic);
    },
  },
};
</script>

<style></style>
