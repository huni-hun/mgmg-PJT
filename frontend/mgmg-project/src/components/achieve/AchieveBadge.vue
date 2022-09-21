<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <img v-if="badge.achievedBadgeFlag" v-bind="attrs" v-on="on" class="badge" :src="require(`@/assets/badge/${badge.badgeName}.png`)" alt="" />
        <img v-if="!badge.achievedBadgeFlag" v-bind="attrs" v-on="on" class="badge" :src="require(`@/assets/badge/${badge.badgeName}Black.png`)" alt="" />
      </template>
      <!-- 모달 컴포넌트 -->
      <attainModal v-if="badge.achievedBadgeFlag" :badge="badge" @set-dialog="isDialog" />
      <noAttainModal v-if="!badge.achievedBadgeFlag" :badge="badge" @set-dialog="isDialog" />
    </v-dialog>
  </div>
</template>

<script>
import AttainModal from "./AttainModal.vue";
import NoAttainModal from "./NoAttainModal.vue";
export default {
  name: "AchieveBadge",
  props: { badge: Object },

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
  height: 10rem;
}
</style>
