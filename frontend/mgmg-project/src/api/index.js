import axios from "axios";

const HOST = process.env.VUE_APP_API_URL + "/api";

function createApi() {
  return axios.create({
    baseURL: HOST,
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
    },
  });
}

export { createApi };
