import { API } from "@/store";

async function achieve_list(accessToken) {
  const response = await API.get("/badge", {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function achieve_detail(accessToken, bid) {
  const response = await API.get(`/badge/${bid}`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function achieve_get_list(accessToken) {
  const response = await API.get(`/badge/achieve`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function statistics_day(accessToken) {
  const response = await API.get(`/statistics/day`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function statistics_day_detail(accessToken, day) {
  const response = await API.get(`/statistics/day/${day}`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function statistics_percent(accessToken, params) {
  const response = await API.get(`/statistics/percent`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
    params: {
      startDate: params.startDate,
      endDate: params.endDate,
    },
  });
  return response.data;
}

async function notice_get(accessToken, params) {
  // 공지사항 조회
  const response = await API.get(`/notice`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
    params: {
      page: params.page,
      keyword: params.keyword,
    },
  });
  return response.data;
}

async function notice_post(accessToken, request) {
  // 공지사항 작성
  const response = await API.post(`/notice`, request, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function notice_detail_get(accessToken, pid) {
  // 공지사항 페이지 읽기
  const response = await API.get(`/notice/${pid}`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function notice_detail_put(accessToken, pid, request) {
  // 공지사항 수정
  const response = await API.put(`/notice/${pid}`, request, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function notice_detail_delete(accessToken, pid) {
  // 공지사항 삭제
  const response = await API.delete(`/notice/${pid}`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function notification_list(accessToken) {
  // 알림 리스트
  const response = await API.get(`/notification`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

async function notification_check(accessToken) {
  // 알림 체크 true & false
  const response = await API.get(`/notification/check`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
  return response.data;
}

export {
  achieve_list,
  achieve_detail,
  achieve_get_list,
  statistics_day,
  statistics_day_detail,
  statistics_percent,
  notice_get,
  notice_post,
  notice_detail_get,
  notice_detail_put,
  notice_detail_delete,
  notification_list,
  notification_check,
};
