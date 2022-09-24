<template>
  <div>
    <div>
      <h1>LandingPage입니다.</h1>
    </div>
    <div>
      <p>
        <router-link :to="`/diarywriting/${date}`">일기 작성</router-link>
        <v-date-picker v-model="date" no-title></v-date-picker>
      </p>

      <hr />

      <p>
        <router-link :to="`/diarydetail/${no}`">일기 상세</router-link>
        <v-text-field v-model="no" label="일기번호 입력" :value="no" />
      </p>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    date: new Date().toISOString(),
    no: null,
  }),

  mounted() {
    var date = new Date();
    var year = date.getFullYear();
    var month = ("0" + (1 + date.getMonth())).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);

    this.date = year + "-" + month + "-" + day;
  },
};
</script>

<style></style>
