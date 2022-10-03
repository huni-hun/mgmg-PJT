<template>
  <div>
    <div class="backBox">
      <div ref="backYellow"></div>
    </div>
    <img class="maskingTape" src="@/assets/statistics/maskingTape.png" />
    <div class="calendarBody" ref="calendar">
      <div class="calendar">
        <v-row>
          <v-col class="leftAlign">
            <!-- <div @click="beforeMonth()" class="leftAlign">이전달</div> -->
            <v-icon @click="beforeMonth()" class="leftAlign"
              >mdi-menu-left</v-icon
            >
          </v-col>
          <v-col class="centerYearMonth">
            <!-- <v-container> -->
            <!-- <v-row class="yearMonth"> -->
            <input
              class="inputLabel"
              type="number"
              id="showYear"
              name="showYear"
              :value="showYear"
              @input="getInputYearMonth()"
            />
            <div class="inputDotLabel">.</div>
            <input
              class="inputLabel"
              type="number"
              id="showMonth"
              name="showMonth"
              :value="showMonth"
              @input="getInputYearMonth()"
            />
            <!-- </v-row> -->
            <!-- </v-container> -->
          </v-col>
          <v-col class="rightAlign">
            <!-- <div @click="afterMonth()" class="rightAlign">다음달</div> -->
            <v-icon @click="afterMonth()" class="rightAlign"
              >mdi-menu-right</v-icon
            >
          </v-col>
        </v-row>
        <v-row class="dateTable">
          <div
            v-for="day in days"
            :key="day.idx"
            class="dayContainer"
            @click="updateEmotionLst()"
          >
            <v-col>
              <div class="dayName">{{ day.dayName }}</div>
            </v-col>
          </div>
        </v-row>
        <v-row
          class="dateTable"
          v-for="(week, index1) in monthLst"
          :key="index1"
        >
          <div
            v-for="(week0, index2) in week"
            :key="index2"
            class="dayContainer"
          >
            <v-col>
              <emotionImage
                v-if="week0 != 0"
                :showYear="showYear"
                :showMonth="showMonth"
                :dateNum="week0"
                :emotionImg="emotionsLst[index1][index2]"
                :diaryNumber="diaryNum[index1][index2]"
                name="emotionImg"
              />
            </v-col>
          </div>
        </v-row>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { monthlyDiaryList } from "@/api/diary.js";
import EmotionImage from "@/components/common/CustomCalendarImage.vue";

