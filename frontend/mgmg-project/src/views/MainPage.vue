<template>
  <v-container>
    <div class="mainpageBody">
      <div>
        <label class="mainpageTitle">" 날짜를 클릭해서 일기를 써보세요! "</label>
      </div>
      <Calendar class="mainpageCalendar" />
    </div>
    <v-btn class="iconDisplay" fab dark large color="indigo darken-1" v-if="todayDiary" @click="diaryWrite">
      <v-icon class="pencilIcon"> mdi-pencil</v-icon>
    </v-btn>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import { monthlyDiaryList } from "@/api/diary.js";
import Calendar from "@/components/common/CustomCalendar.vue";
export default {
  components: {
    Calendar,
  },
  data() {
    return {
      todayDiary: true,
    };
  },
  mounted() {
    this.todayDiaryCheck();
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    diaryWrite() {
      var now = new Date();
      var todayYear = now.getFullYear();
      var todayMonth = now.getMonth() + 1;
      var todayDate = now.getDate();
      //일기작성
      this.$router.push({
        name: "diarywriting",
        params: {
          date: this.changeDateFormat(todayYear, todayMonth, todayDate),
        },
      });
    },
    async monthlyDiaryList(monthInput) {
      var now = new Date();
      var todayYear = now.getFullYear();
      var todayMonth = now.getMonth() + 1;
      var todayDate = now.getDate();
      var dateNum = this.changeDateFormat(todayYear, todayMonth, todayDate);

      let response = await monthlyDiaryList(this.accessToken, monthInput);
      if (response.statusCode == 200) {
        this.diaryLst = response.diaries;
        console.log(response);
        console.log(this.diaryLst);

        var rep;
        for (rep = 0; rep < this.diaryLst.length; rep++) {
          console.log(this.diaryLst[rep].diaryDate, dateNum);
          if (this.diaryLst[rep].diaryDate == dateNum) {
            this.todayDiary = false;
          }
          console.log(this.todayDiary);
        }
      }
    },
    todayDiaryCheck() {
      var now = new Date();
      var todayYear = now.getFullYear();
      var todayMonth = now.getMonth() + 1;
      var monthInput = this.changeDateFormatYM(todayYear, todayMonth);
      console.log(monthInput);
      this.monthlyDiaryList(monthInput);
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
    changeDateFormatYM(year, month) {
      var finYear = String(year);
      var finMonth;
      if (month < 10) {
        finMonth = "0" + String(month);
      } else {
        finMonth = String(month);
      }
      return finYear + "-" + finMonth;
    },
  },
};
</script>

<style scoped>
.mainpageBody {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: auto;
  font-display: center;
  width: 80%;
}

.mainpageCalendar {
  width: 100%;
  /* padding: 2rem; */
}

.mainpageTitle {
  display: block;
  width: 100%;
  font-size: clamp(1.5rem, 1.6vw, 2.2rem);
  margin-bottom: 40px;
}

.iconDisplay {
  position: fixed;
  bottom: 6%;
  right: 10%;
  z-index: 2
}

.pencilIcon {
  font-size: 5vw;
}

@media (max-width: 991px) {
  .mainpageBody {
    width: 100%;
  }

  .pencilIcon {
    font-size: 6vw;
  }
}

@media (max-width: 767px) {
  .pencilIcon {
    font-size: 9vw;
  }
}

@media (max-width: 575px) {
  .pencilIcon {
    font-size: 15vw;
  }
}
</style>
