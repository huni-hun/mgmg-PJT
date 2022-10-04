<template>
  <div>
    <v-container class="justify-center">
      <v-row class="title-row">
        <v-spacer></v-spacer>
        <div class="achieve-title">나의 업적</div>
        <v-spacer></v-spacer>
      </v-row>
      <v-row>
        <v-spacer></v-spacer>
        <v-btn
          class="achieve-btn"
          v-if="!isAchieve"
          @click="[getAchieveList(), changeIsAchieve()]"
          color="rgba(81,81,110,.9)"
          >달성 업적 확인</v-btn
        >
        <v-btn
          class="achieve-btn"
          v-if="isAchieve"
          @click="[achieveList(), changeIsAchieve()]"
          color="rgba(81,81,110,.9)"
          >전체 업적 확인</v-btn
        >
      </v-row>
      <v-row class="achieve-list-frame">
        <router-view></router-view>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "AchievePage",

  data: () => ({
    isAchieve: false,
  }),
  created() {},
  methods: {
    changeIsAchieve() {
      this.isAchieve = !this.isAchieve;
    },
    getAchieveList() {
      this.$router.push({ path: "/achieve/get" });
    },
    achieveList() {
      this.$router.push({ path: "/achieve" });
    },
  },
};
</script>

<style scoped>
.title-row {
  margin-top: 2vh;
}

.achieve-title {
  text-align: center;
  color: #ffffff;
  text-shadow: 0px 20px 20px rgba(0, 0, 0, 0.25);
  font-size: clamp(1.8rem, 3vw, 3rem);
}
.achieve-btn {
  text-align: right;
  color: #ffffff;
}

.achieve-list-frame {
  margin-top: 1vh;
}

/* 반응형 */
@media (max-width: 1023px) {
  .achieve-btn {
    font-size: 1rem;
  }
}

@media (max-width: 639px) {
  .achieve-btn {
    font-size: 0.5rem;
  }
}
</style>
