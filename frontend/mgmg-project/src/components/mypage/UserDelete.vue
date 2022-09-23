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
        <CustomButton btnText="탈퇴하기" @click="userDelete" />
      </v-row>
    </v-container>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import { deleteUser } from "@/api/userApi.js";

export default {
  methods: {
    async userDelete() {
      let response = await deleteUser();
      console.log("응답 데이터", response);
      if (response.statusCode == 200) {
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
      }
    },
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

<style scoped></style>
