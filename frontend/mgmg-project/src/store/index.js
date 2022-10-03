import Vue from "vue";
import Vuex from "vuex";
import { createApi } from "@/api";
import createPersistedState from "vuex-persistedstate";
// import axios from "axios";

Vue.use(Vuex);

import { userStore } from "./modules/userStore.js";
import { diaryStore } from "./modules/diaryStore.js";

export const API = createApi();

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: { userStore, diaryStore },
  plugins: [
    createPersistedState({
      paths: ["userStore"],
      key: "vuex",
      storage: window.sessionStorage,
    }),
  ],
});
