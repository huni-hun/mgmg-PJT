<template>
  <div>
    <h1>Mypage입니다.</h1>
    <MyCheck />
    <MyInfo />
    <InfoEdit />
    <PasswordEdit />
    <MusicEdit />
    <GiftEdit />
    <FontEdit />
    <UserDelete />
  </div>
</template>

<script>
import MyCheck from "@/components/mypage/MyCheck.vue";
import MyInfo from "@/components/mypage/MyInfo.vue";
import InfoEdit from "@/components/mypage/InfoEdit.vue";
import PasswordEdit from "@/components/mypage/PasswordEdit.vue";
import MusicEdit from "@/components/mypage/MusicEdit.vue";
import GiftEdit from "@/components/mypage/GiftEdit.vue";
import FontEdit from "@/components/mypage/FontEdit.vue";
import UserDelete from "@/components/mypage/UserDelete.vue";
export default { components: { MyCheck, MyInfo, InfoEdit, PasswordEdit, MusicEdit, GiftEdit, FontEdit, UserDelete } };
</script>

<style></style>
