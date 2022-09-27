<template>
  <v-container class="calendar">
    <v-row>
      <v-col class="col-4"><div @click="beforeMonth()">이전달</div></v-col>
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
      <v-col class="col-4"><div @click="afterMonth()">다음달</div></v-col>
    </v-row>
    <v-row>
      <div v-for="day in days" :key="day.idx" class="col-1" @click="updateEmotionLst()">
        <v-col>
          <div>{{ day.dayName }}</div>
        </v-col>
      </div>
    </v-row>
    <v-row v-for="(week, index1) in monthLst" :key="index1">
      <!-- <div v-for="(day, idx) in week" :key="idx">{{ day }}</div>
      <div>{{ week }}</div> -->
      <div v-for="(week0, index2) in week" :key="index2" class="col-1">
        <v-col>
          <!-- <div v-if="week0 != 0">{{ week0 }}</div> -->
          <emotionImage
            v-if="week0 != 0"
            :showYear="showYear"
            :showMonth="showMonth"
            :dateNum="week0"
            :emotionImg="emotionsLst[index1][index2]"
            :diaryNumber="diaryNum[index1][index2]"
            name="emotionImg"
          />
          <!-- <div v-if="week0 != 0" name="emotion">{{ emotionsLst[index1][index2] }}</div> -->
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
      testInt: 1,
    };
  },
  // computed: {
  //   emotionsLst(){
  //     return this.emotionLst
  //   }
  // },
  mounted() {
    this.setTodayYearMonth();
  },
  methods: {
    test() {
      console.log(this.diaryLst[0]);
    },
    //axios로 몽글이 가져오는 함수
    async monthlyDiaryList(monthInput) {
      console.log("월별 몽글이 리스트");
      let response = await monthlyDiaryList(monthInput);
      console.log("응답 데이터", response);

      if (response.statusCode == 200) {
        console.log(response);
        this.diaryLst = response.diaries;

        if (this.diaryLst.length > 0) {
          console.log("다이어리스트있음", this.diaryLst);
          var rep;
          for (rep = 0; rep < this.diaryLst.length; rep++) {
            var weeks;
            for (weeks = 0; weeks < this.monthLst.length; weeks++) {
              var days;
              for (days = 0; days < this.monthLst[weeks].length; days++) {
                console.log(this.diaryLst[rep].diaryDate.slice(8, 10));
                // console.log(rep);
                if (this.monthLst[weeks][days] == this.diaryLst[rep].diaryDate.slice(8, 10)) {
                  console.log(this.diaryLst[rep].emotion);
                  console.log(this.diaryLst[rep].diaryNo);
                  console.log(weeks, days);

                  this.emotionLst[weeks][days] = this.diaryLst[rep].emotion;
                  this.diaryNum[weeks][days] = this.diaryLst[rep].diaryNo;
                } else {
                  this.emotionLst[weeks][days] = "없음";
                  this.diaryNum[weeks][days] = 0;
                }
              }
            }
          }
        } else {
          console.log("다이어리스트없음", this.diaryLst);
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

      //몽글이 이미지

      // console.log(this.diaryLst[0].diaryDate);
      console.log(this.emotionLst);
      console.log(this.diaryNum);
    },
    updateEmotionLst() {
      // this.$set(this.emotionLst, "emotion", this.emotionLst);
      this.emotionsLst = [...this.emotionLst]; //이걸로 하면 되긴 되는데 무한 반복 됨.
      console.log(this.emotionsLst, this.emotionLst);
      console.log("updateEmotionLst");
      // vm.$forceUpdate();
    },
    //과거 일기 내역 확인
    writtenDiray(day) {
      console.log(this.diaryLst);
      console.log("DDDDDDDDDD", this.diaryLst.length);
      var rep;
      for (rep = 0; rep < this.diaryLst.length; rep++) {
        console.log("EEEEEEEEEEE", this.diaryLst[rep]);
        if (this.diaryLst[rep].diaryDate.slice(8, 10) == day) {
          console.log("결과조회", this.diaryLst[rep].diaryNo, this.diaryLst[rep].emotion);
        }
      }
      this.diaryLst;
    },

    //오늘 날짜 가져오기
    setTodayYearMonth() {
      console.log("오늘 연월 가져오기");
      var now = new Date();
      this.showYear = now.getFullYear();
      this.showMonth = now.getMonth() + 1;
      this.showDate = now.getDate();

      // console.log(this.showYear, this.showMonth, this.showDate);
      // console.log(this.changeToString(this.showYear, this.showMonth)); //202201꼴
      // console.log(this.reputation(this.changeToString(this.showYear, this.showMonth))); //190001부터 몇개의 달을 거치는가
      console.log(this.lastdayFirstDate(this.changeToString(this.showYear, this.showMonth)));
      //axios에 연, 월 보내서 몽글이 찍기
      this.monthlyDiaryList(this.changeToAxiosShape(this.showYear, this.showMonth));

      this.makeMonthList(this.changeToString(this.showYear, this.showMonth));
    },
    //입력한 곳에 있는 값을 가져오기
    getInputYearMonth() {
      this.showYear = document.getElementById("showYear").value;
      this.showMonth = document.getElementById("showMonth").value;

      // console.log(this.showYear, this.showMonth);
      // console.log(this.changeToString(this.showYear, this.showMonth)); //202201꼴
      // console.log(this.reputation(this.changeToString(this.showYear, this.showMonth))); //190001부터 몇개의 달을 거치는가
      console.log(this.lastdayFirstDate(this.changeToString(this.showYear, this.showMonth)));
      //axios에 연, 월 보내서 몽글이 찍기
      console.log("input", this.showYear, this.showMonth);
      this.monthlyDiaryList(this.changeToAxiosShape(this.showYear, this.showMonth));

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
        console.log(String(year) + "-0" + String(month));
        return String(year) + "-0" + String(month);
      } else {
        console.log(String(year) + "-" + String(month));
        return String(year) + "-" + String(month);
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
          // this.writtenDiray(days);
        }
      }
      //둘째 주부터
      for (week = 1; week < 7; week++) {
        for (rep = 0; rep < 7; rep++) {
          if (days <= lastdate) {
            this.monthLst[week][rep] = days;
            days = days + 1;
            // this.writtenDiray(days);
          } else {
            this.monthLst[week][rep] = 0;
          }
        }
      }
      console.log(this.showYear, "년", this.showMonth, "월", this.monthLst);
    },

    //이전달
    beforeMonth() {
      // this.showMonth--;
      document.getElementById("showMonth").value = Number(this.showMonth) - 1;
      console.log("aaaaaaaaaa" + this.showMonth);
      this.getInputYearMonth();
    },
    //다음달
    afterMonth() {
      document.getElementById("showMonth").value = Number(this.showMonth) + 1;
      console.log("aaaaaaaaaa" + this.showMonth);
      this.getInputYearMonth();
    },
  },
};
</script>

<style scoped>
.calendar {
  width: 100vw;
  height: 80vh;
  border: 2px solid black;
}
.inputBox {
  width: 3em;
  margin: 0;
}
</style>
