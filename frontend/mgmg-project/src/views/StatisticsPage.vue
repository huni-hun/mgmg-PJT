<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-spacer></v-spacer>
        <v-btn-toggle class="btns" background-color="#FFF" v-model="toggle_exclusive" rounded>
          <v-btn @click="getPeriod" :color="btn1"> 기간별 통계 </v-btn>
          <v-btn @click="getDays" :color="btn2"> 요일별 통계 </v-btn>
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
    toggle_exclusive: 0,
    colors: ["rgb(255, 160, 179)", "rgb(255, 222, 245)", "rgb(255, 160, 179)"],
  }),
  methods: {
    getPeriod() {
      this.$router.push({ path: "/statistics" });
    },
    getDays() {
      this.$router.push({ path: "/statistics/day" });
    },
  },
  computed: {
    btn1() {
      console.log(this.toggle_exclusive);
      return this.colors[this.toggle_exclusive];
    },
    btn2() {
      return this.colors[this.toggle_exclusive + 1];
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
