<template>
  <div class="fontEditBody">
    <v-container>
      <div class="fontEditTitle">
        <v-row class="fontSurveyTitle"><label for="">글꼴 변경</label></v-row>
        <v-row><label for="">선택하신 글꼴은 모든 일기에 적용됩니다.</label></v-row>
        <v-row><hr class="hrStyle" /></v-row>
      </div>
    </v-container>

    <div class="fontEditLstBody">
      <div class="fontEditLstBox" v-for="font in fontLst" :key="font.Num" name="fontSelect" @click="changeFont(font)">
        <div class="fontLstBox" :class="{ selected: font.name == selectedFont }">{{ font.name }}</div>
      </div>
    </div>

    <div class="fontEditButtonLine">
      <CustomButton class="fontButton" btnText="확인" @click="userChangeFont" />
    </div>
  </div>
</template>

<script>
import CustomButton from "../common/CustomButton.vue";
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
      console.log(request);
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
.fontEditBody {
  width: 100%;
  margin-top: 5vh;
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
  font-size: clamp(1.2rem, 2.5vw, 1.8rem);
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
  width: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
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
.fontLstBox {
  width: 100%;
  margin: 6%;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25);
}
.selected {
  box-shadow: 0px 0px 4px 5px rgba(99, 99, 99, 0.25), inset 3px 3px 4px 3px rgba(0, 0, 0, 0.38);
}

@media (max-width: 639px) {
  .fontEditLstBox {
    width: 45%;
  }

  .fontLstBox {
    margin: 8%;
  }
  .fontEditBody {
    margin-top: 0;
  }
}
</style>
