<template>
  <v-container class="calendar">
    <v-row>
      <v-col class="col-4"><div @click="test">이전달</div></v-col>
      <v-col class="col-4">
        <div>
          <v-container>
            <v-row>
              <input class="inputBox" type="text" id="showYear" name="showYear" :value="showYear" @change="maketargetMonth(showYear, showMonth)" />
              <div class="inputBox">.</div>
              <input class="inputBox" type="text" id="showMonth" name="showMonth" :value="showMonth" @change="maketargetMonth(showYear, showMonth)" />
            </v-row>
          </v-container>
        </div>
      </v-col>
      <v-col class="col-4"><div>다음달</div></v-col>
    </v-row>
    <v-row>
      <div v-for="day in days" :key="day.idx">
        <v-col>
          <div>{{ day.dayName }}</div>
        </v-col>
      </div>
    </v-row>
    <v-row>
      <div v-for="(week0, index) in monthLst[0]" :key="index">
        <v-col>
          <div :value="week0">{{ week0 }}</div>
        </v-col>
      </div>
    </v-row>
    <v-row>
      <div v-for="week1 in monthLst[1]" :key="week1">
        <v-col>
          <div>{{ week1 }}</div>
        </v-col>
      </div>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      days: [
        { idx: 0, dayName: "월" },
        { idx: 1, dayName: "화" },
        { idx: 2, dayName: "수" },
        { idx: 3, dayName: "목" },
        { idx: 4, dayName: "금" },
        { idx: 5, dayName: "토" },
        { idx: 6, dayName: "일" },
      ],
      showYear: 1,
      showMonth: 1,
      month_lastdate: 31,
      month_firstdate: 1,
      premitive_first: 19000101,

      firstDay: 0,
      year: 1900,
      month: 1,
      day: 0,
      lastday: 31,

      monthLst: [
        [0, 0, 1, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0],
      ],
    };
  },
  mounted() {
    this.test();

    //오늘 날짜 받아오기
    this.todayDate();
    // this.lastdayOfMonth(202002);
    // this.lastdayOfMonth(202209);
    // this.lastdayOfMonth(201001);

    // this.lastdayOfMonth(2022, 9, 3, 30);
    // this.lastdayOfMonth(2022, 9, 3, 30);

    // this.reputation("190012");
  },
  updated() {
    this.makeMonthList("202210");
  },
  methods: {
    test() {
      console.log(this.showYear, this.showMonth);
      // let now = new Date();
      // let date = now.getDate();
      // let month = now.getMonth();
      // let year = now.getFullYear();
      // console.log(year, month, date);
    },
    //오늘 날짜 받아오기
    todayDate() {
      let now = new Date();
      let date = now.getDate();
      let month = now.getMonth();
      let year = now.getFullYear();
      this.showYear = year;
      this.showMonth = month;
      this.date = date;
    },
    //dom 업데이트 이후 함수 실행하기
    updateCalendar(targetMonth) {
      this.$nextTick(function () {
        this.makeMonthList(targetMonth);
      });
    },
    //달력에 표시할 날짜 리스트 만들기
    makeMonthList(targetMonth) {
      this.lastdayOfMonth(targetMonth);
      var startday = this.day;
      var lastdate = this.lastday;
      console.log(startday, lastdate);
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

      console.log(this.monthLst);
    },

    //반복 횟수 정하기
    reputation(targetMonth) {
      var year = targetMonth.slice(0, 4);
      var month = targetMonth.slice(4, 6);
      // console.log(year, month);
      // console.log((year - 1900) * 12 + (month - 1));
      return (year - 1900) * 12 + (month - 1);
    },

    //달의 마지막 날짜와 시작하는 요일 구하기
    lastdayOfMonth(targetMonth) {
      //반복 횟수
      var reapit = this.reputation(targetMonth);
      var rep;

      for (rep = 0; rep < reapit; rep++) {
        // console.log(this.year, this.month);

        //윤달인 년도의 2월은 29일까지 있음
        //윤년 2월
        if ((this.year % 4 == 0 && this.month == 2 && this.year % 100 != 0) || (this.year % 400 == 0 && this.month == 2)) {
          this.lastday = 29;
          //일반 2월
        } else if (this.month == 2) {
          this.lastday = 28;
        } else if ([1, 3, 5, 7, 8, 10, 12].includes(this.month)) {
          this.lastday = 31;
        } else {
          this.lastday = 30;
        }
        this.firstDay = this.day;
        this.day = ((this.lastday % 7) + this.day) % 7;
        // console.log("이번달 막날", this.lastday);
        // console.log("이번달 첫요일", this.firstDay);
        if (this.month == 12) {
          this.month = 1;
          this.year = this.year + 1;
        } else {
          this.month = this.month + 1;
        }
      }
      var month;
      //input 바꿔주기
      if (this.month < 10) {
        month = "0" + String(this.month);
      } else {
        month = String(this.month);
      }
      this.premitive_first = String(this.year) + month;
    },
    //targetMonth 를 202212꼴로 만들어줘야함
    maketargetMonth(year, month) {
      if (month < 10) {
        month = "0" + String(month);
      } else {
        month = String(month);
      }
      console.log(String(year) + month);
      return String(year) + month;
    },
  },
};
</script>

<style scoped>
.calendar {
  width: 100vh;
  height: 80vh;
  border: 2px solid black;
}
.inputBox {
  width: 3em;
  margin: 0;
}
</style>