export default {
  components: { EmotionImage },
  data() {
    return {
      emotionImage: "0",
      days: [
        { idx: 0, dayName: "월" },
        { idx: 1, dayName: "화" },
        { idx: 2, dayName: "수" },
        { idx: 3, dayName: "목" },
        { idx: 4, dayName: "금" },
        { idx: 5, dayName: "토" },
        { idx: 6, dayName: "일" },
      ],
      //달력 상단에 보여줄 값 (오늘 날짜 표기용)
      showYear: 1900,
      showMonth: 1,
      showDate: 1,

      todayYear: 1900,
      todayMonth: 1,

      //반복해서 해당달의 시작요일, 마지막날 구할때 전역으로 쓰는 변수
      repeatYear: 1900, //년
      repeatMonth: 1, //월
      repeatDate: 1, //일
      repeatDay: 0, //요일
      lastday: 31, //월 길이
      //달력 리스트
      monthLst: [
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
      ],
      //감정 리스트(기존)
      emotionLst: [
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
      ],
      //일기 번호 리스트
      diaryNum: [
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
      ],
      //axios로 받아온 작성 일기 정보
      diaryLst: "",
      emotionsLst: [
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
      ],

      width: 0,
      height: 0,
    };
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  mounted() {
    this.setTodayYearMonth();
  },
  updated() {
    this.backSize();
  },
  methods: {
    handleResize() {
      this.width = window.innerWidth;
      this.height = window.innerHeight;
    },
    //axios로 몽글이 가져오는 함수
    async monthlyDiaryList(monthInput) {
      this.emotionLst = [
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
        ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
      ];
      //일기 번호 리스트
      this.diaryNum = [
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
      ];
      let response = await monthlyDiaryList(this.accessToken, monthInput);

      var diaryDirectionLst = [];

      if (response.statusCode == 200) {
        // console.log(response);
        this.diaryLst = response.diaries;
        // var diaryDirection;
        if (this.diaryLst.length > 0) {
          var weeks;
          for (weeks = 0; weeks < this.monthLst.length; weeks++) {
            var days;
            for (days = 0; days < this.monthLst[weeks].length; days++) {
              var rep;
              for (rep = 0; rep < this.diaryLst.length; rep++) {
                if (
                  this.monthLst[weeks][days] ==
                  this.diaryLst[rep].diaryDate.slice(8, 10)
                ) {
                  diaryDirectionLst.push([
                    weeks,
                    days,
                    this.diaryLst[rep].emotion,
                    this.diaryLst[rep].diaryNo,
                  ]);
                }
              }
            }
          }
          var diaryIdx;
          for (diaryIdx = 0; diaryIdx < diaryDirectionLst.length; diaryIdx++) {
            this.emotionLst[diaryDirectionLst[diaryIdx][0]][
              diaryDirectionLst[diaryIdx][1]
            ] = diaryDirectionLst[diaryIdx][2];
            this.diaryNum[diaryDirectionLst[diaryIdx][0]][
              diaryDirectionLst[diaryIdx][1]
            ] = diaryDirectionLst[diaryIdx][3];
          }
        } else {
          //감정 리스트
          this.emotionLst = [
            ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
            ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
            ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
            ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
            ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
            ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
            ["없음", "없음", "없음", "없음", "없음", "없음", "없음"],
          ];
          //일기 번호 리스트
          this.diaryNum = [
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0],
          ];
        }
        this.updateEmotionLst();
      }
    },
    updateEmotionLst() {
      this.emotionsLst = [...this.emotionLst];
    },

    //오늘 날짜 가져오기
    setTodayYearMonth() {
      var now = new Date();
      this.todayYear = now.getFullYear();
      this.todayMonth = now.getMonth() + 1;

      this.showYear = now.getFullYear();
      this.showMonth = now.getMonth() + 1;
      this.showDate = now.getDate();

      this.lastdayFirstDate(this.changeToString(this.showYear, this.showMonth));
      this.monthlyDiaryList(
        this.changeToAxiosShape(this.showYear, this.showMonth)
      );
      this.makeMonthList(this.changeToString(this.showYear, this.showMonth));
    },
    //입력한 곳에 있는 값을 가져오기
    getInputYearMonth() {
      this.showYear = document.getElementById("showYear").value;
      this.showMonth = document.getElementById("showMonth").value;

      if (this.showMonth < 1) {
        this.showYear--;
        this.showMonth = 12;
      } else if (12 < this.showMonth) {
        this.showYear++;
        this.showMonth = 1;
      }

      if (
        (this.todayYear == this.showYear && this.todayMonth < this.showMonth) ||
        this.todayYear < this.showYear
      ) {
        this.showYear = this.todayYear;
        this.showMonth = this.todayMonth;
      }

      this.lastdayFirstDate(this.changeToString(this.showYear, this.showMonth));
      this.monthlyDiaryList(
        this.changeToAxiosShape(this.showYear, this.showMonth)
      );
      this.makeMonthList(this.changeToString(this.showYear, this.showMonth));
    },

    //연, 월 숫자 받아서 202201 꼴 string으로 바꿔주기
    changeToString(year, month) {
      if (month < 10) {
        return String(year) + "0" + String(month);
      } else {
        return String(year) + String(month);
      }
    },
    //연, 월 숫자 받아서 202201 꼴 string으로 바꿔주기
    changeToAxiosShape(year, month) {
      if (month < 10) {
        // console.log(String(year) + "-0" + String(month));
        return String(year) + "-0" + String(month);
      } else {
        // console.log(String(year) + "-" + String(month));
        return String(year) + "-" + String(month);
      }
    },

    //반복 횟수 정하기
    reputation(targetMonth) {
      var year = targetMonth.slice(0, 4);
      var month = targetMonth.slice(4, 6);
      return (year - 1900) * 12 + (month - 1);
    },

    lastdayFirstDate(targetMonth) {
      //초기화
      this.repeatYear = 1900; //년
      this.repeatMonth = 1; //월
      this.repeatDate = 1; //일
      this.repeatDay = 0; //요일
      this.lastday = 31; //월 길이

      //반복 횟수 계산
      var repeat = this.reputation(targetMonth);
      var rep;

      for (rep = 0; rep < repeat; rep++) {
        //한 달 지나서 연, 월 바꿔주기
        if (this.repeatMonth == 12) {
          this.repeatMonth = 1;
          this.repeatYear = Number(this.repeatYear) + 1;
        } else {
          this.repeatMonth = Number(this.repeatMonth) + 1;
        }
        this.repeatDay = ((this.lastday % 7) + this.repeatDay) % 7;

        //월별 일 개수 구하기
        //윤년 2월 29일까지
        if (
          (this.repeatYear % 4 == 0 &&
            this.repeatMonth == 2 &&
            this.repeatYear % 100 != 0) ||
          (this.repeatYear % 400 == 0 && this.repeatMonth == 2)
        ) {
          this.lastday = 29;
          //일반 2월 28일까지
        } else if (this.repeatMonth == 2) {
          this.lastday = 28;
        } else if ([1, 3, 5, 7, 8, 10, 12].includes(this.repeatMonth)) {
          this.lastday = 31;
        } else {
          this.lastday = 30;
        }
      }
      // console.log("마지막날", this.lastday, "시작요일", this.repeatDay);
    },

    // 달력에 표시할 날짜 리스트 만들기
    makeMonthList() {
      var startday = this.repeatDay;
      var lastdate = this.lastday;
      var rep;
      var week;
      var days = 1;
      //첫째 주만
      for (rep = 0; rep < 7; rep++) {
        if (rep < startday) {
          this.monthLst[0][rep] = 0;
        } else {
          this.monthLst[0][rep] = days;
          days = days + 1;
        }
      }
      //둘째 주부터
      for (week = 1; week < 7; week++) {
        for (rep = 0; rep < 7; rep++) {
          if (days <= lastdate) {
            this.monthLst[week][rep] = days;
            days = days + 1;
          } else {
            this.monthLst[week][rep] = 0;
          }
        }
      }
      this.showYear = Number(this.showYear);
      this.showMonth = Number(this.showMonth);
    },

    //이전달
    beforeMonth() {
      // this.showMonth--;
      document.getElementById("showMonth").value = Number(this.showMonth) - 1;
      this.getInputYearMonth();
    },
    //다음달
    afterMonth() {
      document.getElementById("showMonth").value = Number(this.showMonth) + 1;
      this.getInputYearMonth();
    },
    backSize() {
      let box = this.$refs.backYellow;
      box.style.width = "auto";
      box.style.height = "auto";
      box.style.width = `${
        this.$refs.calendar.scrollWidth *
        (100 / document.documentElement.clientWidth)
      }vw`;
      box.style.height = `${
        this.$refs.calendar.scrollHeight *
        (100 / document.documentElement.clientHeight)
      }vh`;
    },
  },
};
</script>

