<template>
  <div>
    <!-- 그리드랑 반복 랜더링 활용하기 -->
    <v-container class="justify-center">
      <v-row>
        <v-col cols="3" v-for="badge in badges" :key="badge.badgeNo">
          <achieveBadge :badge="badge" />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import AchieveBadge from "./AchieveBadge.vue";
export default {
  name: "NoticePage",
  data() {
    return {
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
    };
  },
  components: { AchieveBadge },
};
</script>

<style></style>
