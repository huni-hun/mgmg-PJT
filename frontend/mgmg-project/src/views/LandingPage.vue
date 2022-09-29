<template>
  <div class="backgruound">
    <LandingComponent />
  </div>
</template>

<script>
import LandingComponent from '@/components/landing/LandingComponent.vue';
export default {
  components: { LandingComponent }
};
</script>

<style scoped>
.backgruound {
  height: 100%;
  background: white;
}
</style>
