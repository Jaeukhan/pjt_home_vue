<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div style="color: white"><h3>SIGN IN</h3></div>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col
        cols="8"
        style="background-color: rgba(255, 251, 229, 0.4); padding: 1em"
      >
        <b-card
          class="text-center mt-3"
          style="max-width: 40rem; background-color: #fffbe5"
          align="left"
        >
          <b-form class="text-left">
            <b-form-group label="ID" label-for="id">
              <b-form-input
                id="id"
                v-model="user.userid"
                required
                placeholder="id"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="PASSWORD" label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="user.userpwd"
                required
                placeholder="password"
              ></b-form-input>
            </b-form-group>
            <div style="padding-left: 12em">
              <b-button
                type="button"
                class="m-1"
                @click="confirm"
                style="background-color: rgba(250, 243, 221, 0.6); color: black"
                >SIGN IN</b-button
              >
              <b-button
                type="button"
                class="m-1"
                @click="movePage"
                style="background-color: rgba(250, 243, 221, 0.6); color: black"
                >SIGN UP</b-button
              >
            </div>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "memberInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["memberConfirm", "getMemberInfo"]),
    async confirm() {
      await this.memberConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getMemberInfo(token);
        alert("로그인 되었습니다");
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "memberRegist" });
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Pathway Gothic One", monospace;
}
</style>
