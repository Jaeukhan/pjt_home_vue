import { apiInstance } from "./index.js";

const api = apiInstance();
function getNewsList(success, fail) {
  api.get(`/news/read`).then(success).catch(fail);
}

function newsInit(success, fail) {
  api.get(`/news`).then(success).catch(fail);
}

export { getNewsList, newsInit };
