<template>
  <v-container class="interestListPageBody">
    <div class="interestListParentTotalBody" v-if="!interestOrder">
      <div class="interestListTitle">
        <label for="">관심 음악 리스트</label>
      </div>
      <div>
        <router-link to="/interestlist/music">
          <CustomButton btnText="선물" @click="interestOrderChange" />
        </router-link>
      </div>
    </div>

    <div class="interestListParentTotalBody" v-if="interestOrder">
      <div class="interestListTitle">
        <label for="">관심 선물 리스트</label>
      </div>
      <div>
        <router-link to="/interestlist/gift">
          <!-- <v-icon @click="interestOrderChange">mdi-music-circle</v-icon> -->
          <CustomButton btnText="음악" @click="interestOrderChange" />
        </router-link>
      </div>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </v-container>
</template>

<script>
export default {
  data() {
    return { interestOrder: false };
  },
  created() {
    this.$router.push("/interestlist/music");
  },
  methods: {
    interestOrderChange() {
      this.interestOrder = !this.interestOrder;
    },
  },
};
</script>

<style scope>
.interestListPageBody {
  width: 100%;
  /* padding: 0 10%; */
}
.interestListParentTotalBody {
  margin: 2vw 0 1vw 0;
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.interestListTitle {
  font-size: clamp(1.5rem, 5vw, 2.2rem);
  color: white;
  /* margin-bottom: 2%; */
}
</style>
