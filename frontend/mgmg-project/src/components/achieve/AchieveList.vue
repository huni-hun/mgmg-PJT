<template>
  <div>
    <!-- 그리드랑 반복 랜더링 활용하기 -->
    <v-container class="justify-center custom">
      <v-btn class="left-arrow" icon @click="turnLeft">
        <v-icon size="100">mdi-menu-left</v-icon>
      </v-btn>
      <!-- 일단 억지로 grid row를 5개로 줘서 했지만, 반응형이 안 됨 -->
      <v-row class="five-cols">
        <v-col v-for="badge in badgesList" :key="badge.badgeNo">
          <achieveBadge :badge="badge" />
        </v-col>
      </v-row>
      <v-btn class="right-arrow" icon @click="turnRight">
        <v-icon size="100">mdi-menu-right</v-icon>
      </v-btn>
    </v-container>
  </div>
</template>

<script>
import AchieveBadge from "./AchieveBadge.vue";
export default {
  name: "NoticePage",
  data() {
    return {
      badgeStart: 0,
      badgeEnd: 15,
      // Axios 통신하면서 바로 데이터를 저장하는 방식으로 구현하자.
      badges: [
        { badgeNo: 1, badgeName: "firstMeeting", achievedBadgeFlag: 0 },
        { badgeNo: 2, badgeName: "pencilBronze", achievedBadgeFlag: 1 },
        { badgeNo: 3, badgeName: "pencilGold", achievedBadgeFlag: 1 },
        { badgeNo: 4, badgeName: "pencilSilver", achievedBadgeFlag: 0 },
        { badgeNo: 5, badgeName: "calendarBronze", achievedBadgeFlag: 0 },
        { badgeNo: 6, badgeName: "calendarGold", achievedBadgeFlag: 1 },
        { badgeNo: 7, badgeName: "calendarSilver", achievedBadgeFlag: 1 },
        { badgeNo: 8, badgeName: "angryBronze", achievedBadgeFlag: 0 },
        { badgeNo: 9, badgeName: "angryGold", achievedBadgeFlag: 1 },
        { badgeNo: 10, badgeName: "angrySilver", achievedBadgeFlag: 0 },
        { badgeNo: 11, badgeName: "annoyedBronze", achievedBadgeFlag: 1 },
        { badgeNo: 12, badgeName: "annoyedGold", achievedBadgeFlag: 1 },
        { badgeNo: 13, badgeName: "annoyedSilver", achievedBadgeFlag: 1 },
        { badgeNo: 14, badgeName: "calmBronze", achievedBadgeFlag: 0 },
        { badgeNo: 15, badgeName: "calmGold", achievedBadgeFlag: 1 },
        { badgeNo: 16, badgeName: "calmSilver", achievedBadgeFlag: 0 },
        { badgeNo: 17, badgeName: "expectBronze", achievedBadgeFlag: 1 },
        { badgeNo: 18, badgeName: "expectGold", achievedBadgeFlag: 1 },
        { badgeNo: 19, badgeName: "expectSilver", achievedBadgeFlag: 0 },
        { badgeNo: 20, badgeName: "fatigueBronze", achievedBadgeFlag: 0 },
        { badgeNo: 21, badgeName: "fatigueGold", achievedBadgeFlag: 1 },
        { badgeNo: 22, badgeName: "fatigueSilver", achievedBadgeFlag: 1 },
        { badgeNo: 23, badgeName: "fearBronze", achievedBadgeFlag: 0 },
        { badgeNo: 24, badgeName: "fearGold", achievedBadgeFlag: 1 },
        { badgeNo: 25, badgeName: "fearSilver", achievedBadgeFlag: 0 },
        { badgeNo: 26, badgeName: "happyBronze", achievedBadgeFlag: 0 },
        { badgeNo: 27, badgeName: "happyGold", achievedBadgeFlag: 0 },
        { badgeNo: 28, badgeName: "happySilver", achievedBadgeFlag: 1 },
        { badgeNo: 29, badgeName: "loveBronze", achievedBadgeFlag: 0 },
        { badgeNo: 30, badgeName: "loveGold", achievedBadgeFlag: 0 },
        { badgeNo: 31, badgeName: "loveSilver", achievedBadgeFlag: 1 },
        { badgeNo: 32, badgeName: "sadBronze", achievedBadgeFlag: 1 },
        { badgeNo: 33, badgeName: "sadGold", achievedBadgeFlag: 1 },
        { badgeNo: 34, badgeName: "sadSilver", achievedBadgeFlag: 1 },
        { badgeNo: 35, badgeName: "shameBronze", achievedBadgeFlag: 0 },
        { badgeNo: 36, badgeName: "shameGold", achievedBadgeFlag: 1 },
        { badgeNo: 37, badgeName: "shameSilver", achievedBadgeFlag: 0 },
      ],
      badgeList: [],
    };
  },
  methods: {
    turnLeft() {
      if (this.badgeStart) {
        // 0 이 아니면,(좌측)
        this.badgeStart -= 15;
        this.badgeEnd -= 15;
      }
    },
    turnRight() {
      if (this.badgeEnd < 36) {
        this.badgeStart += 15;
        this.badgeEnd += 15;
      }
    },
  },
  created() {
    this.badgeList = this.badges.slice(this.badgeStart, this.badgeEnd);
  },
  computed: {
    badgesList() {
      return this.badges.slice(this.badgeStart, this.badgeEnd);
    },
  },
  components: { AchieveBadge },
};
</script>

<style scoped>
.custom {
  background: rgba(0, 0, 0, 0.35);
  position: relative;
}
/* grid 5개로 나누는 css */
.five-cols {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
}

.left-arrow {
  position: absolute;
  z-index: 1;
  top: 50%;
}

.right-arrow {
  position: absolute;
  z-index: 1;
  top: 50%;
  right: 0;
}
</style>
