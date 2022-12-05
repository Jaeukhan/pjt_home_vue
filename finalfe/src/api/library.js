import { apiInstance } from "./index";

const api = apiInstance();
//getSchoolList
function getLiblist(gugunName, gugunCode, success, fail) {
  api.get(`/lib/${gugunName}/${gugunCode}`).then(success).catch(fail);
}

export { getLiblist };
