<template>
  <v-carousel hide-delimiters height="100%">
    <v-carousel-item v-for="(item, i) in badgeList" :key="i">
      <v-sheet class="achieve-list-sheet" color="rgba(255, 255, 255, 0)">
        <v-container fluid>
          <v-row align-content="space-around" class="five-cols">
            <v-col
              class="badge-list-col"
              v-for="badge in item"
              :key="badge.badgeNo"
            >
              <achieveBadge :badge="badge" :isMobile="isMobile" />
            </v-col>
          </v-row>
        </v-container>
        <div class="achieve-list-bottom"></div>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
import { mapState } from "vuex";
import AchieveBadge from "./AchieveBadge.vue";
import { achieve_list } from "@/store/modules/etcStore";

export default {
  name: "NoticePage",
  props: { isAchieve: Boolean },
  data() {
    return {
      badgeStart: 0,
      badgeEnd: 15,
      isMobile: Boolean,
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
    this.badges = await achieve_list(this.accessToken);
    this.badgeList1 = this.badges.badges.slice(0, 15);
    this.badgeList2 = this.badges.badges.slice(15, 30);
    this.badgeList3 = this.badges.badges.slice(30, this.badges.badges.length);
    this.badgeList.push(this.badgeList1);
    this.badgeList.push(this.badgeList2);
    this.badgeList.push(this.badgeList3);
    for (var i = 38; i < 46; i++) {
      this.badgeList3.push({ badgeNo: i, badgeName: "없음" });
    }
    const mq = window.matchMedia("(max-width: 767px)"); // 가로 크기 확인
    if (mq.matches === true) {
      this.isMobile = true;
    } else {
      this.isMobile = false;
    }
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
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

.achieve-list-sheet {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.badge-list-col {
  padding: 0;
}

.five-cols {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
}

.achieve-list-bottom {
  height: 2vh;
  width: auto;
  box-shadow: 1rem 1rem 5px #9797979d;
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
