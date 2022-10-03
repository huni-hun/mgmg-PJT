<template>
  <v-container>
    <v-row @click="test">관심 선물</v-row>
    <v-row
      >좋아하는 선물 종류를 선택하세요. 최소 1개, 최대 5개까지 선택할 수
      있습니다.</v-row
    >
    <v-row><hr class="hrStyle" /></v-row>
    <v-row>
      <div
        class="flex"
        v-for="gift in giftLst.slice(0, 5)"
        :key="gift"
        @click="addGift(gift)"
      >
        <div
          id="circle"
          class="giftLstBox"
          :class="{ selected: selectedGift.includes(gift) }"
        ></div>
        <div>{{ gift }}</div>
      </div>
    </v-row>
    <v-row>
      <div
        class="flex"
        v-for="gift in giftLst.slice(5, 10)"
        :key="gift"
        @click="addGift(gift)"
      >
        <div
          id="circle"
          class="giftLstBox"
          :class="{ selected: selectedGift.includes(gift) }"
        ></div>
        <div>{{ gift }}</div>
      </div>
    </v-row>
    <!-- <v-row>
      <div class="col-3" v-for="gift in giftLst" :key="gift" @click="addGift(gift)">
        <div id="circle" class="giftLstBox" :class="{ selected: selectedGift.includes(gift) }"></div>
        <div>{{ gift }}</div>
      </div>
    </v-row> -->
    <v-row>
      <v-col>
        <label for="">가격대</label>
      </v-col>
      <v-col>
        <div>
          <input
            type="number"
            id="underPrice"
            name="underPrice"
            :value="underPrice"
            step="1000"
            min="10000"
            :max="upperPrice - 1000"
            @change="changeUnder()"
          />
          <label for="">~</label>
          <input
            type="number"
            id="upperPrice"
            name="upperPrice"
            :value="upperPrice"
            step="1000"
            :min="underPrice"
            max="1000000"
            @change="changeUpper()"
          />
        </div>
      </v-col>
    </v-row>
    <v-row>
      <CustomButton btnText="확인" @click="mypageGiftEdit" />
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import Swal from "sweetalert2";
import { showInterestGift } from "@/api/userApi.js";
import { changeInterestGift } from "@/api/userApi.js";
export default {
  data() {
    return {
      underPrice: 10000,
      upperPrice: 20000,

      giftLst: [
        "패션의류",
        "패션잡화",
        "화장품/미용",
        "디지털/가전",
        "가구/인테리어",
        "출산/육아",
        "식품",
        "스포츠/레저",
        "생활/건강",
        "여가/생활편의",
      ],
      selectedGift: ["패션의류"],
      // this.$store.state.userStore.interestGiftLstSignup,

      // priceUnder: {
      //   labelText: "하한가",
      //   rules: [(v) => /[^0-9.]/.test(v) || "숫자를 입력하세요."],
      //   id: "priceUnder",
      // },
      // priceUpper: {
      //   labelText: "상한가",
      //   rules: [(v) => /[^0-9.]/.test(v) || "숫자를 입력하세요."],
      //   id: "priceUpper",
      // },
    };
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  mounted() {
    this.showaInterestGift();
  },
  methods: {
    test() {
      console.log(document.getElementById("underPrice").value);
    },
    // 가격대 입력받기
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
    },
    // 선물 선택. 선택리스트에 없으면 추가, 있으면 제거
    addGift(gift) {
      console.log(this.selectedGift);
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
      console.log(this.selectedGift);
    },
    // 선물 리스트 조회
    async showaInterestGift() {
      await showInterestGift(this.accessToken).then((res) => {
        this.selectedGift = res.giftCategories;
        this.underPrice = res.lowPrice;
        this.upperPrice = res.highPrice;
      });
    },
    // 선물 리스트 변경
    async mypageGiftEdit() {
      var request = {
        giftTaste: this.selectedGift,
        lowPrice: this.underPrice,
        highPrice: this.upperPrice,
      };

      await changeInterestGift(this.accessToken, request)
        .then((res) => {
          console.log(res);
          Swal.fire({
            text: "선물 종류가 정상적으로 변경되었습니다.",
            icon: "success",
            // iconColor: "#000000",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            text: "잠시후 다시 시도해주세요.",
            icon: "warning",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
        });
    },
  },
};
</script>

<style scoped>
.hrStyle {
  border: 0.01rem solid #000000;
  width: 80rem;
}
.giftLstBox {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25);
}
.selected {
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25),
    inset 3px 3px 4px 3px rgba(0, 0, 0, 0.38);
}
#circle {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: rgb(156, 156, 156);
}
</style>
