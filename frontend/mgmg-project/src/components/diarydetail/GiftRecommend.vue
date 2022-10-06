<template>
  <div class="outDiv">
    <div class="title">
      <span>이 날의 추천 선물</span>
    </div>
    <div v-if="!isClick" class="giftBoxDiv">
      <span>"click!!"</span>
      <v-icon large>mdi-arrow-down-thin</v-icon>
      <img loading="lazy" class="giftbox" alt="giftbox" src="@/assets/diary/giftbox.png" @click="giftView" />
    </div>
    <div class="gridDiv" v-else>
      <v-img class="productImg" lazy alt="선물 이미지" :src="giftImg" />
      <div class="v-line"></div>
      <div class="outFlexDiv">
        <div class="giftInfo">
          <p>상품명: {{ giftName }}</p>
          <p>가 격: {{ giftPrice }}원</p>
          <p>
            <button @click="clickLink()">
              구매하러가기 <v-icon> mdi-open-in-new </v-icon>
            </button>
          </p>
        </div>
        <label class="ridioButton">
          <input type="checkbox" name="gift" v-model="interestGift" />
          <span class="btnText">관심 선물 추가</span>
        </label>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { giftOpen, giftInterest, detailGift, cancleGift } from "@/api/diary.js";
//detailGift
export default {
  data: function () {
    return {
      isClick: false,

      diaryNo: this.$route.params.no,
      interestGift: false,
      beforeGiftCheck: "",

      giftNo: 0,
      giftImg: "",
      giftName: "",
      giftPrice: "",
      giftLink: "1",
    };
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    async giftView() {
      this.isClick = true;
      await giftOpen(this.accessToken, this.diaryNo)
        .then((res) => {
          this.giftNo = res.giftNo;
          this.giftImg = res.giftImg;
          this.giftName = res.giftName;
          this.giftPrice = res.giftPrice;
          this.giftLink = res.giftUrl;
        })
        .catch((error) => {
          console.log("gift error", error);
        });
    },
    clickLink() {
      window.open(this.giftLink);
    },
  },

  async beforeDestroy() {
    if (this.beforeGiftCheck == this.interestGift) return;

    if (this.interestGift) {
      // "none->관심 선물 추가"
      await giftInterest(this.accessToken, this.giftNo, this.diaryNo);
    } else {
      // "관심 선물 추가 -> none"
      await cancleGift(this.accessToken, this.giftNo);
    }
  },
  async created() {
    await detailGift(this.accessToken, this.diaryNo)
      .then((res) => {
        this.isClick = res.openGift;
        if (this.isClick) {
          this.interestGift = res.checkGift === "good" ? true : false;
          this.beforeGiftCheck = this.interestGift;
          this.giftNo = res.gift.giftNo;
          this.giftImg = res.gift.giftImg;
          this.giftName = res.gift.giftName;
          this.giftPrice = res.gift.giftPrice;
          this.giftLink = res.gift.giftUrl;
        }
      })
      .catch((err) => {
        console.log("선물 데이터 받기 실패", err);
      });
  },
};
</script>

<style scoped src="@/styles/diary/GiftRecommend.css">

</style>
