<template>
  <div class="userDeleteTotalBody">
    <div class="userDeleteTitle">
      <div class="titleLabel">
        <label for="">회원 탈퇴</label>
      </div>
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
      <CustomButton class="userDeleteButton" btnText="탈퇴" @click="userDelete" />
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
      await deleteUser(this.accessToken)
        .then((res) => {
          console.log(res);
          this.$cookies.remove("autoLoginCookie");
          this.$cookies.remove("userIdCookie");
          sessionStorage.clear();

          Swal.fire({
            text: "회원 탈퇴가 정상적으로 처리되었습니다.",
            icon: "success",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
          });
          this.$router.push("/login");
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
};
</script>

<style scoped>
.userDeleteTotalBody {
  width: 100%;
  /* margin-top: 10vh; */
  padding: 7% 10%;
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 0px 20px 20px rgba(0, 0, 0, 0.2);
}

.userDeleteTitle {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: left;
  align-items: center;
}

.titleLabel {
  font-size: clamp(1.5rem, 5vw, 2.2rem);
  margin: 3% 0% 0.5% 0%;
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
  /* width: 50%; */
  padding-right: 9%;
  padding-left: 9%;
  background-color: #fa5f72;
}

.userDeleteButton:hover {
  background-color: #ac424f;
}
</style>
