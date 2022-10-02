<template>
  <v-container class="mypageTotalBody">
    <div class="mypageBody">
      <div class="mypageBanner">
        <div class="bannerTitleLabel">
          <label for="">마이페이지</label>
        </div>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/my/myinfo">
          <div class="bannerMenuLabel"><label for="">내 정보 확인</label></div>
        </router-link>
        <hr class="hrStyle" />

        <!-- <router-link class="bannerLabelDecoration" to="/my/infoedit">
          <div class="bannerMenuLabel"><label for="">회원 정보 변경</label></div>
        </router-link>
        <hr class="hrStyle" /> -->

        <router-link class="bannerLabelDecoration" to="/my/passwordedit">
          <div class="bannerMenuLabel"><label for="">비밀번호 수정</label></div>
        </router-link>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/my/musicedit">
          <div class="bannerMenuLabel"><label for="">음악 장르 변경</label></div>
        </router-link>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/my/giftedit">
          <div class="bannerMenuLabel"><label for="">선물 종류 변경</label></div>
        </router-link>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/my/fontedit">
          <div class="bannerMenuLabel"><label for="">글꼴 변경</label></div>
        </router-link>
        <hr class="hrStyle" />

        <router-link class="bannerLabelDecoration" to="/my/userdelete">
          <div class="bannerMenuLabel"><label for="">회원 탈퇴</label></div>
        </router-link>
        <hr class="hrStyle" />
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
  /* height: 50vh; */
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
  font-size: clamp(1.2rem, 2.5vw, 1.8rem);
  margin: 5% 0;
  padding-left: 5%;
}
.bannerMenuLabel {
  margin: 3% 0;
  padding-left: 5%;
}
.bannerLabelDecoration {
  color: black;
  text-decoration: none;
}

.mypageView {
  width: 75%;
  padding: 1%;
}

.hrStyle {
  border: 0.01rem solid #000000;
  width: 100%;
}
.menuLstBox {
  background-color: antiquewhite;
}
.nowMenu {
  background-color: aquamarine;
}
</style>
