<template>
  <v-container>
    <v-row>
      <label for="" @click="test">본인 인증</label>
    </v-row>
    <v-row>
      <hr class="hrStyle" />
    </v-row>
    <v-row>
      <label for="">비밀번호 재확인</label>
    </v-row>
    <v-row>
      <label for="">회원 정보 보호를 위해 비밀번호를 다시 확인합니다.</label>
    </v-row>
    <v-row>
      <hr class="hrStyle" />
    </v-row>
    <v-row>
      <v-col>
        <label for="">비밀번호</label>
        <div class="inputStyle">
          <v-text-field
            :rules="[pwMyCheckRequired]"
            :type="'password'"
            label="비밀번호를 입력하세요."
            name="input-10-2"
            hint="비밀번호를 입력하세요."
            value=""
            single-line
            outlined
            id="pwMyCheckInput"
          ></v-text-field>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <CustomButton btnText="확인" @click="myCheck" />
    </v-row>
  </v-container>
</template>

<script>
import { myCheck } from "@/api/userApi.js";
import Swal from "sweetalert2";
export default {
  data() {
    return {
      pwMyCheckRequired: (v) => !!v || "인증번호는 필수값입니다. ",
    };
  },
  methods: {
    test() {
      // console.log(this.$store.state.userStore.userId);
      console.log(this.$store.state.userStore.accessToken);
    },
    async myCheck() {
      var check_pw = document.getElementById("pwMyCheckInput").value;

      const request = {
        password: check_pw,
      };
      await myCheck(request)
        .then((res) => {
          console.log(res);
          this.$router.push("/my/myinfo");
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            // toast: true,
            // title: "ID 누락",
            text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
            icon: "warning",
            // iconColor: "#000000",
            confirmButtonColor: "#666666",
            confirmButtonText: "확인",
            // },
          });
        });
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
