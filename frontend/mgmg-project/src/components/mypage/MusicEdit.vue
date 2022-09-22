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
      <CustomButton btnText="완료" />
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import CustomButton from "../common/CustomButton.vue";
import api_url from "@/api/index.js";
import Swal from "sweetalert2";
export default {
  data() {
    return {
      musicLst: ["재즈", "발라드", "클래식", "힙합", "CCM", "댄스", "팝", "컨트리음악"],
      selectedMusic: ["재즈"],
    };
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
    // 음악 리스트 변경
    mypageMusicEdit() {
      axios
        .put(api_url.accounts.interest_music_edit(), {
          headers: {
            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 헤더에 토큰
            Authorization: `Bearer ${this.$store.state.userStore.accessToken}`,
          },
          musicTaste: this.selectedMusic,
        })
        .then((response) => {
          console.log(response);
          Swal.fire({
            text: "음악 장르가 정상적으로 변경되었습니다.",
            icon: "success",
            // iconColor: "#000000",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            text: "음악 장르 변경에 실패했습니다.",
            icon: "warning",
            // iconColor: "#000000",
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
