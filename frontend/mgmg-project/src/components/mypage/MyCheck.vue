<template>
  <div class="myCheckBody">
    <div class="titleLabel">
      <label for="">본인 인증</label>
    </div>

    <div>
      <hr class="hrStyle" />
    </div>

    <div class="myCheckMiddle">
      <div class="myCheckMiddleTitle">
        <label for="">비밀번호 재확인</label>
      </div>

      <div>
        <label for="">회원 정보 보호를 위해 비밀번호를 다시 확인합니다.</label>
      </div>
    </div>

    <div>
      <hr class="hrStyle" />
    </div>

    <div class="myCheckInputLine">
      <div class="myCheckPwLabel">
        <label for="">비밀번호</label>
      </div>

      <div class="inputStyle myCheckPwInput">
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
    </div>
    <div class="myCheckButtonLine">
      <CustomButton class="myCheckButton" btnText="확인" @click="myCheck" />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { myCheck } from "@/api/userApi.js";
import Swal from "sweetalert2";
export default {
  data() {
    return {
      pwMyCheckRequired: (v) => !!v || "인증번호는 필수값입니다. ",
    };
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    async myCheck() {
      var check_pw = document.getElementById("pwMyCheckInput").value;

      const request = {
        password: check_pw,
      };
      await myCheck(this.accessToken, request)
        .then((res) => {
          console.log(res);
          this.$router.push("/mypage/myinfo");
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            text: "입력하신 회원 정보와 일치하는 정보가 없습니다.",
            icon: "warning",
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
.myCheckBody {
  width: 60%;
  margin-right: 20%;
  margin-left: 20%;
  margin-top: 5vh;
  padding: 5% 8%;
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 0px 20px 20px rgba(0, 0, 0, 0.2);
}
.titleLabel {
  font-size: clamp(1.5rem, 5vw, 2.5rem);
  margin: 3% 0% 0.5% 0%;
}
.myCheckMiddle {
  margin: 10%;
}
.myCheckMiddleTitle {
  margin-bottom: 3%;
  font-size: clamp(1rem, 5vw, 2rem);
}

.myCheckInputLine {
  margin-top: 3%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
}
.myCheckPwLabel {
  width: 30%;
  padding-left: 10%;
}
.myCheckPwInput {
  width: 70%;
  padding: 0 10%;
}

.myCheckButtonLine {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 3%;
}
.myCheckButton {
  width: 30%;
}

.hrStyle {
  width: 100%;
}
.inputStyle:deep(fieldset) {
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
  padding: 0;
}

@media (max-width: 1199px) {
  .myCheckBody {
    width: 90%;
    margin-right: 0%;
    margin-left: 0%;
  }
}
@media (max-width: 991px) {
  .myCheckBody {
    width: 100%;
  }
}
@media (max-width: 639px) {
  .myCheckBody {
    width: 100%;
  }
  .myCheckMiddle {
    margin: 10% 0;
  }
  .myCheckPwLabel {
    width: 100%;
    padding-left: 0%;
    margin: 2% 0;
  }
  .myCheckPwInput {
    width: 100%;
    padding: 0;
  }
  .myCheckInputLine {
    margin-top: 3%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
  }
}
</style>
