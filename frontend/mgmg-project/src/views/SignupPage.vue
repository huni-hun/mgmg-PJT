<template>
  <div>
    <h1>SignupPage입니다.</h1>
    <SignUp v-if="signupOrder == 1" />
    <MusicSurvey v-if="signupOrder == 2" />
    <GiftSurvey v-if="signupOrder == 3" />
    <!-- <v-btn>dd</v-btn> -->
    <customButton btnText="이전" id="signupBeforeButton" v-if="signupOrder > 1" @click="signupBefore"></customButton>
    <customButton btnText="다음" id="signupNextButton" v-if="signupOrder < 3" @click="signupNext"></customButton>
    <customButton btnText="완료" id="signupFinish" v-if="signupOrder == 3"></customButton>
    <!-- <button id="signupBeforeButton" v-if="signupOrder > 1" @click="signupBefore">이전</button>
    <button id="signupNextButton" v-if="signupOrder < 3" @click="signupNext">다음</button>
    <button id="signupFinish" v-if="signupOrder == 3">완료</button> -->
  </div>
</template>

<script>
import SignUp from "@/components/signup/SignupComponent.vue";
import GiftSurvey from "@/components/signup/GiftSurvey.vue";
import MusicSurvey from "@/components/signup/MusicSurvey.vue";
import CustomButton from "@/components/common/CustomButton.vue";
export default {
  components: { SignUp, GiftSurvey, MusicSurvey, CustomButton },
  data() {
    return {
      signupOrder: 1,
    };
  },
  methods: {
    signupBefore() {
      this.signupOrder--;
    },
    signupNext() {
      this.signupOrder++;
    },
  },
};
</script>

<style></style>
