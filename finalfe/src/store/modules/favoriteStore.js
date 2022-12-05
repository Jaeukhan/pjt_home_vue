import { listHouse, deleteHouse, deleteApt, listApt } from "@/api/favorite";

const favoriteStore = {
  namespaced: true,
  state: {
    apartlist: [],
    houselist: [],
  },
  mutations: {
    SET_HOUSE_LIST(state, data) {
      state.houselist = data;
    },
    SET_APT_LIST(state, data) {
      state.apartlist = data;
    },
  },
  actions: {
    getHouseList: ({ commit }, userid) => {
      listHouse(
        userid,
        ({ data }) => {
          // console.log(data);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAptList: ({ commit }, userid) => {
      listApt(
        userid,
        ({ data }) => {
          commit("SET_APT_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    removeFavoriteHouse: ({ commit }, houseid, userid) => {
      deleteHouse(
        houseid,
        ({ data }) => {
          if (data === "success") {
            this.getHouseList(userid);
            commit();
          } else {
            console.log("error");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    removeFavoriteApt: ({ commit }, aptid, userid) => {
      deleteApt(
        aptid,
        ({ data }) => {
          if (data === "success") {
            this.getAptList(userid);
            commit();
          } else {
            console.log("error");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default favoriteStore;
