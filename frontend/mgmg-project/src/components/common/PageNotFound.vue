<template>
  <div>
    <!-- 404 페이지 -->
    <div class="divImg">
      <img :src="require(`@/assets/pagenotfound/pagenotfound.png`)" alt="" />
    </div>
  </div>
</template>

<script>
export default {
  name: "PageNotFound",
  components: {},
  data() {
    return {
      sampleData: "",
    };
  },
};
</script>

<style scoped>
.divImg {
  height: 80%;
  width: 80%;
}
</style>
