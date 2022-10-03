<template>
  <div class="myCheckTotalBody">
    <v-container>
      <div class="mypageFirstBody">
        <MyCheck></MyCheck>
      </div>
    </v-container>
  </div>
</template>

<script>
import MyCheck from "@/components/mypage/MyCheck.vue";
export default {
  components: {
    MyCheck,
  },
};
</script>

<style scoped>
.myCheckTotalBody {
  display: flex;
  justify-content: center;
  margin-top: 2vw;
  width: 100%;
}

</style>
