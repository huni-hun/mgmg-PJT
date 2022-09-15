<template>
  <div>
    <v-container fluid>
      <v-row>
        <v-col cols="1">
          <v-card height="80%">제목</v-card>
        </v-col>
        <v-col cols="11">
          <v-text-field label="Outlined" single-line outlined></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="1">
          <v-card height="100%"> 내용 </v-card>
        </v-col>
        <v-col cols="11">
          <v-textarea name="input-7-1" filled auto-grow value="글 내용작성" solo></v-textarea>
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
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      checkbox: true,
    };
  },
};
</script>

<style></style>
