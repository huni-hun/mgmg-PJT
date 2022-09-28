<template>
  <div>
    <div class="relative h-350-px">
      <canvas id="donut-chart" height:=height width:=width></canvas>
    </div>
  </div>
</template>

<script>
import Chart from "chart.js";
import { statistics_percent } from "@/store/modules/etcStore";
import moment from "moment";

export default {
  name: "DoughnutChart",
  props: {
    width: {
      type: Number,
      default: 400,
    },
    height: {
      type: Number,
      default: 400,
    },
    startDate: {
      type: String,
      default: moment().subtract(1, "weeks").format("YYYY-MM-DD"),
    },
    endDate: {
      type: String,
      default: moment().format("YYYY-MM-DD"),
    },
  },
  data() {
    return {
      periodData: Object,
      dater: Object,
      colorsData: {
        슬픔: "rgb(118, 127, 227)",
        공포: "rgb(110, 98, 146)",
        피곤: "rgb(177, 181, 184)",
        화: "rgb(238, 101, 99)",
        기대: "rgb(225, 245, 254)",
        평온: "rgb(255, 255, 255)",
        창피: "rgb(249, 181, 118)",
        짜증: "rgb(215, 95, 166)",
        기쁨: "rgb(255, 231, 154)",
        사랑: "rgb(248, 181, 175)",
      },
      chartData: {
        labels: [],
        datasets: [
          {
            backgroundColor: [],
            data: [],
          },
        ],
      },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  watch: {
    startDate() {
      this.resetDonut();
    },
    endDate() {
      this.resetDonut();
    },
  },
  methods: {
    async resetDonut() {
      this.periodData = await statistics_percent({
        startDate: this.startDate,
        endDate: this.endDate,
      });
      this.dater = this.periodData.statistics;
      // 데이터 리셋
      this.chartData.labels = [];
      this.chartData.datasets[0].backgroundColor = [];
      this.chartData.datasets[0].data = [];
      for (var value of this.dater) {
        this.chartData.labels.push(value.emotion);
        this.chartData.datasets[0].backgroundColor.push(
          this.colorsData[value.emotion]
        );
        this.chartData.datasets[0].data.push(value.percent);
      }
      this.$emit("send-emotion", this.chartData.labels[0]);
    },
  },
  async mounted() {
    this.periodData = await statistics_percent({
      startDate: this.startDate,
      endDate: this.endDate,
    });
    this.dater = this.periodData.statistics;

    for (var value of this.dater) {
      this.chartData.labels.push(value.emotion);
      this.chartData.datasets[0].backgroundColor.push(
        this.colorsData[value.emotion]
      );
      this.chartData.datasets[0].data.push(value.percent);
    }
    this.$emit("send-emotion", this.chartData.labels[0]);

    this.$nextTick(function () {
      let config = {
        type: "doughnut",
        data: {
          labels: this.chartData.labels,
          datasets: this.chartData.datasets,
          backgroundColor: this.chartData.backgroundColor
        },
        options: {
          responsive: true,
          title: {
            display: false,
            text: "Statistics Percent",
          },
          legend: {
            labels: {
              usePointStyle: true,
              fontColor: "rgba(0,0,0,1)",
              fontSize: 15
            },
          },
        },
      };

      let ctx = document.getElementById("donut-chart").getContext("2d");
      window.myBar = new Chart(ctx, config);

    });
  },
};
</script>
