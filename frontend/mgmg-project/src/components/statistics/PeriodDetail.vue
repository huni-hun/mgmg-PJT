<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-col>
          <donutGraph :startDate="startDate" :endDate="endDate" />
        </v-col>
        <v-col>
          <div class="period-board">
            <img class="sticker" :src="require(`@/assets/statistics/adehesive_plaster.png`)" alt="" />
            <br />
            <!-- 달력 picker -->
            <div v-if="isPicker">
              <v-text-field v-model="dateRangeText" label="Date range" prepend-icon="mdi-calendar" readonly></v-text-field>
              <v-date-picker v-model="dates" range no-title></v-date-picker>
              <!-- 취소 -> 원상복귀, 확인 -> startDate, endDate 바꾸기 -->
              <v-btn @click="selectFunc()" elevation="2">뒤로</v-btn>
              <v-btn @click="pickerFunc()" elevation="2">확인</v-btn>
            </div>
            <!-- menus 와 정보 -->
            <div v-else class="text-center">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="primary" dark v-bind="attrs" v-on="on"> {{ menuTitle }} </v-btn>
                </template>
                <v-list>
                  <v-list-item v-for="(item, index) in items" :key="index">
                    <v-list-item-title @click="periodFunc(item.func)">{{ item.title }}</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
              <p @click="selectFunc()">{{ startDate }} ~ {{ endDate }}</p>
              <p>가장 많이 느낀 감정</p>
              <img class="badge" :src="require(`@/assets/badge/a1.png`)" alt="" />
              <p>사랑은 돌아오는거야(명언)</p>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import DonutGraph from "@/components/statistics/DonutGraph.vue";
import moment from "moment";

export default {
  name: "PeriodDetail",

  data: () => ({
    items: [
      { title: "주간 통계", func: "weekDays" },
      { title: "월간 통계", func: "monthDays" },
      { title: "연간 통계", func: "yearDays" },
      { title: "기간 선택", func: "selectDays" },
    ],
    menuTitle: "주간 통계",
    periodData: Object,
    data: Object,
    startDate: moment().subtract(1, "weeks").format("YYYY-MM-DD"),
    endDate: moment().format("YYYY-MM-DD"), // 오늘
    nowDate: moment().format("YYYY-MM-DD"),
    agoWeeks: moment().subtract(1, "weeks").format("YYYY-MM-DD"),
    agoMonths: moment().subtract(1, "months").format("YYYY-MM-DD"),
    agoYears: moment().subtract(1, "years").format("YYYY-MM-DD"),
    isPicker: false,
    dates: [],
    emotion: "",
  }),
  methods: {
    // 달력 on/off 함수
    selectFunc() {
      this.isPicker = !this.isPicker;
      if (!this.isPicker) {
        // 달력 사용 취소 -> 원상복귀
        this.dates = [this.startDate, this.endDate];
      }
    },
    pickerFunc() {
      if (moment(this.dates[1]).isAfter(this.dates[0])) {
        this.startDate = this.dates[0];
        this.endDate = this.dates[1];
      } else {
        this.startDate = this.dates[1];
        this.endDate = this.dates[0];
      }
      this.isPicker = !this.isPicker; // 마지막으로 picker 끔
    },
    periodFunc(period) {
      switch (period) {
        case "weekDays":
          this.endDate = this.nowDate;
          this.startDate = this.agoWeeks;
          this.dates = [this.startDate, this.endDate];
          this.menuTitle = "주간 통계";
          break;

        case "monthDays":
          this.endDate = this.nowDate;
          this.startDate = this.agoMonths;
          this.dates = [this.startDate, this.endDate];
          this.menuTitle = "월간 통계";
          break;

        case "yearDays":
          this.endDate = this.nowDate;
          this.startDate = this.agoYears;
          this.dates = [this.startDate, this.endDate];
          this.menuTitle = "연간 통계";
          break;

        default:
          this.selectFunc();
          break;
      }
    },
    sendEmotion(emotData) {
      console.log("=====감정확인=====");
      this.emotion = emotData;
      console.log(this.emotion);
    },
  },
  components: { DonutGraph },
  created() {
    this.dates = [this.startDate, this.endDate];
  },
  computed: {
    dateRangeText() {
      return this.dates.join(" ~ ");
    },
  },
};
</script>

<style scoped>
.period-board {
  width: 100%;
  height: 100%;
  background-size: contain;
  background-repeat: repeat-y;
  background-image: url("@/assets/statistics/bg_grid_paper.png");
  border-radius: 10%;
  position: relative;
}

.sticker {
  height: 10%;
  position: absolute;
  z-index: 1;
  top: -5%;
  left: 30%;
}

.badge {
  height: 10rem;
}
</style>
