<template>
  <div class="outDiv">
    <span> 이 날의 추천 음악 </span>
    <div class="gridDiv">
      <div class="video-container">
        <iframe class="player" :src="`https://www.youtube.com/embed/${playCode}`" title="YouTube video player"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"></iframe>
      </div>
      <div class='v-line'></div>
      <div>
        <v-img class="musicIcon" src="@/assets/diary/musicIcon.png" />

        <div>
          <span>{{musicTitle}}</span><br />
          <span>{{musicArtist}}</span><br />
          <span>( {{musicYear}} )</span>
        </div>

        <label class="ridioButton">
          <input type="radio" name="music" value="good" v-model="selectMusic">
          <span class="btnText">관심 음악 추가</span>
        </label>

        <label class="ridioButton">
          <input type="radio" name="music" value="bad" v-model="selectMusic">
          <span class="btnText">이 음악 비추천</span>
        </label>
      </div>
    </div>
  </div>
</template>

<script>
import { musicInterest, musicBad } from "@/api/diary.js";
// import { musicBad } from "@/api/diary.js";
// import axios from "axios";
// import store from "@/store/modules/userStore";
export default {
  data: function () {
    return {
      playCode: "D6hqryFvcl8",

      musicTitle: "수고했어, 오늘도",
      musicArtist: "옥상 달빛",
      musicYear: 2019,

      selectMusic: "",

      recommendNo: this.$route.params.no,
    }
  },
  // eslint-disable-next-line vue/no-deprecated-destroyed-lifecycle
  async beforeDestroy() {
    // 음악 추천 받아오고 실행
    if (this.selectMusic == "good") {
      console.log("good 선택");
      await musicInterest(this.recommendNo);
      // axios.post(process.env.VUE_APP_API_URL + `/api/diary/interestmusic/${this.recommendNo}`, {
      //   headers: {
      //     Authorization: "Bearer " + store.state.accessToken,
      //   },
      // })
      //   .then(function (response) {
      //     console.log(response);
      //   })
      //   .catch(function (error) {
      //     console.log(error);
      //   });
    } else if (this.selectMusic == "bad") {
      console.log("bad 선택");
      await musicBad(this.recommendNo);
    }
  },
};
</script>

<style scoped>
.outDiv {
  border: 1px solid black;
  margin: 10px 0px;
}

.gridDiv {
  width: 100%;
  margin: 0 auto;
  padding: 10px;
  display: grid;
  /* grid-template-columns: 1.5fr 0.1fr 1fr; */
  grid-template-columns: 1.5fr 0.1fr auto;
  place-items: center;
  align-items: center;
}

.video-container {
  position: relative;
  min-width: 100%;
  width: 100%;
  padding-top: 50%;
}

iframe {
  z-index: 1;
  top: 0;
  left: 0;
  position: absolute;
  width: 100%;
  height: 100%;
}

.v-line {
  border-left: 1.5px solid rgb(107, 107, 107);
  height: 100%;
}

.musicIcon {
  width: 15%;
}

.ridioButton input[type="radio"] {
  display: none;
}

.ridioButton input[type="radio"]+span {
  display: inline-block;
  padding: 10px 10px;
  background-color: #ffffff;
  border: 1px solid #000000;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border-radius: 10px;
  text-align: center;
  cursor: pointer;
  width: 50%-10px;
  margin: 0px 5px;
  color: #000000;
}

.ridioButton input[type="radio"]:checked+span {
  background-color: #ebb8cd;
  color: white;
  border: none;
  box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.ridioButton span {
  font-size: clamp(0.5rem, 1.2vw, 1rem);
}
</style>
