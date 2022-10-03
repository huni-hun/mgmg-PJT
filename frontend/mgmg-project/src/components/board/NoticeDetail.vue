<template>
  <div>
    <div class="mt-8">
      <h1 class="white--text" style="text-align: left">공지사항</h1>
    </div>
    <!-- 제목과 작성일자 -->
    <v-row>
      <v-col cols="12">
        <v-card
          style="
            background-color: rgba(243, 245, 254, 0.75);
            border-top: 1px solid #444444;
            border-bottom: 1px solid #444444;
          "
        >
          <v-responsive>
            <v-card-text>
              {{ noticeTitle }} <br />
              {{ noticeDate }} <br />
            </v-card-text>
          </v-responsive>
        </v-card>
      </v-col>
    </v-row>
    <!-- 글 내용 -->
    <v-row>
      <v-col cols="12">
        <v-card
          style="
            background-color: rgba(255, 255, 255, 0.5);
            border-top: 1px solid #444444;
            border-bottom: 1px solid #444444;
          "
        >
          <v-responsive :aspect-ratio="16 / 4">
            <v-card-text> {{ noticeContent }} </v-card-text>
          </v-responsive>
        </v-card>
      </v-col>
    </v-row>
    <!-- 글목록으로 나가는 버튼 -->
    <v-row>
      <v-col style="display: flex; width: 70%">
        <router-link
          to="/notice"
          style="text-decoration-line: none; margin: auto"
          ><v-btn class="white--text" style="background-color: #51516e"
            >글목록</v-btn
          ></router-link
        >
      </v-col>
      <v-col v-if="admin" style="display: flex; width: 30%; float: right">
        <v-btn @click="emendNotice"> 수정 </v-btn>
        <v-btn @click="delNotice"> 삭제</v-btn>
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

<style></style>
