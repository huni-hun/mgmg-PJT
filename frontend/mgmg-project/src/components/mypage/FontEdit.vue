<template>
  <v-container>
    <v-row><label for="">글꼴 변경</label></v-row>
    <v-row><label for="">선택하신 글꼴은 모든 일기에 적용됩니다.</label></v-row>
    <v-row><hr class="hrStyle" /></v-row>
    <v-row>
      <div class="col-4" v-for="font in fontLst" :key="font" name="fontSelect" @click="changeFont(font)">
        <div class="fontLstBox" :class="{ selected: font == selectedFont }">{{ font }}</div>
      </div>
    </v-row>
    <v-row>
      <CustomButton btnText="확인" />
    </v-row>
  </v-container>
</template>

<script>
import CustomButton from "../common/CustomButton.vue";
export default {
  data() {
    return {
      fontLst: ["폰트1", "폰트2", "폰트3", "폰트4", "폰트5", "폰트6", "폰트7", "폰트8", "폰트9", "폰트10", "폰트11", "폰트12"],
      selectedFont: "",
    };
  },
  methods: {
    changeFont(new_font) {
      this.selectedFont = new_font;
    },
  },
  components: { CustomButton },
};
</script>

<style scoped>
.hrStyle {
  border: 0.01rem solid #000000;
  width: 80rem;
}
.fontLstBox {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25);
}
.selected {
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25), inset 3px 3px 4px 3px rgba(0, 0, 0, 0.38);
}
</style>
