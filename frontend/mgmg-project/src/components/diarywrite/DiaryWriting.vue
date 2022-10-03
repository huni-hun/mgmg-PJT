<template>
  <div class="outDiv">
    <div v-if="isLoading">
      <LodingView :view="lodingValue" />
    </div>
    <div class="diaryWriteTop" :style="{
      backgroundImage: 'url(' + require(`@/assets/diary/writingtop/${thema}.png`) + ')',
    }">
      <div class="flexTop" :style="{fontFamily:`${font}`}">
        <div class="flexLeft">
          <div>
            <span>날짜 : {{ date }}</span>
          </div>
          <div class="weatherDiv">
            <span>날씨 :</span>
            <v-select class="selectWeather" :items="weatherImg" :value="weather" flat solo dense
              background-color="transparent" :menu-props="{ maxHeight: '80%', overflowX: true }" hide-details>
              <template v-slot:selection="{ item }">
                <v-img class="weatherImg" :src="require(`@/assets/diary/weather/${item}.png`)" />
              </template>
              <template v-slot:item="{ item }">
                <v-img class="weatherImg" :src="require(`@/assets/diary/weather/${item}.png`)"
                  @click="weather = item" />
              </template>
            </v-select>
          </div>
        </div>
        <div>
          <input v-if="uploadReady" ref="file" type="file" accept="image/gif,image/jpeg,image/jpg,image/png" hidden
            @change="readFile($event)" />
          <v-btn icon small>
            <v-icon color="blue lighten-3" @click="selectFile()" large> mdi-image-outline </v-icon>
          </v-btn>
        </div>
      </div>
    </div>
    <div class="diarymiddleImg" v-show="uploadImageSrc" :style="{
      backgroundImage: 'url(' + require(`@/assets/diary/middle/${thema}.png`) + ')',
    }">
      <div class="selectImg">
        <img v-if="uploadImageSrc" :src="uploadImageSrc" />
        <v-icon large color="grey darken-2" @click="cancelImage"> mdi-close </v-icon>
      </div>
    </div>
    <div class="diarymiddle" :style="{
      backgroundImage: 'url(' + require(`@/assets/diary/middle/${thema}.png`) + ')',
      fontFamily:`${font}`,
    }">
      <div class="textWriteDiv">
        <textarea class="textWrite" ref="textarea" minlength="50" maxlength="500" v-model="diary" placeholder="일기를 써보자"
          spellcheck="false" @input="autoResizeTextarea($event.target)"></textarea>
      </div>
      <div class="textLength">
        <sup>(<span id="nowByte">0</span>/500text)</sup>
      </div>
    </div>
    <div class="diarybottom" :style="{
      backgroundImage: 'url(' + require(`@/assets/diary/bottom/${thema}.png`) + ')',
    }">
      <custom-button v-if="isEdit" class="customButton" btnText="수정완료" @click="writingCompletion" />
      <custom-button v-else class="customButton" btnText="작성완료" @click="writingCompletion" />
    </div>
    <div class="microButton">
      <button :class="[onRec? `onMike` : `offMike` ]" fab @click="onRec ? onRecAudio() : offRecAudio()">
        <v-icon v-if="onRec">mdi-microphone</v-icon>
        <v-icon large v-else>mdi-stop</v-icon>
      </button>
    </div>
    <v-snackbar v-model="snackbar" :timeout="timeout" absolute centered rounded="xl" color="blue darken-1"
      elevation="24">
      {{ text }}
      <!-- <template v-slot:action="{ attrs }">
        <v-btn color="blue" text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template> -->
    </v-snackbar>
  </div>
</template>

<script>
import eventBus from "./eventBus.js";
import { diaryDetailView, diaryEdit, diaryWrite, sttWrite } from "@/api/diary.js";
import { notification_check } from "@/store/modules/etcStore";
import { HZRecorder } from "@/components/diarywrite/HZRecorder.js";
import LodingView from "./LodingView.vue";
import axios from 'axios';
import store from "@/store/modules/userStore";

