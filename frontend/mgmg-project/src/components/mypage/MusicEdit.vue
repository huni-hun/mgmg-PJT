<template>
  <div>
    <v-container v-if="interestMusicPage == 1">
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
                  v-for="(genre, idx) in genreLst"
                  :key="idx"
                  :label="genreLst[idx]"
                  :value="genre"
                  v-model="input1[emotion][idx]"
                ></v-checkbox>
              </v-col>
            </v-row>
          </v-container>
        </v-col>
      </v-row>
      <v-row><CustomButton btnText="다음" @click="musicEditNext1Page" /></v-row>
    </v-container>
    <v-container v-if="interestMusicPage == 2">
      <v-row>음악 장르 (2/2)</v-row>
      <v-row>감정별로 선호하는 음악 장르를 선택하세요.</v-row>
      <v-row><hr class="hrStyle" /></v-row>
      <v-row>
        <v-col
          class="genreCheckLst"
          v-for="(emotion, index) in emotionLst2"
          :key="index"
        >
          <v-container>
            <v-row>
              <img
                :src="
                  require(`@/assets/emoticon/${emotionEnglishLst2[index]}.png`)
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
                  v-for="(genre, idx) in genreLst"
                  :key="idx"
                  :label="genreLst[idx]"
                  :value="genre"
                  v-model="input1[emotion][idx]"
                ></v-checkbox>
              </v-col>
            </v-row>
          </v-container>
        </v-col>
      </v-row>
      <v-row
        ><CustomButton btnText="이전" @click="musicEditBefore1Page"
      /></v-row>
      <v-row><CustomButton btnText="완료" @click="musicEditFinish" /></v-row>
    </v-container>
  </div>
</template>

<script>
import CustomButton from "../common/CustomButton.vue";
import Swal from "sweetalert2";
import store from "@/store/modules/userStore";
import axios from "axios";
import { changeInterestMusic } from "@/api/userApi.js";

export default {
  data() {
    return {
      happy: "",
      interestMusicPage: 1,

      emotionLst1: ["평온", "기쁨", "사랑", "짜증", "피곤"],
      emotionEnglishLst1: ["calm", "happy", "love", "annoyed", "fatigue"],
      emotionLst2: ["기대", "슬픔", "창피", "화", "공포"],
      emotionEnglishLst2: ["expect", "sad", "shame", "angry", "fear"],
      emotionLst: [
        "평온",
        "기쁨",
        "사랑",
        "짜증",
        "피곤",
        "기대",
        "슬픔",
        "창피",
        "화",
        "공포",
      ],

      genreLst: [
        "R&B/Soul",
        "댄스",
        "랩/힙합",
        "록/메탈",
        "발라드",
        "인디음악",
        "트로트",
        "포크/블루스",
      ],
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
  methods: {
    check() {
      axios({
        url: process.env.VUE_APP_API_URL + "/api/user/mypage/music",
        method: "get",
        headers: { Authorization: `Bearer ${store.state.accessToken}` },
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
          for (
            let emotionIndex = 0;
            emotionIndex < this.emotionLst.length;
            emotionIndex++
          ) {
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
    test() {
      console.log(this.happy);
    },
    // setgenreLst2() {
    //   this.genreLst2 = [...this.genreLst];
    // },
    musicEditBefore1Page() {
      this.interestMusicPage--;
      console.log("이전페이지 넘어감", this.input1);
    },
    musicEditNext1Page() {
      console.log("다음버튼누름", this.input1);
      var isAllChecked = true;
      var rep;
      var gen;
      var isNotEmpty = false;
      //하나라도 비어있는 항목 있으면 안됨.
      for (rep = 0; rep < 5; rep++) {
        for (gen = 0; gen < 8; gen++) {
          if (this.input1[this.emotionLst1[rep]][gen]) {
            isNotEmpty = true;
            // console.log("감정은", this.emotionLst1[rep]);
            // console.log(this.input1[this.emotionLst1[rep]][gen]);
          }
        }
        console.log("isNotEmpty", isNotEmpty);
        if (isNotEmpty == false) {
          isAllChecked = false;
          // console.log("isAllChecked", isAllChecked);
        }
        isNotEmpty = false;
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
        console.log("다음페이지 넘어감", this.input1);
      }
    },
    musicEditFinish() {
      console.log("최종단계시작", this.input1);
      var isAllChecked = true;
      var rep;
      var gen;
      var isNotEmpty = false;
      //하나라도 비어있는 항목 있으면 안됨.
      for (rep = 0; rep < 5; rep++) {
        for (gen = 0; gen < 8; gen++) {
          if (this.input1[this.emotionLst2[rep]][gen]) {
            isNotEmpty = true;
            console.log("감정은", this.emotionLst2[rep]);
            console.log(this.input1[this.emotionLst2[rep]][gen]);
          }
        }
        console.log("isNotEmpty", isNotEmpty);
        if (isNotEmpty == false) {
          isAllChecked = false;
          console.log("isAllChecked", isAllChecked);
        }
        isNotEmpty = false;
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
        var emoRep;
        for (emoRep = 0; emoRep < 10; emoRep++) {
          var genRep;
          for (genRep = 0; genRep < 8; genRep++) {
            console.log(this.musicTaste[this.emotionLst[emoRep]]);
            if (this.input1[this.emotionLst[emoRep]][genRep] != "") {
              this.musicTaste[this.emotionLst[emoRep]].push(
                this.input1[this.emotionLst[emoRep]][genRep]
              );
            }
            console.log(this.musicTaste[this.emotionLst[emoRep]]);
          }
        }
        this.mypageMusicEdit();
        console.log(this.musicTaste);
      }
    },

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

        this.$router.push("/my/myinfo");
      }
    },
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
