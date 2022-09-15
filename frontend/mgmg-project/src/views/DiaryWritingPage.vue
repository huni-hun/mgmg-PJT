<template>
  <div>
    <h1>DiaryWritingPage입니다.</h1>
    <div style="width: 500px;">
      <CustomInput v-model="date" />
    </div>
  </div>
</template>

<script>
import CustomInput from "../components/common/CustomInput.vue";
export default {
  components: { CustomInput },
  data() {
    return {
      date: {
        labelText: "일기쓰기 input입니다",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "특수문자 없이 입력해주세요",
      },
    };
  },
};
</script>

<style></style>