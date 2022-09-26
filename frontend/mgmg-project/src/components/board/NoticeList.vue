<template>
  <div>
    <router-link to="/notice/writing">
      <v-btn> 글쓰기 </v-btn>
    </router-link>
    <!-- 기본적으로 grid 시스템을 기반으로 게시판 배치 -->
    <v-container class="justify-center">
      <v-row class="justify-center">
        <v-col cols="12">
          <!-- 게시판  검색 연결 API -> :search="search"-->
          <v-data-table
            :headers="headers"
            :items="dataList"
            :page="page"
            :items-per-page="itemsPerPage"
            hide-default-footer
            :sort-by="['noticeNo']"
            :sort-desc="true"
            @click:row="openDetails"
            class="elevation-1 mt-10"
          >
          </v-data-table>
        </v-col>
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
          <v-text-field v-model="search" @keyup.enter="goSearch(1)" append-icon="mdi-magnify" label="Search" single-line hide-details class="mt-10"></v-text-field>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { notice_get } from "@/store/modules/etcStore";
import store from "@/store/modules/userStore";

export default {
  name: "NoticePage",
  data() {
    return {
      search: "",
      page: 1,
      pageCount: 5,
      itemsPerPage: 10,
      headers: [
        {
          text: "번호",
          align: "center",
          value: "noticeNo",
          sortable: false,
        },
        { text: "제목", align: "center", value: "noticeTitle", sortable: false },
        { text: "등록일", align: "center", value: "noticeDate", sortable: false },
      ],
      dataList: [],
      tatalPage: 0,
      data: Object,
      isAdmin: store.state.admin,
      currentPage: 1,
    };
  },
  methods: {
    openDetails(val) {
      // 디테일 열기
      this.$router.push({ name: "noticedetail", params: { pid: val.noticeNo } });
    },

    async getPage(pid) {
      this.data = await notice_get({
        page: pid,
      });
      this.dataList = this.data.page;
      this.pageCount = this.data.totalPage;
      this.page = this.data.currentPage;
    },

    async getKeywordPage(pid, keyword) {
      this.data = await notice_get({
        page: pid,
        keyword: keyword,
      });
      this.dataList = this.data.page;
      if (this.data.totalPage < 5) {
        this.pageCount = this.data.totalPage;
      }
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
      console.log(`====실행=== :pid ${pid}`);
      this.data = await notice_get({
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
    console.log(this.dataList, this.tatalPage);
  },
  components: {},
};
</script>
