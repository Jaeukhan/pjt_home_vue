import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import memberStore from "@/store/modules/memberStore";
import mapStore from "@/store/modules/mapStore";
import chartStore from "@/store/modules/chartStore";
import favoriteStore from "@/store/modules/favoriteStore";
import boardStore from "@/store/modules/boardStore";
import parkingStore from "@/store/modules/parkingStore";
import libraryStore from "@/store/modules/libraryStore";
import martStore from "@/store/modules/martStore";
import transportStore from "@/store/modules/transportStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    memberStore,
    mapStore,
    chartStore,
    favoriteStore,
    boardStore,
    parkingStore,
    transportStore,
    libraryStore,
    martStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
