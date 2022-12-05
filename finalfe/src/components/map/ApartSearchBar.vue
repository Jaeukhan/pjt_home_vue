<template>
  <div>
    <div class="mb-3">
      <b-button v-b-toggle.searchBar style="background-color: #faf3dd; color: black;"
> 매물 찾기 </b-button>
    </div>

    <b-collapse id="searchBar">
      <b-card style="background-color: rgba(255, 255, 255, 0.7)">
        <b-row class="mt-4 mb-4 text-center">
          <b-col>
            <b-form-select v-model="sido" :options="sidos" @change="getGuguns()"></b-form-select>
          </b-col>
          <b-col>
            <b-form-select v-model="gugun" :options="guguns"></b-form-select>
          </b-col>
          <b-col>
            <b-form-select v-model="year" :options="yearList"></b-form-select>
          </b-col>
          <b-col>
            <b-form-select v-model="month" :options="monthList"></b-form-select>
          </b-col>
          <b-col>
            <b-form-select v-model="type" :options="searchType"></b-form-select>
          </b-col>
          <b-col>
            <b-button @click="getList()" style="background-color: #faf3dd; color: black;"
>검색</b-button>
          </b-col>
        </b-row>
      </b-card>
    </b-collapse>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

const mapStore = "mapStore";
const chartStore = "chartStore";

export default {
  name: "ApartSearchBar",
  data() {
    return {
      yearList: ["2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015"],
      monthList: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
      searchType: ["주택", "아파트"],
      sido: null,
      gugun: null,
      year: null,
      month: null,
      type: null,
    };
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.CLEAR_HOUSE_LIST();
    this.getSido(); //시도정보 불러오기(db)
  },
  computed: {
    ...mapState(mapStore, ["sidos", "guguns", "aparts", "sidoName", "gugunName", "rows"]),
    sidoName: function () {
      return this.sidos.find((option) => option.value === this.sido);
    },
    gugunName() {
      return this.guguns.find((option) => option.value === this.gugun);
    },
    // returnSido() {
    //   return this.sidoName;
    // },
    // returnGugun() {
    //   return this.gugunName;
    // },
  },
  methods: {
    ...mapMutations(mapStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_APT_LIST", "CLEAR_HOUSE_LIST"]),
    ...mapActions(mapStore, ["getSido", "getGugun", "getApt", "getHouse", "clearSelectSchool", "clearWeatherData"]),
    getGuguns() {
      this.CLEAR_GUGUN_LIST();
      this.gugun = null;
      if (this.sido) {
        this.getGugun(this.sido);
      }
    },
    ...mapActions(chartStore, ["getAvgPrice"]),
    // getChart() {
    //   const param = {
    //     sido: this.returnSido,
    //     gugun: this.returnGugun,
    //   };
    //   this.getAvgPrice(param);
    // },
    getList() {
      if (this.sido && this.gugun && this.year && this.month && this.type) {
        this.clearSelectSchool();
        this.clearWeatherData();
        let date = this.year + this.month;
        let param = {
          sidoName: this.sidoName.text,
          gugunName: this.gugunName.text,
          gugun: this.gugun,
          date: date,
        };
        if (this.type === "주택") {
          this.CLEAR_APT_LIST();
          this.getHouse(param);
        } else {
          this.CLEAR_HOUSE_LIST();
          this.getApt(param);
          this.getAvgPrice(param);
        }
      } else {
        alert("검색 조건을 모두 선택하세요!");
        return;
      }
    },
  },
};
</script>

<style></style>
