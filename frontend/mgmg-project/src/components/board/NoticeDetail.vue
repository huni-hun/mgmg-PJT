<template>
  <div>
    <!-- 제목과 작성일자 -->
    <v-row class="mt-8">
      <v-col cols="12">
        <v-card>
          <v-responsive>
            <v-card-text>
              {{ noticeTitle }} <br />
              {{ noticeDate }}
            </v-card-text>
          </v-responsive>
        </v-card>
      </v-col>
    </v-row>
    <!-- 글 내용 -->
    <v-row>
      <v-col cols="12">
        <v-card>
          <v-responsive :aspect-ratio="16 / 4">
            <v-card-text> {{ noticeContent }} </v-card-text>
          </v-responsive>
        </v-card>
      </v-col>
    </v-row>
    <!-- 글목록으로 나가는 버튼 -->
    <v-row>
      <v-col cols="12">
        <router-link to="/notice" justify="center">글목록</router-link>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  data: () => ({
    noticeNo: 1,
    noticeTitle: "김치",
    noticeContent:
      "김치, 어렵다면 조금만 준비해서 해서보자. 정확한 비율로 양념하고, 조금씩 김치 담그는 과정을 손에 익히면 나중에는 많은 양의 김장도 문제 없다! 맛있는 발효의 기본은 잘 절인 배추와 신선한 젓갈이다. 좋은 재료로 정성껏 준비해 보자. [레시피 출처: 백설요리원]",
    noticeDate: "2022.09.15",
    fixedFlag: false,
  }),
};
</script>

<style></style>
