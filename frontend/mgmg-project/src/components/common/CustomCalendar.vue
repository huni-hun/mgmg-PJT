<template>
  <v-container class="calendar">
    <v-row>
      <v-col class="col-4"><div @click="setTodayYearMonth">이전달</div></v-col>
      <v-col class="col-4">
        <div>
          <v-container>
            <v-row>
              <input class="inputBox" type="text" id="showYear" name="showYear" :value="showYear" @change="getInputYearMonth()" />
              <div class="inputBox">.</div>
              <input class="inputBox" type="text" id="showMonth" name="showMonth" :value="showMonth" @change="getInputYearMonth()" />
              <!--             
              <input class="inputBox" type="text" id="showYear" name="showYear" :value="showYear" @change="maketargetMonth(showYear, showMonth)" />
              <div class="inputBox">.</div>
              <input class="inputBox" type="text" id="showMonth" name="showMonth" :value="showMonth" @change="maketargetMonth(showYear, showMonth)" /> -->
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
    <v-row v-for="(week, index) in monthLst" :key="index">
      <!-- <div v-for="(day, idx) in week" :key="idx">{{ day }}</div>
      <div>{{ week }}</div> -->
      <div v-for="(week0, index) in week" :key="index">
        <v-col>
          <div>{{ week0 }}</div>
        </v-col>
      </div>
    </v-row>
    <!-- <v-row>
      <div v-for="(week1, index) in monthLst[1]" :key="index">
        <v-col>
          <div>{{ week1 }}</div>
        </v-col>
      </div>
    </v-row> -->
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
      //달력 상단에 보여줄 값 (오늘 날짜 표기용)
      showYear: 1900,
      showMonth: 1,
      showDate: 1,
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
      //감정 리스트
      emotionLst: [
        [0, 0, 0, 0, 0, 0, 0],
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
    this.setTodayYearMonth();
  },
  methods: {
    //오늘 날짜 가져오기
    setTodayYearMonth() {
      console.log("오늘 연월 가져오기");
      var now = new Date();
      this.showYear = now.getFullYear();
      this.showMonth = now.getMonth() + 1;
      this.showDate = now.getDate();
      //axios에 연, 월 보내서 몽글이 찍기
      console.log(this.showYear, this.showMonth, this.showDate);
      console.log(this.changeToString(this.showYear, this.showMonth)); //202201꼴
      console.log(this.reputation(this.changeToString(this.showYear, this.showMonth))); //190001부터 몇개의 달을 거치는가
      console.log(this.lastdayFirstDate(this.changeToString(this.showYear, this.showMonth)));
      this.makeMonthList(this.changeToString(this.showYear, this.showMonth));
    },
    //입력한 곳에 있는 값을 가져오기
    getInputYearMonth() {
      this.showYear = document.getElementById("showYear").value;
      this.showMonth = document.getElementById("showMonth").value;
      //axios에 연, 월 보내서 몽글이 찍기
      console.log(this.showYear, this.showMonth);
      console.log(this.changeToString(this.showYear, this.showMonth)); //202201꼴
      console.log(this.reputation(this.changeToString(this.showYear, this.showMonth))); //190001부터 몇개의 달을 거치는가
      console.log(this.lastdayFirstDate(this.changeToString(this.showYear, this.showMonth)));
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

    //반복 횟수 정하기
    reputation(targetMonth) {
      var year = targetMonth.slice(0, 4);
      var month = targetMonth.slice(4, 6);
      // console.log(year, month);
      // console.log((year - 1900) * 12 + (month - 1));
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
        if ((this.repeatYear % 4 == 0 && this.repeatMonth == 2 && this.repeatYear % 100 != 0) || (this.repeatYear % 400 == 0 && this.repeatMonth == 2)) {
          this.lastday = 29;
          //일반 2월 28일까지
        } else if (this.repeatMonth == 2) {
          this.lastday = 28;
        } else if ([1, 3, 5, 7, 8, 10, 12].includes(this.repeatMonth)) {
          this.lastday = 31;
        } else {
          this.lastday = 30;
        }
        // this.firstDay = this.day;
      }
      console.log("마지막날", this.lastday, "시작요일", this.repeatDay);
    },

    // 달력에 표시할 날짜 리스트 만들기
    makeMonthList() {
      var startday = this.repeatDay;
      var lastdate = this.lastday;
      // console.log(startday, lastdate);
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
      console.log(this.year, "년", this.month, "월", this.monthLst);
    },

    //   //달의 마지막 날짜와 시작하는 요일 구하기
    //   lastdayOfMonth(targetMonth) {
    //     //반복 횟수
    //     var repeat = this.reputation(targetMonth);
    //     var rep;

    //     for (rep = 0; rep < repeat; rep++) {
    //       //윤달인 년도의 2월은 29일까지 있음
    //       //윤년 2월
    //       if ((this.year % 4 == 0 && this.month == 2 && this.year % 100 != 0) || (this.year % 400 == 0 && this.month == 2)) {
    //         this.lastday = 29;
    //         //일반 2월
    //       } else if (this.month == 2) {
    //         this.lastday = 28;
    //       } else if ([1, 3, 5, 7, 8, 10, 12].includes(this.month)) {
    //         this.lastday = 31;
    //       } else {
    //         this.lastday = 30;
    //       }
    //       this.firstDay = this.day;
    //       this.day = ((this.lastday % 7) + this.day) % 7;
    //       if (this.month == 12) {
    //         this.month = 1;
    //         this.year = Number(this.year) + 1;
    //       } else {
    //         this.month = Number(this.month) + 1;
    //       }
    //     }
    //     console.log("달 +1", this.year, this.month);
    //     //input 바꿔주기
    //     if (this.month < 10) {
    //       this.month = "0" + String(this.month);
    //     } else {
    //       this.month = String(this.month);
    //     }
    //     console.log("10이하달은 0붙이기", this.year, this.month);
    //     // this.premitive_first = String(this.year) + month;
    //   },
    // },

    // data() {
    //   return {
    //     days: [
    //       { idx: 0, dayName: "월" },
    //       { idx: 1, dayName: "화" },
    //       { idx: 2, dayName: "수" },
    //       { idx: 3, dayName: "목" },
    //       { idx: 4, dayName: "금" },
    //       { idx: 5, dayName: "토" },
    //       { idx: 6, dayName: "일" },
    //     ],
    //     showYear: 1900,
    //     showMonth: 1,
    //     month_lastdate: 31,
    //     month_firstdate: 1,
    //     premitive_first: 19000101,

    //     firstDay: 0,
    //     year: 1900,
    //     month: 1,
    //     day: 0,
    //     lastday: 31,

    //     monthLst: [
    //       [0, 0, 1, 0, 0, 0, 0],
    //       [0, 0, 0, 0, 0, 0, 0],
    //       [0, 0, 0, 0, 0, 0, 0],
    //       [0, 0, 0, 0, 0, 0, 0],
    //       [0, 0, 0, 0, 0, 0, 0],
    //       [0, 0, 0, 0, 0, 0, 0],
    //       [0, 0, 0, 0, 0, 0, 0],
    //     ],
    //   };
    // },
    // mounted() {
    //   this.test();

    //   //오늘 날짜 받아오기
    //   this.todayDate();
    // },
    // updated() {
    //   this.makeMonthList("202210");
    // },
    // methods: {
    //   test() {
    //     this.year = document.getElementById("showYear").value;
    //     this.month = document.getElementById("showMonth").value;
    //     console.log("test", this.year, this.month);
    //     // console.log(this.showYear, this.showMonth);
    //     // let now = new Date();
    //     // let date = now.getDate();
    //     // let month = now.getMonth();
    //     // let year = now.getFullYear();
    //     // console.log(year, month, date);
    //   },
    //   //오늘 날짜 받아오기
    //   todayDate() {
    //     var calendarnow = new Date();
    //     var calendardate = calendarnow.getDate();
    //     var calendarmonth = calendarnow.getMonth() + 1;
    //     var calendaryear = calendarnow.getFullYear();
    //     console.log("date로 받은 오늘 날짜는 월을 하나 더해야해요", calendaryear, calendarmonth, calendardate);
    //     this.showYear = calendaryear;
    //     this.showMonth = calendarmonth;
    //     this.date = calendardate;
    //     console.log("data에 있는 show 현재 연월 받아옴", this.showYear, this.showMonth);
    //     //이거를 202201꼴로 바꿔주고, 날짜 리스트를 만들어요.
    //     this.makeMonthList(String(this.maketargetMonth(this.showYear, this.showMonth)));
    //   },
    //   //targetMonth 를 202212꼴로 만들어줘야함@@@@@@@@@@@@@
    //   maketargetMonth(year, month) {
    //     this.showYear = document.getElementById("showYear").value;
    //     this.showMonth = document.getElementById("showMonth").value;
    //     if (month < 10) {
    //       month = "0" + String(month);
    //     } else {
    //       month = String(month);
    //     }
    //     console.log("보여줄 연달 ", String(year) + month);
    //     // var updateMonth = String(year) + month;
    //     // this.makeMonthList(updateMonth);
    //   },
    //   //달력에 표시할 날짜 리스트 만들기
    //   makeMonthList(targetMonth) {
    //     this.lastdayOfMonth(targetMonth);
    //     var startday = this.day;
    //     var lastdate = this.lastday;
    //     // console.log(startday, lastdate);
    //     var rep;
    //     var week;
    //     var days = 1;
    //     //첫째 주만
    //     for (rep = 0; rep < 7; rep++) {
    //       if (rep < startday) {
    //         this.monthLst[0][rep] = 0;
    //       } else {
    //         this.monthLst[0][rep] = days;
    //         days = days + 1;
    //       }
    //     }
    //     //둘째 주부터
    //     for (week = 1; week < 7; week++) {
    //       for (rep = 0; rep < 7; rep++) {
    //         if (days <= lastdate) {
    //           this.monthLst[week][rep] = days;
    //           days = days + 1;
    //         } else {
    //           this.monthLst[week][rep] = 0;
    //         }
    //       }
    //     }
    //     console.log(this.year, "년", this.month, "월", this.monthLst);
    //   },
    //   //dom 업데이트 이후 함수 실행하기
    //   // updateCalendar(targetMonth) {
    //   //   this.$nextTick(function () {
    //   //     this.makeMonthList(targetMonth);
    //   //   });
    //   // },

    //   //반복 횟수 정하기
    //   reputation(targetMonth) {
    //     var year = targetMonth.slice(0, 4);
    //     var month = targetMonth.slice(4, 6);
    //     // console.log(year, month);
    //     // console.log((year - 1900) * 12 + (month - 1));
    //     return (year - 1900) * 12 + (month - 1);
    //   },

    //   //달의 마지막 날짜와 시작하는 요일 구하기
    //   lastdayOfMonth(targetMonth) {
    //     //반복 횟수
    //     var repeat = this.reputation(targetMonth);
    //     var rep;

    //     for (rep = 0; rep < repeat; rep++) {
    //       // console.log(this.year, this.month);

    //       //윤달인 년도의 2월은 29일까지 있음
    //       //윤년 2월
    //       if ((this.year % 4 == 0 && this.month == 2 && this.year % 100 != 0) || (this.year % 400 == 0 && this.month == 2)) {
    //         this.lastday = 29;
    //         //일반 2월
    //       } else if (this.month == 2) {
    //         this.lastday = 28;
    //       } else if ([1, 3, 5, 7, 8, 10, 12].includes(this.month)) {
    //         this.lastday = 31;
    //       } else {
    //         this.lastday = 30;
    //       }
    //       this.firstDay = this.day;
    //       this.day = ((this.lastday % 7) + this.day) % 7;
    //       // console.log("이번달 막날", this.lastday);
    //       // console.log("이번달 첫요일", this.firstDay);
    //       if (this.month == 12) {
    //         this.month = 1;
    //         this.year = Number(this.year) + 1;
    //       } else {
    //         this.month = Number(this.month) + 1;
    //       }
    //     }
    //     console.log("달 +1", this.year, this.month);
    //     //input 바꿔주기
    //     if (this.month < 10) {
    //       this.month = "0" + String(this.month);
    //     } else {
    //       this.month = String(this.month);
    //     }
    //     console.log("10이하달은 0붙이기", this.year, this.month);
    //     // this.premitive_first = String(this.year) + month;
    //   },
    // },
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
