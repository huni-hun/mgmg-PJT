<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-spacer></v-spacer>
        <v-btn-toggle class="btns" v-model="toggle_exclusive" rounded>
          <v-btn @click="getPeriod" color="rgb(255, 233, 242)"> 기간별 통계 </v-btn>
          <v-btn @click="getDays" color="rgb(255, 233, 242)"> 요일별 통계 </v-btn>
        </v-btn-toggle>
        <v-spacer></v-spacer>
      </v-row>

      <router-view></router-view>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
    text: "left",
    toggle_exclusive: undefined,
  }),
  methods: {
    getPeriod() {
      this.$router.push({ path: "/statistics" });
    },
    getDays() {
      this.$router.push({ path: "/statistics/day" });
    },
  },
};
</script>

<style scoped>
.btns {
  margin-top: 2vh;
  margin-bottom: 3vh;
}
</style>
