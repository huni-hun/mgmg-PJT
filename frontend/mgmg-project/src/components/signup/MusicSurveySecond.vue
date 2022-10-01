<template>
  <div>
    <v-container>
      <v-row class="musicSurveyTitle">음악 장르 (2/2)</v-row>
      <v-row>감정별로 선호하는 음악 장르를 선택하세요.</v-row>
      <v-row><hr class="hrStyle" /></v-row>
      <v-row class="pcView">
        <v-col class="genreCheckLst" v-for="(emotion, index) in emotionLst1" :key="index">
          <v-container>
            <v-row class="justify_content_center">
              <img class="emoticonImg" :src="require(`@/assets/emoticon/${emotionEnglishLst1[index]}.png`)" alt="" />
            </v-row>
            <v-row class="justify_content_center">
              <div class="justify_content_center emotionName">{{ emotion }}</div>
            </v-row>
            <v-row>
              <v-col class="genresMargin">
                <v-checkbox
                  hide-details
                  class="genreCheckBox"
                  v-for="(genre, index) in genreLst1"
                  :key="index"
                  :label="genreLst1[index]"
                  :value="genreLst1[index]"
                  @click="addGenres(emotion, genreLst1[index])"
                ></v-checkbox>
              </v-col>
            </v-row>
          </v-container>
        </v-col>
      </v-row>
      <v-row class="phoneView" v-for="(emotion, index) in emotionLst1" :key="index">
        <v-col class="genreCheckLst">
          <v-container>
            <v-row class="justify_content_center">
              <img class="emoticonImg" :src="require(`@/assets/emoticon/${emotionEnglishLst1[index]}.png`)" alt="" />
            </v-row>
            <v-row class="justify_content_center">
              <div class="justify_content_center emotionName">{{ emotion }}</div>
            </v-row>
            <v-row class="paddingLeft">
              <div>
                <v-col class="genresMargin">
                  <v-checkbox
                    hide-details
                    class="genreCheckBox"
                    v-for="(genre, index) in genreLst1"
                    :key="index"
                    :label="genreLst1[index]"
                    :value="genreLst1[index]"
                    @click="addGenres(emotion, genreLst1[index])"
                  ></v-checkbox>
                </v-col>
              </div>
            </v-row>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      emotionLst1: ["기대", "슬픔", "창피", "화", "공포"],
      emotionEnglishLst1: ["expect", "sad", "shame", "angry", "fear"],
      genreLst1: ["R&B/Soul", "댄스", "랩/힙합", "록/메탈", "발라드", "인디음악", "트로트", "포크/블루스"],
      musicTaste2: {
        기대: [],
        슬픔: [],
        창피: [],
        화: [],
        공포: [],
      },
    };
  },
  methods: {
    addGenres(emotion, genre) {
      // 선택 리스트의 감정에 해당 장르가 없으면 추가
      if (!this.musicTaste2[emotion].includes(genre)) {
        this.musicTaste2[emotion].push(genre);
      } else {
        //있으면 삭제
        this.musicTaste2[emotion].pop(genre);
      }
      //   console.log(this.musicTaste2);
      this.$emit("updateMusicSecond", this.musicTaste2);
    },
  },
};
</script>

<style scoped>
.musicSurveyTitle {
  font-size: clamp(1.2rem, 2.5vw, 2rem);
}
.hrStyle {
  width: 100%;
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
.genreCheckLst {
  width: 100%;
}
.emoticonImg {
  width: 50%;
  margin: 4% 0;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}
.genresMargin {
  margin: 5%;
}
.genreCheckBox {
  margin: 0;
}
.emotionName {
  display: block;
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
.phoneView {
  display: none;
}
@media (max-width: 767px) {
  .paddingLeft {
    padding-left: 40%;
  }
  .pcView {
    display: none;
  }
  .phoneView {
    display: inline-block;
  }
  .emotionName {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 1% 0 3% 0;
    width: 20%;
    background: #ffe4c4;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    font-size: clamp(1rem, 2.5vw, 2rem);
  }
  .emoticonImg {
    width: 20%;
  }
}
@media (max-width: 575px) {
  .paddingLeft {
    padding-left: 30%;
  }
  .genreCheckLst {
    width: 100%;
  }
  .emotionName {
    width: 30%;
  }
  .emoticonImg {
    width: 30%;
  }
}

/* .container {
    padding: 0;
  } */
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
