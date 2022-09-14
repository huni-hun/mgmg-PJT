<template>
  <v-form>
    <v-text-field single-line outlined :label="value.labelText" :rules="value.rules" :hint="value.hint" />
  </v-form>
</template>

<script>
export default {
  props: ["value"],

  methods: {
    updateDate() {
      this.$emit("input", {
        labelText: this.$refs.value,
        rules: this.$refs.value,
        hint: this.$refs.value,
      });
    },
  },
};
</script>

<style scoped>
.v-text-field >>> fieldset {
  /* border-color: rgb(255, 250, 250); */
  box-shadow: 1px 1px 10px 1px rgb(209, 213, 221);
  border-radius: 0px;
}
</style>
