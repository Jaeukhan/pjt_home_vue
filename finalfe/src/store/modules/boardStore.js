import { getUserArticle, getPopularArticle } from "@/api/board";

const boardStore = {
  namespaced: true,
  state: {
    user_board: [],
    popular_board: [],
  },
  mutations: {
    CLEAR_USER_BOARD(state) {
      state.user_board = [];
    },
    CLEAR_POPULAR_BOARD(state) {
      state.user_board = [];
    },
    GET_USER_BOARD(state, info) {
      state.user_board = info;
    },
    GET_POPULAR_BOARD(state, info) {
      state.popular_board = info;
    },
  },
  actions: {
    getUserBoard: ({ commit }, name) => {
      getUserArticle(
        name,
        ({ data }) => {
          // console.log("[[[[", data);
          commit("GET_USER_BOARD", data);
        },
        (error) => {
          console.log("Getting getUserArticle error: ", error);
        }
      );
    },
    getPopularBoard: ({ commit }) => {
      getPopularArticle(
        ({ data }) => {
          commit("GET_POPULAR_BOARD", data);
        },
        (error) => {
          console.log("Getting getPopularArticle error: ", error);
        }
      );
    },
  },
};

export default boardStore;
