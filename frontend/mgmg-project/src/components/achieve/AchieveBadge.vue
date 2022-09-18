<template>
  <div>
    {{ badge.badgeName }}

    <img v-if="badge.achievedBadgeFlag" class="badge" :src="require(`@/assets/badge/${badge.badgeName}.png`)" alt="" />
    <img v-if="!badge.achievedBadgeFlag" class="badge" :src="require(`@/assets/badge/${badge.badgeName}Black.png`)" alt="" />
  </div>
</template>

<script>
export default {
  name: "App",
  props: { badge: Object },

  data: () => ({}),
};
</script>

<style>
.badge {
  height: 10rem;
}
</style>
