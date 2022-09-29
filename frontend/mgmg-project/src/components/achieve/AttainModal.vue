<template>
  <div>
    <v-img :src="require('@/assets/achieve/attaionmodal.png')">
      <v-card class="badge-card">
        <img class="badge" :src="require(`@/assets/badge/a${badge.badgeNo}.png`)" alt="" />
        <v-card-text class="badge-text">
          <p class="badge-name">{{ badge.badgeName }}</p>
          <p class="badge-content">{{ badgeDetail.badgeContent }}</p>
          <div class="card-date">
            획득날짜<br />
            {{ badgeDetail.achievedBadgeDate }}
          </div>
        </v-card-text>
        <v-btn class="badge-btn" width="10%" @click="setDialog"> 확인 </v-btn>
      </v-card>
    </v-img>
  </div>
</template>

<script>
import { achieve_detail } from "@/store/modules/etcStore";
export default {
  name: "AchieveBadge",
  props: { badge: Object },

  data: () => ({ dialog: false, badgeDetail: Object }),
  methods: {
    setDialog() {
      this.$emit("set-dialog");
    },
  },
  async created() {
    this.badgeDetail = await achieve_detail(this.badge.badgeNo);
  },
};
</script>

<style scoped>
.badge-card {
  border: none;
  background: none;
  height: 100%;
  font-size: 16px;
}
.badge {
  position: absolute;
  top: 15%;
  left: 6%;
  height: 70%;
}

.badge-text {
  position: absolute;
  top: 15%;
  left: 40%;
  width: 60%;
  text-align: center;
}

.badge-name {
  font-size: 1.5em;
}

.badge-content {
  font-size: 1em;
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
  font-size: 1em;
}

@media (max-width: 500px) {
  .badge-text {
    margin: 0;
    padding: 0;
    font-weight: bold;
  }
  .badge-name {
    font-size: 16px;
  }

  .badge-content {
    font-size: 12px;
  }

  .badge-btn {
    position: absolute;
    top: 78%;
    left: 44%;
    font-size: 12px;
    border-radius: 8px;
  }
  .card-date {
    font-size: 12px;
  }
}

@media (max-width: 400px) {
  .badge-text {
    margin: 0;
    padding: 0;
  }
  .badge-name {
    font-size: 12px;
    font-weight: bold;
  }

  .badge-content {
    font-size: 8px;
  }

  .badge-btn {
    position: absolute;
    top: 78%;
    left: 44%;
    font-size: 8px;
    border-radius: 4px;
  }
  .card-date {
    font-size: 8px;
  }
}

@media (max-width: 300px) {
  .badge-text {
    margin: 0;
    padding: 0;
  }
  .badge-name {
    font-size: 8px;
    font-weight: bold;
  }

  .badge-content {
    font-size: 4px;
  }

  .badge-btn {
    position: absolute;
    top: 78%;
    left: 44%;
    font-size: 4px;
    border-radius: 0;
  }
  .card-date {
    font-size: 4px;
  }
}
</style>
