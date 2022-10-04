<template>
  <div>
    <!-- 모바일 404 페이지 -->
    <v-row v-if="isMobile" justify="center">
      <v-col cols="10" align-self="center">
        <v-img class="div-img" :src="require(`@/assets/pagenotfound/mobilepagenotfound.png`)" alt="">
          <div class="btn-position">
            <v-btn class="return-btn" @click="$router.back()" color="error" large>확인</v-btn>
          </div>
        </v-img>
      </v-col>
    </v-row>
    <!-- pc -->
    <v-row v-else justify="center">
      <v-col cols="10" align-self="center">
        <v-img class="div-img" :src="require(`@/assets/pagenotfound/pagenotfound.png`)" alt="">
          <div class="btn-position">
            <v-btn class="return-btn" @click="$router.back()" color="error" large>확인</v-btn>
          </div>
        </v-img>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "PageNotFound",
  components: {},
  data() {
    return {
      isMobile: Boolean,
    };
  },
  created() {
    const mq = window.matchMedia("(max-width: 639.5px)"); // 가로 크기 확인
    console.log(mq);
    if (mq.matches === true) {
      this.isMobile = true;
    } else {
      this.isMobile = false;
    }
  },
};
</script>

<style scoped>
.pagenotfound {
  display: flex;
  justify-content: start;
  align-items: flex-end;
}

.div-img {
  display: flex;
  justify-content: center;
  align-items: end;
  width: 100%;
}

.btn-position {
  display: flex;
  justify-content: center;
  margin: 5%;
}

.return-btn {
  text-decoration: none;
}
</style>
