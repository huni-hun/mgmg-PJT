<template>
  <div>
    <v-container class="justify-center">
      <div style="display: flex; flex-direction: row">
        <div class="achieve-title" style="flex: 1">나의 업적</div>
        <div style="flex: 1"></div>

        <div
          style="
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: flex-end;
            justify-content: flex-end;
          "
        >
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
        </div>
      </div>
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
  font-weight: bold;
  /* text-align: center; */
  color: #ffffff;
  /* text-shadow: 0px 20px 20px rgba(0, 0, 0, 0.25); */
  font-size: clamp(1.8rem, 3vw, 3.2rem);
}
.achieve-btn {
  text-align: right;
  color: #ffffff;
  font-size: clamp(0.9rem, 1vw, 1.3rem);
}

.achieve-list-frame {
  margin-top: 1vh;
  background: rgba(255, 255, 255, 0.35);
  padding-top: 10px;
  box-shadow: 0px 20px 20px rgba(0, 0, 0, 0.25);
}

@media (max-width: 1023px) {
}

@media (max-width: 639px) {
}
</style>
