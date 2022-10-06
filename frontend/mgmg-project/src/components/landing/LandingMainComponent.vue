<template>
  <div class="topBackground" style="display: flex; flex-direction: column">
    <div style="flex: 1"></div>
    <div class="main">
      <div class="main_text">
        <div class="only_text_logo center">
          <img alt="로고" :src="require(`@/assets/logo/logo_only_text.png`)" />
        </div>
        <div class="only_text">
          <div style="flex: 1"></div>
          <div class="datail" style="flex: 5">
            <div class="datail_text left">내 감정이 기록되는</div>
            <div class="datail_text right">감정 분석 일기장</div>
          </div>
          <div style="flex: 1"></div>
        </div>
      </div>
      <div class="main_flex"></div>
      <div class="main_emotion">
        <div class="emotion center">
          <img alt="감정티콘" :src="require(`@/assets/emoticon/calm.png`)" />
        </div>
        <div class="main_btn center">
          <button class="button" value="login" @click="pageLink(`login`)">
            로그인
          </button>
          <button class="button" value="signup" @click="pageLink(`signup`)">
            회원가입
          </button>
        </div>
      </div>
    </div>
    <div style="flex: 1"></div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { autoLogin } from "@/api/userApi.js";

export default {
  mounted() {
    this.autoLogin();
  },
  methods: {
    ...mapActions("userStore", ["setUserInfoAuto"]),
    async autoLogin() {
      if (this.$cookies.isKey("autoLoginCookie")) {
        var refreshToken = this.$cookies.get("autoLoginCookie");
        var userId = this.$cookies.get("userIdCookie");

        const request = {
          refreshToken: refreshToken,
          userId: userId,
        };

        await autoLogin(request).then((res) => {
          this.loginAuto(res);
          this.$router.push("/main");
        });
      }
    },
    loginAuto(response) {
      this.setUserInfoAuto(response);
      this.$cookies.set("autoLoginCookie", response.refreshToken);
      this.$cookies.set("userIdCookie", response.userId);
    },
    pageLink(to) {
      if (to == "login") this.$router.push({ path: "login" });
      else this.$router.push({ path: "signup" });
    },
  },
};
</script>

<style scoped>
.topBackground {
  background-image: url("@/assets/landing/landing_topBackground.png");
  background-size: cover;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.center {
  text-align: center;
}
.left {
  text-align: left;
}

.right {
  text-align: right;
}

.main {
  flex: 3;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.button {
  padding: 10px 45px;
  margin: 0px 10px;
  background: #ffdd28;
  border-radius: 20px;
  width: 180px;
  height: 60px;
  font-size: 25px;
}

.button:hover {
  background-color: #edcd23;
  box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.main_text {
  flex: 4;
}

.only_text_logo img {
  width: 70%;
  min-width: 200px;
  max-width: 600px;
}

.only_text {
  display: flex;
  margin-top: 10%;
}

.datail_text {
  display: block;
  font-size: clamp(2rem, 3vw, 3rem);
  line-height: 72px;
  color: #fcfeff;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.main_emotion {
  flex: 3;
}

.emotion img {
  width: 100%;
  min-width: 200px;
  max-width: 600px;
  filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, 0.2));
}

.main_btn {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
  align-items: center;
}

.main_flex {
  flex: 1;
}

@media screen and (max-width: 835px) {
  .main {
    flex-direction: column;
  }

  .main_text {
    flex: 1;
    display: flex;
    flex-direction: row;
    flex-direction: column-reverse;
  }
  .only_text_logo img {
    width: 60%;
    min-width: 200px;
    max-width: 600px;
  }
  .only_text {
    display: none;
  }
  .emotion img {
    width: 100%;
    min-width: 200px;
    max-width: 200px;
    filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, 0.2));
  }

  .datail_text {
    display: none;
  }

  .main_emotion {
    flex: 2;
  }

  .main_btn {
    flex-direction: column;
  }

  .button {
    margin: 5px 0px 0px;
    padding: 10px;
    width: 140px;
    height: 40px;
    border-radius: 12px;
    font-size: clamp(1rem, 1.4vw, 1.5rem);
  }
  .main_flex {
    display: none;
  }
}
</style>
