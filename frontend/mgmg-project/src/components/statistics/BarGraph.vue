<template>
  <div>
    <div class="relative h-350-px">
      <canvas id="bar-chart" height:=height width:=width></canvas>
    </div>
    <h3 id="check">요일 그래프를 선택하세요.</h3>
  </div>
</template>

<script>
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
    };
  },
  async created() {
    this.dayData = await statistics_day();
    this.statistics = this.dayData.statistics;
    this.chartData.datasets = [
      {
        label: "슬픔",
        data: this.statistics.슬픔,
        backgroundColor: "rgb(118, 127, 227)", 
      },
      {
        label: "공포",
        data: this.statistics.공포,
        backgroundColor: "rgb(110, 98, 146)", 
      },
      {
        label: "피곤",
        data: this.statistics.피곤,
        backgroundColor: "rgb(177, 181, 184)", 
      },
      {
        label: "화",
        data: this.statistics.화,
        backgroundColor: "rgb(238, 101, 99)", 
      },
      {
        label: "기대",
        data: this.statistics.기대,
        backgroundColor: "rgb(225, 245, 254)",
      },
      {
        label: "평온",
        data: this.statistics.평온,
        backgroundColor: "rgb(255, 255, 255)", 
      },
      {
        label: "창피",
        data: this.statistics.창피,
        backgroundColor: "rgb(249, 181, 118)",
      },
      {
        label: "짜증",
        data: this.statistics.짜증,
        backgroundColor: "rgb(215, 95, 166)",
      },
      {
        label: "기쁨",
        data: this.statistics.기쁨,
        backgroundColor: "rgb(255, 231, 154)",
      },
      {
        label: "사랑",
        data: this.statistics.사랑,
        backgroundColor: "rgb(248, 181, 175)", 
      },
    ];

    this.$nextTick(function () {
      let config = {
        type: "bar",
        data: {
          labels: this.chartData.labels,
          datasets: this.chartData.datasets,
        },
        options: {
          responsive: true,
          title: {
            display: false,
            text: "Statistics Day",
          },
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

      let result = await statistics_day_detail(day);
      console.log(result);
      const element = document.getElementById("check");
      element.innerHTML =
        label + "요일에는 " + result.statistics + " 감정이 가장 많아요!";
    },
  },
};
</script>
