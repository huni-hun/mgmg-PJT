<template>
  <div>
    <v-container>
      <v-row class="giftSurveyTitle">관심 선물</v-row>
      <v-row>좋아하는 선물 종류를 선택하세요. 최소 1개, 최대 5개까지 선택할 수 있습니다.</v-row>
      <v-row>
        <hr class="hrStyle" />
      </v-row>
      <!-- 웹화면 -->
      <div class="giftBody pcView">
        <div class="giftBodyFirstLine">
          <div class="justify_content_center_col giftImgBox" v-for="(gift, index) in giftLst.slice(0, 5)" :key="index"
            @click="addGift(gift)">
            <div class="">
              <img class="giftLstBox, circle" :class="{ selected: selectedGift.includes(gift) }"
                :src="require(`../../assets/giftlist/${giftImgName[index]}.png`)" alt="" />
            </div>
            <div class="giftName">{{ gift }}</div>
          </div>
        </div>
        <div class="giftBodySecondLine">
          <div class="justify_content_center_col giftImgBox" v-for="(gift, index) in giftLst.slice(5, 10)" :key="index"
            @click="addGift(gift)">
            <div class="">
              <img class="giftLstBox, circle" :class="{ selected: selectedGift.includes(gift) }"
                :src="require(`../../assets/giftlist/${giftImgName[index + 5]}.png`)" alt="" />
            </div>
            <div class="giftName">{{ gift }}</div>
          </div>
        </div>
      </div>
      <!-- 모바일화면 -->
      <div class="justify_content_center_col">
        <div class="giftBody phoneView">
          <div class="giftBodyFirstLine">
            <div class="justify_content_center_col giftImgBox" v-for="(gift, index) in giftLst.slice(0, 2)" :key="index"
              @click="addGift(gift)">
              <div class="">
                <img class="giftLstBox, circle" :class="{ selected: selectedGift.includes(gift) }"
                  :src="require(`../../assets/giftlist/${giftImgName[index]}.png`)" alt="" />
              </div>
              <div class="giftName">{{ gift }}</div>
            </div>
          </div>
          <div class="giftBodySecondLine">
            <div class="justify_content_center_col giftImgBox" v-for="(gift, index) in giftLst.slice(2, 4)" :key="index"
              @click="addGift(gift)">
              <div class="">
                <img class="giftLstBox, circle" :class="{ selected: selectedGift.includes(gift) }"
                  :src="require(`../../assets/giftlist/${giftImgName[index + 2]}.png`)" alt="" />
              </div>
              <div class="giftName">{{ gift }}</div>
            </div>
          </div>
          <div class="giftBodySecondLine">
            <div class="justify_content_center_col giftImgBox" v-for="(gift, index) in giftLst.slice(4, 6)" :key="index"
              @click="addGift(gift)">
              <div class="">
                <img class="giftLstBox, circle" :class="{ selected: selectedGift.includes(gift) }"
                  :src="require(`../../assets/giftlist/${giftImgName[index + 4]}.png`)" alt="" />
              </div>
              <div class="giftName">{{ gift }}</div>
            </div>
          </div>
          <div class="giftBodySecondLine">
            <div class="justify_content_center_col giftImgBox" v-for="(gift, index) in giftLst.slice(6, 8)" :key="index"
              @click="addGift(gift)">
              <div class="">
                <img class="giftLstBox, circle" :class="{ selected: selectedGift.includes(gift) }"
                  :src="require(`../../assets/giftlist/${giftImgName[index + 6]}.png`)" alt="" />
              </div>
              <div class="giftName">{{ gift }}</div>
            </div>
          </div>
          <div class="giftBodySecondLine">
            <div class="justify_content_center_col giftImgBox" v-for="(gift, index) in giftLst.slice(8, 10)"
              :key="index" @click="addGift(gift)">
              <div class="">
                <img class="giftLstBox, circle" :class="{ selected: selectedGift.includes(gift) }"
                  :src="require(`../../assets/giftlist/${giftImgName[index + 8]}.png`)" alt="" />
              </div>
              <div class="giftName">{{ gift }}</div>
            </div>
          </div>
        </div>
      </div>

      <!-- 가격대 영역 -->
      <div class="priceBody">
        <div class="priceTitle">
          <label for="">가격대</label>
        </div>
        <input class="priceFontSize" type="number" id="underPrice" name="underPrice" :value="underPrice" step="1000"
          min="10000" :max="upperPrice - 1000" @change="changeUnder()" />
        <div class="priceTitle">
          <label for="">~</label>
        </div>
        <input class="priceFontSize" type="number" id="upperPrice" name="upperPrice" :value="upperPrice" step="1000"
          :min="underPrice" max="1000000" @change="changeUpper()" />
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  props: ["selectGift", "userUpperPrice", "userUnderPrice"],
  mounted() {
    this.giftSurveyMounted();
    this.changeUnderMounted();
    this.changeUpperMounted();
  },

  data() {
    return {
      underPrice: 10000,
      upperPrice: 20000,

      giftImgSrc: ["../../assets/giftlist/gift_1_clothes.png"],
      giftImgName: ["gift_1_clothes", "gift_2_things", "gift_3_cosmetic", "gift_4_digital", "gift_5_furniture", "gift_6_baby", "gift_7_food", "gift_8_sports", "gift_9_healthy", "gift_10_life"],

      giftLst: ["패션의류", "패션잡화", "화장품/미용", "디지털/가전", "가구/인테리어", "출산/육아", "식품", "스포츠/레저", "생활/건강", "여가/생활편의"],
      selectedGift: ["패션의류"],
    };
  },
  methods: {
    // 가격대 입력받기
    changeUnderMounted() {
      this.$emit("underPriceSignup", this.underPrice);
    },
    changeUpperMounted() {
      this.$emit("upperPriceSignup", this.upperPrice);
    },
    changeUnder() {
      this.underPrice = Number(document.getElementById("underPrice").value);
      this.upperPrice = Number(document.getElementById("upperPrice").value);
      if (this.underPrice >= this.upperPrice) {
        this.underPrice = this.upperPrice - 1000;
      }
      if (this.underPrice > 1000000) {
        this.underPrice = 990000;
      } else if (this.underPrice < 10000) {
        this.underPrice = this.upperPrice - 1000;
      }
      this.$emit("underPriceSignup", this.underPrice);
    },
    changeUpper() {
      this.underPrice = Number(document.getElementById("underPrice").value);
      this.upperPrice = Number(document.getElementById("upperPrice").value);
      if (this.underPrice >= this.upperPrice) {
        this.upperPrice = this.underPrice + 1000;
      }
      if (this.upperPrice > 1000000) {
        this.upperPrice = 1000000;
      } else if (this.upperPrice < 10000) {
        this.upperPrice = this.underPrice + 1000;
      }
      this.$emit("upperPriceSignup", this.upperPrice);
    },
    // 선물 선택. 선택리스트에 없으면 추가, 있으면 제거
    giftSurveyMounted() {
      this.$emit("selectGifts", this.selectedGift);
    },
    addGift(gift) {
      if (this.selectedGift.includes(gift)) {
        if (this.selectedGift.length == 1) {
          return;
        }
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
  width: 100%;
}

.giftSurveyTitle {
  font-size: clamp(1.2rem, 2.5vw, 2rem);
}

.giftBody {
  margin: 5% 0%;
}

.giftBodyFirstLine {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}

.giftBodySecondLine {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  margin-top: 2%;
}

.justify_content_center_col {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
}

.giftImgBox {
  width: 15%;
}

.giftName {
  text-align: center;
}

.giftLstBox {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25);
}

.selected {
  box-shadow: 0px 0px 7px 7px rgba(54, 54, 54, 0.532), inset 3px 3px 4px 3px rgba(0, 0, 0, 0.38);
}

.circle {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background-color: rgb(156, 156, 156);
}

.priceBody {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.priceTitle {
  margin: 0 3%;
  display: block;
  font-size: clamp(1rem, 2vw, 1.5rem);
}

.priceFontSize {
  font-size: clamp(1rem, 2vw, 1.5rem);
  width: 20%;
  text-align: center;
}

.phoneView {
  display: none;
}

@media (max-width: 724px) {
  .giftImgBox {
    width: 18%;
  }
}

@media (max-width: 639px) {
  .giftBody {
    margin: 10% auto;
  }

  .giftImgBox {
    width: 40%;
  }

  /* 2열 배치 */
  .pcView {
    display: none;
  }

  .phoneView {
    display: inline-block;
  }

  .giftBodySecondLine {
    margin-top: 10%;
  }
}

.col-xl,
.col-xl-auto,
.col-xl-12,
.col-xl-11,
.col-xl-10,
.col-xl-9,
.col-xl-8,
.col-xl-7,
.col-xl-6,
.col-xl-5,
.col-xl-4,
.col-xl-3,
.col-xl-2,
.col-xl-1,
.col-lg,
.col-lg-auto,
.col-lg-12,
.col-lg-11,
.col-lg-10,
.col-lg-9,
.col-lg-8,
.col-lg-7,
.col-lg-6,
.col-lg-5,
.col-lg-4,
.col-lg-3,
.col-lg-2,
.col-lg-1,
.col-md,
.col-md-auto,
.col-md-12,
.col-md-11,
.col-md-10,
.col-md-9,
.col-md-8,
.col-md-7,
.col-md-6,
.col-md-5,
.col-md-4,
.col-md-3,
.col-md-2,
.col-md-1,
.col-sm,
.col-sm-auto,
.col-sm-12,
.col-sm-11,
.col-sm-10,
.col-sm-9,
.col-sm-8,
.col-sm-7,
.col-sm-6,
.col-sm-5,
.col-sm-4,
.col-sm-3,
.col-sm-2,
.col-sm-1,
.col,
.col-auto,
.col-12,
.col-11,
.col-10,
.col-9,
.col-8,
.col-7,
.col-6,
.col-5,
.col-4,
.col-3,
.col-2,
.col-1 {
  padding: 0;
}
</style>
