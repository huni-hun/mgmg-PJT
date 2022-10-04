import { API } from "@/store";

const DIARY = "/diary";

async function diaryWrite(accessToken, userData) {
  let response = await API.post(`${DIARY}`, userData, {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function monthlyDiaryList(accessToken, pid) {
  let response = await API.get(`${DIARY}/month/${pid}`, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function diaryDetailView(accessToken, pid) {
  let response = await API.get(`${DIARY}/detail/${pid}`, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function diaryEdit(accessToken, pid, userData) {
  let response = await API.put(`${DIARY}/${pid}`, userData, {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}

async function diaryDelete(accessToken, pid) {
  let response = await API.delete(`${DIARY}/${pid}`, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function musicInterest(accessToken, pid) {
  let response = await API.post(
    `${DIARY}/interestmusic/${pid}`,
    {},
    {
      headers: {
        Authorization: "Bearer " + accessToken,
      },
    }
  );

  return response.data;
}

async function musicBad(accessToken, pid) {
  let response = await API.post(
    `${DIARY}/badmusic/${pid}`,
    {},
    {
      headers: {
        Authorization: "Bearer " + accessToken,
      },
    }
  );

  return response.data;
}

async function giftInterest(accessToken, pid) {
  let response = await API.post(
    `${DIARY}/interestgift/${pid}`,
    {},
    {
      headers: {
        Authorization: "Bearer " + accessToken,
      },
    }
  );

  return response.data;
}

async function giftOpen(accessToken, pid) {
  let response = await API.get(`${DIARY}/gift/${pid}`, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function detailMusic(accessToken, pid) {
  let response = await API.get(`${DIARY}/detail/${pid}/music`, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function detailGift(accessToken, pid) {
  let response = await API.get(`${DIARY}/detail/${pid}/gift`, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function cancleGift(accessToken, no) {
  let response = await API.delete(`interest/gift`, {
    data: { giftNo: no },
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function cancleGoodMusic(accessToken, no) {
  let response = await API.delete(`interest/music`, {
    data: { musicNo: no },
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function cancleBadMusic(accessToken, pid) {
  let response = await API.delete(`${DIARY}/badmusic/${pid}`, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });

  return response.data;
}

async function sttWrite(accessToken, formData) {
  let response = await API.post(`${DIARY}/speech`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: "Bearer " + accessToken,
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
  detailMusic,
  detailGift,
  cancleGift,
  cancleGoodMusic,
  cancleBadMusic,
  sttWrite,
};
