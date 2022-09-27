<template>
  <v-container>
    <div>{{ dateNum }}</div>
    <div @click="test()">{{ emtionImgLst[emotionImg] }}</div>
  </v-container>
</template>

<script>
export default {
  name: "CalendarEmotionImage",
  props: { emotionImg: { type: String }, dateNum: { type: Number } },
  data() {
    return {
      //   emotionToImage: "기쁨이미지",
      emtionImgLst: { 기쁨: "happy", 슬픔: "sad", 없음: "" },
    };
  },
  methods: {
    test() {
      //   console.log(this.emotionImg);
      console.log(this.emtionImgLst["기쁨"]);
    },
  },
};
</script>

<style></style>
