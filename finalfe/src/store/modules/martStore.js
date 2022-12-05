import { getMartlist } from "@/api/mart";

const martStore = {
  namespaced: true,
  state: {
    market_li: [],
  },
  mutations: {
    SET_MART_LIST(state, info) {
      let li = [];
      for (let d = 0; d < info.length; d++) {
        let temp = {
          lat: info[d].REFINE_WGS84_LAT, //위도
          long: info[d].REFINE_WGS84_LOGT, //경도
          name: info[d].MARKET_NM, //마켓 이름
          address: info[d].REFINE_ROADNM_ADDR, //마트 주소
        };
        li.push(temp);
      }
      state.market_li = li;
    },
  },
  actions: {
    getMart: ({ commit }, param) => {
      console.log("para", param);
      getMartlist(
        param.siGunCode,
        param.siGunName,
        ({ data }) => {
          commit("SET_MART_LIST", data.MrktStoreM[1].row);
        },
        (error) => {
          console.log("Getting Mart_LIST error: ", error);
        }
      );
    },
  },
};

export default martStore;
