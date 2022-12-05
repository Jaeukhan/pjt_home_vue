<template>
  <div>
    <div>
      <b-button
        class="m-3"
        style="background-color: #faf3dd; color: black;"

        v-b-toggle.edc
        v-if="
          keyword.includes('유치원생') ||
          keyword.includes('초등학생') ||
          keyword.includes('중학생') ||
          keyword.includes('고등학생')
        "
      >
        주변 교육 시설 찾기
      </b-button>
    </div>
    <b-collapse id="edc">
      <b-card title="주변 학교 찾기" class="mb-2">
        <b-row class="mt-4 mb-4 text-center">
          <b-col>
            <b-form-select v-model="sch" :options="schools"></b-form-select>
          </b-col>
          <b-col>
            <b-button @click="getSchList()">검색</b-button>
          </b-col>
        </b-row>
      </b-card>
      <b-container v-if="selectedsch && selectedsch.length > 0" class="bv-example-row mt-3">
        <b-row>
          <b-table
            hover
            :items="selectedsch"
            :fields="fields"
            id="schlist-table"
            :per-page="perPage"
            :current-page="currentPage"
            @row-clicked="moveloc"
          ></b-table>
        </b-row>
        <b-row class="justify-content-md-center">
          <b-pagination
            v-model="currentPage"
            pills
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="schlist-table"
          ></b-pagination>
        </b-row>
      </b-container>
    </b-collapse>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const mapStore = "mapStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      fields: [
        { key: "REFINE_ROADNM_ADDR", label: "주소" },
        { key: "title", label: "시설 이름" },
      ],
      schools: [{ value: null, text: "학교 선택" }, "유치원", "초등학교", "중학교", "고등학교"],
      sch: null,
      currentPage: 1,
      rows: 0,
      perPage: 10,
    };
  },
  created() {
    this.rows = this.selectedsch.length;
    this.CLEAR_SCHOOL_LIST();
  },
  name: "EducationList",
  props: ["cup"],
  methods: {
    ...mapMutations(mapStore, ["CLEAR_SCHOOL_LIST"]),
    ...mapActions(mapStore, ["getKinder", "getSchool"]),
    getSchList() {
      const param = {
        siGunName: this.gugunName,
        siGunCode: this.gugunCodeForInfra,
      };
      if (this.sch) {
        if (this.sch == "유치원") {
          param.sort = "유치원";
          this.getKinder(param);
        } else if (this.sch == "초등학교") {
          param.sort = "초등학교";
          this.getSchool(param);
        } else if (this.sch == "중학교") {
          param.sort = "중학교";
          this.getSchool(param);
        } else if (this.sch == "고등학교") {
          param.sort = "고등학교";
          this.getSchool(param);
        }
      }
    },
    moveloc(apart) {
      this.cup.$emit("move", apart.REFINE_ROADNM_ADDR);
    },

    movelocation() {
      console.log("move", this.selectedsch);
      this.cup.$emit("move", this.selectedsch[0].REFINE_ROADNM_ADDR);
    },
  },
  computed: {
    ...mapState(mapStore, ["selectedsch", "gugunName", "gugunCodeForInfra"]),
    ...mapState(memberStore, ["keyword"]),
  },
};
</script>

<style></style>
