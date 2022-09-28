<template>
  <div>
    <v-container>
      <v-row>음악 장르 (1/2)</v-row>
      <v-row>감정별로 선호하는 음악 장르를 선택하세요.</v-row>
      <v-row><hr class="hrStyle" /></v-row>
      <v-row>
        <v-col
          class="genreCheckLst"
          v-for="(emotion, index) in emotionLst1"
          :key="index"
        >
          <v-container>
            <v-row>
              <img
                :src="
                  require(`@/assets/emoticon/${emotionEnglishLst1[index]}.png`)
                "
                alt=""
                class="emoticonImg"
              />
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
      <v-row><CustomButton btnText="다음" @click="musicEditNext1Page" /></v-row>
    </v-container>
  </div>
</template>

<script>
import CustomButton from "../common/CustomButton.vue";
import Swal from "sweetalert2";
// import { showInterestMusic } from "@/api/userApi.js";
import { changeInterestMusic } from "@/api/userApi.js";
export default {
  data() {
    return {
      interestMusicPage: 1,

      emotionLst1: ["평온", "기쁨", "사랑", "짜증", "피곤"],
      emotionEnglishLst1: ["calm", "happy", "love", "annoyed", "fatigue"],

      genreLst1: [
        "R&B/Soul",
        "댄스",
        "랩/힙합",
        "록/메탈",
        "발라드",
        "인디음악",
        "트로트",
        "포크/블루스",
      ],

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
  // mounted() {
  //   this.setgenreLst2();
  // },
  methods: {
    // setgenreLst2() {
    //   this.genreLst2 = [...this.genreLst1];
    // },
    musicEditBefore1Page() {
      this.interestMusicPage--;
    },
    musicEditNext1Page() {
      console.log(this.musicTaste);
      var isAllChecked = true;
      var rep;
      //하나라도 비어있는 항목 있으면 안됨.
      for (rep = 0; rep < 5; rep++) {
        console.log(this.emotionLst1[rep]);
        console.log(this.musicTaste[this.emotionLst1[rep]]);
        if (
          typeof this.musicTaste[this.emotionLst1[rep]] == "undefined" ||
          this.musicTaste[this.emotionLst1[rep]].length == 0
        ) {
          isAllChecked = false;
        }
      }
      if (!isAllChecked) {
        Swal.fire({
          text: "감정별로 음악 장르를 1개 이상 선택해야 합니다.",
          icon: "warning",
          // iconColor: "#000000",
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
      //하나라도 비어있는 항목 있으면 안됨.
      for (rep = 0; rep < 5; rep++) {
        console.log(this.emotionLst2[rep]);
        console.log(this.musicTaste[this.emotionLst2[rep]]);
        if (
          typeof this.musicTaste[this.emotionLst2[rep]] == "undefined" ||
          this.musicTaste[this.emotionLst2[rep]].length == 0
        ) {
          isAllChecked = false;
        }
      }
      if (!isAllChecked) {
        Swal.fire({
          text: "감정별로 음악 장르를 1개 이상 선택해야 합니다.",
          icon: "warning",
          // iconColor: "#000000",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      } else {
        this.mypageMusicEdit();
      }
    },

    addGenres(emotion, genre) {
      // 선택 리스트의 감정에 해당 장르가 없으면 추가
      if (!this.musicTaste[emotion].includes(genre)) {
        this.musicTaste[emotion].push(genre);
      } else {
        //있으면 삭제
        this.musicTaste[emotion].pop(genre);
      }
      console.log(this.musicTaste);
    },

    // // 음악 선택. 선택리스트에 없으면 추가, 있으면 제거
    // addMusic(music) {
    //   if (this.selectedMusic.includes(music)) {
    //     // if (this.selectedMusic.length == 1) {
    //     //   return;
    //     // }
    //     this.selectedMusic.splice(this.selectedMusic.indexOf(music), 1);
    //   } else if (this.selectedMusic.length >= 5) {
    //     return;
    //   } else {
    //     this.selectedMusic.push(music);
    //   }
    // },
    //음악 리스트 조회
    // async showInterestMusic() {
    //   let response = await showInterestMusic();
    //   console.log("응답 데이터", response);
    //   if (response.statusCode == 200) {
    //     this.selectedMusic = response.musicGenres;
    //   }
    // },
    // 음악 리스트 변경
    async mypageMusicEdit() {
      var request = {
        musicTaste: this.musicTaste,
      };

      let response = await changeInterestMusic(request);
      console.log("응답 데이터", response);
      if (response.statusCode == 200) {
        Swal.fire({
          text: "음악 장르가 정상적으로 변경되었습니다.",
          icon: "success",
          // iconColor: "#000000",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      }
    },
    // mypageMusicEdit() {
    //   axios
    //     .put(api_url.accounts.interest_music_edit(), {
    //       headers: {
    //         //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 헤더에 토큰
    //         Authorization: `Bearer ${this.$store.state.userStore.accessToken}`,
    //       },
    //       musicTaste: this.selectedMusic,
    //     })
    //     .then((response) => {
    //       console.log(response);
    //       Swal.fire({
    //         text: "음악 장르가 정상적으로 변경되었습니다.",
    //         icon: "success",
    //         // iconColor: "#000000",
    //         confirmButtonColor: "#666666",
    //         confirmButtonText: "확인",
    //       });
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //       Swal.fire({
    //         text: "음악 장르 변경에 실패했습니다.",
    //         icon: "warning",
    //         // iconColor: "#000000",
    //         confirmButtonColor: "#666666",
    //         confirmButtonText: "확인",
    //       });
    //     });
    // },
  },
  components: { CustomButton },
};
</script>

<style scoped>
.hrStyle {
  border: 0.01rem solid #000000;
  width: 80rem;
}
.musicLstBox {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25);
}
.selected {
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25),
    inset 3px 3px 4px 3px rgba(0, 0, 0, 0.38);
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
