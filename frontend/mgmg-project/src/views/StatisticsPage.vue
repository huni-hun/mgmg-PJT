<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-spacer></v-spacer>
        <v-btn-toggle class="btns" background-color="#FFF" rounded>
          <v-btn class="btn" @click="getPeriod" :color="btn1">
            기간별 통계
          </v-btn>
          <v-btn class="btn" @click="getDays" :color="btn2">
            요일별 통계
          </v-btn>
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
  created() {
    if (window.location.pathname === "/statistics") {
      this.toggle_exclusive = 0;
    } else {
      this.toggle_exclusive = 1;
    }
  },
  methods: {
    getPeriod() {
      this.toggle_exclusive = 0;
      this.$router.push({ path: "/statistics" });
    },
    getDays() {
      this.toggle_exclusive = 1;
      this.$router.push({ path: "/statistics/day" });
    },
  },
  computed: {
    btn1() {
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
.btn {
  font-size: clamp(0.9rem, 1vw, 1.3rem);
}
</style>
