<template>
  <div class="outDiv">
    <div
      class="diaryTop"
      :style="{
        backgroundImage:
          'url(' + require(`@/assets/diary/detailtop/${thema}.png`) + ')',
      }"
    >
      <div class="topOutDiv">
        <div class="box">
          <img
            alt="감정티콘"
            :src="
              require(`@/assets/emoticon/${
                this.emoImgs[this.emotions.indexOf(this.emotion, 0)]
              }.png`)
            "
          />
        </div>

        <div>
          <p>날짜 {{ date }}</p>
          <div>
            날씨
            <img
              style="width: 30px"
              alt="날씨티콘"
              :src="require(`@/assets/diary/weather/${weather}.png`)"
            />
          </div>

          <p>감정 {{ emotion }}</p>
        </div>
      </div>
    </div>
    <div
      class="diaryImg"
      v-show="imageFile"
      :style="{
        backgroundImage:
          'url(' + require(`@/assets/diary/uploadimg/${thema}.png`) + ')',
      }"
    >
      <div class="selectImg">
        <img v-if="imageFile" :src="imageFile" />
      </div>
    </div>
    <div
      class="diarymiddle"
      :style="{
        backgroundImage:
          'url(' + require(`@/assets/diary/middle/${thema}.png`) + ')',
      }"
    >
      <div>
        <v-textarea readonly auto-grow outlined single-line :value="content" />
      </div>
    </div>
    <div
      class="diarybottom"
      :style="{
        backgroundImage:
          'url(' + require(`@/assets/diary/bottom/${thema}.png`) + ')',
      }"
    >
      <div>
        <button type="button">
          <img class="btn_image" src="@/assets/diary/editIcon.png" />
        </button>
        <button type="button">
          <img
            class="btn_image"
            src="@/assets/diary/deleteIcon.png"
            @click="deleteClick"
          />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import { diaryDetailView, diaryDelete } from "@/api/diary.js";

export default {
  data: () => ({
    emotions: [
      "화",
      "피곤",
      "평온",
      "기대",
      "피곤",
      "공포",
      "기쁨",
      "사랑",
      "슬픔",
      "창피",
    ], // DB저장된 string 감정
    emoImgs: [
      "angry",
      "annoyed",
      "calm",
      "expect",
      "fatigue",
      "fear",
      "happy",
      "love",
      "sad",
      "shame",
    ], // 이미지 경로

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

    no: 0, // 일기 번호
    date: "",
    weather: "",
    imageFile: "",
    content: "",
    thema: "",
    emotion: "",
    gift: "",
    music: "",
  }),
  methods: {
    async deleteClick() {
      await diaryDelete(this.no)
        .then((res) => {
          console.log("diaryDelete success", res.data);
        })
        .catch((error) => console.log("diaryDelete error", error));
    },
  },
  async created() {
    this.no = this.$route.params.no;
    console.log("일기상세보기");
    const res = await diaryDetailView(this.no);
    this.date = res.diaryDate;
    this.weather = res.weather;
    this.imageFile = res.diaryImg;
    this.content = res.diaryContent;
    this.thema = res.diaryThema;
    this.emotion = res.emotion;
    // this.gift=res.giftNo;
    // this.music=res.musicNo;
  },
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
  background-size: 100% 100%;
  height: 100%;
  flex-basis: 2vh;
}
.topOutDiv {
  width: 100%;
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
  background-size: 100% 100%;
  height: 100%;
  flex-basis: 70vh;
}
.diaryImg {
  background-size: 100% 100%;
  height: 100%;
  /* max-height: 40vh; */
  flex-basis: 40vh;
}
.diaryImg > .selectImg {
  position: relative;
  height: 100%;
}
.selectImg > img {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  max-width: 70%;
  max-height: 80%;
}
.btn_image {
  width: 40%;
}
@import url("@/assets/font/font.css");
.v-text-field {
  width: 81%;
  height: 100%;
  margin: 0 auto;
  font-family: "KyoboHandwriting2019";
  font-size: xx-large;
}
.v-text-field >>> fieldset {
  border: none;
}
.diarybottom {
  background-size: 100% 100%;
  height: 100%;
  flex-basis: 10vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
