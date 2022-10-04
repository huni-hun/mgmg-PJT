<template>
  <v-container class="mypageTotalBody">
    <div class="mypageBody">
      <!-- 웹화면 -->
      <div class="mypageBanner pcView">
        <div class="bannerTitleLabel">
          <label for="">마이페이지</label>
        </div>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/mypage/myinfo">
          <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 0 }" @click="menuOrderChange(0)"><label for="">내 정보 확인</label></div>
        </router-link>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/mypage/passwordedit">
          <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 1 }" @click="menuOrderChange(1)"><label for="">비밀번호 수정</label></div>
        </router-link>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/mypage/musicedit">
          <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 2 }" @click="menuOrderChange(2)"><label for="">음악 장르 변경</label></div>
        </router-link>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/mypage/giftedit">
          <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 3 }" @click="menuOrderChange(3)"><label for="">선물 종류 변경</label></div>
        </router-link>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/mypage/fontedit">
          <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 4 }" @click="menuOrderChange(4)"><label for="">글꼴 변경</label></div>
        </router-link>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/mypage/userdelete">
          <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 5 }" @click="menuOrderChange(5)"><label for="">회원 탈퇴</label></div>
        </router-link>
        <hr class="hrStyle" />
      </div>

      <!-- 모바일화면 -->
      <div class="phoneView">
        <div class="banner">
          <div class="bannerMenuList menuBorderRight menuBorderBottom menuBorderTop">
            <router-link class="bannerLabelDecoration" to="/mypage/myinfo">
              <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 0 }" @click="menuOrderChange(0)"><label for="">내 정보 확인</label></div>
            </router-link>
          </div>

          <div class="bannerMenuList menuBorderRight menuBorderBottom menuBorderTop">
            <router-link class="bannerLabelDecoration" to="/mypage/musicedit">
              <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 2 }" @click="menuOrderChange(2)"><label for="">음악 장르 변경</label></div>
            </router-link>
          </div>

          <div class="bannerMenuList menuBorderBottom menuBorderTop">
            <router-link class="bannerLabelDecoration" to="/mypage/fontedit">
              <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 4 }" @click="menuOrderChange(4)"><label for="">글꼴 변경</label></div>
            </router-link>
          </div>

          <div class="bannerMenuList menuBorderRight menuBorderBottom">
            <router-link class="bannerLabelDecoration" to="/mypage/passwordedit">
              <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 1 }" @click="menuOrderChange(1)"><label for="">비밀번호 수정</label></div>
            </router-link>
          </div>

          <div class="bannerMenuList menuBorderRight menuBorderBottom">
            <router-link class="bannerLabelDecoration" to="/mypage/giftedit">
              <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 3 }" @click="menuOrderChange(3)"><label for="">선물 종류 변경</label></div>
            </router-link>
          </div>

          <div class="bannerMenuList menuBorderBottom">
            <router-link class="bannerLabelDecoration" to="/mypage/userdelete">
              <div class="bannerMenuLabel" :class="{ nowOrder: menuOrder == 5 }" @click="menuOrderChange(5)"><label for="">회원 탈퇴</label></div>
            </router-link>
          </div>
        </div>
      </div>

      <div class="mypageView">
        <router-view></router-view>
      </div>
    </div>
  </v-container>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      menuOrder: 0,
      menuLst: [
        { id: "MyInfo", menuName: "내 정보 확인" },
        { id: "PasswordEdit", menuName: "비밀번호 수정" },
        { id: "MusicEdit ", menuName: "취향 음악 변경" },
        { id: "GiftEdit", menuName: "취향 선물 변경" },
        { id: "FontEdit", menuName: "글꼴 변경" },
        { id: "UserDelete", menuName: "회원 탈퇴" },
      ],
    };
  },
  methods: {
    menuOrderChange(new_order) {
      this.menuOrder = new_order;
      console.log(this.menuOrder);
    },
  },
};
</script>

<style scoped>
.mypageTotalBody {
  margin-top: 2vw;
  width: 80%;
}
.mypageBody {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.mypageBanner {
  width: 25%;
  padding: 1% 5% 1% 1%;
}
.bannerTitleLabel {
  color: white;
  font-size: clamp(1.2rem, 2.5vw, 1.7rem);
  margin: 5% 0;
  padding-left: 5%;
}
.bannerMenuLabel {
  margin: 0;
  padding: 5%;
  width: 100%;
  color: white;
}
.bannerLabelDecoration {
  color: black;
  text-decoration: none;
  width: 100%;
}

.mypageView {
  width: 75%;
  padding: 1%;
}

.hrStyle {
  width: 100%;
}
.menuLstBox {
  background-color: antiquewhite;
}
.nowMenu {
  background-color: aquamarine;
}

/* 모바일에만 보이는 클래스 */
.banner {
  margin-bottom: 8%;
  margin-right: -6%;
  margin-left: -6%;
  margin-top: -6%;
}
.bannerMenuList {
  width: 33%;
  height: 6vh;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}
.menuBorderRight {
  border-right: 1px solid #ffffff;
}
.menuBorderTop {
  border-top: 1px solid #ffffff;
}
.menuBorderBottom {
  border-bottom: 1px solid #ffffff;
}

/* 배너 현재 효과 */
.nowOrder {
  background: rgba(255, 255, 255, 0.21);
  color: #44444d;
}

.phoneView {
  display: none;
}
@media (max-width: 1199px) {
  .mypageTotalBody {
    width: 90%;
  }
}
@media (max-width: 991px) {
  .mypageTotalBody {
    width: 100%;
  }
}
@media (max-width: 639px) {
  .pcView {
    display: none;
  }
  .phoneView {
    display: inline-block;
  }
  .mypageBody {
    flex-direction: column;
  }
  .mypageView {
    width: 100%;
    padding: 1%;
  }
  .banner {
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
  }
  .bannerMenuLabel {
    margin: 0;
    padding-left: 0;
    font-size: clamp(0.8rem, 2vw, 1.2rem);
  }
}
</style>
