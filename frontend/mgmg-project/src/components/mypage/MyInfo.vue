<template>
  <v-container>
    <v-row>
      <label for="">내 정보 확인</label>
    </v-row>
    <v-row>
      <hr class="hrStyle" />
    </v-row>
    <v-row>
      <v-col><label for="">이름</label></v-col>
      <v-col><label for="">김민영</label></v-col>
    </v-row>
    <v-row>
      <v-col><label for="">아이디</label></v-col>
      <v-col><label for="">min4849</label></v-col>
    </v-row>
    <v-row>
      <v-col><label for="">이메일</label></v-col>
      <v-col><label for="">min4849@naver.com</label></v-col>
    </v-row>
    <v-row>
      <v-col><label for="">생년월일</label></v-col>
      <v-col><label for="">1998-08-26</label></v-col>
    </v-row>
    <v-row>
      <v-col><label for="">성별</label></v-col>
      <v-col><label for="">여성</label></v-col>
    </v-row>
    <v-row>
      <CustomButton btnText="수정하기" />
    </v-row>
  </v-container>
</template>

<script>
export default {};
</script>

<style scoped>
.hrStyle {
  border: 0.01rem solid #000000;
  width: 80rem;
}
.inputStyle >>> fieldset {
  /* border-color: rgb(255, 250, 250); */
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
  padding: 0;
}
</style>
