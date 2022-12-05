import { apiInstance } from "./index.js";

const api = apiInstance();
function listAvgPrice(sido, gugun, success, fail) {
  api.get(`/chart/${sido}/${gugun}`).then(success).catch(fail);
}
export { listAvgPrice };
