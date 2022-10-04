<template>
  <div>
    <!-- 기본적으로 grid 시스템을 기반으로 게시판 배치 -->
    <v-container class="justify-center">
      <div style="display: flex">
        <div style="width: 50%">
          <h1 class="white--text" style="text-align: left">공지사항</h1>
        </div>
        <div style="width: 50%">
          <router-link to="/notice/writing" style="text-decoration-line: none; float: right; padding-top: 8px">
            <v-btn v-if="admin" class="white--text" style="background-color: #51516e">
              글쓰기
            </v-btn>
          </router-link>
        </div>
      </div>

      <v-row style="
          margin-top: 5px;
          background-color: rgba(0, 0, 0, 0.15);
          border-top: 1px solid #444444;
          border-bottom: 1px solid #444444;
        ">
        <v-col class="white--text">번호</v-col>
        <v-col class="white--text" style="text-align: center">제목</v-col>
        <v-col class="white--text" style="text-align: right">작성일자</v-col>
      </v-row>
      <v-row v-for="notice in dataList" :key="notice.noticeNo">
        <v-col v-if="notice.fixedFlag" style="
            background-color: rgba(226, 231, 255, 0.5);
            border-bottom: 1px solid #444444;
            text-decoration: #173261;
          ">공지</v-col>
        <v-col v-else style="
            background-color: rgba(255, 255, 255, 0.5);
            border-bottom: 1px solid #444444;
          ">{{ notice.noticeNo }}</v-col>
        <v-col :style="
          notice.fixedFlag
            ? `background-color: rgba(226, 231, 255, 0.5); border-bottom: 1px solid #444444; text-align: center`
            : `background-color: rgba(255, 255, 255, 0.5); border-bottom: 1px solid #444444; text-align: center`
        " @click="openDetails(notice)">{{ notice.noticeTitle }}</v-col>
        <v-col :style="
          notice.fixedFlag
            ? `background-color: rgba(226, 231, 255, 0.5); border-bottom: 1px solid #444444; text-align: right`
            : `background-color: rgba(255, 255, 255, 0.5); border-bottom: 1px solid #444444; text-align: right`
        ">{{ notice.noticeDate }}</v-col>
      </v-row>

      <v-col cols="12">
        <v-row class="justify-center">
          <!-- 페이징네이션 -->
          <!-- 참고 : https://www.bezkoder.com/vuetify-pagination-server-side/#Vuetify_Pagination_component -->
          <v-pagination v-model="page" :length="pageCount" @input="handlePageChange" class="mt-10"></v-pagination>
        </v-row>
      </v-col>
      <v-row class="justify-center">
        <v-col cols="3">
          <!-- 검색창 -->
          <v-text-field v-model="search" @keyup.enter="goSearch(1)" append-icon="mdi-magnify" label="Search" single-line
            hide-details class="mt-10"></v-text-field>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { notice_get } from "@/store/modules/etcStore";
// import store from "@/store/modules/userStore";

export default {
  name: "NoticePage",
  data() {
    return {
      search: "",
      page: 1,
      pageCount: 5,
      itemsPerPage: 10,
      dataList: [],
      tatalPage: 0,
      data: Object,
      // isAdmin: store.state.admin,
      currentPage: 1,
    };
  },
  computed: {
    ...mapState("userStore", ["accessToken", "admin"]),
  },
  methods: {
    openDetails(val) {
      // 디테일 열기
      this.$router.push({
        name: "noticedetail",
        params: { pid: val.noticeNo },
      });
    },

    async getPage(pid) {
      this.data = await notice_get(this.accessToken, {
        page: pid,
      });
      this.dataList = this.data.page;
      for (let index = 0; index < this.dataList.length; index++) {
        this.dataList[index].noticeDate = this.filter(
          this.dataList[index].noticeDate
        );
      }
      this.pageCount = this.data.totalPage;
      this.page = this.data.currentPage;
    },

    async getKeywordPage(pid, keyword) {
      this.data = await notice_get(this.accessToken, {
        page: pid,
        keyword: keyword,
      });
      this.dataList = this.data.page;
      if (this.data.totalPage < 5) {
        this.pageCount = this.data.totalPage;
      }
    },

    filter(value) {
      var date = new Date(value);

      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var day = date.getDate();

      if (month < 10) {
        month = "0" + month;
      }
      if (day < 10) {
        day = "0" + day;
      }
      return year + "-" + month + "-" + day;
    },

    handlePageChange(value) {
      // 페이지네이션 번호 누를 때
      if (this.search) {
        this.goSearch(value);
      } else {
        this.getPage(value);
      }
    },

    async goSearch(pid) {
      this.data = await notice_get(this.accessToken, {
        page: pid,
        keyword: this.search,
      });
      this.dataList = this.data.page;
      this.pageCount = this.data.totalPage;
      this.page = this.data.currentPage;
    },
  },
  async created() {
    await this.getPage(1);
  },
  components: {},
};
</script>
