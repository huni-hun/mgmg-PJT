<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-col>
          <donutGraph />
        </v-col>
        <v-col>
          <div style="color: red"></div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import DonutGraph from "@/components/statistics/DonutGraph.vue";

export default {
  name: "PeriodDetail",

  data: () => ({
    //
  }),
  components: { DonutGraph },
};
</script>

<style></style>
