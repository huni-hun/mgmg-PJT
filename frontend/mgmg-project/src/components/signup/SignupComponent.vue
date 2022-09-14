<template>
  <v-app id="signupComponentApp">
    <div>회원가입</div>
    <v-container class="signupContainer" id="signupContainer">
      <v-col>
        <v-row>
          <label class="col-4" for="" id="">아이디</label>
          <input type="text" class="col-4" id="idSignupInput" placeholder="아이디를 입력하세요." />
          <div class="col-4">
            <custom-button btnText="중복확인" />
          </div>
        </v-row>
        <v-row>
          <label class="col-4" for="" id="">비밀번호</label>
          <input type="text" class="col-4" id="idSignupInput" placeholder="비밀번호를 입력하세요." />
          <div class="col-4"></div>
        </v-row>
        <v-row>
          <label class="col-4" for="" id="">비밀번호 확인</label>
          <input type="text" class="col-4" id="idSignupInput" placeholder="비밀번호를 다시 한 번 입력하세요." />
          <div class="col-4"></div>
        </v-row>
        <v-row>
          <label class="col-4" for="" id="">이메일</label>
          <input type="text" class="col-4" id="idSignupInput" placeholder="이메일을 입력하세요." />
          <div class="col-4">
            <custom-button btnText="인증하기" />
          </div>
        </v-row>
        <v-row>
          <label class="col-4" for="" id="">인증번호</label>
          <input type="text" class="col-4" id="idSignupInput" placeholder="인증번호를 입력하세요." />
          <div class="col-4">
            <custom-button btnText="확인" />
          </div>
        </v-row>
        <v-row>
          <label class="col-4" for="" id="">이름</label>
          <input type="text" class="col-4" id="idSignupInput" placeholder="이름을 입력하세요." />
          <div class="col-4"></div>
        </v-row>
        <v-row>
          <label class="col-4" for="" id="">생년월일</label>
          <input type="text" class="col-4" id="idSignupInput" placeholder="생일을 입력하세요." />
          <div class="col-4"></div>
        </v-row>
        <v-row>
          <label class="col-4" for="" id="">성별</label>
          <input type="text" class="col-4" id="idSignupInput" placeholder="비밀번호를 입력하세요." />
          <div class="col-4"></div>
        </v-row>
      </v-col>
    </v-container>
    <!-- <label for="" id="">아이디</label>
    <input type="text" id="idSignupInput" placeholder="아이디를 입력하세요." />
    <button>중복확인</button>
    <br />
    <label for="" id="">비밀번호</label>
    <input type="password" id="pwSignupInput" placeholder="비밀번호를 입력하세요." />
    <br />
    <label for="" id="">비밀번호 확인</label>
    <input type="password" id="pwCheckSignupInput" placeholder="비밀번호를 다시 한 번 입력하세요." />
    <br />
    <label for="" id="">이메일</label>
    <input type="text" id="emailSignupInput" placeholder="이메일을 입력하세요." />
    <button>인증하기</button>
    <br />
    <label for="" id="">인증번호</label>
    <input type="text" id="emailNumberSignupInput" placeholder="인증번호를 입력하세요." />
    <button>확인</button>
    <br />
    <label for="" id="">이름</label>
    <input type="text" id="nameSignupInput" placeholder="이름을 입력하세요." />
    <br />
    <label for="" id="">생년월일</label>
    <input type="text" id="birthSignupInput" placeholder="" />
    <br />
    <label for="" id="">성별</label>
    <input type="text" id="genderSignupInput" placeholder="" />
    <br /> -->
    <!-- <v-calendar></v-calendar> -->
    <!-- <v-date-picker></v-date-picker> -->
    <v-date-picker v-model="date" />
  </v-app>
</template>

<script>
import CustomButton from "../common/CustomButton.vue";
export default {
  components: { CustomButton },
  data() {
    return {
      date: new Date(),
    };
  },
};
</script>

<style scoped>
/* .signupContainer {
  width: 50rem;
} */
/* #signupComponentApp {
  margin: 0;
} */
</style>
