import { getLiblist } from "@/api/library";


const libraryStore = {
  namespaced: true,
  state: {
    lib_li: [],
  },
  mutations: {
    SET_LIBRARY_LIST(state, info) {
      let li = [];
      for (let d = 0; d < info.length; d++) {
        let temp = {
          lat: info[d].REFINE_WGS84_LAT, //위도
          long: info[d].REFINE_WGS84_LOGT, //경도
          name: info[d].LIBRRY_NM, //도서관 이름
          address: info[d].LOCPLC_ADDR, //도서관 주소
        };
        li.push(temp);
      }
      state.lib_li = li;

    },
  },
  actions: {
    getLibrary: ({ commit }, param) => {
      getLiblist(
        param.siGunCode,
        param.siGunName,
        ({ data }) => {
          //   console.log("[[[[", data);
          //   console.log("[[[[", data.ParkingPlace[1].row);
          console.log("여기 도서관 스토어: ", data.TBGGIBLLBR[1].row);
          commit("SET_LIBRARY_LIST", data.TBGGIBLLBR[1].row);
        },
        (error) => {
          console.log("Getting avgprice error: ", error);
        }
      );
    },
  },
};

export default libraryStore;
