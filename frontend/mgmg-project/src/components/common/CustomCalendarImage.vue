<template>
  <div class="calendarImageBody">
    <v-container @click="test(showYear, showMonth, dateNum, diaryNumber)" :class="{ dateToday: checkToday() == 1, datePast: checkToday() == 0, dateFuture: checkToday() == 2 }">
      <div class="dateNum">{{ dateNum }}</div>
      <img v-if="!!emotionImgLst[emotionImg] && !this.isToday()" class="emoticonImg shadow" :src="require(`@/assets/emoticon/${emotionImgLst[emotionImg]}.png`)" alt="" />
    </v-container>
  </div>
</template>

<script>
export default {
  name: "CalendarEmotionImage",
  props: { emotionImg: { type: String }, dateNum: { type: Number }, showYear: { type: Number }, showMonth: { type: Number }, diaryNumber: { type: Number } },
  data() {
    return {
      emotionImgLst: {
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
        없음: "",
      },
    };
  },
  methods: {
    //오늘 날짜면 기본 뭉글이 보여주기
    isToday() {
      var now = new Date();

      var todayYear = now.getFullYear();
      var todayMonth = now.getMonth() + 1;
      var todayDate = now.getDate();
      //오늘 날이면 true
      if (this.nowYear == todayYear && this.nowMonth == todayMonth && this.nowDate == todayDate) {
        return true;
      } else {
        return false;
      }
      //오늘이 아니면 false
    },
    //
    checkToday() {
      var now = new Date();

      var todayYear = now.getFullYear();
      var todayMonth = now.getMonth() + 1;
      var todayDate = now.getDate();

      var todayNumber = this.changeDateFormatNoDash(todayYear, todayMonth, todayDate);
      var showNumber = this.changeDateFormatNoDash(this.showYear, this.showMonth, this.dateNum);
      // console.log(todayNumber, showNumber);
      //오늘이면
      if (todayNumber == showNumber) {
        return 1;
      } else if (todayNumber > showNumber) {
        //과거
        return 0;
      } else {
        return 2;
      }
    },
    test(year, month, date, diarynum) {
      //다이어리 넘버 0일떄랑 숫자일 때 구분

      var now = new Date();
      var todayYear = now.getFullYear();
      var todayMonth = now.getMonth() + 1;
      var todayDate = now.getDate();

      var todayNumber = this.changeDateFormatNoDash(todayYear, todayMonth, todayDate);
      var showNumber = this.changeDateFormatNoDash(year, month, date);

      if (todayNumber >= showNumber) {
        if (diarynum == 0) {
          //일기작성
          this.$router.push({
            name: "diarywriting",
            params: { date: this.changeDateFormat(year, month, date) },
          });
        } else {
          //일기 상세보기
          this.$router.push({
            name: "diarydetail",
            params: { no: diarynum },
          });
        }
      }
    },
    changeDateFormatNoDash(year, month, date) {
      var finYear = String(year);
      var finMonth;
      var finDate;
      if (month < 10) {
        finMonth = "0" + String(month);
      } else {
        finMonth = String(month);
      }
      if (date < 10) {
        finDate = "0" + String(date);
      } else {
        finDate = String(date);
      }
      return finYear + finMonth + finDate;
    },
    changeDateFormat(year, month, date) {
      var finYear = String(year);
      var finMonth;
      var finDate;
      if (month < 10) {
        finMonth = "0" + String(month);
      } else {
        finMonth = String(month);
      }
      if (date < 10) {
        finDate = "0" + String(date);
      } else {
        finDate = String(date);
      }
      return finYear + "-" + finMonth + "-" + finDate;
    },
  },
};
</script>

<style scoped>
.calendarImageBody {
  height: 6rem;
  display: flex;
  text-align: center;
  justify-content: center;
  align-content: center;
  /* padding: 1rem; */
}
.dateNum {
  position: absolute;
  margin-top: 8px;
  margin-left: 8px;
  margin-bottom: 5px;
}
.emoticonImg {
  width: 60%;
  /* height: 5rem; */
  margin: 0 auto;
}
.datePast {
  background-color: rgb(246, 240, 251);
}
.dateToday {
  background-color: rgb(205, 240, 255);
}
.dateFuture {
  background-color: rgb(219, 219, 219);
}
.container {
  padding: 0;
}
.shadow {
  filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, 0.2));
}
/* @media (max-width: 1100px) {
  .emoticonImg {
    width: 60%;
    margin-top: 5%;
  }
  .dateNum {
    position: absolute;
    margin-top: 8px;
    margin-left: 8px;
    margin-bottom: 5px;
  }
} */
@media (max-width: 767px) {
  .emoticonImg {
    /* margin-top: 5%; */
    width: 60%;
    height: auto;
    align-content: flex-end;
  }
  .dateNum {
    position: static;
    margin-top: 8px;
    margin-left: 0;
  }
}
@media (max-width: 1357px) {
  .emoticonImg {
    margin-top: 10%;
  }
}
@media (max-width: 1880px) {
  .calendarImageBody {
    height: 4.5rem;
  }
}
</style>
