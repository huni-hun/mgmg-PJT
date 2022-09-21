// import api_url from "@/api/index.js";
// import axios from 'axios';
import { API } from "@/store";

window.accessToken =
  "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImlzcyI6InNzYWZ5LmNvbSIsImV4cCI6MTY2MzgyMTcwMywiaWF0IjoxNjYzNzM1MzAzfQ.UJn4pcnuVxhJSRnj03FYOyAcWd5iNhf62uFWWUdQ0mJ7LW_foiMijtAQVddFPdbv-nOlZ-gSt-5SQV8YOrt6hA";

export default {
  async achieve_list() {
    const response = await API.get("/badge", {
      headers: {
        Authorization: `Bearer ${window.accessToken}`,
      },
    });
    return response.data;
  },

  async achieve_detail(bid) {
    const response = await API.get(`/badge/${bid}`, {
      headers: {
        Authorization: `Bearer ${window.accessToken}`,
      },
    });
    return response.data;
  },

  async achieve_get_list() {
    const response = await API.get(`/badge/achieve`, {
      headers: {
        Authorization: `Bearer ${window.accessToken}`,
      },
    });
    return response.data;
  },
};
