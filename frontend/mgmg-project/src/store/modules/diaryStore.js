import Vue from "vue";
import Vuex from "vuex";
import router from "@/router/index.js";
import { diaryWrite } from "@/api/diary.js";

Vue.use(Vuex);

export const diaryStore = {
  namespaced: true,

  state: () => ({
    content: "",
    date: "",
    img: "",
    thema: "",
    emotion: "",
    gift: "",
    music: "",
    weather: "",
  }),
  mutations: {
    setDiary(state, data) {
      state.content = data.diaryContent;
      state.date = data.diaryDate;
      state.img = data.diaryImg;
      state.thema = data.diaryThema;
      state.emotion = data.emotion;
      state.gift = data.gift;
      state.music = data.music;
      state.weather = data.weather;
    },
  },
  actions: {
    async fetchDiary({ rootGetters, commit }, diaryData, no) {
      await diaryWrite(rootGetters.accessToken, diaryData)
        .then((res) => {
          console.log("vuex success", res.data);
          commit("setDiary", res.data);
          router.push({
            name: "diarydetail",
            params: no,
          });
        })
        .catch((error) => console.log("vuex error", error));
    },
  },
  modules: {},
};

export default diaryStore;
