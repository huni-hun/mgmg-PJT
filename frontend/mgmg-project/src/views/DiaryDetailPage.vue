<template>
  <div class="back">
    <div class="inner">
    <DiaryDetail/>
    <MusicRecommend/>
    <GiftRecommend/>
  </div>
  </div>
</template>

<script>
import DiaryDetail from '@/components/diarydetail/DiaryDetail.vue';
import GiftRecommend from '@/components/diarydetail/GiftRecommend.vue';
import MusicRecommend from '@/components/diarydetail/MusicRecommend.vue';
export default { components: { DiaryDetail, GiftRecommend, MusicRecommend } };
</script>

<style></style>
