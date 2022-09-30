<template>
  <div>
    <v-app-bar v-if="istoken" color="rgba(243, 245, 254, .1)">
      <router-link class="logo" to="/main">
        <img class="logo" :src="img" alt="" />
      </router-link>
      <router-link class="desk-nav-bar" to="/main">
        <v-toolbar-title>몽글몽글</v-toolbar-title>
      </router-link>

      <v-spacer class="desk-nav-bar"></v-spacer>
      <p>
        <router-link class="desk-nav-bar" to="/">랜딩페이지</router-link>
      </p>
      <p>
        <router-link class="desk-nav-bar" to="/achieve">나의업적</router-link>
      </p>
      <p>
        <router-link class="desk-nav-bar" to="/statistics">감정통계</router-link>
      </p>
      <p>
        <router-link class="desk-nav-bar" to="/notice">공지사항</router-link>
      </p>
      <v-spacer></v-spacer>

      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn @click="inf_list" v-bind="attrs" v-on="on" icon>
            <v-badge v-if="isInf" bordered dot overlap color="red">
              <v-icon>mdi-bell-outline</v-icon>
            </v-badge>
            <v-icon v-if="!isInf">mdi-bell-outline</v-icon>
          </v-btn>
          <!-- <v-btn color="primary" dark v-bind="attrs" v-on="on"> Dropdown </v-btn> -->
        </template>
        <v-list>
          <v-list-item v-for="(inf, index) in infList" :key="index">
            <v-list-item-title> {{ inf.notificationContent }} | {{ inf.notificationDate }} </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <h5 class="user-name">{{ isUserName }}님</h5>
      <v-menu class="desk-nav-bar" offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-app-bar-nav-icon class="desk-nav-bar" v-bind="attrs" v-on="on"><v-icon large>mdi-menu-down</v-icon></v-app-bar-nav-icon>
          <!-- <v-btn color="primary" dark v-bind="attrs" v-on="on"> Dropdown </v-btn> -->
        </template>
        <v-list>
          <v-list-item v-for="(item, index) in items" :key="index">
            <v-list-item-title @click="menusMetod(item.link)">
              {{ item.title }}
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <!-- 모바일 메뉴바 -->
      <v-menu class="mobile-nav-bar" offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-app-bar-nav-icon class="mobile-nav-bar" v-bind="attrs" v-on="on"></v-app-bar-nav-icon>
        </template>
        <v-list>
          <v-list-item v-for="(item, index) in moLtems" :key="index">
            <v-list-item-title @click="menusMetod(item.link)">
              {{ item.title }}
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
    <v-app-bar v-else color="rgba(243, 245, 254, .1)">
      <router-link class="logo" to="/main">
        <img class="logo" :src="img" alt="" />
      </router-link>
      <router-link class="desk-nav-bar" to="/main">
        <v-toolbar-title>몽글몽글</v-toolbar-title>
      </router-link>

      <v-spacer></v-spacer>
      <router-link to="/login">
        <v-toolbar-title>로그인</v-toolbar-title>
      </router-link>
    </v-app-bar>
  </div>
</template>

<script>
import store from "@/store/modules/userStore";
import { notification_list, notification_check } from "@/store/modules/etcStore";
export default {
  name: "CustomHeader",

  data: () => ({
    items: [
      { title: "메인페이지", link: "/main" },
      { title: "나의정보", link: "/my" },
      { title: "관심목록", link: "/interestlist" },
      { title: "로그아웃", link: "/login" }, //그냥 로그아웃하면 됨???
    ],
    moLtems: [
      { title: "나의 업적", link: "/achieve" },
      { title: "감정 통계", link: "/statistics" },
      { title: "공지사항", link: "/notice" },
      { title: "나의정보", link: "/my" },
      { title: "관심목록", link: "/interestlist" },
      { title: "로그아웃", link: "/login" }, //그냥 로그아웃하면 됨???
    ],
    notification: Object,
    infList: [],
    img: require("@/assets/emoticon/calm.png"),
    isCheck: Boolean, //  <- 체크 알림 변수
    userName: "손님",
  }),
  methods: {
    async inf_list() {
      this.notification = await notification_list();
      this.infList = this.notification.notifications;
      if (!this.infList.length) {
        this.infList.push({ notificationContent: "알림이 없습니다.", notificationDate: "20xx-xx-xx" });
      }
      this.$store.commit("IS_INF", false);
    },
    async logout() {
      this.$cookies.set("autoLoginCookie", "");
      this.$cookies.set("userIdCookie", "");
      this.$store.state.userStore.accessToken = "";
    },
    async checkNotification() {
      // 체크 확인 변수
      this.isCheck = await notification_check();
      this.$store.commit("IS_INF", this.isCheck.notificationFlag);
    },
    menusMetod(menulink) {
      if (menulink == "/login") {
        // 로그아웃
        this.logout();
      }
      this.$router.push({ path: menulink });
    },
  },
  computed: {
    istoken() {
      return store.state.accessToken;
    },
    isInf() {
      return store.state.isInf;
    },
    isUserName() {
      return store.state.userName;
    },
  },
  comments: {},
};
</script>

<style scoped>
.mobile-nav-bar {
  display: none;
}
p {
  margin-left: 4vw;
  margin-right: 4vw;
  padding-top: 1vh;
}

a {
  text-decoration: none;
  color: black;
}

.user-name {
  color: aliceblue;
}

.logo {
  height: 100%;
}

@media (max-width: 639px) {
  /* 헤더 형태 변환은 display: none; 을 통해 이뤄짐. */
  .desk-nav-bar {
    display: none;
  }
  .user-name {
    display: none;
  }
  .mobile-nav-bar {
    display: inline-block;
  }
  /* 로고 가운데로 */
  .logo {
    height: 80%;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
}
</style>
