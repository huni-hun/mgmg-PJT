<template>
  <div class="outDiv">
    <div class="diaryTop" :style="{
      backgroundImage:
        'url(' + require(`@/assets/diary/writingtop/${thema}.png`) + ')',
    }">
      <div class="flexTop">
        <v-row>
          <v-col>
            <span>날짜 : {{ date }}</span>
          </v-col>
          <v-col>
            <span>날씨 :</span>
          </v-col>
          <v-col>
            <v-select :items="weatherImg" :value="weather" :menu-props="{ maxHeight: '80%', overflowX: true }">
              <template v-slot:selection="{ item }">
                <v-img class="selectWeather" :src="require(`@/assets/diary/weather/${item}.png`)" />
              </template>
              <template v-slot:item="{ item }">
                <v-img class="selectWeather" :src="require(`@/assets/diary/weather/${item}.png`)"
                  @click="weather = item" />
              </template>
            </v-select>
          </v-col>
          <v-col>
            <input v-if="uploadReady" ref="file" type="file" accept="image/gif,image/jpeg,image/jpg,image/png" hidden
              @change="readFile($event)" />
            <v-btn icon small>
              <v-icon color="blue lighten-3" @click="selectFile">
                mdi-image-outline
              </v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </div>
    </div>
    <div class="diarymiddle" v-show="uploadImageSrc" :style="{
      backgroundImage:
        'url(' + require(`@/assets/diary/uploadimg/${thema}.png`) + ')',
    }">
      <div class="selectImg">
        <img v-if="uploadImageSrc" :src="uploadImageSrc" />
        <v-icon large color="gray darken-2" @click="cancelImage">
          mdi-close
        </v-icon>
      </div>
    </div>
    <div class="diarymiddle" :style="{
      backgroundImage:
        'url(' + require(`@/assets/diary/middle/${thema}.png`) + ')',
    }">
      <div>
        <v-textarea v-model="diary" auto-grow outlined single-line :value="diary" label="일기를 써보자" />
      </div>
    </div>
    <div class="diarybottom" :style="{
      backgroundImage:
        'url(' + require(`@/assets/diary/bottom/${thema}.png`) + ')',
    }">
      <custom-button v-if="isEdit" class="customButton" btnText="수정완료" @click="writingCompletion" />
      <custom-button v-else class="customButton" btnText="작성완료" @click="writingCompletion" />
    </div>
  </div>
</template>

<script>
import eventBus from "./eventBus.js";
import { diaryWrite, diaryDetailView, diaryEdit } from "@/api/diary.js";
// import { mapActions } from "vuex";

export default {
  data: function () {
    return {
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
      uploadReady: true,

      date: "",
      weather: "sunny",
      uploadImageSrc: "",
      imageFile: "",
      diary: "",
      thema: "blackLine",

      isEdit: this.$route.query.no,
    };
  },
  methods: {
    // ...mapActions("diaryStore", ["fetchDiary"]),

    async writingCompletion() {
      const diaryData = {
        diaryContent: this.diary,
        diaryDate: this.date,
        weather: this.weather,
        diaryThema: this.thema,
        emotion: "기쁨",
        musicNo: 0,
        giftNo: 0,
      };
      // date: this.date,

      let form = new FormData();
      form.append("multipartFile", this.imageFile);
      // form.append(
      //   "diaryRequest",
      //   new Blob([JSON.stringify(diaryData)], { type: "application/json" })
      // );

      if (this.no === undefined) {
        // 일반 작성 create
        form.append(
          "diaryRequest",
          new Blob([JSON.stringify(diaryData)], { type: "application/json" })
        );
        await diaryWrite(form).then((res) => {
          console.log("vuex success", res);
          this.$router.push({
            name: "diarydetail",
            params: { no: res.diaryNo },
          });
        });
      } else {
        // 일기 수정 update
        form.append(
          "diaryUpdateRequest",
          new Blob([JSON.stringify(diaryData)], { type: "application/json" })
        );
        await diaryEdit(this.no, form).then((res) => {
          console.log("edit success", res);
          this.$router.push({
            name: "diarydetail",
            params: { no: this.no },
          });
        });
      }
    },
    selectFile() {
      this.uploadReady = true;
      let fileInputElement = this.$refs.file;
      fileInputElement.click();
    },
    readFile(e) {
      const file = e.target.files[0];
      this.uploadImageSrc = URL.createObjectURL(file);
      this.imageFile = file;
    },
    cancelImage() {
      this.uploadImageSrc = null;
      this.uploadReady = false;
      this.$nextTick(() => {
        this.uploadReady = true;
      });
    },
    async isEditView() {
      if (this.$route.query.no !== undefined) {
        const res = await diaryDetailView(this.no);
        this.weather = res.weather;
        this.uploadImageSrc = res.diaryImg;
        this.imageFile = res.diaryImg;
        this.diary = res.diaryContent;
        this.thema = res.diaryThema;
      }
    },
  },
  created() {
    eventBus.$on("backImgChoice", (props) => {
      this.thema = props;
    });
    this.date = this.$route.params.date;
    this.no = this.$route.query.no;
    this.isEditView();
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
  flex-basis: 10vh;
}

.flexTop {
  width: 81%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(5%, auto));
  align-content: center;
  align-items: center;
}

.flexTop>.row {
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
  background-size: 100% 100%;
  height: 100%;
  flex-basis: 45vh;
}

.diarymiddle>.selectImg {
  position: relative;
  height: 100%;
}

.selectImg>img {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  max-width: 70%;
  max-height: 80%;
}

.selectImg>.v-icon {
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

.v-text-field>>>fieldset {
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
