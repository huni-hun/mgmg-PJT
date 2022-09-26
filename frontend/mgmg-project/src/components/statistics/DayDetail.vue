<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-col>
          <barGraph />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import BarGraph from "@/components/statistics/BarGraph.vue";

export default {
  name: "DayDetail",

  data: () => ({
    week: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
  }),
  created() {
    console.log("----------ㅇㅁDayDetail--");
  },
  components: { BarGraph },
};
</script>

<style></style>
