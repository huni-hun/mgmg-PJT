<template>
  <div>
    <v-img :src="require('@/assets/achieve/attaionmodal.png')">
      <v-card class="badge-card">
        <img class="badge" :src="require(`@/assets/badge/${badge.badgeName}.png`)" alt="" />
        <v-card-text class="badge-text">
          <p class="text-h5 text--primary">몽글이 뱃지 이름</p>
          <p class="text-h6 text--primary">몽글이 뱃지 내용입니다.</p>
          <div class="card-date">
            획득날짜<br />
            2022.xx.xx"
          </div>
        </v-card-text>
        <v-btn class="badge-btn" @click="setDialog"> 확인 </v-btn>
      </v-card>
    </v-img>
  </div>
</template>

<script>
export default {
  name: "AchieveBadge",
  props: { badge: Object },

  data: () => ({ dialog: false }),
  methods: {
    setDialog() {
      this.$emit("set-dialog");
    },
  },
};
</script>

<style scoped>
.badge-card {
  border: none;
  background: none;
  height: 100%;
}
.badge {
  position: absolute;
  top: 15%;
  left: 6%;
  height: 12rem;
}

.badge-text {
  position: absolute;
  top: 15%;
  left: 40%;
  width: 60%;
  text-align: center;
}

.badge-btn {
  position: absolute;
  top: 78%;
  left: 44%;
  border-radius: 12px;
}

.card-date {
  background-color: rgb(255, 242, 208);
  width: 60%;
  padding: 0.5rem;
  margin: auto;
  border-radius: 12px;
}
</style>
