<template>
  <v-container>
    <v-row v-if="0 < giftLst.length">
      <div v-for="(gift, index) in giftLst" :key="gift.name" class="col-3">
        <div>
          <v-img
            :src="gift.gift.giftImg"
            alt="상품사진"
            height="100"
            width="100"
          />
          <div>{{ gift.gift.giftName }}</div>
          <div>가격: {{ gift.gift.giftPrice }}원</div>
          <p>
            <button @click="clickLink(gift.gift.giftUrl)">
              구매하러가기 <v-icon small> mdi-open-in-new </v-icon>
            </button>
          </p>
          <div>작성 일기 날짜: {{ gift.registDate }}</div>
          <div>삭제아이콘</div>
          <v-btn @click="deleteGift(index, gift)">
            <v-icon>mdi-trash-can-outline</v-icon></v-btn
          >
        </div>
      </div>
    </v-row>
    <v-row v-else> 관심 선물 없음 </v-row>
  </v-container>
</template>

<script>
import { getInterestGift, deleteInterestGift } from "@/api/userApi.js";
import Swal from "sweetalert2";

export default {
  data() {
    return {
      giftLst: [],
    };
  },
  async created() {
    await getInterestGift()
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
      await deleteInterestGift(gift.gift.giftNo)
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
  },
};
</script>

<style></style>
