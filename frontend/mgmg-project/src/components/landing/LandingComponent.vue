<template>
  <div class="topBackground">
    <div>
      <button class="button" value="login" @click="pageLink(`login`)">로그인</button>
      <button class="button" value="signup" @click="pageLink(`signup`)">회원가입</button>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    pageLink(to) {
      if (to == "login") this.$router.push({ path: 'login' })
      else this.$router.push({ path: 'signup' })
    }
  }
};
</script>

<style>
.topBackground {
  background-image: url("@/assets/landing/landing_topBackground.png");
  background-size: cover;
  width: 100vw;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.button {
  padding: 10px 50px;
  margin: 0px 10px;
  background: #FFDD28;
  border-radius: 20px;
}
</style>
