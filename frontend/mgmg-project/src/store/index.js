import Vue from "vue";
import Vuex from "vuex";
import userStore from "./modules/userStore.js";
// import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: { userStore },
});
