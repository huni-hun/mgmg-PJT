<template>
  <v-app>
    <CustomHeader ref="header" v-if="(`landing`!=$route.name)" />
    <v-main>
      <router-view v-if="(`landing`==$route.name)" />
      <v-container v-else class="app_view justify-center">
        <router-view />
      </v-container>
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

<style src="./styles/App.css">

</style>
