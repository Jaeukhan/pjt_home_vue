import { getSidoCode, getGugunsCode, getApartList, getHouseList } from "@/api/map";
import { getKinderList, getElement, getMiddle, getHigh } from "@/api/edu";
import { getWeatherApi } from "@/api/weather";

const mapStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도 선택" }],
    guguns: [{ value: null, text: "구/군 선택" }],
    aparts: [],
    houses: [],
    apt: null, //아파트 주소랑(load), 아파트 이름(aptName)
    sidoName: "",
    gugunName: "",
    gugunCodeForInfra: null,
    selectedsch: [],
    ////////////////////날씨//////////////////////
    weatherLoc: { lat: null, lon: null }, //날씨 불러올 위치: lat, lon
    info: {
      name: null,
      temp: null,
      min_temp: null,
      max_temp: null,
      clouds: null,
      weather: null,
    },
  },
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.aparts = [];
    },
    CLEAR_HOUSE_LIST(state) {
      state.houses = [];
    },
    CLEAR_SCHOOL_LIST(state) {
      state.selectedsch = [];
    },
    CLEAR_APT(state) {
      state.apt = {};
    },
    CLEAR_SELECT_SCHOOL(state) {
      state.selectedsch = [];
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_APART_LIST(state, info) {
      state.aparts = info.aparts;
      state.sidoName = info.sidoName;
      state.gugunName = info.gugunName;
      state.gugunCodeForInfra = info.gcode;
      // console.log("apart, mutations", state.aparts);
    },
    SET_HOUSE_LIST(state, info) {
      state.houses = info.houses;
      state.sidoName = info.sidoName;
      state.gugunName = info.gugunName;
    },
    GET_APT_ONE(state, info) {
      state.apt = {
        load: state.sidoName + " " + state.gugunName + " " + info.load,
        aptName: info.aptName,
      };
    },
    //SET_SCHOOL_LIST
    SET_KINDER_LIST(state, info) {
      let li = [];
      for (let d = 0; d < info.length; d++) {
        let temp = {
          REFINE_ROADNM_ADDR: info[d].REFINE_ROADNM_ADDR,
          title: info[d].FACLT_NM,
          markname: "kindergarden",
        };
        li.push(temp);
      }
      state.selectedsch = li;
    },
    SET_SCHOOL_LIST(state, info) {
      let li = [];
      for (let d = 0; d < info.data.length; d++) {
        let temp = {
          REFINE_ROADNM_ADDR: info.data[d].REFINE_ROADNM_ADDR,
          title: info.data[d].FACLT_NM,
          markname: info.markname,
        };
        li.push(temp);
      }
      state.selectedsch = li;
    },
    ////////////////////날씨//////////////////////
    SET_WEATHER_LOC(state, info) {
      state.weatherLoc = {
        lat: info.lat,
        lon: info.lon,
      };
    },
    CLEAR_WEATHER_DATA(state) {
      state.info = {
        name: null,
        temp: null,
        min_temp: null,
        max_temp: null,
        clouds: null,
        weather: null,
      };
    },
    UPDATE_DATA(state, payload) {
      state.info = {
        name: payload.data.name,
        temp: payload.data.main.temp,
        min_temp: payload.data.main.temp_min,
        max_temp: payload.data.main.temp_max,
        clouds: payload.data.clouds.all,
        weather: payload.data.weather[0].main,
      };
      console.log(state.info);
    },
  },
  actions: {
    clearSelectSchool({ commit }) {
      commit("CLEAR_SELECT_SCHOOL");
    },
    getSido: ({ commit }) => {
      getSidoCode(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log("Getting sidoCode error: ", error);
        }
      );
    },
    getGugun: ({ commit }, sido) => {
      const param = { sido: sido };
      getGugunsCode(
        param,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log("Getting gugunCode error: ", error);
        }
      );
    },
    getApt: ({ commit }, condition) => {
      const gcode = condition.gugun;
      const date = condition.date;
      getApartList(
        gcode,
        date,
        ({ data }) => {
          const info = {
            aparts: data.response.body.items.item,
            sidoName: condition.sidoName,
            gugunName: condition.gugunName,
            gcode: gcode,
          };
          commit("SET_APART_LIST", info);
        },
        (error) => {
          console.log("Getting ApartList error: ", error);
        }
      );
    },
    getHouse: ({ commit }, condition) => {
      const gcode = condition.gugun;
      const date = condition.date;
      getHouseList(
        gcode,
        date,
        ({ data }) => {
          const info = {
            houses: data.response.body.items.item,
            sidoName: condition.sidoName,
            gugunName: condition.gugunName,
          };
          commit("SET_HOUSE_LIST", info);
        },
        (error) => {
          console.log("Getting HouseList error: ", error);
        }
      );
    },
    getAptOne: ({ commit }, apt) => {
      const data = {
        load: apt.도로명,
        aptName: apt.아파트,
      };
      commit("GET_APT_ONE", data);
    },
    getKinder: ({ commit }, params) => {
      const SERVICE_KEY = process.env.VUE_APP_GG_SCHOOL_KEY;
      const param = {
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      getKinderList(
        param,
        params.siGunName,
        params.siGunCode,
        ({ data }) => {
          console.log("dd", data);
          commit("SET_KINDER_LIST", data.Kndrgrschoolstus[1].row);
        },
        (error) => {
          console.log("Getting KinderList error: ", error);
        }
      );
    },
    getSchool: ({ commit }, params) => {
      const SERVICE_KEY = process.env.VUE_APP_GG_SCHOOL_KEY;
      const param = {
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      if (params.sort == "초등학교") {
        getElement(
          param,
          params.siGunName,
          params.siGunCode, // (41+구군코드)
          ({ data }) => {
            const info = {
              data: data.ElmschlM[1].row,
              markname: "element",
            };
            commit("SET_SCHOOL_LIST", info);
          },
          (error) => {
            console.log("Getting KinderList error: ", error);
          }
        );
      } else if (params.sort == "중학교") {
        getMiddle(
          param,
          params.siGunName,
          params.siGunCode,
          ({ data }) => {
            const info = {
              data: data.MskulM[1].row,
              markname: "middle",
            };
            commit("SET_SCHOOL_LIST", info);
          },
          (error) => {
            console.log("Getting KinderList error: ", error);
          }
        );
      } else if (params.sort == "고등학교") {
        getHigh(
          param,
          params.siGunName,
          params.siGunCode,
          ({ data }) => {
            const info = {
              data: data.HgschlM[1].row,
              markname: "high",
            };
            commit("SET_SCHOOL_LIST", info);
          },
          (error) => {
            console.log("Getting KinderList error: ", error);
          }
        );
      }
    },
    ////////////////////날씨//////////////////////

    clearWeatherData: ({ commit }) => {
      console.log("clear weather");
      commit("CLEAR_WEATHER_DATA");
    },
    setWeatherLoc: ({ commit }, loc) => {
      const data = {
        lat: loc.lat,
        lon: loc.lon,
      };
      commit("SET_WEATHER_LOC", data);
    },
    apiload(context, payload) {
      // this.clearWeatherData();
      getWeatherApi(payload).then((res) => {
        context.commit("UPDATE_DATA", res);
      });
    },
  },
};

export default mapStore;
