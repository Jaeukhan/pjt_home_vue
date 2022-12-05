import { apiInstance } from "./index";

const api = apiInstance();
// const apartapi = apartInstance();

function getSidoCode(success, fail) {
  api.get("/map/sido").then(success).catch(fail);
}

function getGugunsCode(param, success, fail) {
  api.get(`/map/gugun`, { params: param }).then(success).catch(fail);
}

function getApartList(gcode, date, success, fail) {
  api.get(`/map/aptlist/${gcode}/${date}`).then(success).catch(fail);
}

function getHouseList(gcode, date, success, fail) {
  api.get(`/map/houselist/${gcode}/${date}`).then(success).catch(fail);
}

export { getSidoCode, getGugunsCode, getApartList, getHouseList };
