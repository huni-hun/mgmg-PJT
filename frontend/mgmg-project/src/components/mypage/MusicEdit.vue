<template>
  <div class="musicEditBody">
    <v-container v-if="interestMusicPage == 1">
      <div class="musicEditTitle">
        <v-row class="musicSurveyTitle">음악 장르 (1/2)</v-row>
        <v-row>감정별로 선호하는 음악 장르를 선택하세요.</v-row>
        <v-row><hr class="hrStyle" /></v-row>
      </div>
      <div class="genreCheckList">
        <div class="genreCheckItem" v-for="(emotion, index) in emotionLst1" :key="index">
          <div class="justify_content_center label">
            <img :src="require(`@/assets/emoticon/${emotionEnglishLst1[index]}.png`)" alt="" class="emoticonImg" />
            <div class="justify_content_center emotionName">
              <div>{{ emotion }}</div>
            </div>
          </div>
          <div class="genre">
            <v-checkbox hide-details class="genreCheckBox" v-for="(genre, idx) in genreLst" :key="idx" :label="genreLst[idx]" :value="genre" v-model="input1[emotion][idx]"></v-checkbox>
          </div>
        </div>
      </div>
      <div class="musicEditButtonLine1"><CustomButton btnText="다음" @click="musicEditNext1Page" /></div>
    </v-container>

    <v-container v-if="interestMusicPage == 2">
      <div class="musicEditTitle">
        <v-row class="musicSurveyTitle">음악 장르 (2/2)</v-row>
        <v-row>감정별로 선호하는 음악 장르를 선택하세요.</v-row>
        <v-row><hr class="hrStyle" /></v-row>
      </div>
      <div class="genreCheckList">
        <div class="genreCheckItem" v-for="(emotion, index) in emotionLst2" :key="index">
          <div class="justify_content_center label">
            <img :src="require(`@/assets/emoticon/${emotionEnglishLst2[index]}.png`)" alt="" class="emoticonImg" />
            <div class="justify_content_center emotionName">{{ emotion }}</div>
          </div>
          <div class="genre">
            <v-checkbox hide-details class="genreCheckBox" v-for="(genre, idx) in genreLst" :key="idx" :label="genreLst[idx]" :value="genre" v-model="input1[emotion][idx]"></v-checkbox>
          </div>
        </div>
      </div>
      <div class="musicEditButtonLine2">
        <CustomButton btnText="이전" @click="musicEditBefore1Page" />
        <CustomButton btnText="완료" @click="musicEditFinish" />
      </div>
    </v-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import CustomButton from "../common/CustomButton.vue";
import Swal from "sweetalert2";
import axios from "axios";
import { changeInterestMusic } from "@/api/userApi.js";