export default {
  components: { LodingView },
  data: function () {
    return {
      weatherImg: ["sunny", "overcast", "cloudy", "windy", "rain", "snow", "lightning", "mild"],
      uploadReady: true,
      userId: "",
      date: "",
      weather: "sunny",
      uploadImageSrc: "",
      imageFile: "",
      diary: "",
      thema: "blueCheck",
      font: "",

      // 감정 분석 결과 이후
      emotion: "기쁨",
      musicNo: 1,
      giftNo: 1,

      // 스낵바
      snackbar: false,
      text: '50자 이상 일기를 작성하세요.',
      timeout: 2500,

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

      // 로딩 여부
      isLoading: false,
      lodingValue: "",
    };
  },
  methods: {
    // ...mapActions("diaryStore", ["fetchDiary"]),
    onRecAudio() {
      this.lodingValue = "recording";
      this.isLoading = true;

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
      this.lodingValue = "convert";
      this.isLoading = true;

      var mp3Blob = this.media.upload();
      var form = new FormData();
      form.append("file", mp3Blob);

      // 음성 전송
      sttWrite(form).then((res) => {
        this.isLoading = false;
        console.log("전송된 음성입니다", res);
        this.diary += res.text;
        if (500 < this.diary.length)
          this.diary = this.diary.substring(0, 500);
        this.autoResizeTextarea(this.$refs.textarea);
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
        await diaryDetailView(this.no).then((res) => {
          console.log("수정눌러서 들어왔을 때 데이터", res);
          this.weather = res.weather;
          this.uploadImageSrc = res.diaryImg;
          this.imageFile = res.diaryImg;
          this.diary = res.diaryContent;
          this.thema = res.diaryThema;
        });
      }
      this.autoResizeTextarea(this.$refs.textarea);
    },
    async writingCompletion() {
      if (this.diary.length < 50) {
        console.log("글자수 : ", this.diary.length)
        this.snackbar = true;
        return 0;
      }
      // 감정 분석
      console.log("id", store.state.userId);
      const userDiary = {
        user_id: store.state.userId,
        diary_content: this.diary,
      }

      this.lodingValue = "predict";
      this.isLoading = true;

      await axios.post(`${process.env.VUE_APP_EMOTION_API_URL}/predict/diary`, userDiary, {
      }).then(async (res) => {
        // console.log("감정분석 결과", res);
        let statusCode = res.data.statusCode;

        if (statusCode == 401) {
          console.log("전송실패", res);
          alert(res.data.message)
        }
        else {
          this.emotion = res.data.emotion;
          this.musicNo = res.data.music_no;
          this.dataSend();
        }
        this.isLoading = false;
      })
    },
    async dataSend() {
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
        form.append("diaryRequest", new Blob([JSON.stringify(diaryData)], { type: "application/json" }));

        await diaryWrite(form).then((res) => {
          this.isLoading = false;
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
        form.append("diaryUpdateRequest", new Blob([JSON.stringify(diaryData)], { type: "application/json" }));
        await diaryEdit(this.no, form).then((res) => {
          console.log("edit success", res);
          this.$router.push({
            name: "diarydetail",
            params: { no: this.no },
          });
        });
      }
    },
    autoResizeTextarea(obj) {
      const text_len = this.diary.length; //입력한 문자수

      obj.style.height = 'auto';
      let height = obj.scrollHeight; // 높이
      obj.style.height = `${height}px`;

      if (text_len < 50) {
        document.getElementById("nowByte").innerText = text_len;
        document.getElementById("nowByte").style.color = "red";
      } else {
        document.getElementById("nowByte").innerText = text_len;
        document.getElementById("nowByte").style.color = "blue";
      }
    }
  },
  created() {
    this.userId = store.state.userId;
    eventBus.$on("backImgChoice", (props) => {
      this.thema = props;
    });
    this.date = this.$route.params.date;
    this.no = this.$route.query.no;
    this.font = "KyoboHandwriting2019"
    this.isEditView();
  },
};
</script>

<style scoped src="@/styles/diary/DiaryStyle.css">

</style>
