<template>
  <v-container>
    <div class="mainpageBody">
      <div>
        <v-spacer></v-spacer>
        <label class="mainpageTitle">날짜를 클릭해서 일기를 써보세요.</label>
        <v-spacer></v-spacer>
      </div>
      <Calendar class="mainpageCalendar" />
    </div>
  </v-container>
</template>

<script>
import Calendar from "@/components/common/CustomCalendar.vue";
export default {
  components: {
    Calendar,
  },
};
</script>

<style scoped>
.mainpageBody {
  /* display: flex;
  flex-direction: column;
  justify-content: center; */
  margin: 0;
  font-display: center;
}
/* @media (max-width: 1023px) {
  .body {
    width: 80vw;
    height: 90vh;
    margin: 0;
  }
}
@media (max-width: 767px) {
  .body {
    width: 80vw;
    height: 90vh;
    margin: 0;
  }
}
@media (max-width: 638px) {
  .body {
    width: 80vw;
    height: 90vh;
    margin: 0;
  }
} */
.mainpageCalendar {
  width: 100%;
  padding: 2rem;
}
.mainpageTitle {
  display: block;
  width: 100%;
  font-size: 1.5rem;
  margin-bottom: 1%;
}
</style>
