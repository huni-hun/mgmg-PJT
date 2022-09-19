<template>
  <v-container>
    <v-row v-if="!interestOrder">
      <v-col>
        <label for="">관심 음악 리스트</label>
      </v-col>
      <v-col>
        <router-link to="/interestlist/music">
          <CustomButton btnText="선물" @click="interestOrderChange" />
        </router-link>
      </v-col>
    </v-row>
    <v-row v-if="!!interestOrder">
      <v-col>
        <label for="">관심 선물 리스트</label>
      </v-col>
      <v-col>
        <router-link to="/interestlist/gift">
          <CustomButton btnText="음악" @click="interestOrderChange" />
        </router-link>
      </v-col>
    </v-row>
    <v-row>
      <router-view></router-view>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return { interestOrder: 0 };
  },
  methods: {
    interestOrderChange() {
      this.interestOrder = !this.interestOrder;
    },
  },
};
</script>

<style></style>
