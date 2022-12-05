import { apiInstance } from "./index";

const api = apiInstance();
//getSchoolList
function getMartlist(gugunCode, gugunName, success, fail) {
  api.get(`/mart/${gugunName}/${gugunCode}`).then(success).catch(fail);
}

export { getMartlist };
