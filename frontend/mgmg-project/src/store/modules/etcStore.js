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

export { achieve_list, achieve_detail, achieve_get_list, statistics_day };
