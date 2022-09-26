<template>
  <div class="outDiv">
    <p>이 날의 추천 선물</p>
    <div v-if="!isClick" class="giftBoxDiv">
      <img alt="giftbox" src="@/assets/diary/giftbox.png" @click="giftView" />
    </div>
    <div class="gridDiv" v-else>
      <div>{{giftImg}}</div>
      <div>
        <p>상품명: {{giftName}}</p>
        <p>가 격: {{giftPrice}}</p>
        <p>구매하러가기 {{giftLink}}</p>

        <label class="ridioButton">
          <input type="radio" name="gift" value="interest" v-model="interestGift">
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

      giftNo: this.$route.params.no,
      interestGift: "",

      giftImg: "선물 이미지",
      giftName: "심플 챌린지 자수 레터링 5컬러 데일리 볼캡 여성 캡모자",
      giftPrice: "10,700",
      giftLink: "링크주소",
    }
  },
  methods: {
    async giftView() {
      this.isClick = true;
      await giftOpen(this.giftNo).then((res) => {
        console.log("gift success", res);
      }).catch((error) => {
        console.log("gift error", error);
      })
    },
  },
  // eslint-disable-next-line vue/no-deprecated-destroyed-lifecycle
  async beforeDestroy() {
    // 선물 추천 받아오고 실행
    if (this.interestGift == "interest") {
      console.log("interest 선택");
      await giftInterest(this.giftNo);
    }
  },
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

.ridioButton input[type="radio"] {
  display: none;
}

.ridioButton input[type="radio"]+span {
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

.ridioButton input[type="radio"]:checked+span {
  background-color: #ebb8cd;
  color: white;
  border: none;
  box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.ridioButton span {
  font-size: clamp(0.5rem, 1.2vw, 1rem);
}
</style>
