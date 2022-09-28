<template>
  <div>
    <v-container>
      <v-row>음악 장르 (2/2)</v-row>
      <v-row>감정별로 선호하는 음악 장르를 선택하세요.</v-row>
      <v-row><hr class="hrStyle" /></v-row>
      <v-row>
        <v-col class="genreCheckLst" v-for="(emotion, index) in emotionLst1" :key="index">
          <v-container>
            <v-row>
              <img :src="require(`@/assets/emoticon/${emotionEnglishLst1[index]}.png`)" alt="" class="emoticonImg" />
            </v-row>
            <v-row>
              <div>{{ emotion }}</div>
            </v-row>
            <v-row>
              <v-col>
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

      <!-- <v-row>관심 음악</v-row>
        <v-row>좋아하는 음악 장르를 선택하세요. 최소 1개, 최대 5개까지 선택할 수 있습니다.</v-row>
        <v-row><hr class="hrStyle" /></v-row>
        <v-row>
          <div class="col-3" v-for="music in musicLst" :key="music" @click="addMusic(music)">
            <div class="musicLstBox" :class="{ selected: selectedMusic.includes(music) }">{{ music }}</div>
          </div>
        </v-row> -->
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
      musicTaste1: {
        기대: [],
        슬픔: [],
        창피: [],
        화: [],
        공포: [],
        // 피곤: [],
        // 평온: [],
        // 짜증: [],
        // 기쁨: [],
        // 사랑: [],
      },
    };
  },
  methods: {
    addGenres(emotion, genre) {
      // 선택 리스트의 감정에 해당 장르가 없으면 추가
      if (!this.musicTaste1[emotion].includes(genre)) {
        this.musicTaste1[emotion].push(genre);
      } else {
        //있으면 삭제
        this.musicTaste1[emotion].pop(genre);
      }
      //   console.log(this.musicTaste1);
      this.$emit("updateMusicSecond", this.musicTaste2);
    },
  },
};
</script>

<style scoped>
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
  width: 20%;
}
.emoticonImg {
  width: 50%;
}
.genreCheckBox {
  margin: 0;
}
</style>
