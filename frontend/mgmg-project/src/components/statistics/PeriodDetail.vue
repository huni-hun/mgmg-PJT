<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-col cols="12" md="6">
          <div class="donut-frame">
            <donut-graph :startDate="startDate" :endDate="endDate" @send-emotion="sendEmotion" />
          </div>
        </v-col>
        <v-col cols="12" md="6">
          <div class="period-board">
            <img class="sticker" :src="require(`@/assets/statistics/adehesive_plaster.png`)" alt="" />

            <br />
            <!-- 달력 picker -->
            <div v-if="isPicker">
              <v-row justify="center">
                <v-dialog v-model="isPicker" persistent max-width="290">
                  <v-card>
                    <v-text-field v-model="dateRangeText" background-color="#FFF" label="Date range" prepend-icon="mdi-calendar" readonly></v-text-field>
                    <v-date-picker v-model="dates" range no-title :max="today" :min="allowMaxDate" color="green" class="custom-picker">
                      <v-spacer />
                      <v-btn text class="font-weight-bold" color="gray" @click="selectFunc()"> 취소 </v-btn>
                      <v-btn text class="font-weight-bold" color="green " @click="pickerFunc()"> 확인 </v-btn>
                    </v-date-picker>
                  </v-card>
                </v-dialog>
              </v-row>
            </div>
            <!-- menus 와 정보 -->
            <div v-else class="text-center">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn class="periodMenu" color="primary" dark v-bind="attrs" v-on="on">
                    {{ menuTitle }}
                    <v-icon class="periodMenuIcon" large>mdi-menu-down</v-icon>
                  </v-btn>
                </template>
                <v-list>
                  <v-list-item v-for="(item, index) in items" :key="index">
                    <v-list-item-title @click="periodFunc(item.func)">{{ item.title }}</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
              <p @click="selectFunc()" class="date-period">{{ startDate }} ~ {{ endDate }}</p>
              <p v-if="!isMostEmotion" class="most-emotion">일기를 쓰면 배지를 선물로 줘요.</p>
              <p v-else class="most-emotion">제일 많이 느낀 감정 : "{{ mostEmotion }}"</p>
              <img class="badge" :src="require(`@/assets/emoticon/${imgSticker}.png`)" alt="" />
              <p class="explanation">{{ emotionExplanation }}</p>
              <p class="by-person">by. {{ explanationPerson }}</p>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <div></div>
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
    today: moment().format("YYYY-MM-DD"), // 오늘
    allowMaxDate: moment().subtract(3, "years").format("YYYY-MM-DD"), // 넉넉하게 3년
    startDate: moment().subtract(1, "weeks").format("YYYY-MM-DD"),
    endDate: moment().format("YYYY-MM-DD"), // 오늘
    nowDate: moment().format("YYYY-MM-DD"),
    agoWeeks: moment().subtract(1, "weeks").format("YYYY-MM-DD"),
    agoMonths: moment().subtract(1, "months").format("YYYY-MM-DD"),
    agoYears: moment().subtract(1, "years").format("YYYY-MM-DD"),
    isPicker: false,
    dates: [],
    emotionExplanation: "일기를 써줘요. 당신의 감정을 기억할께요.",
    explanationPerson: "몽글이",
    mostEmotion: "몽글", // 가장 많은 감정
    isMostEmotion: false, // 가장 많은 감정 있는지
    imgNameData: {
      슬픔: "sad",
      공포: "fear",
      피곤: "fatigue",
      화: "angry",
      기대: "expect",
      평온: "calm",
      창피: "shame",
      짜증: "annoyed",
      기쁨: "happy",
      사랑: "love",
      몽글: "mgmg",
    },
    imgSticker: "mgmg",
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
      this.menuTitle = "기간 통계";
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
      this.emotionExplanation = emotData.emotionExplanation;
      this.explanationPerson = emotData.explanationPerson;
      this.mostEmotion = emotData.mostEmotion;
      if (this.mostEmotion == "몽글") {
        this.isMostEmotion = false;
      } else {
        this.isMostEmotion = true;
      }
      this.imgSticker = this.imgNameData[emotData.mostEmotion];
    },
    getDay(date) {
      const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
      let i = new Date(date).getDay(date);
      return daysOfWeek[i];
    },
    getMonth(date) {
      const monthName = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];

      let i = new Date(date).getMonth(date);
      return monthName[i];
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