export default {
  data() {
    return {
      interestMusicPage: 1,

      emotionLst1: ["평온", "기쁨", "사랑", "짜증", "피곤"],
      emotionEnglishLst1: ["calm", "happy", "love", "annoyed", "fatigue"],
      emotionLst2: ["기대", "슬픔", "창피", "화", "공포"],
      emotionEnglishLst2: ["expect", "sad", "shame", "angry", "fear"],
      emotionLst: ["평온", "기쁨", "사랑", "짜증", "피곤", "기대", "슬픔", "창피", "화", "공포"],

      genreLst: ["R&B/Soul", "댄스", "랩/힙합", "록/메탈", "발라드", "인디음악", "트로트", "포크/블루스"],
      input1: {
        평온: ["", "", "", "", "", "", "", ""],
        기쁨: ["", "", "", "", "", "", "", ""],
        사랑: ["", "", "", "", "", "", "", ""],
        짜증: ["", "", "", "", "", "", "", ""],
        피곤: ["", "", "", "", "", "", "", ""],
        기대: ["", "", "", "", "", "", "", ""],
        슬픔: ["", "", "", "", "", "", "", ""],
        창피: ["", "", "", "", "", "", "", ""],
        화: ["", "", "", "", "", "", "", ""],
        공포: ["", "", "", "", "", "", "", ""],
      },
      testInput: true,
      musicTaste: {
        피곤: [],
        평온: [],
        짜증: [],
        기쁨: [],
        사랑: [],
        기대: [],
        슬픔: [],
        창피: [],
        화: [],
        공포: [],
      },
    };
  },
  created() {
    this.check();
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    check() {
      axios({
        url: process.env.VUE_APP_API_URL + "/api/user/mypage/music",
        method: "get",
        headers: { Authorization: `Bearer ${this.accessToken}` },
      })
        .then(({ data }) => {
          let list = {
            평온: ["", "", "", "", "", "", "", ""],
            기쁨: ["", "", "", "", "", "", "", ""],
            사랑: ["", "", "", "", "", "", "", ""],
            짜증: ["", "", "", "", "", "", "", ""],
            피곤: ["", "", "", "", "", "", "", ""],
            기대: ["", "", "", "", "", "", "", ""],
            슬픔: ["", "", "", "", "", "", "", ""],
            창피: ["", "", "", "", "", "", "", ""],
            화: ["", "", "", "", "", "", "", ""],
            공포: ["", "", "", "", "", "", "", ""],
          };
          for (let emotionIndex = 0; emotionIndex < this.emotionLst.length; emotionIndex++) {
            const emotion = this.emotionLst[emotionIndex];
            for (let index = 0; index < this.genreLst.length; index++) {
              if (data.musicTaste[emotion].includes(this.genreLst[index])) {
                list[emotion][index] = this.genreLst[index];
              }
            }
          }

          this.input1 = list;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    musicEditBefore1Page() {
      this.interestMusicPage--;
    },
    musicEditNext1Page() {
      var isAllChecked = true;
      var rep;
      var gen;
      var isNotEmpty = false;
      //하나라도 비어있는 항목 있으면 안됨.
      for (rep = 0; rep < 5; rep++) {
        for (gen = 0; gen < 8; gen++) {
          if (this.input1[this.emotionLst1[rep]][gen]) {
            isNotEmpty = true;
          }
        }
        console.log("isNotEmpty", isNotEmpty);
        if (isNotEmpty == false) {
          isAllChecked = false;
        }
        isNotEmpty = false;
      }
      if (!isAllChecked) {
        Swal.fire({
          text: "감정별로 음악 장르를 1개 이상 선택해야 합니다.",
          icon: "warning",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      } else {
        this.interestMusicPage++;
      }
    },
    musicEditFinish() {
      var isAllChecked = true;
      var rep;
      var gen;
      var isNotEmpty = false;
      //하나라도 비어있는 항목 있으면 안됨.
      for (rep = 0; rep < 5; rep++) {
        for (gen = 0; gen < 8; gen++) {
          if (this.input1[this.emotionLst2[rep]][gen]) {
            isNotEmpty = true;
          }
        }
        if (isNotEmpty == false) {
          isAllChecked = false;
        }
        isNotEmpty = false;
      }
      if (!isAllChecked) {
        Swal.fire({
          text: "감정별로 음악 장르를 1개 이상 선택해야 합니다.",
          icon: "warning",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      } else {
        var emoRep;
        for (emoRep = 0; emoRep < 10; emoRep++) {
          var genRep;
          for (genRep = 0; genRep < 8; genRep++) {
            if (this.input1[this.emotionLst[emoRep]][genRep] != "") {
              this.musicTaste[this.emotionLst[emoRep]].push(this.input1[this.emotionLst[emoRep]][genRep]);
            }
          }
        }
        this.mypageMusicEdit();
      }
    },

    // 음악 리스트 변경
    async mypageMusicEdit() {
      var request = {
        musicTaste: this.musicTaste,
      };

      await changeInterestMusic(this.accessToken, request)
        .then((res) => {
          console.log(res);
          Swal.fire({
            text: "음악 장르가 정상적으로 변경되었습니다.",
            icon: "success",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });

          this.$router.push("/mypage/myinfo");
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            text: "잠시후 다시 시도해주세요.",
            icon: "warning",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
        });
    },
  },
  components: { CustomButton },
};
</script>

<style scoped>
.musicEditBody {
  width: 100%;
  padding: 5% 0 5% 0;
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 0px 20px 20px rgba(0, 0, 0, 0.2);
}
.musicEditTitle {
  padding: 0 5% 2% 5%;
}
.musicEditButtonLine1 {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 3%;
}
.musicEditButtonLine2 {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-top: 3%;
  padding: 0 3%;
}
/* 새로운거 */
.musicSurveyTitle {
  font-size: clamp(1.2rem, 2.5vw, 1.8rem);
}
.hrStyle {
  width: 100%;
  margin-bottom: 2%;
}
.musicLstBox {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25);
}
.selected {
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25), inset 3px 3px 4px 3px rgba(0, 0, 0, 0.38);
}
.genreCheckList {
  display: flex;
  flex-direction: row;
}
.genreCheckItem {
  display: flex;
  flex-direction: column;
  width: 100%;
}
.emoticonImg {
  width: 50%;
  margin: 4% 0;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}

.genreCheckBox {
  margin: 0;
}
.emotionName {
  margin: 1% 0;
  width: 55%;
  background: #ffe4c4;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.justify_content_center {
  display: flex;
  justify-content: center;
  align-items: center;
}
.genre {
  flex: 1;
  margin: 5%;
  display: flex;
  flex-direction: column;
}
.label {
  flex: 1;
  display: flex;
  flex-direction: column;
}
::v-deep .genreCheckBox .v-label {
  font-size: clamp(0.9rem, 2.5vw, 1rem);
}
@media (max-width: 767px) {
  .genreCheckList {
    flex-direction: column;
  }
  .genreCheckItem {
    flex-direction: row;
  }
  .emotionName {
    margin: 1% 0 3% 0;
    width: 80%;
    background: #ffe4c4;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    font-size: clamp(1rem, 2.5vw, 2rem);
  }
  .emoticonImg {
    width: 80%;
  }
  .genre {
    border-left-style: dashed;
    border-left-width: 1px;
    padding-left: 2px;
  }
}

.col-xl,
.col-xl-auto,
.col-xl-12,
.col-xl-11,
.col-xl-10,
.col-xl-9,
.col-xl-8,
.col-xl-7,
.col-xl-6,
.col-xl-5,
.col-xl-4,
.col-xl-3,
.col-xl-2,
.col-xl-1,
.col-lg,
.col-lg-auto,
.col-lg-12,
.col-lg-11,
.col-lg-10,
.col-lg-9,
.col-lg-8,
.col-lg-7,
.col-lg-6,
.col-lg-5,
.col-lg-4,
.col-lg-3,
.col-lg-2,
.col-lg-1,
.col-md,
.col-md-auto,
.col-md-12,
.col-md-11,
.col-md-10,
.col-md-9,
.col-md-8,
.col-md-7,
.col-md-6,
.col-md-5,
.col-md-4,
.col-md-3,
.col-md-2,
.col-md-1,
.col-sm,
.col-sm-auto,
.col-sm-12,
.col-sm-11,
.col-sm-10,
.col-sm-9,
.col-sm-8,
.col-sm-7,
.col-sm-6,
.col-sm-5,
.col-sm-4,
.col-sm-3,
.col-sm-2,
.col-sm-1,
.col,
.col-auto,
.col-12,
.col-11,
.col-10,
.col-9,
.col-8,
.col-7,
.col-6,
.col-5,
.col-4,
.col-3,
.col-2,
.col-1 {
  padding: 0;
}
</style>
