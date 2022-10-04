<template>
  <div>
    <v-app-bar v-if="accessToken" color="rgba(243, 245, 254, .1)">
      <router-link class="logo" to="/main">
        <img class="logo" :src="img" alt="" />
      </router-link>
      <router-link class="desk-nav-bar logo-text" to="/main">
        <img class="logo-text" src="@/assets/logo/logo_only_text.png" alt="" />
      </router-link>

      <v-spacer class="desk-nav-bar"></v-spacer>
      <p>
        <router-link class="desk-nav-bar router-link-active" to="/achieve"
          >나의업적</router-link
        >
      </p>
      <p>
        <router-link class="desk-nav-bar router-link-active" to="/statistics"
          >감정통계</router-link
        >
      </p>
      <p>
        <router-link class="desk-nav-bar router-link-active" to="/notice"
          >공지사항</router-link
        >
      </p>
      <v-spacer></v-spacer>

      <v-menu class="notifi" offset-y left transition="slide-y-transition">
        <template v-slot:activator="{ on, attrs }">
          <v-btn @click="inf_list" v-bind="attrs" v-on="on" icon>
            <v-badge v-if="isInf" bordered dot overlap color="red">
              <v-icon>mdi-bell-outline</v-icon>
            </v-badge>
            <v-icon v-if="!isInf">mdi-bell-outline</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item v-for="(inf, index) in infList" :key="index">
            <v-list-item-title v-if="inf.notificationDate == ''">
              {{ inf.notificationContent }}
            </v-list-item-title>
            <v-list-item-title v-else @click="clickAlarm()">
              {{ inf.notificationContent }} | {{ inf.notificationDate }}
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <v-menu class="desk-nav-bar user-nav" offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-app-bar-nav-icon class="desk-nav-bar" v-bind="attrs" v-on="on">
            <div class="user-name" v-bind="attrs" v-on="on">
              {{ userName }}님
            </div>
            <v-icon large>mdi-menu-down</v-icon>
          </v-app-bar-nav-icon>
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
          <v-app-bar-nav-icon
            class="mobile-nav-bar"
            v-bind="attrs"
            v-on="on"
          ></v-app-bar-nav-icon>
        </template>
        <v-list>
          <v-list-item v-for="(item, index) in moLtems" :key="index">
            <v-list-item-title @click="item.link;">
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
      <router-link class="desk-nav-bar logo-text" to="/main">
        <img class="logo-text" src="@/assets/logo/logo_only_text.png" alt="" />
      </router-link>

      <v-spacer></v-spacer>
      <router-link class="desk-nav-bar logo-text" to="/login">
        <v-toolbar-title>로그인</v-toolbar-title>
      </router-link>
    </v-app-bar>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

import {
  notification_list,
  notification_check,
} from "@/store/modules/etcStore";

export default {
  name: "CustomHeader",

  data: () => ({
    items: [
      { title: "나의정보", link: "/my" },
      { title: "관심목록", link: "/interestlist" },
      { title: "로그아웃", link: "/login" },
    ],
    moLtems: [
      { title: "나의업적", link: "/achieve" },
      { title: "감정통계", link: "/statistics" },
      { title: "공지사항", link: "/notice" },
      { title: "나의정보", link: "/my" },
      { title: "관심목록", link: "/interestlist" },
      { title: "로그아웃", link: "/login" },
    ],
    notification: Object,
    infList: [],
    img: require("@/assets/emoticon/calm.png"),
    isCheck: Boolean,
    isLogin: false,
  }),
  methods: {
    ...mapActions("userStore", ["setIsInf", "setUserInfoNotAuto"]),
    async inf_list() {
      this.notification = await notification_list(this.accessToken);
      this.infList = this.notification.notifications;
      if (!this.infList.length) {
        this.infList.push({
          notificationContent: "알림이 없습니다.",
          notificationDate: "",
        });
      }
      this.setIsInf(false);
    },
    async logout() {
      this.$cookies.remove("autoLoginCookie");
      this.$cookies.remove("userIdCookie");
      sessionStorage.clear();
      const data = {
        userId: "",
        userPw: "",
        userName: "",
        accessToken: "",
        refreshToken: "",
        diaryFont: 0,
        admin: 0,
        isInf: false,
      };
      this.setUserInfoNotAuto(data);
    },
    async checkNotification() {
      this.isCheck = await notification_check(this.accessToken);
      this.setIsInf(this.isCheck.notificationFlag);
    },
    menusMetod(menulink) {
      if (menulink == "/login") {
        this.logout();
      }
      this.$router.push({ path: menulink });
    },
    clickAlarm() {
      this.$router.push("/achieve");
    },
  },
  computed: {
    ...mapState("userStore", ["accessToken", "isInf", "userName"]),
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

/* 라우터 링크 CSS 효과 */
.router-link-active {
  text-decoration: none;
  color: white;
  font-size: clamp(1rem, 1.5vw, 1.8rem);
}

.user-name {
  color: aliceblue;
}

.logo {
  height: 100%;
}

.logo-text {
  height: 100%;
}

.user-nav {
  margin-left: 3%;
}

.notifi {
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
