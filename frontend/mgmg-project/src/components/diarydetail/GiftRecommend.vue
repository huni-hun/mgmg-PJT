<template>
  <div class="outDiv">
    <p>이 날의 추천 선물</p>
    <div v-if="!isClick" class="giftBoxDiv">
      <img loading="lazy" alt="giftbox" src="@/assets/diary/giftbox.png" @click="giftView" />
    </div>
    <div class="gridDiv" v-else>

      <v-img alt="선물 이미지" :src=giftImg />

      <div>
        <p>상품명: {{giftName}}</p>
        <p>가 격: {{giftPrice}}</p>
        <p>
          <button @click="clickLink()">
            구매하러가기 <v-icon small> mdi-open-in-new </v-icon>
          </button>
        </p>

        <label class="ridioButton">
          <input type="checkbox" name="gift" value="" v-model="interestGift">
          <span class="btnText">관심 선물 추가</span>
        </label>
      </div>
    </div>
  </div>
</template>

<script>
import { giftOpen, giftInterest } from "@/api/diary.js";
export default {
  data: function () {
    return {
      isClick: false,

      diaryNo: this.$route.params.no,
      interestGift: "",

      giftImg: "",
      giftName: "",
      giftPrice: "",
      giftLink: "1",
    }
  },
  methods: {
    async giftView() {
      this.isClick = true;
      await giftOpen(this.diaryNo).then((res) => {
        console.log("gift success", res);
        this.giftImg = res.giftImg;
        this.giftName = res.giftName;
        this.giftPrice = res.giftPrice;
        this.giftLink = res.giftUrl;
      }).catch((error) => {
        console.log("gift error", error);
      })
    },
    clickLink() {
      window.open(this.giftLink);
    },
  },
  // eslint-disable-next-line vue/no-deprecated-destroyed-lifecycle
  async beforeDestroy() {
    // 선물 추천 받아오고 실행
    console.log("선물추천", this.interestGift)
    if (this.interestGift) {
      console.log("interest 선택");
      await giftInterest(this.giftNo);
    }
  },
  created() {
    let test = false;
    this.isClick = test;
  }
};
</script>

<style scoped>
.outDiv {
  border: 1px solid black;
  margin: 10px 0px;
  padding: 10px;
}

.giftBoxDiv {
  display: flex;
  justify-content: center;
  align-items: center;
}

.giftBoxDiv>img {
  width: 12%;
}

.gridDiv {
  width: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 2fr));
  place-items: center;
  align-items: center;
}

.ridioButton input[type="checkbox"] {
  display: none;
}

.ridioButton input[type="checkbox"]+span {
  display: inline-block;
  padding: 10px 10px;
  background-color: #ffffff;
  border: 1px solid #000000;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border-radius: 10px;
  text-align: center;
  cursor: pointer;
  width: 50%-10px;
  margin: 0px 5px;
  color: #000000;
}

.ridioButton input[type="checkbox"]:checked+span {
  background-color: #ebb8cd;
  color: white;
  border: none;
  box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.ridioButton span {
  font-size: clamp(0.5rem, 1.2vw, 1rem);
}
</style>
