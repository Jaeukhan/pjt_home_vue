import { apiInstance } from "./index";

const api = apiInstance();
//getSchoolList
function getParkingList(gugunCode, gugunName,success, fail) {
  api.get(`/parking/${gugunName}/${gugunCode}`).then(success).catch(fail);
}

export { getParkingList };
