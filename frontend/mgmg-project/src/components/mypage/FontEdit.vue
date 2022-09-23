<template>
  <v-container>
    <v-row><label for="">글꼴 변경</label></v-row>
    <v-row><label for="">선택하신 글꼴은 모든 일기에 적용됩니다.</label></v-row>
    <v-row><hr class="hrStyle" /></v-row>
    <v-row>
      <div class="col-4" v-for="font in fontLst" :key="font.Num" name="fontSelect" @click="changeFont(font)">
        <div class="fontLstBox" :class="{ selected: font.name == selectedFont }">{{ font.name }}</div>
      </div>
    </v-row>
    <v-row>
      <CustomButton btnText="확인" @click="userChangeFont" />
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import CustomButton from "../common/CustomButton.vue";
import api_url from "@/api/index.js";
import Swal from "sweetalert2";
import { changeFont } from "@/api/userApi.js";
export default {
  data() {
    return {
      fontLst: [
        { fontNum: 1, name: "폰트1" },
        { fontNum: 2, name: "폰트2" },
        { fontNum: 3, name: "폰트3" },
        { fontNum: 4, name: "폰트4" },
        { fontNum: 5, name: "폰트5" },
        { fontNum: 6, name: "폰트6" },
        { fontNum: 7, name: "폰트7" },
        { fontNum: 8, name: "폰트8" },
        { fontNum: 9, name: "폰트9" },
        { fontNum: 10, name: "폰트10" },
        { fontNum: 11, name: "폰트11" },
        { fontNum: 12, name: "폰트12" },
      ],
      selectedFont: "",
      selectedFontNum: 0,
    };
  },
  methods: {
    // 폰트 선택할 때
    changeFont(new_font) {
      this.selectedFont = new_font.name;
      this.selectedFontNum = new_font.fontNum;
      console.log(this.selectedFont);
    },
    // 폰트 변경 버튼 선택시
    async userChangeFont() {
      var request = {
        diaryFont: this.selectedFontNum,
      };
      let response = await changeFont(request);
      console.log("응답 데이터", response);

      Swal.fire({
        text: "글꼴이 변경되었습니다.",
        icon: "success",
        // iconColor: "#000000",
        confirmButtonColor: "#666666",
        confirmButtonText: "확인",
      });
      // this.$router.push("/main");
    },

    // userChangeFont() {
    //   axios
    //     .put(api_url.accounts.font_edit(), {
    //       headers: {
    //         //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 헤더에 토큰
    //         Authorization: `Bearer ${this.$store.state.userStore.accessToken}`,
    //       },
    //       diaryFont: this.selectedFontNum,
    //     })
    //     .then((response) => {
    //       console.log(response);
    //       Swal.fire({
    //         text: "폰트가 정상적으로 변경되었습니다.",
    //         icon: "success",
    //         // iconColor: "#000000",
    //         confirmButtonColor: "#666666",
    //         confirmButtonText: "확인",
    //       });
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //       Swal.fire({
    //         text: "폰트 변경에 실패했습니다.",
    //         icon: "warning",
    //         // iconColor: "#000000",
    //         confirmButtonColor: "#666666",
    //         confirmButtonText: "확인",
    //       });
    //     });
    // },
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
