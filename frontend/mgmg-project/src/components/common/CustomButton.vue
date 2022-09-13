<template>
  <button class="customButton" v-on="this.$listeners" v-bind="$attrs">{{ btnText }}</button>
</template>

<script>
export default {
  props: {
    btnText: String,
  },
};
</script>

<style scoped>
button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;

  margin: 0;
  padding: 0.5rem 1rem;

  text-align: center;
  text-decoration: none;

  display: inline-block;
  width: auto;

  background-color: #ffffff;
  border: 1px solid #000000;
  border-radius: 10px;

  filter: drop-shadow(0px 0px 10px rgba(0, 0, 0, 0.25));

  cursor: pointer;

  transition: 0.2s;

  color: #6667ab;
}
button:hover {
  background-color: #727272;
  color: #000000;
}
</style>

