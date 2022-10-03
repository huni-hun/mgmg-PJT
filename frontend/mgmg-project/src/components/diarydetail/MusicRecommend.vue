<template>
  <div class="outDiv">
    <div class="title">
      <span>이 날의 추천 음악</span>
    </div>
    <div class="gridDiv">
      <div class="video-container">
        <iframe :src="`https://www.youtube.com/embed/${playCode}`" title="YouTube video player" frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"></iframe>
      </div>
      <div class='v-line'></div>
      <div class="contentDiv">
        <!-- <v-img class="musicIcon" src="@/assets/diary/musicIcon.png" /> -->
        <v-img class="musicIcon" :src="this.musicAlbumImg" />

        <div class="musicInfo">
          <span>{{musicTitle}}</span>
          <span>{{musicArtist}}</span>
          <span><sub>{{musicYear}}</sub></span>
        </div>

        <div>
          <label class="ridioButton">
            <input type="radio" name="music" value="good" v-model="radioCheck" @click="radioClick($event)">
            <span class="btnText">관심 음악 추가</span>
          </label>
          <label class="ridioButton">
            <input type="radio" name="music" value="bad" v-model="radioCheck" @click="radioClick($event)">
            <span class="btnText">이 음악 비추천</span>
          </label>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { musicInterest, musicBad, detailMusic, cancleGoodMusic, cancleBadMusic } from "@/api/diary.js";
import axios from "axios";

const YoutubeURL = 'https://www.googleapis.com/youtube/v3/search';

export default {
  data: function () {
    return {
      diaryNo: this.$route.params.no,

      playCode: "",

      musicAlbumImg: "",
      musicTitle: "수고했어, 오늘도",
      musicArtist: "옥상 달빛",
      musicYear: 2019,
      musicNo: 1,

      radioCheck: "none",
      beforeRadioCheck: "",
    }
  },
  methods: {
    async getYoutubeList() {
      const config = {
        params: {
          part: 'snippet',
          maxResults: 1,
          q: this.musicArtist + " " + this.musicTitle,
          type: 'video',
          key: process.env.VUE_APP_YOUTUBE_API_KEY,
        },
      }
      const response = await axios.get(YoutubeURL, config);
      this.playCode = response.data.items[0].id.videoId;
    },
    radioClick() {
      let value = event.target.value;
      if (value == "good" || value == "bad") this.radioCheck = "";
      else this.radioCheck = event.target.value;
    }
  },
  // eslint-disable-next-line vue/no-deprecated-destroyed-lifecycle
  async beforeDestroy() {
    if (this.beforeRadioCheck == this.radioCheck) return;

    else if (this.radioCheck == "good") {
      if (this.beforeRadioCheck == "bad") {
        // console.log("비추천 -> 추천");
        await cancleBadMusic(this.musicNo).then(async () => {
          await musicInterest(this.musicNo);
        });
      }
      else {
        // console.log("none -> 추천");
        await musicInterest(this.musicNo);
      }

    } else if (this.radioCheck == "bad") {
      if (this.beforeRadioCheck == "good") {
        // console.log("추천 -> 비추천");
        await cancleGoodMusic(this.musicNo).then(async () => {
          // console.log("추천->비추천 관심음악에서 삭제");
          await musicBad(this.musicNo);
        })
      }
      else {
        // none -> 비추천
        // console.log("none -> 비추천");
        await musicBad(this.musicNo);
      }
    }
    else {
      if (this.beforeRadioCheck == "good") {
        // console.log("추천 -> none");
        await cancleGoodMusic(this.musicNo)
      }
      else {
        // console.log("비추천 -> none");
        await cancleBadMusic(this.musicNo);
      }
    }

  },
  async created() {
    await detailMusic(this.diaryNo).then((res) => {
      console.log(res);
      this.radioCheck = res.checkMusic;
      this.beforeRadioCheck = res.checkMusic;
      this.musicAlbumImg = res.music.albumArt;
      this.musicTitle = res.music.musicName;
      this.musicArtist = res.music.artist;
      this.musicYear = res.music.releaseDate;
      this.musicNo = res.music.musicNo;
      // this.getYoutubeList();
    }).catch((err) => {
      console.log(err);
    });
  },
}
</script>

<style scoped src="@/styles/diary/MusicRecommend.css">

</style>
