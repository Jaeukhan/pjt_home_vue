<template>
  <div id="wcard" v-if="info">
    <b-card>
      <b-card-text>
        <div class="mb-3" style="font-family: 'Titan One', monospace; font-size: 20px; color: #2b3752">
          {{ info.name }}
        </div>
        <div>{{ days }}</div>
        <div>
          <img
            style="display: inline-block; width: 50px; height: 50px"
            :src="require(`@/assets/weather/${info.weather}.gif`)"
            alt=""
          />
          <h2 class="m-2" style="display: inline-block; font-size: 23px">{{ info.temp }}°</h2>
          <!-- 현재기온 -->
          <div style="display: inline-block">{{ info.min_temp }}° / {{ info.max_temp }}°</div>
          <!-- 최고/최저기온 -->
        </div>
        <div>
          <!-- 현재기온: {{ info.temp }} 도 최저기온: {{ info.min_temp }} 도 최고기온: {{ info.max_temp }} 도 날씨:
         -->
        </div>
      </b-card-text>
    </b-card>
  </div>
</template>

<script>
import { mapState } from "vuex";
// let date = new Date();
// let month = date.getMonth() + 1;
// let day = date.getDate();
// document.getElementById("current_date").innerHTML = "오늘, " + month + "월 " + day + "일";
const mapStore = "mapStore";

export default {
  data() {
    return {
      days: "11월 23일",
    };
  },
  created() {
    this.getDays();
  },
  computed: {
    ...mapState(mapStore, ["weatherLoc", "info", "apt"]),
  },

  methods: {
    getDays() {
      this.days = "";
      let date = new Date();
      let month = date.getMonth() + 1;
      let day = date.getDate();
      this.days = "오늘, " + month + "월 " + day + "일";
    },
  },
};
</script>

<style>
/* #wcard {
    background-image: url('../../assets/cloud.jpeg');
} */
</style>
