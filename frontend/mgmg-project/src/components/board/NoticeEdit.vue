<template>
  <div>
    <v-container fluid>
      <v-row>
        <v-col cols="1">
          <v-card height="80%">제목</v-card>
        </v-col>
        <v-col cols="11">
          <v-text-field label="Outlined" v-model="title" single-line outlined></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="1">
          <v-card height="100%"> 내용 </v-card>
        </v-col>
        <v-col cols="11">
          <v-textarea name="input-7-1" v-model="content" filled auto-grow value="글 내용작성" solo></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="1">
          <v-card height="80%">설정</v-card>
        </v-col>
        <v-col cols="11">
          <v-card>
            <v-checkbox v-model="checkbox" :label="`중요 공지로 등록: ${checkbox.toString()}`"></v-checkbox>
          </v-card>
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-btn @click="sendPost"> 등록 </v-btn>
        <v-btn @click="goBack"> 취소 </v-btn>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { notice_detail_put, notice_detail_get } from "@/store/modules/etcStore";
export default {
  data() {
    return {
      title: "",
      content: "",
      checkbox: true,
    };
  },
  async created() {
    this.noticeData = await notice_detail_get(this.$route.params.pid);
    this.title = this.noticeData.noticeTitle;
    this.content = this.noticeData.noticeContent;
    this.checkbox = this.noticeData.fixedFlag;
  },
  methods: {
    sendPost() {
      notice_detail_put(this.$route.params.pid, {
        noticeTitle: this.title,
        noticeContent: this.content,
        fixedFlag: this.checkbox,
      }).then(() => {
        this.$router.push(`/notice/detail/${this.$route.params.pid}`);
      });
    },
    goBack() {
      this.$router.push(`/notice/detail/${this.$route.params.pid}`);
    },
  },
};
</script>

<style></style>
