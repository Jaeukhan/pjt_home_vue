<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div><h3 style="color: white">SIGN UP</h3></div>
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
          <b-form @submit="onSubmit" class="text-left">
            <b-form-group label="id:" label-for="id">
              <b-form-input
                id="id"
                v-model="member.id"
                required
                placeholder="ID"
                :disabled="isModify"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="name:" label-for="name">
              <b-form-input
                type="text"
                id="name"
                v-model="member.name"
                required
                placeholder="name"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="password:" label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="member.password"
                required
                placeholder="PASSWORD"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="email:" label-for="email">
              <b-form-input
                type="email"
                id="email"
                v-model="member.email"
                required
                placeholder="email"
              ></b-form-input>
            </b-form-group>
            <div
              v-if="this.type === 'regist'"
              style="padding-left: 14.3em; background-color: #fffbe5"
            >
              <b-button type="submit" class="m-1">SIGN UP</b-button>
            </div>
            <div v-else style="padding-left: 10.6em">
              <b-button
                type="submit"
                variant="outline-info"
                size="sm"
                class="m-1"
                ><b-icon-check2></b-icon-check2
              ></b-button>
              <b-button
                variant="outline-danger"
                size="sm"
                @click="memberDelete()"
                class="m-1"
                ><b-icon-trash-fill font-scale="1.2"></b-icon-trash-fill
              ></b-button>
            </div>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { regist, modify, remove } from "@/api/member";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberInputItem",
  data() {
    return {
      member: {
        id: "",
        password: "",
        name: "",
        email: "",
      },
      isModify: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      this.member.id = this.memberInfo.userid;
      this.member.name = this.memberInfo.username;
      this.member.email = this.memberInfo.email;
      this.isModify = true;
    }
  },
  computed: {
    ...mapState(memberStore, ["memberInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["getMemberInfo", "memberLogout"]),
    onSubmit(event) {
      event.preventDefault();

      if (
        this.id === "" ||
        this.password === "" ||
        this.name === "" ||
        this.email === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      } else this.type === "regist" ? this.memberRegist() : this.memberModify();
    },
    memberRegist() {
      let param = {
        userid: this.member.id,
        userpwd: this.member.password,
        username: this.member.name,
        email: this.member.email,
      };
      regist(param, ({ data }) => {
        if (data === "success") {
          alert("회원가입이 완료되었습니다.");
        } else {
          alert("회원가입 오류!");
        }
        this.$router.push({ name: "member" });
      });
    },
    memberModify() {
      let param = {
        userid: this.member.id,
        userpwd: this.member.password,
        username: this.member.name,
        email: this.member.email,
      };
      modify(param, ({ data }) => {
        if (data === "success") {
          let token = sessionStorage.getItem("access-token");
          this.getMemberInfo(token);
          alert("회원 정보 수정 왼료!");
        } else {
          alert("회원 정보 수정 오류..");
        }
        this.$router.push({ name: "memberInfo" });
      });
    },
    memberDelete() {
      let param = this.memberInfo.userid;
      if (confirm("정말 탈퇴하시겠습니까?")) {
        remove(param, ({ data }) => {
          if (data === "success") {
            alert("회원 탈퇴가 완료되었습니다!");
            this.memberLogout(this.memberInfo.userid);
            sessionStorage.removeItem("access-token");
            sessionStorage.removeItem("refresh-token");
            this.$router.push({ name: "home" });
            return;
          } else {
            alert("회원 탈퇴 중 오류 발생");
          }
        });
      } else {
        alert("회원 탈퇴가 취소되었습니다!");
      }
      this.$router.push({ name: "memberInfo" });
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Pathway Gothic One", monospace;
}
</style>
