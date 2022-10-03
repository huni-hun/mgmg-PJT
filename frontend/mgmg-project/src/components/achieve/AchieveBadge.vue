<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <img v-if="badge.achievedBadgeFlag" v-bind="attrs" v-on="on" class="badge" :src="require(`@/assets/badge/a${badge.badgeNo}.png`)" alt="" loading="lazy" />
        <img v-if="!badge.achievedBadgeFlag" v-bind="attrs" v-on="on" class="badge" :src="require(`@/assets/badge/${badge.badgeNo}Black.png`)" alt="" loading="lazy" />
      </template>
      <!-- 모달 컴포넌트 -->
      <attainModal v-if="badge.achievedBadgeFlag" :badge="badge" :isMobile="isMobile" @set-dialog="isDialog" />
      <noAttainModal v-if="!badge.achievedBadgeFlag" :badge="badge" :isMobile="isMobile" @set-dialog="isDialog" />
    </v-dialog>
  </div>
</template>

<script>
import AttainModal from "./AttainModal.vue";
import NoAttainModal from "./NoAttainModal.vue";

export default {
  name: "AchieveBadge",
  props: { badge: Object, isMobile: Boolean },

  data: () => ({ dialog: false }),

  components: { AttainModal, NoAttainModal },
  methods: {
    isDialog() {
      this.dialog = !this.dialog;
    },
  },
};
</script>

<style scoped>
.badge {
  width: 10vw;
  /* width: 12vw; */
}
@media (max-width: 1023px) {
  .badge {
    width: 12vw;
  }
}

@media (max-width: 767px) {
  .badge {
    width: auto;
    height: 10vh;
  }
}

/* 갤럭시 폴드 */
@media (max-width: 660px) {
  .badge {
    width: auto;
    height: 10vh;
  }
}

@media (max-width: 639px) {
  .badge {
    width: auto;
    height: 10vh;
  }
}
</style>
