<template>
  <div class="text-center">
    <div class="badge"></div>
  </div>
</template>

<script>
export default {
  name: "AchieveBadge",
  props: { badge: Object },
};
</script>

<style scoped>
.badge {
  height: 10rem;
}
@media (max-width: 1023px) {
  .badge {
    height: 12rem;
  }
}

@media (max-width: 767px) {
  .badge {
    height: 11rem;
  }
}

/* 갤럭시 폴드 */
@media (max-width: 660px) {
  .badge {
    height: 6rem;
  }
}

@media (max-width: 639px) {
  .badge {
    height: 4rem;
  }
}
</style>
