<template>
  <div>
    <v-card>
      <v-data-table :headers="headers" :items="desserts" :page="page" :items-per-page="itemsPerPage" :search="search" hide-default-footer class="elevation-1"> </v-data-table>
      <v-pagination v-model="page" :length="pageCount"></v-pagination>
      <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line hide-details></v-text-field>
    </v-card>
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
        },
        { text: "제목", align: "center", value: "noticeTitle" },
        { text: "등록일", align: "center", value: "noticeDate" },
      ],
      desserts: [
        {
          noticeNo: 1,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 2,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 3,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 4,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 5,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 6,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 7,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 8,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 9,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 10,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 11,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 12,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 13,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 14,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 15,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 16,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 17,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 18,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 19,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 20,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 21,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 22,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 23,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 24,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 25,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 26,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 27,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 28,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 29,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
        {
          noticeNo: 30,
          noticeTitle: "안녕하세요 제목입니다.",
          noticeDate: "2022.09.22",
        },
      ],
    };
  },
  components: {},
};
</script>

<style></style>
