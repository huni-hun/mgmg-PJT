<template>
  <div class="interestGiftShowTotalBody">
    <div class="interestGiftBody" v-if="0 < giftLst.length">
      <div v-for="(gift, index) in giftLst" :key="gift.name" class="giftCard">
        <div>
          <div class="img-wrapper">
            <img style="width: 100%; height: 100%" :src="gift.gift.giftImg" alt="" />
          </div>
          <!-- <v-img :src="gift.gift.giftImg" alt="상품사진" height="100" width="100" /> -->
          <div class="giftNameLabel">{{ tooLongName(gift.gift.giftName) }}</div>
          <div>{{ gift.gift.giftPrice }}원</div>
          <p>
            <button @click="clickLink(gift.gift.giftUrl)">구매하러가기 <v-icon small> mdi-open-in-new </v-icon></button>
          </p>
          <div class="giftCardNamePrice">
            <div>
              <div>일기 작성 날짜</div>
              <div>{{ gift.registDate }}</div>
            </div>
            <!-- <v-btn @click="deleteGift(index, gift)"> -->
            <v-icon @click="deleteGift(index, gift)">mdi-trash-can-outline</v-icon>
            <!-- </v-btn> -->
          </div>
        </div>
      </div>
    </div>
    <div class="noGift" v-else>
      <img class="noGiftImg" src="@/assets/emoticon/sad.png" alt="" />
      <div>관심 선물이 없어요.</div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { getInterestGift, deleteInterestGift } from "@/api/userApi.js";
import Swal from "sweetalert2";

export default {
  data() {
    return {
      // giftLst: [
      //   { name: "장갑", gift: { giftName: "장갑", giftImg: "https://media3.giphy.com/media/H44Gd0GTIzhDsk4TBC/giphy.gif", giftPrice: 12000, giftUrl: "naver.com" }, registDate: "2022-01-11" },
      //   ],
      giftLst: [],
    };
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  async created() {
    await getInterestGift(this.accessToken)
      .then((res) => {
        this.giftLst = res.gifts;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    clickLink(link) {
      window.open(link);
    },
    async deleteGift(index, gift) {
      await deleteInterestGift(this.accessToken, gift.gift.giftNo)
        .then((res) => {
          console.log(res);
          this.giftLst.splice(index, 1);
          Swal.fire({
            text: "관심선물에서 삭제했습니다.",
            icon: "success",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    tooLongName(longGiftName) {
      var shortGiftName;
      if (longGiftName.length > 10) {
        shortGiftName = longGiftName.substr(0, 10) + "…";
      } else {
        shortGiftName = longGiftName;
      }
      return shortGiftName;
    },
  },
};
</script>

<style scoped>
.interestGiftShowTotalBody {
  background-color: white;
  box-shadow: 0px 0px 20px 20px rgba(23, 50, 97, 0.2);
  border-radius: 10px;
  padding: 2% 5%;
  height: 70vh;
  overflow: auto;
}
.interestGiftShowTotalBody::-webkit-scrollbar {
  display: none;
}
.interestGiftBody {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: start;
  margin: 1% 0;
}
.giftCard {
  width: 20%;
  padding: 2%;
  border-bottom: 2px solid #d3d3d3;
  background-color: white;
}
.giftCardNamePrice {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.giftNameLabel {
  font-size: clamp(1.6rem, 3vw, 2rem);
  display: block;
  margin-bottom: 1%;
  white-space: nowrap;
}
.noGift {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
}
.noGiftImg {
  width: 9.3vw;
}
.img-wrapper {
  position: relative;
  overflow: hidden;
  height: 100%;
  padding-bottom: 100%;
}
.img-wrapper img {
  position: absolute;
  display: block;
  min-width: 100%;
  min-height: 100%;
  -ms-interpolation-mode: bicubic;
  top: 0;
  left: 0;
  transform: translate(50, 50);
  width: 100%;
  height: 100%;
  object-fit: cover;
  margin: 5% 0;
}
@media (max-width: 767px) {
  .giftCard {
    width: 33%;
  }
}
@media (max-width: 639px) {
  .giftCard {
    width: 50%;
  }
  .noGiftImg {
    width: 25vw;
  }
}
@media (max-width: 575px) {
  .giftCard {
    width: 100%;
    padding: 0 25%;
  }
}
@media (max-width: 350px) {
  .giftCard {
    width: 100%;
    padding: 0 10%;
  }
}
</style>
