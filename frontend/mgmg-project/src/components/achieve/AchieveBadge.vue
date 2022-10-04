<template>
  <div class="badge-frame text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <img
          v-if="badge.badgeName == '없음'"
          class="achieve-badge"
          :src="require(`@/assets/badge/dumpbadge.png`)"
          alt=""
          loading="lazy"
        />
        <img
          v-else-if="badge.achievedBadgeFlag"
          v-bind="attrs"
          v-on="on"
          class="achieve-badge"
          :src="require(`@/assets/badge/a${badge.badgeNo}.png`)"
          alt=""
          loading="lazy"
        />
        <img
          v-else
          v-bind="attrs"
          v-on="on"
          class="achieve-badge"
          :src="require(`@/assets/badge/${badge.badgeNo}Black.png`)"
          alt=""
          loading="lazy"
        />
        <div
          :class="{
            'left-shelf': shelfValue === 'left-shelf',
            'right-shelf': shelfValue === 'right-shelf',
            shelf: shelfValue === 'shelf',
          }"
        ></div>
      </template>
      <!-- 모달 컴포넌트 -->
      <attainModal
        v-if="badge.achievedBadgeFlag"
        :badge="badge"
        :isMobile="isMobile"
        @set-dialog="isDialog"
      />
      <noAttainModal
        v-if="!badge.achievedBadgeFlag"
        :badge="badge"
        :isMobile="isMobile"
        @set-dialog="isDialog"
      />
    </v-dialog>
  </div>
</template>

<script>
import AttainModal from "./AttainModal.vue";
import NoAttainModal from "./NoAttainModal.vue";

export default {
  name: "AchieveBadge",
  props: { badge: Object, isMobile: Boolean, index: Number },

  data: () => ({
    dialog: false,
    width: 0,
    shelfValue: "",
    idx: 0,
  }),

  components: { AttainModal, NoAttainModal },
  methods: {
    isDialog() {
      this.dialog = !this.dialog;
    },
    handleResize() {
      this.width = window.innerWidth;
    },
    isIndex() {
      if (this.index || this.index === 0) {
        this.idx = this.index + 1;
      } else {
        this.idx = this.badge.badgeNo;
      }
    },
    shelfClass() {
      if (this.isMobile) {
        const number = this.idx % 3;
        if (number === 1) {
          return (this.shelfValue = "left-shelf");
        } else if (number === 0) {
          return (this.shelfValue = "right-shelf");
        } else {
          return (this.shelfValue = "shelf");
        }
      } else {
        const number = this.idx % 5;
        if (number === 1) {
          return (this.shelfValue = "left-shelf");
        } else if (number === 0) {
          return (this.shelfValue = "right-shelf");
        } else {
          return (this.shelfValue = "shelf");
        }
      }
    },
  },
  created() {
    this.isIndex();
    this.shelfClass();
  },
};
</script>

<style scoped>
.badge-frame {
  padding: 0;
}

.achieve-badge {
  width: 10vw;
  /* width: 12vw; */
}
.shelf {
  background-color: #ecececda;
  height: 1.8rem;
  width: 100%;
  margin-bottom: 0.5rem;
  box-shadow: 0.5rem 0.5rem 5px #9797979d;
}

.left-shelf {
  background-color: #ecececda;
  height: 1.8rem;
  width: 80%;
  margin-bottom: 0.5rem;
  box-shadow: 0.5rem 0.5rem 5px #9797979d;
  float: right;
}

.right-shelf {
  background-color: #ecececda;
  height: 1.8rem;
  width: 80%;
  margin-bottom: 0.5rem;
  box-shadow: 0.5rem 0.5rem 5px #9797979d;
}
@media (max-width: 1023px) {
  .shelf {
    height: 1.5rem;
  }
  .left-shelf {
    height: 1.5rem;
  }
  .right-shelf {
    height: 1.5rem;
  }
  .achieve-badge {
    width: 12vw;
  }
}

@media (max-width: 767px) {
  .shelf {
    height: 1.2rem;
  }
  .left-shelf {
    height: 1.2rem;
  }
  .right-shelf {
    height: 1.2rem;
  }
  .achieve-badge {
    width: auto;
    height: 10vh;
  }
}

/* 갤럭시 폴드 */
@media (max-width: 660px) {
  .shelf {
    height: 1rem;
  }
  .left-shelf {
    height: 1rem;
  }
  .right-shelf {
    height: 1rem;
  }
  .achieve-badge {
    width: auto;
    height: 10vh;
  }
}

@media (max-width: 639px) {
  .achieve-badge {
    width: auto;
    height: 10vh;
  }
}
</style>
