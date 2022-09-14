//giftSurver.vue
<template>
  <div>
    <v-container>
      <v-row>관심 선물</v-row>
      <v-row>좋아하는 선물 종류를 선택하세요. 최소 1개, 최대 5개까지 선택할 수 있습니다.</v-row>
      <v-row><hr class="hrStyle" /></v-row>
      <v-row>
        <div class="col-3" v-for="gift in giftLst" :key="gift" @click="addGift(gift)">
          <div id="circle" class="giftLstBox" :class="{ selected: selectedGift.includes(gift) }"></div>
          <div>{{ gift }}</div>
        </div>
      </v-row>
      <v-row>
        <v-col>
          <label for="">가격대</label>
          <CustomInput v-model="priceUnder" />
          <CustomInput v-model="priceUpper" />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  props: ["selectGift"],

  data() {
    return {
      giftLst: ["패션의류", "패션잡화", "화장품/미용", "디지털/가전", "가구/인테리어", "출산/육아", "식품", "스포츠/레저", "생활/건강", "여가/생활편의"],
      selectedGift: [],
      // this.$store.state.userStore.interestGiftLstSignup,

      priceUnder: {
        labelText: "하한가",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "영어로 시작, 영어 숫자 포함 6자 이상 16자 이하",
      },
      priceUpper: {
        labelText: "상한가",
        rules: [(v) => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || "특수문자 사용불가"],
        hint: "영어로 시작, 영어 숫자 포함 6자 이상 16자 이하",
      },
    };
  },
  methods: {
    // 선물 선택. 선택리스트에 없으면 추가, 있으면 제거
    addGift(gift) {
      if (this.selectedGift.includes(gift)) {
        this.selectedGift.splice(this.selectedGift.indexOf(gift), 1);
      } else if (this.selectedGift.length >= 5) {
        return;
      } else {
        this.selectedGift.push(gift);
      }
      this.$emit("selectGifts", this.selectedGift);
    },
  },
};
</script>

<style scoped>
.hrStyle {
  width: 50rem;
}
.giftLstBox {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25);
}
.selected {
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25), inset 3px 3px 4px 3px rgba(0, 0, 0, 0.38);
}
#circle {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: rgb(156, 156, 156);
}
</style>
