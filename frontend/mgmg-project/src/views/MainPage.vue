<template>
  <v-container>
    <div>
      <!-- <h1>MainPage입니다</h1> -->
      <Calendar class="calendar" />
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
.body {
  width: 80vw;
  margin: 0;
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
.calendar {
  width: 100%;
  padding: 2rem;
}
</style>
