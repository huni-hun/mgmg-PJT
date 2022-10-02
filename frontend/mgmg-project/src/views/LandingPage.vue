<template>
  <div class="backgruound">
    <landing-main-component />
    <landing-sub-component />
    <div class="main-btn">
      <button class="button" value="login" @click="pageLink(`login`)">
        일기쓰러가기
      </button>
    </div>
  </div>
</template>

<script>
import LandingMainComponent from "@/components/landing/LandingMainComponent.vue";
import LandingSubComponent from "@/components/landing/LandingSubComponent.vue";
export default {
  components: { LandingMainComponent, LandingSubComponent },
  methods: {
    pageLink(to) {
      if (to == "login") this.$router.push({ path: "login" });
    },
  },
};
</script>

<style scoped>
.backgruound {
  height: 100%;
  background: white;
}
.main-btn {
  text-align: center;
}
.button {
  padding: 10px 45px;
  margin: 20px;
  background: #ffdd28;
  border-radius: 20px;
  width: 250px;
  height: 60px;
  font-size: 25px;
}
@media screen and (max-width: 835px) {
  .button {
    padding: 10px;
    width: 140px;
    height: 40px;
    border-radius: 12px;
    font-size: clamp(1rem, 1.4vw, 1.5rem);
  }
}
</style>
