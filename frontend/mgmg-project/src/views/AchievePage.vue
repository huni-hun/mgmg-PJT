<template>
  <div>
    <v-container class="justify-center">
      <v-row class="title-row">
        <v-spacer></v-spacer>
        <h1 class="achieve-title">나의업적</h1>
        <v-spacer></v-spacer>
      </v-row>
      <v-row>
        <v-spacer></v-spacer>
        <v-btn class="achieve-btn" color="rgba(81,81,110,.9)" @click="changeIsAchieve">
          <router-link v-if="!isAchieve" to="/achieve/get">달성 업적 확인</router-link>
          <router-link v-if="isAchieve" to="/achieve">전체 업적 확인</router-link>
        </v-btn>
      </v-row>
      <v-row>
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
  },
};
</script>

<style scoped>
.title-row {
  margin-top: 2vh;
}

.achieve-title {
  text-align: center;
  color: aliceblue;
}
.achieve-btn {
  text-align: right;

  color: aliceblue;
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
