<template>
  <div>
    <v-container>
      <v-row class="musicSurveyTitle">음악 장르 (1/2)</v-row>
      <v-row>감정별로 선호하는 음악 장르를 선택하세요.</v-row>
      <v-row><hr class="hrStyle" /></v-row>
      <div class="genreCheckList">
        <div class="genreCheckItem" v-for="(emotion, index) in emotionLst1" :key="index">
          <div class="justify_content_center label">
            <img class="emoticonImg" :src="require(`@/assets/emoticon/${emotionEnglishLst1[index]}.png`)" alt="" />
            <div class="justify_content_center emotionName">
              {{ emotion }}
            </div>
          </div>
          <div class="genre">
            <v-checkbox hide-details class="genreCheckBox" v-for="(genre, index) in genreLst1" :key="index" :label="genreLst1[index]" :value="genre" v-model="musicTaste1[emotion][index]"></v-checkbox>
          </div>
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      emotionLst1: ["평온", "기쁨", "사랑", "짜증", "피곤"],
      emotionEnglishLst1: ["calm", "happy", "love", "annoyed", "fatigue"],
      genreLst1: ["R&B/Soul", "댄스", "랩/힙합", "록/메탈", "발라드", "인디음악", "트로트", "포크/블루스"],
      musicTaste1: {},
    };
  },
  props: ["selectMusic"],
  created() {
    this.musicTaste1 = this.selectMusic;
  },
};
</script>

<style scoped>
.musicSurveyTitle {
  font-size: clamp(1.2rem, 2.5vw, 2rem);
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
/* ::v-deep .genreCheckBox .v-label {
  font-size: clamp(0.9rem, 2.5vw, 1rem);
} */
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
