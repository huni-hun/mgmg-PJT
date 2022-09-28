<template>
  <div>
    <!-- 그리드랑 반복 랜더링 활용하기 -->
    <v-container class="justify-center custom">
      <v-btn class="left-arrow" icon @click="turnLeft">
        <v-icon size="100">mdi-menu-left</v-icon>
      </v-btn>
      <!-- 일단 억지로 grid row를 5개로 줘서 했지만, 반응형이 안 됨 -->
      <v-row>
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
import { achieve_list } from "@/store/modules/etcStore";

export default {
  name: "NoticePage",
  props: { isAchieve: Boolean },
  data() {
    return {
      badgeStart: 0,
      badgeEnd: 15,
      // Axios 통신하면서 바로 데이터를 저장하는 방식으로 구현하자.
      badges: [],
      badgeList: [],
    };
  },
  methods: {
    turnLeft() {
      if (this.badgeStart) {
        // 0 이 아니면,(좌측)
        this.badgeStart -= 15;
        this.badgeEnd -= 15;
        this.badgeList = this.badges.badges.slice(this.badgeStart, this.badgeEnd);
      }
    },
    turnRight() {
      if (this.badgeEnd < 36) {
        this.badgeStart += 15;
        this.badgeEnd += 15;
        this.badgeList = this.badges.badges.slice(this.badgeStart, this.badgeEnd);
      }
    },
  },
  async created() {
    this.badges = await achieve_list();
    this.badgeList = this.badges.badges.slice(this.badgeStart, this.badgeEnd);
  },
  computed: {
    badgesList() {
      return this.badgeList;
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
