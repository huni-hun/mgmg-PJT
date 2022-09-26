<template>
  <div>
    <!-- 기본적으로 grid 시스템을 기반으로 게시판 배치 -->
    <v-container class="justify-center">
      <v-row class="justify-center">
        <v-col cols="12">
          <!-- 게시판 -->
          <v-data-table
            :headers="headers"
            :items="dataList"
            :page="page"
            :items-per-page="itemsPerPage"
            :search="search"
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
          <v-pagination v-model="page" :length="pageCount" class="mt-10"></v-pagination>
        </v-row>
      </v-col>
      <v-row class="justify-center">
        <v-col cols="3">
          <!-- 검색창 -->
          <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line hide-details class="mt-10"></v-text-field>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { notice_get } from "@/store/modules/etcStore";

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
    };
  },
  methods: {
    openDetails(val) {
      this.$router.push("details/" + val.noticeNo);
    },
    // openDetails() {
    //   this.$router.push("/notice/detail");
    // },

    async getPage(pid) {
      console.log("=========공지사항 조회==========");
      this.data = await notice_get({
        page: pid,
      });
      this.dataList = this.data.page;
      this.pageCount = this.data.totalPage;
    },

    async getKeywordPage(pid, keyword) {
      console.log("=========공지사항 조회==========");
      this.data = await notice_get({
        page: pid,
        keyword: keyword,
      });
      this.dataList = this.data.page;
      this.pageCount = this.data.totalPage;
    },
  },
  async created() {
    await this.getPage(1);
    console.log(this.dataList, this.tatalPage);
  },
  components: {},
};
</script>
