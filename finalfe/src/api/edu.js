import { apiInstance } from "./index";

const api = apiInstance();
//getSchoolList
function getKinderList(param, gugunName, gugunCode, success, fail) {
  api.get(`/edu/kinder/${gugunName}/${gugunCode}`, { params: param }).then(success).catch(fail);
}
function getElement(param, gugunName, gugunCode, success, fail) {
  api.get(`/edu/element/${gugunName}/${gugunCode}`, { params: param }).then(success).catch(fail);
}
function getMiddle(param, gugunName, gugunCode, success, fail) {
  api.get(`/edu/middle/${gugunName}/${gugunCode}`, { params: param }).then(success).catch(fail);
}
function getHigh(param, gugunName, gugunCode, success, fail) {
  api.get(`/edu/high/${gugunName}/${gugunCode}`, { params: param }).then(success).catch(fail);
}

export { getKinderList, getElement, getMiddle, getHigh };
