<template>
  <v-app>
    <CustomHeader />
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import CustomHeader from "./components/common/CustomHeader.vue";
export default {
  name: "App",

  data: () => ({
    //
  }),
  components: { CustomHeader },
};
</script>

<style>
/* @font-face {
  font-family: "Cafe24Oneprettynight";
  src: url("@/assets/font/Cafe24Oneprettynight.ttf");
}
#app {
  font-family: "Cafe24Oneprettynight";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
} */
</style>
