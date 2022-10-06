<template>
  <div>
    <div class="sub sub1">
      <div class="img" data-aos="fade-right">
        <img alt="기능" :src="require(`@/assets/landing/landing1.png`)" />
      </div>
      <div class="text">
        <div class="title-text">감정 분석 일기장</div>
        <div class="content">매일 일기를 쓰고 감정 분석을 받아봐요</div>
      </div>
    </div>
    <div class="sub sub2">
      <div class="img" data-aos="fade-left">
        <img alt="기능" :src="require(`@/assets/landing/landing2.png`)" />
      </div>
      <div class="text">
        <div class="title-text">감정 기반 음악추천/랜덤 선물박스</div>
        <div class="content">분석된 감정으로 음악을 추천받고,</div>
        <div class="content">선물 랜덤박스를 개봉해봐요.</div>
      </div>
    </div>
    <div class="sub sub3">
      <div class="img" data-aos="fade-right">
        <img alt="기능" :src="require(`@/assets/landing/landing3.png`)" />
      </div>
      <div class="text">
        <div class="title-text">업적 시스템과 보상 뱃지</div>
        <div class="content">업적을 달성하고,</div>
        <div class="content">개성있고 재미있는 뱃지들을 모아봐요.</div>
      </div>
    </div>
    <div class="sub sub2">
      <div class="img" data-aos="fade-left">
        <img alt="기능" :src="require(`@/assets/landing/landing4.png`)" />
      </div>
      <div class="text">
        <div class="title-text">시간별 / 요일별 감정 통계</div>
        <div class="content">여러분의 감정들을 통계를 통해 확인해봐요.</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    pageLink(to) {
      if (to == "login") this.$router.push({ path: "login" });
      else this.$router.push({ path: "signup" });
    },
  },
};
</script>

<style scoped>
.sub {
  width: 100vw;
  height: 50vh;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

.sub1 {
  background: linear-gradient(180deg,
      #bdc8d5 0%,
      rgba(189, 200, 212, 0.413726) 21.13%,
      rgba(247, 233, 255, 0) 100%);
  flex-direction: row;
}

.sub2 {
  flex-direction: row-reverse;
}

.sub3 {
  background: #eef4fa;
  flex-direction: row;
}

.text {
  color: #173261;
  text-shadow: 5px 8px 4px rgba(0, 0, 0, 0.25);
}

.title-text {
  font-size: clamp(2rem, 3vw, 3rem);
  line-height: 72px;
}

.content {
  font-size: clamp(1.5rem, 1vw, 2.5rem);
  line-height: 40px;
  margin-left: 10px;
}

.img img {
  width: 100%;
  min-width: 200px;
  max-height: 350px
}

.img {
  text-align: center;
}

@media screen and (max-width: 835px) {
  .sub1 {
    flex-direction: column;
  }

  .sub2 {
    overflow: hidden;
    flex-direction: column;
  }

  .sub3 {
    flex-direction: column;
  }

  .img img {
    width: 80%;
  }

  .title-text {
    font-size: clamp(1.5rem, 2vw, 2.3rem);
    line-height: 72px;
  }

  .content {
    font-size: clamp(1rem, 1vw, 1.8rem);
    line-height: 25px;
    margin-left: 10px;
  }
}
</style>
