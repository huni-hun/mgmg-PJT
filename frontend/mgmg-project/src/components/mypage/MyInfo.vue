<template>
  <v-container>
    <v-row>
      <label for="">내 정보 확인</label>
    </v-row>
    <v-row>
      <hr class="hrStyle" />
    </v-row>
    <v-row>
      <v-col><label for="">이름</label></v-col>
      <v-col
        ><label for="">{{ userName }}</label></v-col
      >
    </v-row>
    <v-row>
      <v-col><label for="">아이디</label></v-col>
      <v-col
        ><label for="">{{ userId }}</label></v-col
      >
    </v-row>
    <v-row>
      <v-col><label for="">이메일</label></v-col>
      <v-col
        ><label for="">{{ userEmail }}</label></v-col
      >
    </v-row>
    <v-row>
      <v-col><label for="">생년월일</label></v-col>
      <v-col
        ><label for="">{{ userBirth }}</label></v-col
      >
    </v-row>
    <v-row>
      <v-col><label for="">성별</label></v-col>
      <v-col
        ><label for="">{{ userGender }}</label></v-col
      >
    </v-row>
    <v-row>
      <CustomButton btnText="수정하기" @click="gotoInfoEdit" />
    </v-row>
  </v-container>
</template>

<script>
import { showUserInfo } from "@/api/userApi.js";
export default {
  data() {
    return {
      userBirth: "",
      userEmail: "",
      userGender: "",
      userId: "",
      userName: "",
    };
  },
  async created() {
    let response = await showUserInfo();
    console.log(response);
    this.userBirth = response.birth;
    this.userEmail = response.email;
    this.userGender = response.gender;
    this.userId = response.userId;
    this.userName = response.userName;
  },
  // getUserInfo() {
  //   axios
  //     .get(api_url.accounts.mypage_show_edit_delete(), {
  //       headers: {
  //         //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 헤더에 토큰
  //         Authorization: `Bearer ${this.$store.state.userStore.accessToken}`,
  //       },
  //     })
  //     .get((response) => {
  //       console.log(response.data);
  //       this.userBirth = response.data.birth;
  //       this.userEmail = response.data.email;
  //       this.userGender = response.data.gender;
  //       this.userId = response.data.userId;
  //       this.userName = response.data.name;
  //     });
  // },
  methods: {
    gotoInfoEdit() {
      this.$router.push("/my/infoedit");
    },
  },
};
</script>

<style scoped>
.hrStyle {
  border: 0.01rem solid #000000;
  width: 80rem;
}
.inputStyle:deep(fieldset) {
  /* border-color: rgb(255, 250, 250); */
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
  padding: 0;
}
</style>
