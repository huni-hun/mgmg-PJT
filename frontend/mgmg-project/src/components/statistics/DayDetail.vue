<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-col>
          <barGraph :statistics="statistics" />
        </v-col>
      </v-row>
    </v-container>
    <div>이번주는 사랑이 가장 많구만유.</div>
  </div>
</template>

<script>
import BarGraph from "@/components/statistics/BarGraph.vue";
import { statistics_day } from "@/store/modules/etcStore";

export default {
  name: "DayDetail",

  data: () => ({
    dayData: Object,
    statistics: Object,
    stackBarData: {
      보통: [], // 7개
      기쁨: [],
      사랑: [],
      부끄: [],
      기대: [],
      슬픔: [],
      짜증: [],
      피곤: [],
      화남: [],
      공포: [],
    },
    week: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
  }),
  async created() {
    this.dayData = await statistics_day();
    this.statistics = this.dayData.statistics;
    console.log("=========스택바데이터 확인시작=============");

    // for (const key of this.week) {
    //   for (const k of this.statistics[key]) {
    //     for (const emotion of Object.keys(this.stackBarData))
    //     console.log(k);
    //     console.log(this.stackBarData[k["emotion"]]);

    //     if (k["percent"] > 0) {
    //       this.stackBarData[k["emotion"]].push(k["percent"]);
    //     } else {
    //       this.stackBarData[k["emotion"]].push(0);
    //     }
    //   }
    // }

    console.log("=========데이터 확인=============");
    console.log(this.stackBarData);
  },
  components: { BarGraph },
};
</script>

<style></style>
