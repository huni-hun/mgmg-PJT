<template>
  <div>
    <v-app-bar>
      <v-toolbar-title>몽글몽글</v-toolbar-title>

      <v-spacer></v-spacer>
      개발용 header
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
      { title: "/landing", link: "/" },
      { title: "/main", link: "/main" },
      { title: "/login", link: "/login" },
      { title: "/signup", link: "/signup" },
      { title: "/my", link: "/my" },
      { title: "/diaryWriting", link: "/diaryWriting" },
      { title: "/diarydetail", link: "/diarydetail" },
      { title: "/achieve", link: "/achieve" },
      { title: "/interestlist", link: "/interestlist" },
      { title: "/statistics", link: "/statistics" },
      { title: "/notice", link: "/notice" },
    ],
  }),
  comments: {},
};
</script>

<style></style>
