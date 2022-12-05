<template>
  <b-container class="bv-example-row mt-3 text-center" style="padding: 5em;">
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "MemberView",
  methods: {
    moveRegist() {
this.$router.push({ name: "memberRegister" });
    },
  },
};
</script>

<style>
</style>