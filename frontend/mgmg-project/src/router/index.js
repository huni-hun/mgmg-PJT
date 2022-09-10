import Vue from "vue";
import VueRouter from "vue-router";
// 메인
import LandingPage from "../views/LandingPage.vue";
import MainPage from "../views/MainPage.vue";
// 회원
import LoginPage from "../views/LoginPage.vue";
import SignupPage from "../views/SignupPage.vue";
import MyPage from "../views/MyPage.vue";
// 일기
import DiaryWritingPage from "../views/DiaryWritingPage.vue";
import DiaryDetailPage from "../views/DiaryDetailPage.vue";
// 업적, 관심, 통계
import AchievePage from "../views/AchievePage.vue";
import InterestListPage from "../views/InterestListPage.vue";
import StatisticsPage from "../views/StatisticsPage.vue";
// 공지사항
import NoticePage from "../views/NoticePage.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "landing",
    component: LandingPage,
  },
  {
    path: "/main",
    name: "main",
    component: MainPage,
  },
  {
    path: "/login",
    name: "login",
    component: LoginPage,
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupPage,
  },
  {
    path: "/my",
    name: "my",
    component: MyPage,
  },
  {
    path: "/diaryWriting",
    name: "diaryWriting",
    component: DiaryWritingPage,
  },
  {
    path: "/diarydetail",
    name: "diarydetail",
    component: DiaryDetailPage,
  },
  {
    path: "/achieve",
    name: "achieve",
    component: AchievePage,
  },
  {
    path: "/interestlist",
    name: "interestlist",
    component: InterestListPage,
  },
  {
    path: "/statistics",
    name: "statistics",
    component: StatisticsPage,
  },
  {
    path: "/notice",
    name: "notice",
    component: NoticePage,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
