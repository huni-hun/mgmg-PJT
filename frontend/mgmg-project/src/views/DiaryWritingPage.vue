<template>
  <div class="page">
    <BackgroundChoice />
    <DiaryWriting />
  </div>
</template>

<script>
import BackgroundChoice from "@/components/diarywrite/BackgroundChoice.vue";
import DiaryWriting from "@/components/diarywrite/DiaryWriting.vue";
export default {
  components: { BackgroundChoice, DiaryWriting },
};
</script>


<style scoped src="@/styles/diary/DiaryWritingPage.css"/>