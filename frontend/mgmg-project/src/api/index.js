import axios from "axios";

const HOST = process.env.VUE_APP_API_URL + "/api";

const ACCOUNT = "/user";
const INTEREST = "/interest";
const STATISTICS = "/statistics";
const BADGE = "/badge";
const NOTICE = "/notice";
const DIARY = "/diary";
const NOTIFICATION = "/notification";

function createApi() {
  return axios.create({
    // baseURL: HOST,
    // headers: {
    //   "Content-Type": "application/json; charset=UTF-8",
    // },
  });
}

export { createApi };

export default {
  accounts: {
    auto_login: () => HOST + ACCOUNT + "/autologin", //post
    login: () => HOST + ACCOUNT + "/login", //post
    find_pw: () => HOST + ACCOUNT + "/findpw", //get
    find_id: () => HOST + ACCOUNT + "/findid", //get
    id_check: () => HOST + ACCOUNT + "/idcheck", //get
    email: () => HOST + ACCOUNT + "/email", //post
    email_check: () => HOST + ACCOUNT + "/emailcheck", //get
    regist: () => HOST + ACCOUNT + "/regist", //post
    my_check: () => HOST + ACCOUNT + "/pwcheck", //post
    mypage_show_edit_delete: () => HOST + ACCOUNT + "/mypage", //get, put, delete
    password_edit: () => HOST + ACCOUNT + "/mypage/password", //put
    font_edit: () => HOST + ACCOUNT + "/mypage/diaryfont", //put
    interest_music_show: () => HOST + ACCOUNT + "/mypage/music", //get
    interest_gift_show: () => HOST + ACCOUNT + "/mypage/gift", //get
    interest_music_edit: () => HOST + ACCOUNT + "/mypage/musicchango", //put
    interest_gift_edit: () => HOST + ACCOUNT + "/mypage/giftchange", //put
  },
  interest: {
    musiclst_show_delete: () => HOST + INTEREST + "/music", //get, delete
    giftlst_show_delete: () => HOST + INTEREST + "/gift", //get, delete
  },
  statistics: {
    statistics_percent: () => HOST + STATISTICS + "/percent", //post
    statistics_day: () => HOST + STATISTICS + "/day", //get
    statistics_day_select: (pid) => HOST + STATISTICS + `/${pid}`, //get
  },
  badge: {
    achieve_list: () => HOST + BADGE, //get
    achieve_get_list: () => HOST + BADGE + "/achieve", //get
    achieve_detail: (pid) => HOST + BADGE + `/${pid}`, //get, put, delete
  },
  notice: {
    notice: () => HOST + NOTICE, //get, post
    notice_detail: (pid) => HOST + NOTICE + `/${pid}`, //get, put, delete
  },
  diary: {
    diary_write: () => HOST + DIARY, //post
    monthly_diary_list: (pid) => HOST + DIARY + "/month" + `/${pid}`, //get
    diary_detail_view: (pid) => HOST + DIARY + "/detail" + `/${pid}`, //get
    diary: (pid) => HOST + DIARY + `/${pid}`, //put, delete
    music_interest: (pid) => HOST + DIARY + "/interestmusic" + `/${pid}`, //post
    music_bad: (pid) => HOST + DIARY + "/badmusic" + `/${pid}`, //post
    gift_interest: (pid) => HOST + DIARY + "/interestgift" + `/${pid}`, //post
    gift_open: (pid) => HOST + DIARY + "/opengift" + `/${pid}`, //post
  },
  notification: {
    notification_check: () => HOST + NOTIFICATION + "/check", //get
    notification_list: () => HOST + NOTIFICATION, //get
  },
};
