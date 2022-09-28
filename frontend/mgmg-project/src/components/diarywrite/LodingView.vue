<template>
    <div class="loading">
        <v-progress-linear color="light-green darken-4" height="8" indeterminate striped />
        <p>음성데이터를 글씨로 변환중입니다.</p>
        <v-progress-linear color="light-green darken-4" height="8" indeterminate striped />
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.loading {
    z-index: 2;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    box-shadow: rgba(0, 0, 0, 0.1) 0 0 0 9999px;
}

.loading>* {
    z-index: 2;
    box-shadow: rgba(0, 0, 0, 0.1) 0 0 0 9999px;
}
</style>