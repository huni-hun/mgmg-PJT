import Vue from "vue";
import VueRouter from "vue-router";
// 메인
import LandingPage from "../views/LandingPage.vue";
import MainPage from "../views/MainPage.vue";
// 회원
import LoginPage from "../views/LoginPage.vue"; // 부모
import Login from "../components/login/LogIn.vue";
import FindId from "../components/login/IdFind.vue";
import FindPw from "../components/login/PasswordFind.vue";
import SignupPage from "../views/SignupPage.vue";
import MyPage from "../views/MyPage.vue"; // 부모
import MyCheck from "../components/mypage/MyCheck.vue";
import MyInfo from "../components/mypage/MyInfo.vue";
import InfoEdit from "../components/mypage/InfoEdit.vue";
import PasswordEdit from "../components/mypage/PasswordEdit.vue";
import MusicEdit from "../components/mypage/MusicEdit.vue";
import GiftEdit from "../components/mypage/GiftEdit.vue";
import FontEdit from "../components/mypage/FontEdit.vue";
import UserDelete from "../components/mypage/UserDelete.vue";
// 일기
import DiaryWritingPage from "../views/DiaryWritingPage.vue";
import DiaryDetailPage from "../views/DiaryDetailPage.vue";
// 업적, 관심, 통계
import AchievePage from "../views/AchievePage.vue";
import GetAchieveList from "../components/achieve/GetAchieveList.vue";
import InterestListPage from "../views/InterestListPage.vue"; // 부모
import InterestMusic from "../components/interestlist/InterestMusicShow.vue";
import InterestGift from "../components/interestlist/InterestGiftShow.vue";
import AchieveList from "../components/achieve/AchieveList.vue";
import StatisticsPage from "../views/StatisticsPage.vue"; // 통계 부모
import DayDetail from "../components/statistics/DayDetail.vue";
import PeriodDetail from "../components/statistics/PeriodDetail.vue";

// 공지사항
import NoticePage from "../views/NoticePage.vue"; // 부모
import NoticeDetail from "../components/board/NoticeDetail.vue";
import NoticeList from "../components/board/NoticeList.vue";
import NoticeWriting from "../components/board/NoticeWriting.vue";

//컴포넌트 보는 용도 (추후 삭제예정)
import Calendar from "../components/common/CustomCalendar.vue";

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
    // name: "login",
    component: LoginPage,
    children: [
      { path: "", name: "login", component: Login },
      { path: "findid", name: "findid", component: FindId },
      { path: "findpw", name: "findpw", component: FindPw },
    ],
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupPage,
  },
  {
    path: "/my",
    // name: "my",
    component: MyPage,
    children: [
      { path: "", name: "myCheck", component: MyCheck },
      { path: "myinfo", name: "myinfo", component: MyInfo },
      { path: "infoedit", name: "infoedit", component: InfoEdit },
      { path: "passwordedit", name: "passwordedit", component: PasswordEdit },
      { path: "musicedit", name: "musicedit", component: MusicEdit },
      { path: "giftedit", name: "giftedit", component: GiftEdit },
      { path: "fontedit", name: "fontedit", component: FontEdit },
      { path: "userdelete", name: "userdelete", component: UserDelete },
    ],
  },
  {
    path: "/diaryWriting/:no/:date",
    name: "diaryWriting",
    component: DiaryWritingPage,
  },
  {
    path: "/diarydetail/:no",
    name: "diarydetail",
    component: DiaryDetailPage,
  },
  {
    path: "/achieve",
    // name: "achieve",
    component: AchievePage,
    children: [
      {
        path: "",
        name: "achievelist",
        component: AchieveList,
      },
      {
        path: "get",
        name: "getachievelist",
        component: GetAchieveList,
      },
    ],
  },
  {
    path: "/interestlist",
    // name: "interestlist",
    component: InterestListPage,
    children: [
      { path: "music", name: "interestmusic", component: InterestMusic },
      { path: "gift", name: "interestgift", component: InterestGift },
    ],
  },
  {
    path: "/statistics",
    // name: "statistics",
    component: StatisticsPage,
    children: [
      {
        path: "",
        name: "perioddetail",
        component: PeriodDetail,
      },
      {
        path: "day",
        name: "daydetail",
        component: DayDetail,
      },
    ],
  },
  {
    path: "/notice",
    // name: "notice",
    component: NoticePage,
    children: [
      {
        path: "detail",
        name: "noticedetail",
        component: NoticeDetail,
      },
      {
        path: "",
        name: "noticelist",
        component: NoticeList,
      },
      {
        path: "writing",
        name: "noticewriting",
        component: NoticeWriting,
      },
    ],
  },
  {
    path: "/calendar",
    name: "calendar",
    component: Calendar,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
