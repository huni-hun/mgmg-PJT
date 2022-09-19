<template>
  <div>
    <v-app-bar>
      <router-link class="logo" to="/main">
        <img class="logo" :src="img" alt="" />
      </router-link>
      <router-link to="/main">
        <v-toolbar-title>몽글몽글</v-toolbar-title>
      </router-link>

      <v-spacer></v-spacer>
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
          <v-app-bar-nav-icon v-bind="attrs" v-on="on"></v-app-bar-nav-icon>
          <!-- <v-btn color="primary" dark v-bind="attrs" v-on="on"> Dropdown </v-btn> -->
        </template>
        <v-list>
          <v-list-item v-for="(item, index) in items" :key="index">
            <v-list-item-title
              ><router-link :to="item.link">{{ item.title }}</router-link></v-list-item-title
            >
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: "CustomHeader",

  data: () => ({
    items: [
      { title: "랜딩페이지", link: "/" },
      { title: "메인페이지", link: "/main" },
      { title: "로그인", link: "/login" },
      { title: "회원가입", link: "/signup" },
      { title: "마이페이지", link: "/my" },
      { title: "일기작성", link: "/diaryWriting" },
      { title: "일기상세", link: "/diarydetail" },
      { title: "업적", link: "/achieve" },
      { title: "관심목록", link: "/interestlist" },
      { title: "통계", link: "/statistics" },
      { title: "공지사항", link: "/notice" },
    ],
    img: require("@/assets/emoticon/calm.png"),
  }),
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
</style>
