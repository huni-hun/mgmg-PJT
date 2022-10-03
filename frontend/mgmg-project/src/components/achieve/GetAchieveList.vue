<template>
  <v-carousel hide-delimiters height="90%">
    <v-carousel-item v-for="(item, i) in badgeList" :key="i">
      <v-sheet color="rgba(255, 255, 255, 0.3)">
        <v-container fluid>
          <v-row class="five-cols">
            <v-col v-for="(badge, index) in item" :key="index">
              <dumpBadge v-if="badge.badgeName == '없음'" :badge="badge" />
              <achieveBadge v-else :badge="badge" :isMobile="isMobile" />
            </v-col>
          </v-row>
        </v-container>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
import { mapState } from "vuex";
import AchieveBadge from "./AchieveBadge.vue";
import DumpBadge from "./DumpBadge.vue";
import { achieve_get_list } from "@/store/modules/etcStore";

export default {
  name: "GetNoticePage",
  props: { isAchieve: Boolean },
  data() {
    return {
      isMobile: false,
      badgeStart: 0,
      badgeEnd: 15,
      // Axios 통신하면서 바로 데이터를 저장하는 방식으로 구현하자.
      badges: [],
      badgeList: [],
      badgeList1: [],
      badgeList2: [],
      badgeList3: [],
    };
  },
  methods: {},
  async created() {
    this.badges = await achieve_get_list(this.accessToken);
    const badgeLen = this.badges.badges.length;
    // 1페이지만 생기는 경우
    if (badgeLen < 16) {
      console.log("1페이지만 생기는 경우");
      this.badgeList1 = this.badges.badges.slice(0, badgeLen);

      for (var i = badgeLen; i < 15; i++) {
        this.badgeList1.push({ badgeNo: i, badgeName: "없음" });
      }
      this.badgeList.push(this.badgeList1);
      // 2페이지 생기는 경우
    } else if (badgeLen < 31 && 15 < badgeLen) {
      console.log("2페이지만 생기는 경우");
      this.badgeList1 = this.badges.badges.slice(0, 15);
      this.badgeList2 = this.badges.badges.slice(15, badgeLen);

      for (var j = badgeLen; j < 30; j++) {
        this.badgeList3.push({ badgeNo: j, badgeName: "없음" });
      }
      this.badgeList.push(this.badgeList1);
      this.badgeList.push(this.badgeList2);
      // 3페이지 생기는 경우
    } else if (badgeLen < 45 && 30 < badgeLen) {
      console.log("3페이지만 생기는 경우");
      this.badgeList1 = this.badges.badges.slice(0, 15);
      this.badgeList2 = this.badges.badges.slice(15, 30);
      this.badgeList3 = this.badges.badges.slice(30, badgeLen);
      for (var k = badgeLen; k < 45; i++) {
        this.badgeList3.push({ badgeNo: k, badgeName: "없음" });
      }
      this.badgeList.push(this.badgeList1);
      this.badgeList.push(this.badgeList2);
      this.badgeList.push(this.badgeList3);
    }
    this.isMobile = window.matchMedia("(max-width: 639.5px)").matches; // 가로 크기 확인
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
    badgesList() {
      return this.badgeList;
    },
  },
  components: { AchieveBadge, DumpBadge },
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

/* Desktop First */

@media (max-width: 1023px) {
  .five-cols {
    display: grid;
    grid-template-columns: repeat(5, 1fr);
  }
}

@media (max-width: 767px) {
  .five-cols {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
  }
}

@media (max-width: 639px) {
  .five-cols {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
  }
}
</style>
