<template>
  <div class="back">
    <div class="inner">
      <DiaryDetail />
      <MusicRecommend />
      <GiftRecommend />
      <div class="detail-bottom">
        <custom-button class="button" btnText="달력으로 가기" @click="getMain()" />
        <custom-button class="button" btnText="관심 목록 조회" @click="interestlist()" />
      </div>
    </div>
  </div>
</template>

<script>
import DiaryDetail from '@/components/diarydetail/DiaryDetail.vue';
import GiftRecommend from '@/components/diarydetail/GiftRecommend.vue';
import MusicRecommend from '@/components/diarydetail/MusicRecommend.vue';
import CustomButton from '@/components/common/CustomButton.vue';
export default {
  components: { DiaryDetail, GiftRecommend, MusicRecommend, CustomButton },
  methods: {
    getMain() {
      this.$router.push("/main");
    },
    interestlist() {
      this.$router.push("/interestlist");
    }
  }
};
</script>

<style scoped>
.detail-bottom {
  width: 100%;
  margin-top: 40px;
  margin-bottom: 15px;
  display: flex;
  justify-content: center;
  flex-direction: row;
}

.button {
  border-radius: 30px;
  margin: 0px 20px
}
</style>
