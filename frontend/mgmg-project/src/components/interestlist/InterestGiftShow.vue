<template>
  <v-container>
    <v-row>
      <div v-for="gift in giftLst" :key="gift.name" class="col-3">
        <div>
          <div>image</div>
          <div>{{ gift.name }}</div>
          <div>가격: {{ gift.price }}</div>
          <div>링크: {{ gift.link }}</div>
          <div>삭제아이콘</div>
        </div>
      </div>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      giftLst: [
        { name: "name1", price: 1000, link: "link1" },
        { name: "name2", price: 2000, link: "link2" },
        { name: "name3", price: 3000, link: "link3" },
      ],
    };
  },
};
</script>

<style></style>