<style scoped lang="scss">
/* 달력 picker */
.custom-picker {
  border-radius: 10px !important;

  .v-btn--active.green {
    background-color: #edffff !important;

    .v-btn__content {
      color: #00b1bb !important;
      font-weight: bold !important;
    }
  }

  .v-picker__title {
    display: none !important;
  }

  .v-date-picker-header {
    padding-top: 10px !important;
  }
}

.v-date-picker-table thead tr th {
  color: #1c1c1c !important;
  font-weight: 400 !important;

  &:nth-child(1) {
    color: #ff7451 !important;
  }

  &:nth-child(7) {
    color: #ff7451 !important;
  }
}

.v-date-picker-table tbody tr td {
  &:nth-child(1) {
    .v-btn--disabled {
      .v-btn__content {
        color: #ffcbbe;
      }
    }

    .v-btn__content {
      color: #ff7451;
    }
  }

  &:nth-child(7) {
    .v-btn--disabled {
      .v-btn__content {
        color: #ffcbbe;
      }
    }

    .v-btn__content {
      color: #ff7451;
    }
  }
}

.date-picker {
  max-width: 40vh;
  width: 100%;
  margin-left: auto;
  margin-right: auto;
}

.period-board {
  background-size: contain;
  background-repeat: repeat;
  background-image: url("@/assets/statistics/bg_grid_paper.png");
  border-radius: 10%;
  position: relative;
  height: 60vh;
}

.donut-frame {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  border-radius: 10%;
  background-color: rgba(226, 226, 226, 0.356);
  height: 60vh;
}

.sticker {
  height: 10%;
  position: absolute;
  z-index: 1;
  top: -5%;
  left: 30%;
}

/* 이미지 */
.badge {
  height: 24vh;
}

.periodMenu {
  margin: 1rem;
}

/* 큰 태블릿 세로*/
@media (max-width: 1023px) {
  .date-picker {
    max-width: 30vh;
  }

  .period-board {
    height: 50vh;
  }
  .donut-frame {
    height: 50vh;
  }
  .badge {
    height: 20vh;
  }

  .periodMenu {
    margin: 1rem;
  }

  .date-period {
    margin: 0;
    font-size: 1rem;
  }

  .most-emotion {
    font-size: 1rem;
  }

  .explanation {
    margin: 0;
    font-size: 1rem;
  }

  .by-person {
    font-size: 1rem;
  }
}
@media (max-width: 960px) {
  .date-picker {
    max-width: 30vw;
  }
  .period-board {
    height: 40vh;
  }
  .donut-frame {
    height: 30vh;
  }
}
/* 작은 태블릿 세로*/
@media (max-width: 767px) {
  .period-board {
    height: 36vh;
  }
  .donut-frame {
    height: 30vh;
  }
  .badge {
    height: 12vh;
  }

  .periodMenu {
    margin: 0.5rem;
  }

  .date-period {
    margin: 0;
  }

  .most-emotion {
    margin: 0;
  }

  .explanation {
    margin: 0;
  }
}

/* 스마트폰 세로 */
@media (max-width: 480px) {
  .period-board {
    height: 39vh;
  }
  .donut-frame {
    height: 27vh;
  }
  .periodMenu {
    font-size: 1em;
    margin: 1rem 0 0.5em 0;
  }
  .periodMenuIcon {
    font-size: 0.5em;
  }
  .date-period {
    font-size: 0.5rem;
  }
  .most-emotion {
    font-size: 0.5rem;
  }

  .explanation {
    font-size: 0.5rem;
  }

  .by-person {
    font-size: 0.5rem;
  }
}
/* 갤럭시 폴드 */
@media (max-width: 300px) {
}
</style>
