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
