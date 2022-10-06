<template>
  <v-container class="interestListPageBody">
    <div class="interestListParentTotalBody" v-if="!interestOrder">
      <div class="interestListTitle">
        <label for="">관심 음악 리스트</label>
      </div>
      <div>
        <router-link class="iconCircle" to="/interestlist/music">
          <v-icon @click="interestOrderChange" color="#ffffff" size="clamp(1.2rem, 3vw, 2rem)">mdi-gift-outline</v-icon>
        </router-link>
      </div>
    </div>

    <div class="interestListParentTotalBody" v-if="interestOrder">
      <div class="interestListTitle">
        <label for="">관심 선물 리스트</label>
      </div>
      <div>
        <router-link class="iconCircle" to="/interestlist/gift">
          <v-icon @click="interestOrderChange" color="#ffffff" size="clamp(1.2rem, 3vw, 2rem)">mdi-music</v-icon>
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
.iconCircle {
  display: flex;
  padding: 10px !important;
  border-radius: 50%;
  padding: 2%;
  color: white;
  text-decoration: none;

  /* border: 2px solid white; */
  box-shadow: 0px 0px 5px 5px rgba(47, 47, 47, 0.163);

  /* background-color: #51516e; */
  background-color: #3949ab;
}
</style>
