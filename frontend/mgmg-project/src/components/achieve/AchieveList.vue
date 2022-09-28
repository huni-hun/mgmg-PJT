<template>
  <v-carousel hide-delimiters height="90%">
    <v-carousel-item v-for="(item, i) in badgeList" :key="i">
      <v-container fluid>
        <v-row class="five-cols">
          <v-col v-for="badge in item" :key="badge.badgeNo">
            <achieveBadge :badge="badge" />
          </v-col>
        </v-row>
      </v-container>
    </v-carousel-item>
  </v-carousel>
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
      badgeList1: [],
      badgeList2: [],
      badgeList3: [],
    };
  },
  methods: {},
  async created() {
    this.badges = await achieve_list();
    this.badgeList1 = this.badges.badges.slice(0, 15);
    this.badgeList2 = this.badges.badges.slice(15, 30);
    this.badgeList3 = this.badges.badges.slice(30, this.badges.badges.length);
    this.badgeList.push(this.badgeList1);
    this.badgeList.push(this.badgeList2);
    this.badgeList.push(this.badgeList3);
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

/* Desktop First */

@media (max-width: 1023px) {
  .five-cols {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
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
