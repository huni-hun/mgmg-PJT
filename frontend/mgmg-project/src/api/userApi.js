import { API } from "@/store";

const ACCOUNT = "/user";

//아이디 중복 검사
async function idDoubleCheck(params) {
  let response = await API.get(`${ACCOUNT}` + "/idcheck", {
    params: {
      userId: params.userId,
    },
  });
  return response.data;
}
//이메일 중복 검사
async function emailDoubleCheck(request) {
  let response = await API.post(`${ACCOUNT}` + "/email", request);
  return response.data;
}
//이메일 인증번호 검사
async function emailNumCheck(params) {
  let response = await API.get(`${ACCOUNT}` + "/emailcheck", {
    params: {
      email: params.user_email,
      emailNum: params.user_emailNum,
    },
  });
  return response.data;
}
//회원가입
async function signUp(request) {
  let response = await API.post(`${ACCOUNT}` + "/regist", request);
  return response.data;
}

//마이페이지 본인인증
async function myCheck(accessToken, request) {
  let response = await API.post(`${ACCOUNT}` + "/pwcheck", request, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//마이페이지 내 정보 확인
async function showUserInfo(accessToken) {
  let response = await API.get(`${ACCOUNT}` + "/mypage", {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//마이페이지 회원 정보 수정
async function editUserInfo(accessToken, request) {
  let response = await API.put(`${ACCOUNT}` + "/mypage", request, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//마이페이지 비밀번호 수정
async function editPasswordInfo(accessToken, request) {
  let response = await API.put(`${ACCOUNT}` + "/mypage/password", request, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//마이페이지 회원 탈퇴
async function deleteUser(accessToken) {
  let response = await API.delete(`${ACCOUNT}` + "/mypage", {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//일반로그인
async function logIn(request) {
  let response = await API.post(`${ACCOUNT}` + "/login", request);
  return response.data;
}
//자동 로그인
async function autoLogin(request) {
  let response = await API.post(`${ACCOUNT}` + "/autologin", request);
  return response.data;
}
//아이디 찾기
async function findId(params) {
  let response = await API.get(`${ACCOUNT}` + "/findid", {
    params: {
      userName: params.userName,
      email: params.userEmail,
    },
  });
  return response.data;
}
//비밀번호 찾기
async function findpw(params) {
  let response = await API.get(`${ACCOUNT}` + "/findpw", {
    params: {
      userId: params.userId,
      email: params.userEmail,
    },
  });
  return response.data;
}
//글꼴 수정
async function changeFont(accessToken, request) {
  let response = await API.put(`${ACCOUNT}` + "/mypage/diaryfont", request, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//선물 취향 조회 - 목록조회
async function showInterestGift(accessToken) {
  let response = await API.get(`${ACCOUNT}` + "/mypage/gift", {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//음악 취향 변경 - 마이페이지
async function changeInterestMusic(accessToken, request) {
  let response = await API.put(`${ACCOUNT}` + "/mypage/musicchange", request, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//선물 취향 변경 - 마이페이지
async function changeInterestGift(accessToken, request) {
  let response = await API.put(`${ACCOUNT}` + "/mypage/giftchange", request, {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//관심 음악 삭제 - 목록조회
async function deleteInterestMusic(accessToken, musicNo) {
  let response = await API.delete("/interest/music", {
    data: { musicNo: musicNo },
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//관심 음악 조회 - 목록조회
async function getInterestMusic(accessToken) {
  let response = await API.get("/interest/music", {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//관심 선물 삭제 - 목록조회
async function deleteInterestGift(accessToken, giftNo) {
  let response = await API.delete("/interest/gift", {
    data: { giftNo: giftNo },
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}
//관심 선물 조회 - 목록조회
async function getInterestGift(accessToken) {
  let response = await API.get("/interest/gift", {
    headers: {
      Authorization: "Bearer " + accessToken,
    },
  });
  return response.data;
}

export {
  idDoubleCheck,
  emailDoubleCheck,
  emailNumCheck,
  signUp,
  myCheck,
  showUserInfo,
  editUserInfo,
  editPasswordInfo,
  deleteUser,
  logIn,
  autoLogin,
  findId,
  findpw,
  changeFont,
  showInterestGift,
  changeInterestMusic,
  changeInterestGift,
  getInterestMusic,
  deleteInterestMusic,
  getInterestGift,
  deleteInterestGift,
};
