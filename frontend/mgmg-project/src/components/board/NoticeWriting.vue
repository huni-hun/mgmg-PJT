<template>
  <div>
    <v-container fluid>
      <v-row>
        <v-col cols="2">
          <v-card
            height="80%"
            style="
              background-color: rgba(243, 245, 254, 0.75);
              /* border-top: 1px solid #444444;
              border-bottom: 1px solid #444444; */
              text-align: center;
              align-items: center;
            "
            >제목</v-card
          >
        </v-col>
        <v-col cols="8">
          <v-text-field
            style="padding-top: 8px; width: 100%"
            background-color="rgba(255, 255, 255, 0.5)"
            v-model="title"
            outlined
            dense
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="2">
          <v-card
            height="100%"
            style="
              background-color: rgba(243, 245, 254, 0.75);
              /* border-top: 1px solid #444444;
              border-bottom: 1px solid #444444; */
              text-align: center;
              align-items: center;
            "
          >
            내용
          </v-card>
        </v-col>
        <v-col>
          <v-textarea
            style="padding-top: 20px; width: 80%"
            name="input-7-1"
            v-model="content"
            background-color="rgba(255, 255, 255, 0.5)"
            outlined
            dense
            filled
            auto-grow
            value="글 내용작성"
          ></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="2">
          <v-card
            height="90%"
            style="
              background-color: rgba(243, 245, 254, 0.75);
              /* border-top: 1px solid #444444;
              border-bottom: 1px solid #444444; */
              text-align: center;
              align-items: center;
            "
            >설정</v-card
          >
        </v-col>
        <v-col cols="8">
          <v-card
            height="80%"
            style="
              background-color: rgba(255, 255, 255, 0.5);
              /* border-top: 1px solid #444444;
              border-bottom: 1px solid #444444; */
              text-align: center;
              align-items: center;
            "
          >
            <v-checkbox
              v-model="checkbox"
              label="중요 공지로 등록"
            ></v-checkbox>
          </v-card>
        </v-col>
      </v-row>
      <v-row style="display: flex; justify-content: center">
        <v-btn style="margin-right: 10px" @click="sendPost"> 등록 </v-btn>
        <v-btn style="margin-left: 10px; margin-right: 20px" @click="goBack">
          취소
        </v-btn>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { notice_post } from "@/store/modules/etcStore";
export default {
  data() {
    return {
      title: "",
      content: "",
      checkbox: true,
    };
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    sendPost() {
      notice_post(this.accessToken, {
        noticeTitle: this.title,
        noticeContent: this.content,
        fixedFlag: this.checkbox,
      }).then(() => {
        this.$router.replace("/notice");
      });
    },
    goBack() {
      this.$router.replace("/notice");
    },
  },
};
</script>

<style></style>
