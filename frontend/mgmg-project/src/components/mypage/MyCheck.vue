<template>
  <v-container>
    <v-row>
      <label for="">본인 인증</label>
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
      <CustomButton btnText="확인" />
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      pwMyCheckRequired: (v) => !!v || "인증번호는 필수값입니다.",
    };
  },
  methods: {
    
  }
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
