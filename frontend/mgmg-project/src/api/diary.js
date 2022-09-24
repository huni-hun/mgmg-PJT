//import axios from "axios";
import { API } from "@/store";
import store from "@/store/modules/userStore";

const DIARY = "/diary";

async function diaryWrite(userData) {
  let response = await API.post(`${DIARY}`, userData, {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: "Bearer " + store.state.accessToken,
    },
  });

  return response.data;
}

async function monthlyDiaryList(pid) {
  let response = await API.get(`${DIARY}/month/${pid}`, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });

  return response.data;
}

async function diaryDetailView(pid) {
  let response = await API.get(`${DIARY}/detail/${pid}`, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });

  return response.data;
}

async function diaryEdit(pid, userData) {
  let response = await API.put(`${DIARY}/${pid}`, userData, {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: "Bearer " + store.state.accessToken,
    },
  });
  return response.data;
}

async function diaryDelete(pid) {
  let response = await API.delete(`${DIARY}/${pid}`, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });

  return response.data;
}

async function musicInterest(pid) {
  let response = await API.post(`${DIARY}/interestmusic/${pid}`, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });

  return response.data;
}

async function musicBad(pid) {
  let response = await API.post(`${DIARY}/badmusic/${pid}`, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });

  return response.data;
}

async function giftInterest(pid) {
  let response = await API.post(`${DIARY}/interestgift/${pid}`, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });

  return response.data;
}

async function giftOpen(pid) {
  let response = await API.post(`${DIARY}/opengift/${pid}`, {
    headers: {
      Authorization: "Bearer " + store.state.accessToken,
    },
  });

  return response.data;
}

export {
  diaryWrite,
  monthlyDiaryList,
  diaryDetailView,
  diaryEdit,
  diaryDelete,
  musicInterest,
  musicBad,
  giftInterest,
  giftOpen,
};
