<template>
  <div>
    <v-container>
      <v-row>
        <label for="">회원 탈퇴</label>
      </v-row>
      <v-row>
        <label for="">※ 회원 탈퇴 시 모든 개인 정보는 삭제됩니다.</label>
      </v-row>
      <v-row>
        <label for="">※ 탈퇴 처리 후에는 아이디 및 데이터를 복구할 수 없습니다.</label>
      </v-row>
      <v-row>
        <CustomButton btnText="탈퇴하기" />
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import api_url from "@/api/index.js";
import Swal from "sweetalert2";
export default {
  userDelete() {
    axios
      .delete(api_url.accounts.mypage_show_edit_delete(), {
        headers: {
          //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 헤더에 토큰
          Authorization: `Bearer ${this.$store.state.userStore.accessToken}`,
        },
      })
      .then((response) => {
        console.log(response);
        Swal.fire({
          text: "정상적으로 탈퇴 처리 되었습니다.",
          icon: "success",
          // iconColor: "#000000",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      })
      .catch((err) => {
        console.log(err);
        Swal.fire({
          text: "데이터 전송에 실패했습니다.",
          icon: "warning",
          // iconColor: "#000000",
          confirmButtonColor: "#666666",
          confirmButtonText: "확인",
        });
      });
  },
};
</script>

<style scoped></style>