<style scoped>
.backBox {
  position: absolute;
  left: 50%;
  transform: translate(-50%, 0%);
  z-index: 1;
}

.backBox > div {
  background-color: #e5d7ca;
  transform: rotate(2.5deg);
}

.maskingTape {
  position: absolute;
  min-width: 160px;
  width: 12%;
  max-width: 260px;
  /* top: 100%; */
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 3;
}

.calendarBody {
  position: relative;
  padding-top: 30px;
  padding-bottom: 30px;
  /* width: 80vw; */
  /* border: 2px solid black; */
  background-color: white;
  z-index: 2;
}

/* .calendar {
  margin: 2rem;
  border: 2px solid black;
} */

.leftArrow {
  width: 10%;
}

.rightArrow {
  width: 10%;
}

.centerYearMonth {
  width: 80%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.yearMonth {
  margin-top: 0.5%;
  /* margin-bottom: 1%; */
  text-align: center;
  display: flex;
  justify-content: center;
}

.leftAlign {
  font-size: 3rem;
  text-align: right;
  /* margin-left: 5%; */
}

.rightAlign {
  font-size: 3rem;
  /* text-align: right; */
  /* margin-right: 5%; */
}

.dateTable {
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}

.dayContainer {
  width: 13%;
  height: auto;
  padding: 2px;
}

.dayName {
  text-align: center;
  margin-bottom: 0.2rem;
}

.inputLabel {
  font-size: 1.5em;
  width: 3em;
  text-align: center;
}

.inputDotLabel {
  font-size: 1.5em;
  text-align: center;
}

.container {
  padding: 0;
}

.col-xl,
.col-xl-auto,
.col-xl-12,
.col-xl-11,
.col-xl-10,
.col-xl-9,
.col-xl-8,
.col-xl-7,
.col-xl-6,
.col-xl-5,
.col-xl-4,
.col-xl-3,
.col-xl-2,
.col-xl-1,
.col-lg,
.col-lg-auto,
.col-lg-12,
.col-lg-11,
.col-lg-10,
.col-lg-9,
.col-lg-8,
.col-lg-7,
.col-lg-6,
.col-lg-5,
.col-lg-4,
.col-lg-3,
.col-lg-2,
.col-lg-1,
.col-md,
.col-md-auto,
.col-md-12,
.col-md-11,
.col-md-10,
.col-md-9,
.col-md-8,
.col-md-7,
.col-md-6,
.col-md-5,
.col-md-4,
.col-md-3,
.col-md-2,
.col-md-1,
.col-sm,
.col-sm-auto,
.col-sm-12,
.col-sm-11,
.col-sm-10,
.col-sm-9,
.col-sm-8,
.col-sm-7,
.col-sm-6,
.col-sm-5,
.col-sm-4,
.col-sm-3,
.col-sm-2,
.col-sm-1,
.col,
.col-auto,
.col-12,
.col-11,
.col-10,
.col-9,
.col-8,
.col-7,
.col-6,
.col-5,
.col-4,
.col-3,
.col-2,
.col-1 {
  padding: 0;
}
</style>
