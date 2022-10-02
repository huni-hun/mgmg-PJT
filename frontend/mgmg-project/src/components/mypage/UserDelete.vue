<template>
  <div class="userDeleteTotalBody">
    <div class="userDeleteTitle">
      <div class="titleLabel">
        <label for="">회원 탈퇴</label>
      </div>
      <!-- <div>
        <img class="titleImg" src="../../assets/emoticon/sad.png" alt="" />
      </div> -->
    </div>
    <div class="userDeleteBody">
      <div class="userDeleteWarning">
        <label for="">※ 회원 탈퇴 시 모든 개인 정보는 삭제됩니다.</label>
      </div>
      <div>
        <label for="">※ 탈퇴 처리 후에는 아이디 및 데이터를 복구할 수 없습니다.</label>
      </div>
    </div>
    <div class="userDeleteButtonLine">
      <CustomButton class="userDeleteButton" btnText="탈퇴하기" @click="userDelete" />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Swal from "sweetalert2";
import { deleteUser } from "@/api/userApi.js";

export default {
  methods: {
    async userDelete() {
      console.log("회원탈퇴");
      await deleteUser(this.accessToken)
        .then((res) => {
          console.log(res);
          this.$cookies.remove("autoLoginCookie");
          this.$cookies.remove("userIdCookie");

          Swal.fire({
            text: "회원 탈퇴가 정상적으로 처리되었습니다.",
            icon: "success",
            // iconColor: "#000000",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
          this.$router.push("/main");
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            text: "잠시후 다시 시도해주세요.",
            icon: "warning",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
        });
    },
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  // userDelete() {
  //   axios
  //     .delete(api_url.accounts.mypage_show_edit_delete(), {
  //       headers: {
  //         //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 헤더에 토큰
  //         Authorization: `Bearer ${this.$store.state.userStore.accessToken}`,
  //       },
  //     })
  //     .then((response) => {
  //       console.log(response);
  //       Swal.fire({
  //         text: "정상적으로 탈퇴 처리 되었습니다.",
  //         icon: "success",
  //         // iconColor: "#000000",
  //         confirmButtonColor: "#666666",
  //         confirmButtonText: "확인",
  //       });
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //       Swal.fire({
  //         text: "데이터 전송에 실패했습니다.",
  //         icon: "warning",
  //         // iconColor: "#000000",
  //         confirmButtonColor: "#666666",
  //         confirmButtonText: "확인",
  //       });
  //     });
  // },
};
</script>

<style scoped>
.userDeleteTotalBody {
  width: 100%;
  padding: 7% 10%;
  display: flex;
  flex-direction: column;
  background-color: white;
}
.userDeleteTitle {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: left;
  align-items: center;
}
.titleLabel {
  font-size: clamp(1.2rem, 2.5vw, 1.8rem);
  margin: 3% 0% 0.5% 0%;
  /* width: 70%; */
  /* display: block; */
}
.titleImg {
  width: 5%;
}

.userDeleteBody {
  margin-top: 5%;
}
.userDeleteWarning {
  margin-bottom: 2%;
}

.userDeleteButtonLine {
  width: 100%;
  margin-top: 10%;
  display: flex;
  justify-content: center;
}
.userDeleteButton {
  width: 50%;
  background-color: #fa5f72;
}
.userDeleteButton:hover {
  background-color: #ac424f;
}
</style>
