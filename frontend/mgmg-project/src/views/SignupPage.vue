<template>
  <div>
    <h1 @click="upGift">SignupPage입니다.</h1>
    <SignUp v-if="signupOrder == 1" />
    <MusicSurvey v-if="signupOrder == 2" />
    <GiftSurvey name="Gift" @setGift="setGift" v-if="signupOrder == 3" />
    <!-- <GiftSurvey selectedGifts="selectedGift" @updateGift="upGift" v-if="signupOrder == 3" /> -->
    <customButton btnText="이전" id="signupBeforeButton" v-if="signupOrder > 1" @click="signupBefore"></customButton>
    <customButton btnText="다음" id="signupNextButton" v-if="signupOrder < 3" @click="signupNext"></customButton>
    <customButton btnText="완료" id="signupFinish" v-if="signupOrder == 3"></customButton>
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
      sltGift: [],
      signupOrder: 1,
      giftValue: [],
    };
  },
  methods: {
    signupBefore() {
      this.signupOrder--;
    },
    signupNext() {
      this.signupOrder++;
    },
    setGift(giftValue) {
      this.giftValue = giftValue;
      console.log("자식컴포넌트에게 받은 값", giftValue);
    },
    // upGift() {
    //   console.log(this.selectedGift);
    // },
  },
};
</script>

<style></style>
