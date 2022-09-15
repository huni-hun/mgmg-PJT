<template>
  <div class="back">
    <div class="inner">
      <BackgroundChoice />
      <DiaryWriting />
    </div>
  </div>
</template>

<script>
import BackgroundChoice from "@/components/diarywrite/BackgroundChoice.vue";
import DiaryWriting from "@/components/diarywrite/DiaryWriting.vue";
export default {
  components: { BackgroundChoice, DiaryWriting },
};
</script>

<style>
.back {
  /* background: url("E:/SSAFY/특화프로젝트/배경이미지.png") no-repeat center; */
  background-size: cover;
  height: 93.2vh;
}
.inner {
  width: 80%;
  display: inline-block;
  position: absolute;
  left: 50%;
  margin-left: -40vw;
}
</style>