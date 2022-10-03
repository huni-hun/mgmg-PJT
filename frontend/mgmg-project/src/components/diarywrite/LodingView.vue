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
            </div>
            <v-progress-linear class="progress" :color=this.colors[this.views.indexOf(this.view,0)] height="8"
                indeterminate rounded striped bottom />
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

<style scoped>
.loading {
    position: relative;
    z-index: 1;
    margin: 0 auto;
    text-align: center;
    box-shadow: rgba(0, 0, 0, 0.4) 0 0 0 9999px;
}

.backImg {
    position: absolute;
    max-width: 500px;
    width: 88vw;
    z-index: 2;
    left: 50%;
    transform: translate(-50%, 0%);
}

.inDiv {
    position: absolute;
    z-index: 3;
    width: 100%;
    height: 50%;
    left: 40%;
    transform: translate(-50%, 0%);
    margin: 0 auto;
}

.context {
    position: relative;
    width: 50%;
    height: 50%;
    display: flex;
    align-items: center;
    flex-direction: column;
    top: 70px;
    left: 70%;
    transform: translate(-70%, 0%);
}

.emoticonImg {
    min-width: 140px;
    width: 40%;
    margin: 30px 0px 10px 0px;
}

.progress {
    position: absolute;
    max-width: 450px;
    width: 50%;
    top: 50%;
    left: 60%;
    transform: translate(-50%, 50%);
}
</style>