<template>
  <div>
    <Bar
      :chart-options="chartOptions"
      :chart-data="chartData"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :width="width"
      :height="height"
    />
    <h3>{{ statistics }}</h3>
  </div>
</template>

<script>
import { Bar } from "vue-chartjs";
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from "chart.js";
import { statistics_day } from "@/store/modules/etcStore";

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: "BarChart",
  components: { Bar },
  props: {
    chartId: {
      type: String,
      default: "bar-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 400,
    },
    height: {
      type: Number,
      default: 400,
    },
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Object,
      default: () => {},
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
      chartOptions: {
        responsive: true,
        scales: {
          x: {
            stacked: true,
          },
          y: {
            stacked: true,
          },
        },
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
        backgroundColor: "rgb(118, 127, 227)", // 색상 직접 찾아서 입력
      },
      {
        label: "공포",
        data: this.statistics.공포,
        backgroundColor: "rgb(110, 98, 146)", // 색상 직접 찾아서 입력
      },
      {
        label: "피곤",
        data: this.statistics.피곤,
        backgroundColor: "rgb(177, 181, 184)", // 색상 직접 찾아서 입력
      },
      {
        label: "화",
        data: this.statistics.화,
        backgroundColor: "rgb(238, 101, 99)", // 색상 직접 찾아서 입력
      },
      {
        label: "기대",
        data: this.statistics.기대,
        backgroundColor: "rgb(225, 245, 254)", // 색상 직접 찾아서 입력
      },
      {
        label: "평온",
        data: this.statistics.평온,
        backgroundColor: "rgb(255, 255, 255)", // 색상 직접 찾아서 입력
      },
      {
        label: "창피",
        data: this.statistics.창피,
        backgroundColor: "rgb(249, 181, 118)", // 색상 직접 찾아서 입력
      },
      {
        label: "짜증",
        data: this.statistics.짜증,
        backgroundColor: "rgb(215, 95, 166)", // 색상 직접 찾아서 입력
      },
      {
        label: "기쁨",
        data: this.statistics.기쁨,
        backgroundColor: "rgb(255, 231, 154)", // 색상 직접 찾아서 입력
      },
      {
        label: "사랑",
        data: this.statistics.사랑,
        backgroundColor: "rgb(248, 181, 175)", // 색상 직접 찾아서 입력
      },
    ];
  },
};
</script>
