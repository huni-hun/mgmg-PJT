<template>
  <div>
    <label for="" id="">아이디</label>
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
    <br />
    <!-- <v-calendar></v-calendar> -->
    <!-- <v-date-picker></v-date-picker> -->
    <v-date-picker v-model="date" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      date: new Date(),
    };
  },
};
</script>

<style></style>

