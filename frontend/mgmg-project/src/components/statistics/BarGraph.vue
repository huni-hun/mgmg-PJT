<template>
  <div>
    <div id="chart-range" class="relative h-350-px">
      <canvas id="bar-chart" height:=height width:=width></canvas>
    </div>
    <br />
    <div class="bar-data-board">
      <div v-if="nameData" class="circle">
        <img class="badge" :src="require(`@/assets/emoticon/${nameData}.png`)" alt="" />
      </div>
      <span id="check">
        요일 그래프를 선택해주세요!
      </span>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Chart from "chart.js";

import {
  statistics_day,
  statistics_day_detail,
} from "@/store/modules/etcStore";

export default {
  name: "BarChart",
  props: {
    width: {
      type: Number,
      default: 400,
    },
    height: {
      type: Number,
      default: 400,
    },
  },
  data() {
    return {
      dayData: Object,
      statistics: Object,
      chartData: {
        labels: ["월", "화", "수", "목", "금", "토", "일"],
        datasets: [],
      },
      nameData: '',
      isNameData: {
        슬픔: "sad",
        공포: "fear",
        피곤: "fatigue",
        화: "angry",
        기대: "expect",
        평온: "calm",
        창피: "shame",
        짜증: "annoyed",
        기쁨: "happy",
        사랑: "love",
      },
    };
  },
  async created() {
    this.dayData = await statistics_day(this.accessToken);
    this.statistics = this.dayData.statistics;
    this.chartData.datasets = [
      {
        label: "슬픔",
        data: this.statistics.슬픔,
        backgroundColor: "rgb(159, 164, 235)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,

      },
      {
        label: "공포",
        data: this.statistics.공포,
        backgroundColor: "rgb(130, 120, 164)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,
      },
      {
        label: "피곤",
        data: this.statistics.피곤,
        backgroundColor: "rgb(194, 197, 200)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,
      },
      {
        label: "화",
        data: this.statistics.화,
        backgroundColor: "rgb(240, 123, 120)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,
      },
      {
        label: "기대",
        data: this.statistics.기대,
        backgroundColor: "rgb(225, 245, 254)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,
      },
      {
        label: "평온",
        data: this.statistics.평온,
        backgroundColor: "rgb(255, 255, 255)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,
      },
      {
        label: "창피",
        data: this.statistics.창피,
        backgroundColor: "rgb(250, 191, 138)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,
      },
      {
        label: "짜증",
        data: this.statistics.짜증,
        backgroundColor: "rgb(223, 129, 185)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,
      },
      {
        label: "기쁨",
        data: this.statistics.기쁨,
        backgroundColor: "rgb(255, 231, 154)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,
      },
      {
        label: "사랑",
        data: this.statistics.사랑,
        backgroundColor: "rgb(248, 181, 175)",
        borderColor: "rgb(255, 255, 255)",
        borderWidth: 1,
      },
    ];
    const mq = window.matchMedia("(max-width: 639.5px)");  // 가로 크기 확인

    this.$nextTick(function () {
      if (mq.matches === true) { // 모바일 크기
        let config = {
          type: "horizontalBar",
          data: {
            labels: this.chartData.labels,
            datasets: this.chartData.datasets,
          },
          options: {
            responsive: true,
            aspectRatio: 0.75,
            title: {
              display: false,
              text: "Statistics Day",
            },
            tooltips: {
              mode: 'nearest'
            },
            width: 'auto',
            legend: {
              labels: {
                usePointStyle: true,
                fontColor: "rgba(0,0,0,1)",
                fontSize: 15
              },
            },
            scales: {
              xAxes: [
                {
                  stacked: true,
                  display: true,
                  scaleLabel: {
                    display: false,
                    labelString: "Day",
                  },
                  gridLines: {
                    borderDash: [2],
                    borderDashOffset: [2],
                    color: "rgba(33, 37, 41, 0.3)",
                    zeroLineColor: "rgba(33, 37, 41, 0.3)",
                    zeroLineBorderDash: [2],
                    zeroLineBorderDashOffset: [2],
                  },
                },
              ],
              yAxes: [
                {
                  stacked: true,
                  display: true,
                  scaleLabel: {
                    display: false,
                    labelString: "Value",
                  },
                  gridLines: {
                    borderDash: [2],
                    drawBorder: false,
                    borderDashOffset: [2],
                    color: "rgba(33, 37, 41, 0.2)",
                    zeroLineColor: "rgba(33, 37, 41, 0.15)",
                    zeroLineBorderDash: [2],
                    zeroLineBorderDashOffset: [2],
                  },
                },
              ],
            },
            onClick: (event) => {
              var activePoints = window.myBar.getElementsAtEventForMode(
                event,
                "point",
                window.myBar.options
              );
              if (activePoints.length > 0) {
                var firstPoint = activePoints[0];
                var label = window.myBar.data.labels[firstPoint._index];

                this.checkDay(label);
              }
            },
          },
        };

        let ctx = document.getElementById("bar-chart").getContext("2d");
        window.myBar = new Chart(ctx, config);
      } else {  //================================== 데스크탑 크기
        let config = {
          type: "bar",
          data: {
            labels: this.chartData.labels,
            datasets: this.chartData.datasets,
          },
          options: {
            responsive: false,
            title: {
              display: false,
              text: "Statistics Day",
            },
            width: '100%',
            legend: {
              labels: {
                usePointStyle: true,
                fontColor: "rgba(0,0,0,1)",
                fontSize: 15
              },
            },
            scales: {
              xAxes: [
                {
                  stacked: true,
                  display: true,
                  scaleLabel: {
                    display: false,
                    labelString: "Day",
                  },
                  gridLines: {
                    borderDash: [2],
                    borderDashOffset: [2],
                    color: "rgba(33, 37, 41, 0.3)",
                    zeroLineColor: "rgba(33, 37, 41, 0.3)",
                    zeroLineBorderDash: [2],
                    zeroLineBorderDashOffset: [2],
                  },
                },
              ],
              yAxes: [
                {
                  stacked: true,
                  display: true,
                  scaleLabel: {
                    display: false,
                    labelString: "Value",
                  },
                  gridLines: {
                    borderDash: [2],
                    drawBorder: false,
                    borderDashOffset: [2],
                    color: "rgba(33, 37, 41, 0.2)",
                    zeroLineColor: "rgba(33, 37, 41, 0.15)",
                    zeroLineBorderDash: [2],
                    zeroLineBorderDashOffset: [2],
                  },
                },
              ],
            },
            onClick: (event) => {
              var activePoints = window.myBar.getElementsAtEventForMode(
                event,
                "point",
                window.myBar.options
              );
              if (activePoints.length > 0) {
                var firstPoint = activePoints[0];
                var label = window.myBar.data.labels[firstPoint._index];

                this.checkDay(label);
              }
            },
          },
        };

        let ctx = document.getElementById("bar-chart").getContext("2d");
        window.myBar = new Chart(ctx, config);
      }
    });
  },
  methods: {
    async checkDay(label) {
      let day;
      switch (label) {
        case "월":
          day = "Mon";
          break;
        case "화":
          day = "Tue";
          break;
        case "수":
          day = "Wed";
          break;
        case "목":
          day = "Thu";
          break;
        case "금":
          day = "Fri";
          break;
        case "토":
          day = "Sat";
          break;
        default:
          day = "Sun";
          break;
      }
      let result = await statistics_day_detail(this.accessToken, day);
      const element = document.getElementById("check");
      element.innerHTML =
        label + "요일에는 " + result.statistics + " 감정이 가장 많아요!";
      this.nameData = this.isNameData[result.statistics]
    },
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
    x() {
      return this.realw * 0.8
    },
    y() {
      return this.realh * 0.4
    }
  }
};
</script>
<style scoped>
.circle {
  background: #ffffff;
  border-radius: 50%;
  margin-right: 1rem;
}

