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
    <div class="microButton">
      <button :class="[onRec? `onMike` : `offMike` ]" fab @click="onRec ? onRecAudio() : offRecAudio()">
        <v-icon v-if="onRec">mdi-microphone</v-icon>
        <v-icon v-else>mdi-microphone-off</v-icon>
      </button>
    </div>
    <div v-if="isLoading" class="loading-container">
      <LodingView />
    </div>
  </div>
</template>

<script>
import eventBus from "./eventBus.js";
import { diaryWrite, diaryDetailView, diaryEdit, sttWrite } from "@/api/diary.js";
import { notification_check } from "@/store/modules/etcStore";
import { HZRecorder } from "@/components/diarywrite/HZRecorder.js";
import LodingView from "./LodingView.vue";
// import { mapActions } from "vuex";

export default {
  components: { LodingView },
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
      // 감정 분석 결과 추가로 받을 것
      emotion: "기쁨",
      musicNo: 5,
      giftNo: 1,

      // 오디오 스트림
      stream: null,
      media: null,
      onRec: true,
      source: null,
      audioUrl: null,
      disabled: false,
      audioCtx: null,
      analyser: null,

      // 수정 여부
      isEdit: this.$route.query.no,
      isLoading: false,
    };
  },
  methods: {
    // ...mapActions("diaryStore", ["fetchDiary"]),
    onRecAudio() {
      console.log(process.env.VUE_APP_API_URL);
      console.log(process.env.EMOTION_APP_API_URL);
      console.log(process.env.YOUTUBE_API_KEY);
      this.onRec = !this.onRec;
      // 음원 정보를 담은 노드를 생성하거나 음원을 실행 또는 디코딩 시키는일을 한다.
      this.audioCtx = new (window.AudioContext || window.webkitAudioContext)();
      // 자바스크립트를 통해 음원의 진행상태에 직접 접근에 사용된다.
      this.analyser = this.audioCtx.createScriptProcessor(0, 1, 1);

      navigator.mediaDevices.getUserMedia({ audio: true }).then((stream) => {
        const recorder = new HZRecorder(stream);
        recorder.start();
        this.stream = stream;
        this.media = recorder;
        const source = this.audioCtx.createMediaStreamSource(stream);
        this.source = source;
        this.source.connect(this.analyser);
        this.analyser.connect(this.audioCtx.destination);
      });
    },
    offRecAudio() {
      var mp3Blob = this.media.upload();
      var form = new FormData();
      form.append("file", mp3Blob);
      // 음성 전송
      this.isLoading = true;
      sttWrite(form).then((res) => {
        this.isLoading = false;
        console.log("전송된 음성입니다", res);
        this.diary += res.text;
      }).catch((err) => {
        this.isLoading = false;
        console.log(err);
      });
      this.onRec = !this.onRec;
      // 모든 트랙에서 stop()을 호출해 오디오 스트림을 정지
      this.stream.getAudioTracks().forEach(function (track) {
        track.stop();
      });
      //미디어 캡처 중지
      this.media.stop();
      // 메서드가 호출 된 노드 연결 해제
      this.analyser.disconnect();
      this.source.disconnect();
    },
    async writingCompletion() {
      const diaryData = {
        diaryContent: this.diary,
        diaryDate: this.date,
        weather: this.weather,
        diaryThema: this.thema,
        emotion: this.emotion,
        musicNo: this.musicNo,
        giftNo: this.giftNo,
      };
      let form = new FormData();
      form.append("multipartFile", this.imageFile);
      if (this.no === undefined) {
        // 일반 작성 create
        form.append(
          "diaryRequest",
          new Blob([JSON.stringify(diaryData)], { type: "application/json" })
        );
        await diaryWrite(form).then((res) => {
          console.log("writing success", res);
          this.$router.push({
            name: "diarydetail",
            params: { no: res.diaryNo },
          });
        });
        this.isCheck = await notification_check();
        this.$store.commit("IS_INF", this.isCheck.notificationFlag);
      }
      else {
        // 일기 수정 update
        console.log("수정된 이미지 src : ", this.imageFile);
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
      this.imageFile = null;
      this.uploadReady = false;
      this.$nextTick(() => {
        this.uploadReady = true;
      });
    },
    async isEditView() {
      if (this.$route.query.no !== undefined) {
        const res = await diaryDetailView(this.no);
        console.log("수정눌러서 들어왔을 떄 데이터", res);
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

.microButton {
  position: fixed;
  bottom: 60px;
  right: 17vw;
  width: 50px;
  height: 50px;
}

.microButton>button {
  width: 100%;
  height: 100%;
  border-radius: 70%;
  object-fit: cover;
}

.onMike {
  background-color: #888898
}

.v-icon {
  color: beige;
}

.offMike {
  background-color: rgb(236, 72, 72);
}

.loading {
  z-index: 2;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  box-shadow: rgba(0, 0, 0, 0.1) 0 0 0 9999px;
}

.loading>* {
  z-index: 2;
  box-shadow: rgba(0, 0, 0, 0.1) 0 0 0 9999px;
}
</style>
