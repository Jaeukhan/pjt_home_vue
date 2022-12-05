import { apiInstance } from "./index";

const api = apiInstance();

async function login(member, success, fail) {
  await api.post(`/member/login`, JSON.stringify(member)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/member/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/member/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/member/logout/${userid}`).then(success).catch(fail);
}

function regist(member, success, fail) {
  console.log(member);
  api.post(`/member/regist`, JSON.stringify(member)).then(success).catch(fail);
}

function modify(member, success, fail) {
  api.put(`/member`, JSON.stringify(member)).then(success).catch(fail);
}

function remove(userid, success, fail) {
  api.delete(`/member/${userid}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, regist, modify, remove };
