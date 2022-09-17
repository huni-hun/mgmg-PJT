<template>
  <v-container>
    <h1>Mypage입니다.</h1>
    <v-row>
      <v-col class="col-3">
        <v-container>
          <label for="">마이페이지</label>
          <hr class="hrStyle" />
          <router-link to="/my/mycheck">
            <div><label for="">본인 인증</label></div>
          </router-link>
          <hr class="hrStyle" />
          <router-link to="/my/myinfo">
            <div><label for="">내 정보 확인</label></div>
          </router-link>
          <hr class="hrStyle" />
          <router-link to="/my/infoedit">
            <div><label for="">회원 정보 변경</label></div>
          </router-link>
          <hr class="hrStyle" />
          <router-link to="/my/passwordedit">
            <div><label for="">비밀번호 수정</label></div>
          </router-link>
          <hr class="hrStyle" />
          <router-link to="/my/musicedit">
            <div><label for="">음악 장르 변경</label></div>
          </router-link>
          <hr class="hrStyle" />
          <router-link to="/my/giftedit">
            <div><label for="">선물 종류 변경</label></div>
          </router-link>
          <hr class="hrStyle" />
          <router-link to="/my/fontedit">
            <div><label for="">글꼴 변경</label></div>
          </router-link>
          <hr class="hrStyle" />
          <router-link to="/my/userdelete">
            <div><label for="">회원 탈퇴</label></div>
          </router-link>
          <hr class="hrStyle" />
          <!-- <div v-for="menu in menuLst" :key="menu.id" @click="menuOrderChange(menu.id)">
            <label for="" class="menuLstBox" :class="{ nowMenu: menu.id == menuOrder }">{{ menu.menuName }}</label>
            <hr class="hrStyle" />
          </div> -->
          <!-- <label for="">회원 정보 수정</label>
          <hr class="hrStyle" />
          <label for="">비밀번호 수정</label>
          <hr class="hrStyle" />
          <label for="">음악 장르 변경</label>
          <hr class="hrStyle" />
          <label for="">선물 종류 변경</label>
          <hr class="hrStyle" />
          <label for="">글꼴 변경</label>
          <hr class="hrStyle" />
          <label for="">회원 탈퇴</label>
          <hr class="hrStyle" /> -->
        </v-container>
      </v-col>
      <v-col class="col-9">
        <router-view></router-view>
        <!-- <MyCheck v-if="menuOrder == 'MyCheck'" />
        <MyInfo v-if="menuOrder == 'MyInfo'" />
        <InfoEdit v-if="menuOrder == 'InfoEdit'" />
        <PasswordEdit v-if="menuOrder == 'PasswordEdit'" />
        <MusicEdit v-if="menuOrder == 'MusicEdit'" />
        <GiftEdit v-if="menuOrder == 'GiftEdit'" />
        <FontEdit v-if="menuOrder == 'FontEdit'" />
        <UserDelete v-if="menuOrder == 'UserDelete'" /> -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// import MyCheck from "@/components/mypage/MyCheck.vue";
// import MyInfo from "@/components/mypage/MyInfo.vue";
// import InfoEdit from "@/components/mypage/InfoEdit.vue";
// import PasswordEdit from "@/components/mypage/PasswordEdit.vue";
// import MusicEdit from "@/components/mypage/MusicEdit.vue";
// import GiftEdit from "@/components/mypage/GiftEdit.vue";
// import FontEdit from "@/components/mypage/FontEdit.vue";
// import UserDelete from "@/components/mypage/UserDelete.vue";
export default {
  components: {},
  data() {
    return {
      menuOrder: "MyCheck",
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
