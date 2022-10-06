<template>
  <div>
    <v-container>
      <v-row v-if="0 < musicLst.length">
        <v-col class="col-12 selectedMusicBody musicShowRight mobileView">
          <div class="video-container">
            <iframe
              class="player"
              :src="`https://www.youtube.com/embed/${playCode}`"
              title="YouTube video player"
              frameborder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            ></iframe>
          </div>
          <div v-if="picked != null" class="mt-3 selectedMusicDetail">
            <div class="musicTitleLabel">{{ picked.music.musicName }}</div>
            <div>가수: {{ picked.music.artist }}</div>
            <div>앨범명: {{ picked.music.albumName }}</div>
            <div>발매일: {{ picked.music.releaseDate }}</div>
            <div>장르: {{ picked.music.musicGenreName }}</div>
            <div>작성 일기 날짜: {{ picked.registDate }}</div>
          </div>
        </v-col>

        <div class="musicShowListBody col-12 col-md-5 col-sm-6">
          <v-col class="">
            <v-list>
              <v-list-item-group v-model="model" mandatory color="indigo">
                <v-list-item v-for="(music, index) in musicLst" :key="music.title" @click="checkMusic(music)">
                  <div class="oneMusicBox">
                    <v-row class="mt-1 mb-1">
                      <v-col class="col-3">
                        <v-img :src="music.music.albumArt" alt="앨범아트" height="100" width="100" />
                      </v-col>
                      <v-col class="col-9">
                        <div>{{ music.music.musicName }}</div>
                        <div>가수: {{ music.music.artist }}</div>
                        <div>작성 일기 날짜: {{ music.registDate }}</div>
                        <div class="deleteIcon">
                          <v-icon @click="deleteMusic(index, music)">mdi-trash-can-outline</v-icon>
                          <!-- <v-btn @click="deleteMusic(index, music)">삭제 아이콘</v-btn> -->
                        </div>
                      </v-col>
                    </v-row>
                  </div>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-col>
        </div>

        <v-col class="col-12 col-md-7 col-sm-6 selectedMusicBody musicShowRight pcView">
          <div class="video-container">
            <iframe
              class="player"
              :src="`https://www.youtube.com/embed/${playCode}`"
              title="YouTube video player"
              frameborder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            ></iframe>
          </div>
          <div v-if="picked != null" class="mt-3 selectedMusicDetail">
            <div class="musicTitleLabel">{{ picked.music.musicName }}</div>
            <div>가수: {{ picked.music.artist }}</div>
            <div>앨범명: {{ picked.music.albumName }}</div>
            <div>발매일: {{ picked.music.releaseDate }}</div>
            <div>장르: {{ picked.music.musicGenreName }}</div>
            <div>작성 일기 날짜: {{ picked.registDate }}</div>
          </div>
        </v-col>
      </v-row>
      <v-row class="noMusic" v-else>
        <img style="width: 9.3vw" src="@/assets/emoticon/sad.png" alt="" />
        <div>관심 음악이 없어요.</div>
      </v-row>
    </v-container>
  </div>
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
      // musicLst: [
      //   { title: "애국가", music: { albumArt: "https://media3.giphy.com/media/H44Gd0GTIzhDsk4TBC/giphy.gif", musicName: "애국가", artist: "작곡가" }, registDate: "2022-01-11" },
      // ],
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
      //this.getYoutubeList(music.music.musicName + " " + music.music.artist);
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

<style>
.video-container {
  position: relative;
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

.musicShowListBody {
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 0px 20px 20px rgba(0, 0, 0, 0.2);
  height: 70vh;
  overflow: auto;
}

.musicShowListBody::-webkit-scrollbar {
  display: none;
}

.musicShowRight {
  padding-left: 3%;
}

.oneMusicBox {
  width: 100%;
  border-bottom: 2px solid #d3d3d3;
}

.selectedMusicBody {
  height: 70vh;
  overflow: auto;
}

.selectedMusicBody::-webkit-scrollbar {
  display: none;
}

.selectedMusicDetail {
  padding: 5%;
  border-radius: 5px;
  background-color: rgba(0, 0, 0, 0.11);
}

.musicTitleLabel {
  font-size: clamp(1rem, 2.5vw, 1.4rem);
  display: block;
  margin-bottom: 1%;
}

.deleteIcon {
  display: flex;
  justify-content: flex-end;
}

.mobileView {
  display: none;
}

@media (max-width: 639px) {
  .selectedMusicDetail {
    display: none;
  }

  .mobileView {
    display: inline-block;
  }

  .pcView {
    display: none;
  }

  .selectedMusicBody {
    height: auto;
    overflow: auto;
  }

  .musicShowRight {
    padding-left: 0;
  }

  .col-12 {
    padding: 12px 0 12px 0;
  }

  .musicShowListBody {
    height: 50vh;
  }
}

.noMusic {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;
  box-shadow: 0px 0px 20px 20px rgba(23, 50, 97, 0.2);
  border-radius: 10px;
  padding: 2% 10%;
  height: 70vh;
}
</style>
