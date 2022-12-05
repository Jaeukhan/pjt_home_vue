<template>
  <div id="app">
    <header-nav></header-nav>
    <div class="container">
      <router-view></router-view>
    </div>
    <footer-nav></footer-nav>
  </div>
</template>
<script>
import HeaderNav from "@/components/HeaderNav.vue";
import FooterNav from "@/components/FooterNav.vue";

export default {
  components: {
    HeaderNav,
    FooterNav,
  },
};
</script>
<style>
#app {
  background: url("@/assets/mainview.jpg") no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  /* font-family: 'Pathway Gothic One', monospace; */
  font-family: 'Nanum Gothic', monospace;
}

.container {
  min-height: 100vh;
}
</style>
