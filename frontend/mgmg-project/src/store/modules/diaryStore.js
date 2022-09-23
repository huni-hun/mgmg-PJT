import Vue from "vue";
import Vuex from "vuex";
import router from "@/router/index.js";
import { diaryWrite, diaryDetailView } from "@/api/diary.js";

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
    async fetchDiary({ commit }, diaryData) {
      await diaryWrite(diaryData)
        .then((res) => {
          console.log("vuex success", res.data);
          commit("setDiary", res.data);
          router.push({ name: "diarydetail" });
        })
        .catch((error) => console.log("vuex error", error));
    },
    async detailDiary({ commit }, id) {
      await diaryDetailView(id)
        .then((res) => {
          console.log("vuex success", res.data);
          commit("setDiary", res.data);
          router.push({ name: "diarydetail" });
        })
        .catch((error) => console.log("vuex error", error));
    },
  },
  modules: {},
};

export default diaryStore;
