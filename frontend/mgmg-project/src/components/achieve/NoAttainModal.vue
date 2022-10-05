<template>
  <div>
    <v-img
      v-if="isMobile"
      :src="require('@/assets/achieve/mobilenoattaionmodal.png')"
    >
      <v-card class="mobile-badge-card">
        <img
          class="mobile-badge"
          :src="require(`@/assets/badge/${badge.badgeNo}Black.png`)"
          alt=""
        />
        <v-card-text class="mobile-badge-text">
          <p class="mobile-badge-name">{{ badge.badgeName }}</p>
          <p class="mobile-badge-content">{{ badgeDetail.badgeContent }}</p>
          <div class="mobile-card-date">
            획득조건<br />
            {{ badgeDetail.badgeCodition }}
          </div>
        </v-card-text>
        <v-btn class="mobile-badge-btn" @click="setDialog"> 확인 </v-btn>
      </v-card>
    </v-img>
    <!-- pc -->
    <v-img v-else :src="require('@/assets/achieve/noattaionmodal.png')">
      <v-card class="badge-card">
        <img
          class="badge"
          :src="require(`@/assets/badge/${badge.badgeNo}Black.png`)"
          alt=""
        />
        <v-card-text class="badge-text">
          <p class="badge-name">{{ badge.badgeName }}</p>
          <p class="badge-content" v-html="badgeDetail.badgeContent"></p>
          <div class="card-date">
            획득조건<br />
            {{ badgeDetail.badgeCodition }}
          </div>
        </v-card-text>
        <v-btn class="badge-btn" @click="setDialog"> 확인 </v-btn>
      </v-card>
    </v-img>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { achieve_detail } from "@/store/modules/etcStore";
export default {
  name: "AchieveBadge",
  props: { badge: Object, isMobile: Boolean },

  data: () => ({ dialog: false, badgeDetail: Object }),
  methods: {
    setDialog() {
      this.$emit("set-dialog");
    },
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  async created() {
    await achieve_detail(this.accessToken, this.badge.badgeNo).then((res) => {
      res.badgeContent = res.badgeContent.replace(
        /(?:\r\n|\r|\n|\\n)/g,
        "<br />"
      );
      this.badgeDetail = res;
    });
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
  background-color: rgb(241, 244, 255);
  width: 60%;
  padding: 0.5rem;
  margin: auto;
  border-radius: 12px;
}

/* 여기서 부터 모바일  */
@media (max-width: 639px) {
  .mobile-badge-card {
    border: none;
    background: none;
    height: 100%;
    font-size: 16px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  .mobile-badge {
    height: 24vh;
  }

  .mobile-badge-text {
    text-align: center;
  }

  .mobile-badge-name {
    font-size: 2em;
  }

  .mobile-badge-content {
    font-size: 1em;
  }

  .mobile-badge-btn {
    border-radius: 12px;
  }

  .mobile-card-date {
    background-color: rgb(241, 244, 255);
    width: 60%;
    padding: 0.5rem;
    margin: auto;
    border-radius: 12px;
    font-size: 1em;
  }
}
</style>
