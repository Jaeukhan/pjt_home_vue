<template>
  <div style="width: 560px; padding: 1em">
    <LineChartGenerator
      :chart-data="chartData"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :width="width"
      :height="height"
    />
  </div>
</template>

<script>
import { Line as LineChartGenerator } from "vue-chartjs/legacy";
import { mapState } from "vuex";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  LinearScale,
  CategoryScale,
  PointElement,
} from "chart.js";
const chartStore = "chartStore";
ChartJS.register(Title, Tooltip, Legend, LineElement, LinearScale, CategoryScale, PointElement);

export default {
  name: "LineChart",
  components: {
    LineChartGenerator,
  },
  props: {
    chartId: {
      type: String,
      default: "line-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 200,
    },
    height: {
      type: Number,
      default: 120,
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
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      // chartData: {
      // labels: this.price_name,
      // datasets: [
      //   {
      //     label: "Data One",
      //     backgroundColor: "#f87979",
      //     data: this.price_li,
      //   },
      // ],
      // },
      // chartOptions: {
      //   responsive: true,
      //   maintainAspectRatio: false,
      // },
    };
  },
  computed: {
    ...mapState(chartStore, ["price_li", "price_name"]),
    chartData() {
      const data = {
        labels: this.price_name,
        datasets: [
          {
            label: "매매가격(천 원)",
            backgroundColor: "#2b3752   ",
            data: this.price_li,
          },
        ],
      };
      return data;
      /* mutable chart data */
    },
    // chartOptions() {
    //   return; /* mutable chart options */
    // },
  },
  // watch: {
  //   data(val) {
  //     console.log(val);
  //   },
  // },
};
</script>
