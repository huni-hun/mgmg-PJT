<template>
  <div class="myInfoTotalBody">
    <div class="titleLabel">
      <label for="">내 정보 확인</label>
    </div>

    <div>
      <hr class="hrStyle" />
    </div>

    <div class="myInfoBody">
      <div class="myInfoLine">
        <div class="myInfoBodyLabel"><label for="">이름</label></div>
        <div class="myInfoBodyContent">
          <label for="">{{ userName }}</label>
        </div>
      </div>

      <div class="myInfoLine">
        <div class="myInfoBodyLabel"><label for="">아이디</label></div>
        <div>
          <label class="myInfoBodyContent" for="">{{ userId }}</label>
        </div>
      </div>

      <div class="myInfoLine">
        <div class="myInfoBodyLabel"><label for="">이메일</label></div>
        <div>
          <label class="myInfoBodyContent" for="">{{ userEmail }}</label>
        </div>
      </div>

      <div class="myInfoLine">
        <div class="myInfoBodyLabel"><label for="">생년월일</label></div>
        <div>
          <label class="myInfoBodyContent" for="">{{ userBirth }}</label>
        </div>
      </div>

      <div class="myInfoLine">
        <div class="myInfoBodyLabel"><label for="">성별</label></div>
        <div>
          <label class="myInfoBodyContent" for="">{{ userGender }}</label>
        </div>
      </div>

      <div class="myInfoButtonLine">
        <CustomButton class="myInfoButton" btnText="수정하기" @click="gotoInfoEdit" />
      </div>
    </div>
  </div>
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
.myInfoTotalBody {
  width: 100%;
  padding: 7% 10%;
  display: flex;
  flex-direction: column;
  background-color: white;
}

.titleLabel {
  font-size: clamp(1.2rem, 2.5vw, 1.8rem);
  margin-bottom: 0.5%;
}

.myInfoBody {
  padding: 5% 5% 0% 5%;
}
.myInfoLine {
  display: flex;
  flex-direction: row;
  margin-bottom: 2%;
}
.myInfoBodyLabel {
  width: 20%;
}
.myInfoBodyContent {
  width: 80%;
}
.myInfoButtonLine {
  width: 100%;
  margin-top: 7%;
  display: flex;
  justify-content: center;
}
.myInfoButton {
  width: 25%;
}

.hrStyle {
  border: 0.01rem solid #000000;
}

.inputStyle:deep(fieldset) {
  /* border-color: rgb(255, 250, 250); */
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
  padding: 0;
}
</style>
