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
  padding: 0.5rem 1.5rem;

  text-align: center;
  text-decoration: none;

  display: inline-block;
  width: auto;

  background-color: #51516E;
  color: white;
  border-radius: 5px;

  cursor: pointer;

  transition: 0.2s;

}

button:hover {
  background-color: #69698d;
  box-shadow: inset 0px 4px 4px rgba(0, 0, 0, 0.25);
  color: white;
}
</style>

