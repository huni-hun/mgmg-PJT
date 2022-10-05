<template>
  <div class="fontEditBody">
    <v-container>
      <div class="fontEditTitle">
        <v-row class="fontSurveyTitle"><label for="">글꼴 변경</label></v-row>
        <v-row><label for="">선택하신 글꼴은 모든 일기에 적용됩니다.</label></v-row>
        <v-row>
          <hr class="hrStyle" />
        </v-row>
      </div>
    </v-container>

    <div class="fontEditLstBody">
      <div class="fontEditLstBox" :class="{ selected: font.name == selectedFont }" v-for="(font, index) in fontLst" :key="index" name="fontSelect" @click="changeFont(index)">
        <img class="fontImage" :src="require(`../../assets/fontlist/${font.url}.png`)" alt="" />
      </div>
    </div>

    <div class="fontEditButtonLine">
      <CustomButton class="fontButton" btnText="확인" @click="userChangeFont" />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import CustomButton from "../common/CustomButton.vue";
import Swal from "sweetalert2";
import { changeFont } from "@/api/userApi.js";
export default {
  data() {
    return {
      fontLst: [
        { fontNum: 0, name: "KyoboHandwriting2019.ttf", url: "1_교보손글씨" },
        { fontNum: 1, name: "Misaeng.ttf", url: "2_미생체" },
        { fontNum: 2, name: "BoksungaTint.ttf", url: "3_봉숭아틴트" },
        { fontNum: 3, name: "Onipgeul.ttf", url: "4_온글잎의연체" },
        { fontNum: 4, name: "KoteuraHuimang.ttf", url: "5_코트라희망체" },
        { fontNum: 5, name: "Cafe24Oneprettynight.ttf", url: "6_카페24고운밤" },
        { fontNum: 6, name: "RidiBatang.otf", url: "7_리디바탕체" },
        { fontNum: 7, name: "Pretendard.ttf", url: "8_프리텐다드" },
        { fontNum: 8, name: "mabiyet.ttf", url: "9_마비옛체" },
      ],
      selectedFont: "",
      selectedFontNum: 0,
      selectedFontName: "",
    };
  },
  mounted() {
    this.setdefaultFont();
  },
  computed: {
    ...mapState("userStore", ["accessToken", "diaryFont"]),
  },
  methods: {
    ...mapActions("userStore", ["setFont"]),
    // 기존 폰트 뭔지 초기화
    setdefaultFont() {
      this.selectedFont = this.fontLst[this.diaryFont].name;
    },
    // 폰트 선택할 때
    changeFont(new_font) {
      this.selectedFont = this.fontLst[new_font].name;
      this.selectedFontNum = this.fontLst[new_font].fontNum;
      this.selectedFontName = this.fontLst[new_font].name;
    },
    // 폰트 변경 버튼 선택시
    async userChangeFont() {
      var request = {
        diaryFont: this.selectedFontNum,
      };
      await changeFont(this.accessToken, request);

      this.setFont(this.selectedFontNum);

      Swal.fire({
        text: "글꼴이 변경되었습니다.",
        icon: "success",
        confirmButtonColor: "#666666",
        confirmButtonText: "확인",
      });
    },
  },
  components: { CustomButton },
};
</script>

<style scoped>
.fontEditBody {
  width: 100%;
  /* margin-top: 5vh; */
  padding: 5% 0 5% 0;
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 0px 20px 20px rgba(0, 0, 0, 0.2);
}

.fontEditTitle {
  padding: 0 5% 2% 5%;
}

.fontSurveyTitle {
  font-size: clamp(1.5rem, 5vw, 2.2rem);
}

.fontEditLstBody {
  margin: 3% 15% 3% 15%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: center;
}

.fontEditLstBox {
  width: 28%;
  margin: 2%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 0px 3px 3px rgba(202, 202, 202, 0.25);
}

.fontEditButtonLine {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 3%;
  width: 100%;
}

.fontButton {
  width: 50%;
}

.hrStyle {
  width: 100%;
}

.fontImage {
  width: 100%;
}

.selected {
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25), inset 3px 3px 4px 3px rgba(0, 0, 0, 0.38);
}

@media (max-width: 639px) {
  .fontEditLstBox {
    width: 45%;
  }

  .fontEditBody {
    margin-top: 0;
  }

  .fontEditLstBox {
    margin: 4% 2%;
  }
}
</style>
