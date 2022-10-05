<template>
  <div>
    <v-row>
      <v-col cols="12">
        <div class="div-title">
          <div class="notice-title">
            {{ noticeTitle }}
          </div>
          <div class="notice-date">작성일자: {{ noticeDate }}</div>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <div div class="div-content">
          <div class="notice-content">
            {{ noticeContent }}
          </div>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col style="display: flex; width: 70%">
        <router-link
          to="/notice"
          style="text-decoration-line: none; margin: auto"
          ><v-btn
            class="notice-btn white--text"
            style="background-color: #51516e"
            >글목록</v-btn
          ></router-link
        >
      </v-col>
      <v-col v-if="admin" style="display: flex; width: 30%; float: right">
        <v-btn
          class="notice-btn white--text"
          style="margin-right: 10px; background-color: #51516e"
          @click="emendNotice"
        >
          수정
        </v-btn>
        <v-btn
          class="notice-btn white--text"
          style="
            margin-left: 10px;
            margin-right: 20px;
            background-color: #51516e;
          "
          @click="delNotice"
        >
          삭제</v-btn
        >
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
import {
  notice_detail_get,
  notice_detail_delete,
} from "@/store/modules/etcStore";
// import store from "@/store/modules/userStore";
import Swal from "sweetalert2/dist/sweetalert2.js";

export default {
  data: () => ({
    noticeNo: 0,
    noticeTitle: String,
    noticeContent: String,
    noticeDate: String,
    noticeData: Object,
    fixedFlag: false,
    // isAdmin: store.state.admin,
  }),
  async created() {
    this.noticeData = await notice_detail_get(
      this.accessToken,
      this.$route.params.pid
    );
    console.log(this.noticeData);
    this.noticeNo = this.noticeData.noticeNo;
    this.noticeTitle = this.noticeData.noticeTitle;
    this.noticeContent = this.noticeData.noticeContent;
    this.noticeDate = this.noticeData.noticeDate;
    this.fixedFlag = this.noticeData.fixedFlag;
  },
  computed: {
    ...mapState("userStore", ["accessToken", "admin"]),
  },
  methods: {
    delNotice() {
      // 글삭제 함수
      Swal.fire({
        title: "공지사항 삭제",
        text: "공지사항 게시글을 삭제하시겠습니까?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        cancelButtonText: "취소",
        confirmButtonText: "삭제",
      }).then((result) => {
        if (result.isConfirmed) {
          notice_detail_delete(this.accessToken, this.$route.params.pid).then(
            () => {
              this.$router.replace("/notice");
              Swal.fire(
                "삭제 성공",
                "공지사항 게시글이 성공적으로 삭제되었습니다.",
                "success"
              );
            }
          );
        }
      });
    },
    emendNotice() {
      this.$router.push(`/notice/edit/${this.$route.params.pid}`);
    },
  },
};
</script>

<style scoped>
.notice-btn {
  text-align: right;
  color: #ffffff;
  font-size: clamp(0.9rem, 1vw, 1.3rem);
}
.notice-title {
  padding-top: 5px;
  padding-left: 20px;
  padding-right: 20px;
  font-size: clamp(1.3rem, 1.5vw, 2rem);
}
.notice-date {
  padding-bottom: 5px;
  padding-left: 20px;
  padding-right: 20px;
  font-size: clamp(0.8rem, 1vw, 1rem);
}
.notice-content {
  padding: 5px 20px;
  font-size: clamp(1.3rem, 1.5vw, 2rem);
  min-height: 30rem;
}
.div-title {
  background-color: rgba(243, 245, 254, 0.75);
  display: flex;
  flex-direction: column;
}
.div-content {
  background-color: rgba(255, 255, 255, 0.6);
  display: flex;
  flex-direction: column;
}
</style>
