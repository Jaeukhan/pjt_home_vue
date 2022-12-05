import { listBus } from "@/api/transport";

const transportStore = {
  namespaced: true,
  state: {
    prevSidoCode: null,
    busList: null,
  },
  mutations: {
    SET_BUS_LIST(state, payload) {
      state.busList = payload;
    },
  },

  actions: {
    getBusList: ({ commit }, sidocode) => {
      listBus(
        sidocode,
        ({ data }) => {
          commit("SET_BUS_LIST", data);
        },
        (error) => {
          console.log("get listBus Error: ", error);
        }
      );
    },
  },
};

export default transportStore;
