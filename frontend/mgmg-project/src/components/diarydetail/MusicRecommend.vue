<template>
  <div class="outDiv">
    <span> 이 날의 추천 음악 </span>
    <div class="gridDiv">
      <iframe
        class="player"
        :src="`https://www.youtube.com/embed/${playCode}`"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
      ></iframe>
      <div>
        <div>음악 소개할거임</div>
        <CustomButton class="custombutton" btnText="관심 음악 추가" />
        <CustomButton class="custombutton" btnText="이 음악 비추천" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    playCode: "D6hqryFvcl8",
  }),
  methods: {},
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
  grid-template-columns: 2fr 1fr;
  place-items: center;
  align-items: center;
}
.player {
  width: 80%;
  height: 100%;
  max-height: 600px;
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
