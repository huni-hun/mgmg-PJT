<template>
    <div class="loading">
        <v-img class="backImg" :src="require(`@/assets/diary/loading/${view}.png`)" />
        <div class="inDiv">
            <div class="context">
                <v-img class="emoticonImg"
                    :src="require(`@/assets/emoticon/${this.emoImgs[ this.views.indexOf(this.view, 0)]}.png`)" />
                <h3>
                    <p>{{this.title[ this.views.indexOf(this.view, 0)]}}</p>
                </h3>
                <p>{{this.subTitle[ this.views.indexOf(this.view, 0)]}}</p>
                <div class="progress">
                    <v-progress-linear :color=this.colors[this.views.indexOf(this.view,0)] height="8" indeterminate
                        rounded striped bottom />
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    props: ["view"],
    data: function () {
        return {
            views: ["predict", "recording", "convert"],
            emoImgs: ["question", "expect", "happy"],
            title: ["일기 감정 분석 중...", "음성 녹음 중...", "음성을 텍스트로 변환 중..."],
            subTitle: ["어떤 몽글이를 만나게 될까요?", "녹음이 끝났다면 녹음 중지 버튼을 눌러주세요!", "잠시만 기다려주세요!"],
            colors: ["light-green darken-2", "blue darken-1", "yellow darken-2"],
        }
    },
}
</script>

<style scoped src="@/styles/diary/LodingView.css">

</style>