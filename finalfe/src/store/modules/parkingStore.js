import { getParkingList } from "@/api/parking";

const parkingStore = {
  namespaced: true,
  state: {
    parking_li: [],
  },
  mutations: {
    SET_PARKING_LIST(state, info) {
      state.parking_li = [];
      let li = [];
      for (let d = 0; d < info.length; d++) {
        let temp = {
          lat: info[d].REFINE_WGS84_LAT, //위도
          long: info[d].REFINE_WGS84_LOGT, //경도
          name: info[d].TMP01, //주차장 이름
          charge: info[d].CHRG_INFO, //요금 - 무료, 유료ㅋ
        };
        li.push(temp);
      }
      state.parking_li = li;
      // console.log("mut", state.parking_li);
    },
  },
  actions: {
    getParking: ({ commit }, param) => {
      getParkingList(
        param.siGunCode,
        param.siGunName,
        ({ data }) => {
          // console.log("[[[[parking", data);
          //   console.log("[[[[", data.ParkingPlace[1].row);
          commit("SET_PARKING_LIST", data.ParkingPlace[1].row);
        },
        (error) => {
          console.log("Getting avgprice error: ", error);
        }
      );
    },
  },
};

export default parkingStore;
