<template>
  <div class="outDiv">
    <span> 이 날의 추천 선물 </span>
    <div v-if="!isClick">
      <img
        width="12%"
        alt="giftbox"
        src="@/assets/diary/giftbox.png"
        @click="isClick = !isClick"
      />
    </div>
    <div class="gridDiv" v-else>
      <div>선물이미지</div>
      <div>
        <p>선물 정보임</p>
        <CustomButton class="custombutton" btnText="관심 선물 추가" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    isClick: false,
  }),
  methods: {
    giftView() {
      this.isClick = true;
    },
  },
};
</script>

<style scoped>
.outDiv {
  border: 1px solid black;
  margin: 10px 0px;
}
.gridDiv {
  width: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 2fr;
  place-items: center;
  align-items: center;
}
.custombutton {
  width: 50%-5px;
  background-color: #ffffff;
  margin: 0px 5px;
}
.custombutton:hover,
.custombutton:focus {
  background-color: #ebb8cd;
  color: white;
  border: none;
  box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25);
}
</style>
