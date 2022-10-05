<template>
  <div>
    <v-container class="justify-center">
      <div style="display: flex; flex-direction: row">
        <div class="notice-title" style="flex: 1">공지사항</div>
        <div style="flex: 1"></div>
        <div
          style="
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: flex-end;
            justify-content: flex-end;
          "
        >
          <router-link
            to="/notice/writing"
            style="text-decoration-line: none; float: right; padding-top: 8px"
          >
            <v-btn
              class="notice-write-btn white--text"
              style="background-color: #51516e"
              v-if="admin & noticeList"
              >글쓰기</v-btn
            >
          </router-link>
        </div>
      </div>
      <router-view></router-view>
    </v-container>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      noticeList: false,
    };
  },
  created() {
    if (window.location.pathname === "/notice") {
      this.noticeList = true;
    } else {
      this.noticeList = false;
    }
  },
  computed: {
    ...mapState("userStore", ["admin"]),
  },
  watch: {
    // eslint-disable-next-line
    $route(to, form) {
      if (to.path === "/notice") {
        this.noticeList = true;
      } else {
        this.noticeList = false;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
a {
  text-decoration: none;
  color: black;
}
.notice-write-btn {
  text-align: right;
  color: #ffffff;
  font-size: clamp(0.9rem, 1vw, 1.3rem);
}
.notice-title {
  font-weight: bold;
  color: #ffffff;
  font-size: clamp(1.8rem, 3vw, 3.2rem);
}
</style>
