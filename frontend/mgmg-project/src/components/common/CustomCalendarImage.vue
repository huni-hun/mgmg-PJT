<template>
  <v-container @click="test" class="calendarDate">
    <div>{{ dateNum }}</div>
    <!-- <div @click="test()">{{ emotionImgLst[emotionImg] }}</div> -->
    <img v-if="!!emotionImgLst[emotionImg] && !this.isToday()" class="emoticonImg" :src="require(`@/assets/emoticon/${emotionImgLst[emotionImg]}.png`)" alt="" />
  </v-container>
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
        기대: "expeck",
        평온: "calm",
        창피: "shame",
        짜증: "annoyed",
        기쁨: "happy",
        사랑: "love",
        없음: "",
      },
      nowYear: this.showYear,
      nowMonth: this.showMonth,
      nowDate: this.dateNum,
      diaryNum: this.diaryNumber,
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
    //아니면
    test() {
      console.log(this.showYear, this.showMonth);
      //   console.log(this.emotionImg);
      // console.log(this.emtionImgLst["기쁨"]);
    },
  },
};
</script>

<style>
.emoticonImg {
  width: 3rem;
}
.calendarDate {
  background-color: antiquewhite;
}
</style>
