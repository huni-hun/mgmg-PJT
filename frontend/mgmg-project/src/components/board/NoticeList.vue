<template>
  <div>
    <!-- 기본적으로 grid 시스템을 기반으로 게시판 배치 -->
    <v-container class="justify-center">
      <v-row class="justify-center">
        <v-col cols="12">
          <!-- 게시판 -->
          <v-data-table
            :headers="headers"
            :items="desserts"
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
export default {
  name: "NoticePage",
  data() {
    return {
      search: "",
      page: 1,
      pageCount: 5,
      itemsPerPage: 5,
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
      desserts: [
        {
          noticeNo: 1,
          noticeTitle: "안녕하세요 제목01입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 2,
          noticeTitle: "안녕하세요 제목02입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 3,
          noticeTitle: "안녕하세요 제목03입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 4,
          noticeTitle: "안녕하세요 제목4입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 5,
          noticeTitle: "안녕하세요 제목5입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 6,
          noticeTitle: "안녕하세요 제목6입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 7,
          noticeTitle: "안녕하세요 제목7입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 8,
          noticeTitle: "안녕하세요 제목8입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 9,
          noticeTitle: "안녕하세요 제목9입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 10,
          noticeTitle: "안녕하세요 제목10입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 11,
          noticeTitle: "안녕하세요 제목11입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 12,
          noticeTitle: "안녕하세요 제목12입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 13,
          noticeTitle: "안녕하세요 제목13입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 14,
          noticeTitle: "안녕하세요 제목14입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 15,
          noticeTitle: "안녕하세요 제목15입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 16,
          noticeTitle: "안녕하세요 제목16입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 17,
          noticeTitle: "안녕하세요 제목17입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 18,
          noticeTitle: "안녕하세요 제목18입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 19,
          noticeTitle: "안녕하세요 제목19입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 20,
          noticeTitle: "안녕하세요 제목20입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 21,
          noticeTitle: "안녕하세요 제목21입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 22,
          noticeTitle: "안녕하세요 제목22입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 23,
          noticeTitle: "안녕하세요 제목23입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 24,
          noticeTitle: "안녕하세요 제목24입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 25,
          noticeTitle: "안녕하세요 제목25입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 26,
          noticeTitle: "안녕하세요 제목26입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 27,
          noticeTitle: "안녕하세요 제목27입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 28,
          noticeTitle: "안녕하세요 제목28입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 29,
          noticeTitle: "안녕하세요 제목29입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 30,
          noticeTitle: "안녕하세요 제목30입니다.",
          noticeDate: "2022.09.22",
        },
      ],
    };
  },
  methods: {
    //     openDetails(val) {
    //   this.$router.push("details/"val.id)
    // }
    openDetails() {
      this.$router.push("/notice/detail");
    },
  },
  components: {},
};
</script>
