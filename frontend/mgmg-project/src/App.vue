<template>
  <v-app>
    <CustomHeader ref="header" />
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import CustomHeader from "./components/common/CustomHeader.vue";
export default {
  name: "App",
  components: { CustomHeader },

  data: () => ({}),
};
</script>

<style src="./styles/App.css"></style>
