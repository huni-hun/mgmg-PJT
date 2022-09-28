<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <h1>나의업적</h1>

        <v-btn v-if="!isAchieve" @click="changeIsAchieve">
          <router-link to="/achieve/get">달성 업적 확인</router-link>
        </v-btn>
        <v-btn v-if="isAchieve" @click="changeIsAchieve">
          <router-link to="/achieve">전체 업적 확인</router-link>
        </v-btn>
      </v-row>
      <router-view></router-view>
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

<style></style>
