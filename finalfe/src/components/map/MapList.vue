<template>
  <div>
    <apart-search-bar></apart-search-bar>
    <kakao-map :cup="bus" class="m-3" />
    <b-container v-if="aparts && aparts.length > 0" class="bv-example-row mt-3">
      <education-list :cup="bus" />
      <div>
        <b-button v-b-toggle.sidebar-1       style="background-color: #faf3dd; color: black;"

          >현지역 과거 부동산 동향 보기</b-button
        >
        <b-sidebar id="sidebar-1" title="최근 1년 가격동향" width="600px">
          <div class="px-3 py-2">
            <line-chart></line-chart>
            <br />
            <br />
            <br />
            <weather-card></weather-card>
            <!-- <b-img src="https://picsum.photos/500/500/?image=54" fluid thumbnail></b-img> -->
          </div>
        </b-sidebar>
      </div>
      <apart-list></apart-list>
    </b-container>
    <b-container v-else-if="houses && houses.length > 0" class="bv-example-row mt-3">
      <house-list></house-list>
    </b-container>
    <b-container v-else>
      <b-row style="padding: 10px">
        <b-col><b-alert show>조건에 해당하는 매물이 없습니다!</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import ApartSearchBar from "@/components/map/ApartSearchBar.vue";
import ApartList from "@/components/map/ApartList.vue";
import HouseList from "@/components/map/HouseList.vue";
import KakaoMap from "@/components/map/KakaoMap";
import { mapState } from "vuex";
import EducationList from "./EducationList.vue";
import Vue from "vue";
import LineChart from "@/components/map/Line.vue";
import WeatherCard from "@/components/map/WeatherCard.vue";

const mapStore = "mapStore";

var bus = new Vue();

export default {
  name: "MapList",
  components: {
    ApartSearchBar,
    KakaoMap,
    EducationList,
    ApartList,
    HouseList,
    LineChart,
    WeatherCard,
  },
  data() {
    return {
      bus: bus,
      sido: "",
      gugun: "",
    };
  },
  computed: {
    ...mapState(mapStore, ["aparts", "houses", "apt"]),
  },
};
</script>

<style></style>
