<template>
  <div class="outDiv">
    <div class="diaryTop">
      <div class="topOutDiv">
        <div class="box">
          <img
            alt="감정티콘"
            :src="require(`@/assets/emoticon/${findEmotionImg}.png`)"
          />
        </div>

        <div>
          <p>날짜 {{ date }}</p>
          <div>
            날씨
            <img
              style="width:30px"
              alt="날씨티콘"
              :src="require(`@/assets/diary/weather/${findWeatherImg}.png`)"
            />
          </div>

          <p>감정 {{emotion}}</p>
        </div>
      </div>
    </div>
    <div class="diarymiddle">
      <div>
        <v-textarea readonly auto-grow outlined single-line :value="diary" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    date: "2022년 09월 15일", // 일자 받는 변수

    emotions: ["기쁨", "슬픔"], // DB저장된 string 감정
    emoImgs: ["happy", "sad"], // 이미지 경로
    emotion: "기쁨", // 감정 받을 변수
    findEmotionImg: "sad", // 감정 찾은 변수

    weathers: [
      "맑음",
      "구름많음",
      "흐림",
      "바람",
      "비",
      "눈",
      "번개",
      "무지개",
    ],
    weatherImg: [
      "sunny",
      "overcast",
      "cloudy",
      "windy",
      "rain",
      "snow",
      "lightning",
      "mild",
    ],
    weather: "맑음", // 날씨 받을 변수
    findWeatherImg: "overcast", // 날씨 찾은 변수

    diary: "일기 상세 내용이 들어갈 예정"
  }),
  methods: {
    findEmotion() {
      const index = this.emotions.indexOf(this.emotion, 0);
      this.findEmotionImg = this.emoImgs[index];
      //console.log(this.findEmotionImg);
    },
    findWeather() {
      const index = this.weathers.indexOf(this.weather, 0);
      this.findWeatherImg = this.weatherImg[index];
      //console.log(this.findWeatherImg);
    },
  },
  // created() {
  //   this.findEmotion();
  //   this.findWeather();
  // },
};
</script>

<style scoped>
.outDiv {
  background-color: rgba(255, 255, 255, 0.7);
  border: 1px solid black;
  padding: 2px 2px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.diaryTop {
  background: url("@/assets/diary/detailtop/blackLineTop.png") no-repeat center;
  background-size: cover;
  object-fit: none;
  flex-basis: 10vh;
  /* min-height: 10vh; */
}
.topOutDiv {
  width: 75vw;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr;
  place-items: center;
  align-items: center;
}
.box {
  height: 100%;
  margin: 10px 20px;
}
.box img {
  width: 100%;
  max-width: 200px;
}
.diarymiddle {
  background: url("@/assets/diary/middle/blacklineMiddle.png") repeat-y center;
  background-size: 100% 100%;
  height: 100%;
  flex-basis: 45vh;
}
.v-text-field {
  width: 80%;
  height: 100%;
  margin: 0 auto;
}
.v-text-field >>> fieldset {
  border: none;
}

</style>
