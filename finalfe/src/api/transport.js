import { apiInstance } from "./index";

const api = apiInstance();

function listBus(sidocode, success, fail) {
    api.get(`/transport/${sidocode}`).then(success).catch(fail);
}

export { listBus };