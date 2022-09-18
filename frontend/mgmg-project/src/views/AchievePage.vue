<template>
  <div>
    <v-container class="justify-center">
      <v-row>
        <v-col>
          <h1>나의업적</h1>
        </v-col>
      </v-row>
      <router-link to="/achieve">획득한 업적만 모아보기</router-link>
      <router-view></router-view>
    </v-container>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
