<template>
  <div class="outDiv">
    <div class="diaryDetailTop" :style="{
      backgroundImage:
        'url(' + require(`@/assets/diary/detailtop/${thema}.png`) + ')',
    }">
      <div class="topOutDiv">
        <div class="box">
          <img alt="감정티콘" :src="
            require(`@/assets/emoticon/${
              this.emoImgs[this.emotions.indexOf(this.emotion, 0)]
            }.png`)
          " />
        </div>

        <div class="textFlex" :style="{ fontFamily: `${font}` }">
          <span>날짜 : {{ date }}</span>

          <span class="weatherDetailDiv">날씨 :
            <img class="weatherImg" alt="날씨티콘" :src="require(`@/assets/diary/weather/${weather}.png`)" />
          </span>

          <span>감정 : {{ emotion }}</span>
        </div>
      </div>
      <hr />
    </div>
    <div class="diarymiddleImg" v-show="imageFile" :style="{
      backgroundImage:
        'url(' + require(`@/assets/diary/middle/${thema}.png`) + ')',
    }">
      <div class="selectImg">
        <img v-if="imageFile" :src="imageFile" />
      </div>
    </div>
    <div class="diaryDitailmiddle" :style="{
      backgroundImage:
        'url(' + require(`@/assets/diary/middle/${thema}.png`) + ')',
      fontFamily: `${font}`,
    }">
      <div class="textWriteDiv">
        <textarea class="textWrite" ref="textarea" readonly v-model="content"></textarea>
      </div>
    </div>
    <div class="diarybottom" :style="{
      backgroundImage:
        'url(' + require(`@/assets/diary/bottom/${thema}.png`) + ')',
    }">
      <hr />
      <div>
        <button type="button">
          <img class="btn_image" src="@/assets/diary/editIcon.png" @click="editClick" />
        </button>
        <button type="button">
          <img class="btn_image" src="@/assets/diary/deleteIcon.png" @click="deleteClick" />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { diaryDetailView, diaryDelete } from "@/api/diary.js";
import Swal from "sweetalert2";

export default {
  data: () => ({
    emotions: [
      "화",
      "짜증",
      "평온",
      "기대",
      "피곤",
      "공포",
      "기쁨",
      "사랑",
      "슬픔",
      "창피",
    ],

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

    no: 0,
    date: "",
    weather: "",
    imageFile: "",
    content: "",
    thema: "",
    emotion: "",

    font: "",
  }),
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    editClick() {
      Swal.fire({
        html: "<b>수정한 일기를 바탕으로<br> 감정분석이 다시 시작됩니다</b>",
        icon: "warning",
        iconColor: "#11C6FF",
        showCancelButton: true,
        confirmButtonColor: "#51516E",
        cancelButtonColor: "#d33",
        confirmButtonText: "수정",
        cancelButtonText: "취소",
      }).then(async (result) => {
        if (result.isConfirmed) {
          this.$router.push({
            name: "diarywriting",
            params: { date: this.date },
            query: { no: this.no },
          });
        }
      });
    },
    deleteClick() {
      Swal.fire({
        title: "정말로 삭제하나요?",
        text: "삭제한 일기는 되돌릴 수 없습니다.",
        icon: "warning",
        iconColor: "#11C6FF",
        showCancelButton: true,
        confirmButtonColor: "#51516E",
        cancelButtonColor: "#d33",
        confirmButtonText: "삭제",
        cancelButtonText: "취소",
      }).then(async (result) => {
        if (result.isConfirmed) {
          await diaryDelete(this.accessToken, this.no);
          Swal.fire({
            title: "삭제되었습니다!",
            text: "",
            icon: "success",
            confirmButtonColor: "#51516E",
            confirmButtonText: "확인",
          }).then(() => {
            this.$router.push({ name: "main" });
          });
        }
      });
    },
    autoResizeTextarea(obj) {
      obj.style.height = "auto";
      let height = obj.scrollHeight;
      obj.style.height = `${height}px`;
    },
  },
  async created() {
    this.no = this.$route.params.no;
    await diaryDetailView(this.accessToken, this.no).then((res) => {
      this.date = res.diaryDate;
      this.weather = res.weather;
      this.imageFile = res.diaryImg;
      this.content = res.diaryContent;
      this.thema = res.diaryThema;
      this.emotion = res.emotion;
    });
    this.font = "KyoboHandwriting2019";
    this.autoResizeTextarea(this.$refs.textarea);
  },
};
</script>

<style scoped src="@/styles/diary/DiaryStyle.css">

</style>
