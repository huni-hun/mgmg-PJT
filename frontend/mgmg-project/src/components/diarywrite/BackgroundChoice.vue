<template>
  <div class="outDiv">
    <span>배경지 선택</span>
    <div class="scroll-image">
      <div v-for="item in backImgs" :key="item" class="choiceImg">
        <v-img :src="require(`@/assets/diary/choice/${item}.png`)" @click="imgClick(item)" />
      </div>
    </div>
  </div>
</template>

<script>
import eventBus from "./eventBus.js";
export default {
  data: () => ({
    backImgs: ["noteMg", "noteClip", "blueCheck", "pinkCheck", "springBlue", "springYellow", "springGreen", "springPurple", "flowerYellow", "pullGreen", "nightOwl"],
  }),
  methods: {
    imgClick(props) {
      eventBus.$emit("backImgChoice", props);
    },
  },
};
</script>




<style scoped src="@/styles/diary/BackgroundChoice.css"/>
