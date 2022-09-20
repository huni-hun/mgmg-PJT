//import axios from "axios";
import { createApi } from "./index.js";

const DIARY = "/diary";
// axios.post(  process.env.VUE_APP_API_URL + "api/diary",
export async function diaryWrite(userData) {
  return await createApi.post(`${DIARY}`, userData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
}

// function monthlyDiaryList(pid) {
//   return createApi.get(`${DIARY}/month/${pid}`);
// }

// function diaryDetailView(pid) {
//   return createApi.get(`${DIARY}/detail/${pid}`);
// }

// function diaryEdit(pid, userData) {
//   return createApi.put(`${DIARY}/${pid}`, userData);
// }

// function diaryDelete(pid) {
//   return createApi.delete(`${DIARY}/${pid}`);
// }

// function musicInterest(pid, userData) {
//   return createApi.post(`${DIARY}/interestmusic/${pid}`, userData);
// }

// function musicBad(pid) {
//   return createApi.post(`${DIARY}/badmusic/${pid}`);
// }

// function giftInterest(pid) {
//   return createApi.post(`${DIARY}/interestgift/${pid}`);
// }

// function giftOpen(pid) {
//   return createApi.post(`${DIARY}/opengift/${pid}`);
// }

// export {
//   diaryWrite,
//   monthlyDiaryList,
//   diaryDetailView,
//   diaryEdit,
//   diaryDelete,
//   musicInterest,
//   musicBad,
//   giftInterest,
//   giftOpen,
// };
