<template>
  <div>
    <b-row>
      <b-table
        hover
        :items="houses"
        :fields="fields"
        id="houselist-table"
        :per-page="perPage"
        :current-page="currentPage"
      >
        <template #cell(자세히보기)="row">
          <b-button size="sm" @click="row.toggleDetails" class="mr-2"       style="background-color: #faf3dd; color: black;"
>
            <b-icon icon="info-circle"></b-icon>
          </b-button>
        </template>
        <template #row-details="row">
          <b-card>
            <b-row class="mb-2">
              <b-col sm="6"><b>대지면적 </b></b-col>
              <b-col>{{ row.item.대지면적 }} 제곱미터</b-col>
            </b-row>
            <b-row class="mb-2">
              <b-col sm="6"><b>연면적 </b></b-col>
              <b-col>{{ row.item.연면적 }} 제곱미터</b-col>
            </b-row>
            <b-row class="mb-2">
              <b-col sm="6"><b>건축년도 </b></b-col>
              <b-col>{{ row.item.건축년도 }} 년</b-col>
            </b-row>
            <b-row class="mb-2">
              <b-col sm="6"><b>거래금액 </b></b-col>
              <b-col>{{ row.item.거래금액 }},000 원</b-col>
            </b-row>
            <b-button size="sm" @click="addFavoriteHouse(row.item)"       style="background-color: #ffa69e; color: black;"

              ><b-icon icon="heart"></b-icon
            ></b-button>
          </b-card>
        </template>
        <!-- <template #cell(지도보기)="mapRow">
                <b-button size="sm" @click="moveloc(mapRow.item)" class="mr-2">
                <b-icon icon="pin-map"></b-icon>
                </b-button>
            </template> -->
      </b-table>
    </b-row>
    <b-row class="justify-content-md-center">
      <b-pagination
        v-model="currentPage"
        pills
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="aptlist-table"
      ></b-pagination>
    </b-row>
  </div>
</template>

<script>
import { addHouse } from "@/api/favorite";
import { mapState } from "vuex";

const mapStore = "mapStore";
const memberStore = "memberStore";

export default {
  name: "HouseList",
  data() {
    return {
      fields: [{ key: "주택유형" }, { key: "법정동" }, "자세히보기"],
      currentPage: 1,
      perPage: 10,
    };
  },
  methods: {
    // ...mapActions(mapStore, ["getAptOne"]),
    // moveloc(apart) {
    //     this.getAptOne(apart);
    // },
    addFavoriteHouse(house) {
      let param = {
        userid: this.memberInfo.userid,
        houseaddress: house.법정동,
        houseprice: house.거래금액,
        housearea: house.대지면적,
        housetype: house.주택유형,
      };
      addHouse(
        param,
        ({ data }) => {
          let msg = "오류 발생";
          if (data === "success") {
            msg = "관심 매물에 등록되었습니다!";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  computed: {
    ...mapState(mapStore, ["houses", "rows"]),
    ...mapState(memberStore, ["memberInfo"]),
  },
};
</script>