<template>
  <v-container>
    <v-row v-if="0 < musicLst.length">
      <v-col class="col-5">
        <v-list>
          <v-list-item-group v-model="model" mandatory color="indigo">
            <v-list-item
              v-for="(music, index) in musicLst"
              :key="music.title"
              @click="checkMusic(music)"
            >
              <v-row class="mt-1 mb-1">
                <v-col class="col-3">
                  <v-img
                    :src="music.music.albumArt"
                    alt="앨범아트"
                    height="100"
                    width="100"
                  />
                </v-col>
                <v-col class="col-9">
                  <div>제목: {{ music.music.musicName }}</div>
                  <div>가수: {{ music.music.artist }}</div>
                  <div>작성 일기 날짜: {{ music.registDate }}</div>
                  <div>
                    <v-btn @click="deleteMusic(index, music)"
                      >삭제 아이콘</v-btn
                    >
                  </div>
                </v-col>
              </v-row>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-col>
      <v-col class="col-7">
        <div class="video-container">
          <iframe
            class="player"
            :src="`https://www.youtube.com/embed/${playCode}`"
            title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          ></iframe>
        </div>
        <div v-if="picked != null" class="mt-3">
          <div>제목: {{ picked.music.musicName }}</div>
          <div>가수: {{ picked.music.artist }}</div>
          <div>앨범명: {{ picked.music.albumName }}</div>
          <div>발매일: {{ picked.music.releaseDate }}</div>
          <div>장르: {{ picked.music.musicGenreName }}</div>
          <div>작성 일기 날짜: {{ picked.registDate }}</div>
        </div>
      </v-col>
    </v-row>
    <v-row v-else> 관심 음악 없음 </v-row>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import { getInterestMusic, deleteInterestMusic } from "@/api/userApi.js";
import Swal from "sweetalert2";
import axios from "axios";

const YoutubeURL = "https://www.googleapis.com/youtube/v3/search";

export default {
  data() {
    return {
      musicLst: [],
      model: 0,
      playCode: "",
      picked: null,
    };
  },
  async created() {
    await getInterestMusic(this.accessToken)
      .then((res) => {
        this.musicLst = res.musics;
        if (0 < this.musicLst.length) {
          this.checkMusic(this.musicLst[0]);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    async deleteMusic(index, music) {
      await deleteInterestMusic(this.accessToken, music.music.musicNo)
        .then((res) => {
          console.log(res);
          this.musicLst.splice(index, 1);
          if (0 < this.musicLst.length) {
            if (this.musicLst.length == index) {
              this.checkMusic(this.musicLst[index - 1]);
            } else {
              this.checkMusic(this.musicLst[index]);
            }
          }

          Swal.fire({
            text: "관심음악에서 삭제했습니다.",
            icon: "success",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    checkMusic(music) {
      this.picked = music;
      // this.getYoutubeList(music.music.musicName + " " + music.music.artist);
    },
    async getYoutubeList(searchContent) {
      const config = {
        params: {
          part: "snippet",
          maxResults: 1,
          q: searchContent,
          type: "video",
          key: process.env.VUE_APP_YOUTUBE_API_KEY,
        },
      };
      const response = await axios.get(YoutubeURL, config);
      this.playCode = response.data.items[0].id.videoId;
    },
  },
};
</script>

<style></style>
