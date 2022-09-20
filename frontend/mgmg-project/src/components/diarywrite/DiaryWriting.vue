<template>
  <div class="outDiv">
    <div
      class="diaryTop"
      :style="{
        backgroundImage:
          'url(' + require(`@/assets/diary/writingtop/${backImg}.png`) + ')',
      }"
    >
      <div class="flexTop">
        <v-row>
          <v-col cols="3">
            <span>날짜 : {{ date }}</span>
          </v-col>
          <v-col cols="1">
            <span>날씨 :</span>
          </v-col>
          <v-col cols="2">
            <v-select
              :items="weatherImg"
              :value="weather"
              :menu-props="{ maxHeight: '80%', overflowX: true }"
            >
              <template v-slot:selection="{ item }">
                <v-img
                  class="selectWeather"
                  :src="require(`@/assets/diary/weather/${item}.png`)"
              /></template>
              <template v-slot:item="{ item }">
                <v-img
                  class="selectWeather"
                  :src="require(`@/assets/diary/weather/${item}.png`)"
                  @click="weather = item"
              /></template>
            </v-select>
          </v-col>
          <v-col>
            <input
              v-if="uploadReady"
              ref="file"
              type="file"
              accept="image/gif,image/jpeg,image/jpg,image/png"
              hidden
              @change="readFile($event)"
            />
            <v-btn icon small>
              <v-icon color="blue lighten-3" @click="selectFile">
                mdi-image-outline
              </v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </div>
    </div>
    <div
      class="diarymiddle"
      v-show="uploadImageFile"
      :style="{
        backgroundImage:
          'url(' + require(`@/assets/diary/uploadimg/${backImg}.png`) + ')',
      }"
    >
      <div class="selectImg">
        <img v-if="uploadImageFile" :src="uploadImageFile" />
        <v-icon large color="gray darken-2" @click="cancelImage">
          mdi-close
        </v-icon>
      </div>
    </div>
    <div
      class="diarymiddle"
      :style="{
        backgroundImage:
          'url(' + require(`@/assets/diary/middle/${backImg}.png`) + ')',
      }"
    >
      <div>
        <v-textarea
          v-model="diary"
          auto-grow
          outlined
          single-line
          :value="diary"
          label="일기를 써보자"
        />
      </div>
    </div>
    <div
      class="diarybottom"
      :style="{
        backgroundImage:
          'url(' + require(`@/assets/diary/bottom/${backImg}.png`) + ')',
      }"
    >
      <custom-button
        class="customButton"
        btnText="작성완료"
        @click="writingCompletion"
      />
    </div>
  </div>
</template>

<script>
import eventBus from "./eventBus.js";
import { diaryWrite } from "@/api/diary.js";

export default {
  data: () => ({
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
    uploadReady: false,

    date: "2022-09-17",
    weather: "sunny",
    uploadImageFile: null,
    diary: "",
    backImg: "blackLine",
  }),
  methods: {
    async writingCompletion() {
      const form = new FormData();
      form.append("multipartFile", this.uploadImageFile);
      const userData = {
        diaryContent: this.diary,
        diaryDate: this.date,
        weather: this.weather,
        diaryThema: this.backImg,
        emotion: "슬픔",
        musicNo: 0,
        giftNo: 0,
      };
      form.append("diaryRequest", userData);

      const response = await diaryWrite(form);
      console.log("응답 데이터", response);
      //this.$router.push({ path: "diarydetail" });
    },
    selectFile() {
      this.uploadReady = true;
      let fileInputElement = this.$refs.file;
      fileInputElement.click();
      console.log("이미지불러오기", this.uploadImageFile);
    },
    readFile(e) {
      const file = e.target.files[0];
      this.uploadImageFile = URL.createObjectURL(file);
      console.log("이미지 선택하기", this.uploadImageFile);
    },
    cancelImage() {
      this.uploadImageFile = null;
      this.uploadReady = false;
      this.$nextTick(() => {
        this.uploadReady = true;
      });
      console.log("취소", this.uploadImageFile);
    },
  },
  created() {
    eventBus.$on("backImgChoice", (props) => {
      // console.log("전달 받은 배경 이름", data);
      this.backImg = props;
    });
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
  /* background: url("@/assets/diary/writingtop/blackLine.png") no-repeat center; */
  background-size: cover;
  object-fit: none;
  flex-basis: 10vh;
  /* min-height: 10vh; */
}
.flexTop {
  width: 81%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  align-content: center;
  align-items: center;
}
.flexTop > .row {
  margin: 0px;
  align-items: baseline;
}
.v-select {
  padding: 0px;
}
.selectWeather {
  width: 2%;
  max-width: 50px;
}

.diarymiddle {
  background: url("@/assets/diary/middle/blackLine.png") repeat-y center;
  background-size: 100% 100%;
  height: 100%;
  flex-basis: 45vh;
}
.diarymiddle > .selectImg {
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
.selectImg > .v-icon {
  position: absolute;
  top: 8%;
  right: 11%;
  transform: translate(-50%, -50%);
  max-width: 70%;
  max-height: 80%;
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
  background: url("@/assets/diary/bottom/blackLine.png") no-repeat center;
  background-size: cover;
  object-fit: none;
  flex-basis: 10vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
