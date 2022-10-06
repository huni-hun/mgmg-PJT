<template>
  <v-app>
    <CustomHeader ref="header" v-if="`landing` != $route.name" />
    <v-main>
      <router-view v-if="`landing` == $route.name" />
      <v-container v-else class="app_view justify-center">
        <router-view />
      </v-container>

      <a href="https://url.kr/3kda6z" download v-if="(`login` == $route.name)||(`signup` == $route.name)">
        <button class="download_btn">
          <v-icon color="blue-grey darken-4" class="mr-1">mdi-download</v-icon>.apk 다운
        </button>
      </a>
    </v-main>
  </v-app>
</template>

<script>
import CustomHeader from "./components/common/CustomHeader.vue";
export default {
  name: "App",
  components: { CustomHeader },
};
</script>

<style src="./styles/App.css">

</style>
