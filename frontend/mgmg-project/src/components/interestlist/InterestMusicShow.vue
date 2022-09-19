<template>
  <v-container>
    <v-row>
      <v-col>
        <v-container v-for="music in musicLst" :key="music.title">
          <v-row>
            <div class="col-2">image</div>
            <v-col class="col-8">
              <label for="">제목: {{ music.title }}</label> <br />
              <label for="">가수: {{ music.singer }}</label> <br />
              <label for="">작성 일기 날짜: {{ music.date }}</label> <br />
              <label for="">삭제아이콘</label><br />
            </v-col>
          </v-row>
        </v-container>
      </v-col>
      <v-col>
        <div>video</div>
        <div>video detail</div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      musicLst: [
        { title: "title1", singer: "singer1", date: "2022-01-01" },
        { title: "title2", singer: "singer2", date: "2022-02-02" },
        { title: "title3", singer: "singer3", date: "2022-03-03" },
      ],
    };
  },
};
</script>

<style></style>
