<template>
  <div>
    <b-navbar
      toggleable="lg"
      type="light"
      style="background-color: rgba(174, 217, 224, 0)"
    >
    <!--  -->
      <b-collapse id="nav-collapse" is-nav>
      <b-navbar-brand href="/" id="navItem" style="font-size: 23px;">Where Is My HOME?  </b-navbar-brand>

          <b-navbar-nav>
            <b-link to="/" router-tag="b-nav-item" id="navItem">  HOME  |</b-link> 
            <b-link to="/board" router-tag="b-nav-item" id="navItem">  BOARD  |</b-link> 
            <b-link to="/map" router-tag="b-nav-item" id="navItem">  MAP  |</b-link> 
            <b-link to="/chat" router-tag="b-nav-item" id="navItem">  CHAT  |</b-link>
          </b-navbar-nav>

        <!-- Right aligned nav items -->
        <!-- 로그인 후 nav bar -->
        <b-navbar-nav v-if="memberInfo">
          <b-nav-item @click.prevent="Logout" id="navItem">  LOGOUT  |</b-nav-item>
          <b-link :to="{ name: 'memberInfo' }" router-tag="b-nav-item" id="navItem"
            >  MYPAGE </b-link
          >
        </b-navbar-nav>
        <!-- 로그인 전 nav bar -->
        <b-navbar-nav v-else>
          <b-link to="/member" router-tag="b-nav-item" id="navItem">  LOGIN  |</b-link>
          <b-link :to="{ name: 'memberRegist' }" router-tag="b-nav-item" id="navItem"
            >  SIGNUP</b-link
          >
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "HeaderNav",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "memberInfo"]),
    ...mapGetters(["checkMemberInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["memberLogout"]),
    Logout() {
      this.memberLogout(this.memberInfo.userid);
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("refresh-token");
      alert("로그아웃 되었습니다!");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
      else this.$router.push({ name: "memberLogin" });
    },
  },
};
</script>

<style>
#navItem {
  font-family: 'Titan One', monospace;
  color: #2b3752;
}
#logo {
  height: 50px;
  width: 250px;
}
</style>
