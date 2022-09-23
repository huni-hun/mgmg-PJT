<template>
  <div>
    <v-container>
      <v-row>관심 음악</v-row>
      <v-row>좋아하는 음악 장르를 선택하세요. 최소 1개, 최대 5개까지 선택할 수 있습니다.</v-row>
      <v-row><hr class="hrStyle" /></v-row>
      <v-row>
        <div class="col-3" v-for="music in musicLst" :key="music" @click="addMusic(music)">
          <div class="musicLstBox" :class="{ selected: selectedMusic.includes(music) }">{{ music }}</div>
        </div>
      </v-row>
      <!-- <v-item-group multiple>
        <v-container>
          <v-row>
            <v-col v-for="music in musicLst" :key="music" cols="12" md="3">
              <v-item v-slot="{ active, toggle }">
                <v-card :color="active ? '#dddddd' : '#777777'" class="d-flex align-center" dark height="200" @click="toggle">
                  <v-scroll-y-transition>
                    <div v-if="active" class="text-h2 flex-grow-1 text-center">{{ music }}</div>
                  </v-scroll-y-transition>
                </v-card>
              </v-item>
            </v-col>
          </v-row>
        </v-container>
      </v-item-group> -->
    </v-container>
  </div>
</template>

<script>
export default {
  props: ["selectMusic"],
  mounted() {
    this.musicSurveyMounted();
  },
  data() {
    return {
      musicLst: ["댄스", "랩/힙합", "록/메탈", "발라드", "인디음악", "트로트", "포크/블루스", "R&B/Soul"],
      selectedMusic: ["댄스"],
      // selectedMusic: this.$store.state.userStore.interestMusicLstSignup,
    };
  },
  methods: {
    // 음악 선택. 선택리스트에 없으면 추가, 있으면 제거
    musicSurveyMounted() {
      this.$emit("selectMusics", this.selectedMusic);
    },
    addMusic(music) {
      if (this.selectedMusic.includes(music)) {
        if (this.selectedMusic.length == 1) {
          return;
        }
        this.selectedMusic.splice(this.selectedMusic.indexOf(music), 1);
      } else if (this.selectedMusic.length >= 5) {
        return;
      } else {
        this.selectedMusic.push(music);
      }
      // this.$store.state.userStore.interestMusicLstSignup = this.selectedMusic;
      // console.log(this.selectedMusic);
      this.$emit("selectMusics", this.selectedMusic);
    },
  },
};
</script>

<style scoped>
.hrStyle {
  width: 50rem;
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
</style>
