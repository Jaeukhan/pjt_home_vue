import { apiInstance } from "./index";

const api = apiInstance();

function addApt(apt, success, fail) {
  api.post(`/favorite/apt`, JSON.stringify(apt)).then(success).catch(fail);
}

function addHouse(house, success, fail) {
  api.post(`/favorite/house`, JSON.stringify(house)).then(success).catch(fail);
}

function deleteApt(aptid, success, fail) {
  api.delete(`/favorite/apt/${aptid}`).then(success).catch(fail);
}

function deleteHouse(houseid, success, fail) {
  api.delete(`/favorite/house/${houseid}`).then(success).catch(fail);
}

function selectApt(aptid, success, fail) {
  api.get(`/favorite/apt/${aptid}`).then(success).catch(fail);
}

function selectHouse(houseid, success, fail) {
  api.get(`/favorite/house/${houseid}`).then(success).catch(fail);
}

function listApt(userid, success, fail) {
  api.get(`/favorite/aptlist/${userid}`).then(success).catch(fail);
}

function listHouse(userid, success, fail) {
  api.get(`/favorite/houselist/${userid}`).then(success).catch(fail);
}

export { addApt, addHouse, deleteApt, deleteHouse, selectApt, selectHouse, listApt, listHouse };
