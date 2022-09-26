// import api_url from "@/api/index.js";
// import axios from 'axios';
import { API } from "@/store";
import store from "@/store/modules/userStore";

async function achieve_list() {
  const response = await API.get("/badge", {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
  });
  return response.data;
}

async function achieve_detail(bid) {
  const response = await API.get(`/badge/${bid}`, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
  });
  return response.data;
}

async function achieve_get_list() {
  const response = await API.get(`/badge/achieve`, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
  });
  return response.data;
}

async function statistics_day() {
  const response = await API.get(`/statistics/day`, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
  });
  return response.data;
}

async function statistics_percent(params) {
  const response = await API.get(`/statistics/percent`, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
    params: {
      startDate: params.startDate,
      endDate: params.endDate,
    },
  });
  return response.data;
}

async function notice_get(params) {
  // 공지사항 조회
  const response = await API.get(`/notice`, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
    params: {
      page: params.page,
      keyword: params.keyword,
    },
  });
  return response.data;
}

async function notice_post(request) {
  // 공지사항 작성
  const response = await API.post(`/notice`, request, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
  });
  return response.data;
}

async function notice_detail_get(pid) {
  // 공지사항 페이지 읽기
  const response = await API.get(`/notice/${pid}`, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
  });
  return response.data;
}

async function notice_detail_put(pid, request) {
  // 공지사항 수정
  const response = await API.put(`/notice/${pid}`, request, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
  });
  return response.data;
}

async function notice_detail_delete(pid) {
  // 공지사항 삭제
  const response = await API.delete(`/notice/${pid}`, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
  });
  return response.data;
}

export { achieve_list, achieve_detail, achieve_get_list, statistics_day, statistics_percent, notice_get, notice_post, notice_detail_get, notice_detail_put, notice_detail_delete };
