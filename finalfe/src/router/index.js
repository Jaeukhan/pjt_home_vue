import Vue from "vue";
import VueRouter from "vue-router";

import HomeView from "../views/HomeView.vue";
import BoardView from "../views/BoardView.vue";
import MemberView from "../views/MemberView.vue";
import MapView from "../views/MapView.vue";
import ChatBot from "../components/ChatBot.vue";

import store from "@/store";

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkMemberInfo = store.getters["memberStore/checkMemberInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkMemberInfo, token);

  if (checkMemberInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getMemberInfo", token);
  }
  if (!checkToken || checkMemberInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "memberLogin" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/board",
    name: "board",
    component: BoardView,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleno",
        name: "boarddetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDetail"),
      },
      {
        path: "modify",
        name: "boardmodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDelete"),
      },
    ],
  },
  {
    path: "/member",
    name: "member",
    component: MemberView,
    redirect: "/member/login",
    children: [
      {
        path: "join",
        name: "memberRegist",
        component: () => import("@/components/member/MemberRegist"),
      },
      {
        path: "login",
        name: "memberLogin",
        component: () => import("@/components/member/MemberLogin"),
      },
      {
        path: "mypage",
        name: "memberInfo",
        component: () => import("@/components/member/MemberInfo"),
      },
      {
        path: "modify",
        name: "memberModify",
        component: () => import("@/components/member/MemberModify"),
      },
    ],
  },
  {
    path: "/map",
    name: "map",
    component: MapView,
    redirect: "/map/maplist",
    children: [
      {
        path: "maplist",
        name: "MapList",
        component: () => import("@/components/map/MapList"),
      },
    ],
  },
  {
    path: "/chat",
    name: "ChatBot",
    component: ChatBot,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
