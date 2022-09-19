<template>
  <div>
    <v-container class="justify-center">
      <h1>공지사항</h1>
      <router-link to="/notice/detail">글상세</router-link> | <router-link to="/notice">글목록</router-link> |
      <router-link to="/notice/writing">글쓰기</router-link>
      <router-view></router-view>
    </v-container>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
a {
  text-decoration: none;
  color: black;
}
</style>
