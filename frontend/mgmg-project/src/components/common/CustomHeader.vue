<template>
  <div>
    <v-app-bar v-if="istoken" color="rgba(243, 245, 254, .1)">
      <router-link class="logo" to="/main">
        <img class="logo" :src="img" alt="" />
      </router-link>
      <router-link to="/main">
        <v-toolbar-title>몽글몽글</v-toolbar-title>
      </router-link>

      <v-spacer></v-spacer>
      <p>
        <router-link to="/">랜딩페이지</router-link>
      </p>
      <p>
        <router-link to="/achieve">나의업적</router-link>
      </p>
      <p>
        <router-link to="/statistics">감정통계</router-link>
      </p>
      <p>
        <router-link to="/notice">공지사항</router-link>
      </p>
      <v-spacer></v-spacer>

      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn @click="inf_list" v-bind="attrs" v-on="on" icon>
            <v-icon v-if="isInf">mdi-heart</v-icon>
          </v-btn>
          <!-- <v-btn color="primary" dark v-bind="attrs" v-on="on"> Dropdown </v-btn> -->
        </template>
        <v-list>
          <v-list-item v-for="(inf, index) in infList" :key="index">
            <v-list-item-title> {{ inf.notificationContent }} | {{ inf.notificationDate }} </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-app-bar-nav-icon v-bind="attrs" v-on="on"></v-app-bar-nav-icon>
          <!-- <v-btn color="primary" dark v-bind="attrs" v-on="on"> Dropdown </v-btn> -->
        </template>
        <v-list>
          <v-list-item v-for="(item, index) in items" :key="index">
            <v-list-item-title @click="logout">
              <router-link :to="item.link">{{ item.title }}</router-link>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
    <v-app-bar v-else color="rgba(243, 245, 254, .1)">
      <router-link class="logo" to="/main">
        <img class="logo" :src="img" alt="" />
      </router-link>
      <router-link to="/main">
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
    notification: Object,
    infList: [],
    img: require("@/assets/emoticon/calm.png"),
    isCheck: Boolean, //  <- 체크 알림 변수
  }),
  methods: {
    async inf_list() {
      this.notification = await notification_list();
      this.infList = this.notification.notifications;
      this.$store.commit("IS_INF", false);
    },
    async logout() {
      this.$cookies.remove("autoLoginCookie");
      this.$cookies.remove("userIdCookie");
    },
    async checkNotification() {
      // 체크 확인 변수
      this.isCheck = await notification_check();
      this.$store.commit("IS_INF", this.isCheck.notificationFlag);
    },
  },
  computed: {
    istoken() {
      return store.state.accessToken;
    },
    isInf() {
      return store.state.isInf;
    },
  },
  comments: {},
};
</script>

<style scoped>
p {
  margin-left: 4vw;
  margin-right: 4vw;
  padding-top: 1vh;
}

a {
  text-decoration: none;
  color: black;
}

.logo {
  height: 100%;
}

.navBar {
  background-color: rgba(231, 227, 227, 0.1);
}
</style>
