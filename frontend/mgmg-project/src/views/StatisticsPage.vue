<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-col>
          <h1>통계페이지</h1>
        </v-col>
      </v-row>
      <v-btn-toggle class="btns" v-model="text" borderless color="rgb(225, 146, 179)" rounded group background-color="rgb(255, 233, 242)">
        <v-btn value="left" color="rgb(255, 233, 242)">
          <router-link to="/statistics"> 기간별 통계 </router-link>
        </v-btn>

        <v-btn value="right" color="rgb(255, 233, 242)">
          <router-link to="/statistics/day"> 요일별 통계 </router-link>
        </v-btn>
      </v-btn-toggle>

      <router-view></router-view>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({ text: "left" }),
};
</script>

<style scoped>
.btns {
  display: flex;
  justify-content: center;
}
</style>