/* 차트 그래프 */
#bar-chart {
  height: 58vh;
  width: auto;
  margin-left: auto;
  margin-right: auto;
}

/* 하단 글씨 */
#check {
  font-size: 1.5rem;
}

/* 몽글이 이미지 */
.badge {
  filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, 0.2));
  vertical-align: middle;
  height: 6vh;
  margin: 0.5rem;
}

/* 그래프 뒷배경 */
#chart-range {
  display: flex;
  align-items: center;
  background-color: rgba(226, 226, 226, 0.356);
  height: 60vh;
}

/* 글자 뒤 */
.bar-data-board {
  background-color: rgba(226, 226, 226, 0.356);
  height: 10vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

@media (max-width: 1023px) {
  #bar-chart {
    height: 48vh;
    width: 100%;
  }

  .badge {
    vertical-align: middle;
    height: 6vh;
  }

  #chart-range {
    height: 50vh;
  }

  .bar-data-board {
    height: 10vh;
    text-align: center;
  }

}

@media (max-width: 960px) {

  /* #bar-chart{
    height: 39vh;
    width: 26vh;
  } */
  #chart-range {
    height: 60vh;
    width: 80vw;
  }

  #check {
    font-size: 1rem;
  }

}

/* 작은 태블릿 세로*/
@media (max-width: 767px) {
  .circle {
    margin-right: 0.5rem;
  }

  #bar-chart {
    height: 39vh;
    width: 26vh;
  }

  #chart-range {
    height: 60vh;
    width: auto;
  }

  .badge {
    vertical-align: middle;
    height: 4vh;
    margin: 0.3rem;
  }
}

/* 스마트폰 세로 */
@media (max-width: 639px) {
  #check {
    font-size: 0.5rem;
    margin-top: auto;
    margin-bottom: auto;
  }

}

/* 갤럭시 폴드 */
@media (max-width: 300px) {}
</style>