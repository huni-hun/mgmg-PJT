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
import { mapState } from "vuex";
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
    let response = await showUserInfo(this.accessToken);
    console.log(response);
    this.userBirth = response.birth;
    this.userEmail = response.email;
    this.userGender = response.gender;
    this.userId = response.userId;
    this.userName = response.userName;
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    gotoInfoEdit() {
      this.$router.push("/mypage/infoedit");
    },
  },
};
</script>

<style scoped>
.myInfoTotalBody {
  width: 100%;
  margin-top: 5vh;
  padding: 7% 10%;
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 0px 20px 20px rgba(0, 0, 0, 0.2);
}

.titleLabel {
  font-size: clamp(1.2rem, 2.5vw, 1.8rem);
  margin: 3% 0% 0.5% 0%;
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
  width: 25%;
}
.myInfoBodyContent {
  width: 75%;
}
.myInfoButtonLine {
  width: 100%;
  margin-top: 10%;
  display: flex;
  justify-content: center;
}
.myInfoButton {
  width: 50%;
}

.hrStyle {
  width: 100%;
}

.inputStyle:deep(fieldset) {
  /* border-color: rgb(255, 250, 250); */
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
  padding: 0;
}

@media (max-width: 639px) {
  .myInfoBodyLabel {
    width: 30%;
  }
  .myInfoBodyContent {
    width: 70%;
  }
  .myInfoTotalBody {
    margin-top: 0;
  }
}
</style>
