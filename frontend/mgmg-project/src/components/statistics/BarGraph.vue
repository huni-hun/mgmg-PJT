<template>
  <div>
    <!-- <Bar
      :chart-options="chartOptions"
      :chart-data="chartData"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :width="width"
      :height="height"
    /> -->
    <h3>{{ statistics }}</h3>
  </div>
</template>

<script>
// import { Bar } from "vue-chartjs";
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from "chart.js";

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: "BarChart",
  // components: { Bar },
  props: {
    statistics: {},
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
      chartData: {
        labels: ["월", "화", "수", "목", "금", "토", "일"],
        // datasets: [{ data: [40, 20, 12] }],
        datasets: [
          {
            label: "보통",
            data: null,
            backgroundColor: "rgb(255, 99, 132)", // 색상 직접 찾아서 입력
          },
          {
            label: "기쁨",
            data: null,
            backgroundColor: "rgb(255, 199, 132)", // 색상 직접 찾아서 입력
          },
          {
            label: "사랑",
            data: null,
            backgroundColor: "rgb(155, 99, 232)", // 색상 직접 찾아서 입력
          },
          {
            label: "부끄",
            data: null,
            backgroundColor: "rgb(205, 99, 232)", // 색상 직접 찾아서 입력
          },
          {
            label: "기대",
            data: null,
            backgroundColor: "rgb(155, 199, 132)", // 색상 직접 찾아서 입력
          },
          {
            label: "슬픔",
            data: null,
            backgroundColor: "rgb(2, 99, 132)", // 색상 직접 찾아서 입력
          },
          {
            label: "짜증",
            data: null,
            backgroundColor: "rgb(25, 199, 132)", // 색상 직접 찾아서 입력
          },
          {
            label: "피곤",
            data: null,
            backgroundColor: "rgb(25, 99, 12)", // 색상 직접 찾아서 입력
          },
          {
            label: "화남",
            data: null,
            backgroundColor: "rgb(55, 99, 12)", // 색상 직접 찾아서 입력
          },
          {
            label: "공포",
            data: null,
            backgroundColor: "rgb(255, 99, 132)", // 색상 직접 찾아서 입력
          },
        ],
      },
      chartOptions: {
        responsive: true,
      },
    };
  },
};
</script>
