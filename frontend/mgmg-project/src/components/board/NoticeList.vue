<template>
  <div>
    <v-row
      style="
        margin-top: 5px;
        background-color: rgba(0, 0, 0, 0.15);
        border-top: 1px solid #444444;
        border-bottom: 1px solid #444444;
        display: flex;
        flex-direction: row;
        margin-top: 1vh;
      "
    >
      <v-col class="white--text notice-num">번호</v-col>
      <v-col class="white--text notice-title">제목</v-col>
      <v-col class="white--text notice-date">작성일자</v-col>
    </v-row>
    <v-row
      v-for="notice in dataList"
      :key="notice.noticeNo"
      style="display: flex; flex-direction: row"
    >
      <v-col
        class="notice-num"
        v-if="notice.fixedFlag"
        style="
          background-color: rgba(226, 231, 255, 0.5);
          border-bottom: 1px solid #444444;
          text-decoration: #173261;
        "
        >공지</v-col
      >
      <v-col
        class="notice-num"
        v-else
        style="
          background-color: rgba(255, 255, 255, 0.5);
          border-bottom: 1px solid #444444;
        "
        >{{ notice.noticeNo }}</v-col
      >
      <v-col
        class="notice-title"
        :style="
          notice.fixedFlag
            ? `background-color: rgba(226, 231, 255, 0.5); border-bottom: 1px solid #444444;`
            : `background-color: rgba(255, 255, 255, 0.5); border-bottom: 1px solid #444444; `
        "
        @click="openDetails(notice)"
        >{{ notice.noticeTitle }}</v-col
      >
      <v-col
        class="notice-date"
        :style="
          notice.fixedFlag
            ? `background-color: rgba(226, 231, 255, 0.5); border-bottom: 1px solid #444444;`
            : `background-color: rgba(255, 255, 255, 0.5); border-bottom: 1px solid #444444;`
        "
        >{{ notice.noticeDate }}</v-col
      >
    </v-row>

    <v-col cols="12">
      <v-row class="justify-center">
        <v-pagination
          v-model="page"
          :length="pageCount"
          @input="handlePageChange"
          class="mt-10"
        ></v-pagination>
      </v-row>
    </v-col>
    <v-row class="justify-center" style="display: flex; flex-direction: row">
      <!-- <v-col cols="3"> -->
      <div style="flex: 1"></div>
      <div class="search">
        <v-text-field
          v-model="search"
          @keyup.enter="goSearch(1)"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
          class="mt-10"
        ></v-text-field>
      </div>
      <div style="flex: 1"></div>

      <!-- </v-col> -->
    </v-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { notice_get } from "@/store/modules/etcStore";

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
      currentPage: 1,
    };
  },
  computed: {
    ...mapState("userStore", ["accessToken", "admin"]),
  },
  methods: {
    openDetails(val) {
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
      for (let index = 0; index < this.dataList.length; index++) {
        this.dataList[index].noticeDate = this.filter(
          this.dataList[index].noticeDate
        );
      }
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
<style scoped>
.notice-btn {
  text-align: right;
  color: #ffffff;
  font-size: clamp(0.9rem, 1vw, 1.3rem);
}
.notice-num {
  text-align: center;
  flex: 1;
}
.notice-title {
  text-align: center;
  flex: 9;
}
.notice-date {
  text-align: center;
  flex: 2;
}
.search {
  flex: 2;
}
@media screen and (max-width: 650px) {
  .notice-date {
    flex: 3;
  }
  .search {
    flex: 5;
  }
}

@media screen and (max-width: 450px) {
  .notice-date {
    display: none;
  }
}
</style>
