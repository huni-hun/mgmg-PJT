<template>
  <div>
    <v-app-bar v-if="accessToken" color="rgba(243, 245, 254, .1)">
      <div class="appBar">
        <div class="webLogo">
          <router-link class="logo" to="/main">
            <img class="logo" :src="img" />
            <img class="desk-nav-bar logo-text" src="@/assets/logo/logo_only_text.png" alt="" />
          </router-link>
        </div>

        <div class="desk-nav-bar web-router-link">
          <router-link class="router-link-active" to="/achieve">나의업적</router-link>
          <router-link class="router-link-active" to="/statistics">감정통계</router-link>
          <router-link class="router-link-active" to="/notice">공지사항</router-link>
        </div>

        <div class="web-right-menu">
          <div class="bell-icon">
            <v-menu class="notifi" offset-y transition="scroll-y-transition" bottom right>
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
                  <v-list-item-title v-else @click="clickAlarm()" avatar style="cursor: pointer;">
                    {{ inf.notificationContent }} | {{ inf.notificationDate }}
                  </v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </div>

          <v-menu class="desk-nav-bar" rounded="lg" bottom right open-on-hover offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn class="desk-nav-bar user-nav" v-bind="attrs" v-on="on" text color="transparent">
                <span class="user-name">{{ userName }}님 </span>
                <v-icon x-large color="blue-grey darken-3">mdi-menu-down</v-icon>
              </v-btn>
            </template>

            <v-list class="text-lg-center" color="rgb(236, 255, 255, 0.7)">
              <v-list-item v-for=" (item, index) in items" :key="index">
                <v-list-item-content>
                  <v-list-item-title @click="menusMetod(item.link)" avatar style="cursor: pointer;">
                    {{ item.title }}
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </div>

      <!-- 모바일 메뉴바 -->
      <v-menu class="mobile-nav-bar" offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-app-bar-nav-icon class="mobile-nav-bar" v-bind="attrs" v-on="on" />
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
      <div class="appBar">
        <div class="webLogo">
          <router-link class="logo" to="/main">
            <img class="logo" :src="img" />
            <img class="desk-nav-bar logo-text" src="@/assets/logo/logo_only_text.png" alt="" />
          </router-link>
        </div>
        <router-link class="router-link-active" to="/login">로그인</router-link>
      </div>
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
@import url("@/assets/font/font.css");

* {
  font-family: "EF_Diary";
  font-size: clamp(1rem, 1vw, 1.3rem);
}

.mobile-nav-bar {
  display: none;
}

.appBar {
  display: flex;
  height: 100%;
  width: 100%;
  margin: 0px 30px;
  justify-content: space-between;
  align-items: center;
}

.webLogo {
  height: 100%;
  display: flex;
  align-items: center;
}

.web-router-link {
  width: 40%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

/* 라우터 링크 CSS 효과 */
.router-link-active {
  text-decoration: none;
  color: white;
  font-size: clamp(1rem, 1.5vw, 1.8rem);
}

.web-right-menu {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

.user-name {
  color: aliceblue;
  font-size: clamp(1rem, 1.3vw, 1.4rem);
}

.logo {
  height: 100%;
  margin-right: 5px;
}

.logo-text {
  height: 100%;
  padding: 0.4vh 0px;
}

.user-nav {
  margin-left: 10px;
  padding: 0px !important;
}

.notifi {
  height: 100%;
}

@media (max-width: 639px) {

  /* 헤더 형태 변환은 display: none; 을 통해 이뤄짐. */
  .desk-nav-bar {
    display: none;
  }

  .appBar {
    margin: 0px 0px;
  }

  .user-name {
    display: none;
  }

  .mobile-nav-bar {
    display: inline-block;
  }

  .web-right-menu {
    width: auto;
  }

  /* 로고 가운데로 */
  .logo {
    height: 80%;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    margin-right: 0px;
  }
}
</style>
