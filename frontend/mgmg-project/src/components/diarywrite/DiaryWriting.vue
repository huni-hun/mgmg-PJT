<template>
  <div class="outDiv">
    <div class="diaryTop">
      <div class="flexTop">
        <v-row>
          <v-col cols="3">
            <span>날짜 : 2022년 9월13일</span>
          </v-col>
          <v-col cols="1">
            <span>날씨 :</span>
          </v-col>
          <v-col cols="2">
            <v-select :items="items" :value="weather" :menu-props="{maxHeight: '80%', overflowX: true}">
              <template v-slot:selection="{ item }"> <v-img class="selectImg" :src="item.image" /></template>
              <template v-slot:item="{ item }"> <v-img class="selectImg" :src="item.image" /></template>
            </v-select>
          </v-col>
          <v-col>
            <v-btn icon small>
              <v-icon color="blue lighten-3"> mdi-image-outline </v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </div>
    </div>
    <div class="diarymiddle">
      <div>
        <v-textarea auto-grow outlined single-line :value="diary" label="일기를 써보자" />
      </div>
    </div>
    <div class="diarybottom">
      <custom-button class="customButton" btnText="작성완료" @click="writingCompletion"/>
    </div>
  </div>
</template>

<script>
import CustomButton from "../common/CustomButton.vue";
export default {
  components: { CustomButton },
  data: () => ({
    items: [
      {
        name: "맑음",
        image: "http://drive.google.com/uc?export=view&id=1dQZmzod0_QpIFxf2ZBaNAF4GC-dSoD6o",
      },
      {
        name: "구름많음",
        image: "http://drive.google.com/uc?export=view&id=1vR6dPKvfz4YY85nuejk_3dzz2JrQZkQH",
      },
      {
        name: "흐림",
        image: "http://drive.google.com/uc?export=view&id=1w74NgrYIZzCI_HvHXhrhSGF5LZmmO1k8",
      },
      {
        name: "바람",
        image: "http://drive.google.com/uc?export=view&id=1OJHgysCuI407D4l9SipYaija2jvAzW8T",
      },
      {
        name: "비",
        image: "http://drive.google.com/uc?export=view&id=1FCWSejY7KNK1sCeiFXN-WfAKxwF5kKqV",
      },
      {
        name: "눈",
        image: "http://drive.google.com/uc?export=view&id=12Ru0mvrGVjgPQ3hAgCoAjT50szNX_f1V",
      },
      {
        name: "번개",
        image: "http://drive.google.com/uc?export=view&id=1MCLxgNMM-UQhhQ_bRso7MsUs4Jw_irwj",
      },
      {
        name: "무지개",
        image: "http://drive.google.com/uc?export=view&id=1ec1iziusU4LWHvmuLLQLOO1DrsdzyvcA",
      },
    ],
    diary: "",
    weather:  {
        name: "맑음",
        image: "http://drive.google.com/uc?export=view&id=1dQZmzod0_QpIFxf2ZBaNAF4GC-dSoD6o",
      },
  }),
  methods: {
    writingCompletion() {
      this.$router.push({ path: 'diarydetail' })
    },
  }
};
</script>

<style scoped>
.outDiv {
  background-color: rgba(255, 255, 255, 0.7);
  border: 1px solid black;
  padding: 2px 2px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.diaryTop {
  background: url("@/assets/diary/writingtop/blackLineTop.png") no-repeat center;
  background-size: cover;
  object-fit: none;
  flex-basis: 10vh;
  /* min-height: 10vh; */
}
.flexTop {
  width: 63vw;
  height: 100%;
  margin: 0 auto;
  display: grid;
  align-content: center;
  align-items: center;
}
.flexTop > .row {
  margin: 0px;
  align-items: baseline;
}
.v-select {
  padding: 0px;
}
.selectImg {
  width: 2vw;
  max-width: 50px;
}

.diarymiddle {
  background: url("@/assets/diary/middle/blacklineMiddle.png") repeat-y center;
  background-size: 100% 100%;
  height: 100%;
  flex-basis: 45vh;
}
.v-text-field {
  width: 63vw;
  height: 100%;
  margin: 0 auto;
}
.v-text-field >>> fieldset {
  border: none;
}
.diarybottom {
  background: url("@/assets/diary/bottom/blackLineBottom.png") no-repeat center;
  background-size: cover;
  object-fit: none;
  flex-basis: 10vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>