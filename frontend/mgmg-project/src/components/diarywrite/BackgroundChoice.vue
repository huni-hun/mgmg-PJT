<template>
  <div class="outDiv">
    <span>배경지 선택</span>
    <div class="scroll-image">
      <div v-for="item in backImgs" :key="item" class="choiceImg">
        <v-img
          :src="require(`@/assets/diary/choice/${item}.png`)"
          @click="imgClick(item)"
        />
      </div>
    </div>
  </div>
</template>

<script>
import eventBus from "./eventBus.js";
export default {
  data: () => ({
    backImgs: ["blackLine", "blueLine", "blueCheck", "pinkCheck"],
  }),
  methods: {
    imgClick(props) {
      // console.log(props);
      eventBus.$emit("backImgChoice", props);
    },
  },
};
</script>

<style scoped>
.outDiv {
  background-color: rgba(255, 255, 255, 0.7);
  border: 1px solid black;
  border-radius: 10px;
  padding: 5px 2vw;
  margin-bottom: 10px;
}
.scroll-image {
  display: flex;
  align-items: flex-start;
  margin: 10px 0px;
  overflow-x: scroll;
  white-space: nowrap;
}
.scroll-image > .choiceImg {
  width: calc(10%);
  margin: 0 15px 15px 0;
}
.scroll-image > .choiceImg:nth-of-type(20n),
.scroll-image > .choiceImg:last-child {
  margin-right: 0;
}
@media screen and (max-width: 480px) {
  .scroll-image {
    flex-wrap: nowrap;
    justify-content: flex-start;
    position: relative;
    width: 100%;
    left: -4%;
    padding: 0 7%;
    overflow-y: hidden;
    overflow-x: auto;
    -ms-overflow-style: none;
    -webkit-overflow-scrolling: touch;
  }
  .scroll-image > .choiceImg {
    display: block;
    flex: 0 0 auto;
    width: 40%;
    margin: 0 15px 15px 0;
  }
  .scroll-image > .choiceImg:last-of-type {
    margin-right: 0;
  }
  .scroll-image:after {
    content: "";
    display: block;
    flex: 0 0 auto;
    align-self: stretch;
    width: 7%;
  }
}
</style>
