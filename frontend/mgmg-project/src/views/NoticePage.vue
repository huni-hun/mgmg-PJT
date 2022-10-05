<template>
  <div>
    <v-container class="justify-center">
      <div style="display: flex; flex-direction: row">
        <div class="notice-title" style="flex: 1">공지사항</div>
      </div>
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
.notice-title {
  font-weight: bold;
  color: #ffffff;
  font-size: clamp(1.8rem, 3vw, 3.2rem);
}
</style>
