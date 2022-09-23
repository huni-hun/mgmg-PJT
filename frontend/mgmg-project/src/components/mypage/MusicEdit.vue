<template>
  <v-container>
    <v-row> <label for="">음악 장르</label> </v-row>
    <v-row>
      <hr class="hrStyle" />
    </v-row>
    <v-row> <label for="">좋아하는 음악 장르를 선택하세요. 최소 1개, 최대 5개까지 선택할 수 있습니다.</label> </v-row>
    <v-row>
      <div class="col-3" v-for="music in musicLst" :key="music" @click="addMusic(music)">
        <div class="musicLstBox" :class="{ selected: selectedMusic.includes(music) }">{{ music }}</div>
      </div>
    </v-row>
    <v-row>
      <CustomButton btnText="완료" @click="mypageMusicEdit" />
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import CustomButton from "../common/CustomButton.vue";
import api_url from "@/api/index.js";
import Swal from "sweetalert2";
import { showInterestMusic } from "@/api/userApi.js";
import { changeInterestMusic } from "@/api/userApi.js";
export default {
  data() {
    return {
      musicLst: ["댄스", "랩/힙합", "록/메탈", "발라드", "인디음악", "트로트", "포크/블루스", "R&B/Soul"],
      selectedMusic: ["댄스"],
    };
  },
  mounted() {
    this.showInterestMusic();
  },
  methods: {
    // 음악 선택. 선택리스트에 없으면 추가, 있으면 제거
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
    },
    //음악 리스트 조회
    async showInterestMusic() {
      let response = await showInterestMusic();
      console.log("응답 데이터", response);
      if (response.statusCode == 200) {
        this.selectedMusic = response.musicGenres;
      }
    },
    // 음악 리스트 변경
    async mypageMusicEdit() {
      var request = {
        musicTaste: this.selectedMusic,
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
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25), inset 3px 3px 4px 3px rgba(0, 0, 0, 0.38);
}
</style>
