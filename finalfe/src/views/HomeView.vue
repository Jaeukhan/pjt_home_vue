<template>
  <div>
    <b-row>
      <b-container
        class="bv-example-row mt-3 text-center"
        id="home"
        fluid="true"
      >
        <b-row style="padding-bottom: 8em; padding-top: 5.5em">
          <p
            style="
              font-family: 'Lobster', monospace;
              font-size: 100px;
              color: #faf3dd;
            "
          >
            Where Is My <br />
            House?
          </p>
        </b-row>
        <img
          src="../assets/airPlane.png"
          style="
            position: absolute;
            left: 250px;
            top: 100px;
            width: 200px;
            height: 200px; ;
          "
        />

        <b-row>
          <p
            style="
              font-family: 'Pathway Gothic One', monospace;
              font-size: 30px;
              color: #a7804a;
              padding-left: 1.3em;
            "
          >
            Be our member and Find your dream house!
          </p>
        </b-row>
        <b-row>
          <p
            style="
              font-family: 'Pathway Gothic One', monospace;
              font-size: 20px;
              color: #a7804a;
              padding-left: 8.5em;
            "
          >
            JUST SCROLL DOWN
          </p>
        </b-row>

        <b-row>
          <p
            style="
              font-family: 'Pathway Gothic One', monospace;
              color: #a7804a;
              padding-left: 12.3em;
              padding-top: 2em;
              padding-bottom: 6em;
            "
          >
            <b-icon
              icon="arrow-down"
              animation="cylon-vertical"
              font-scale="4"
            ></b-icon>
          </p>
        </b-row>
        <img
          src="../assets/BoardAndComment.png"
          style="
            position: absolute;
            left: 200px;
            top: 820px;
            width: 250px;
            height: 170px; ;
          "
        />
        <b-row>
          <div
            style="
              font-family: 'Pathway Gothic One', monospace;
              font-size: 30px;
              color: #5e6472;
              padding-left: 5em;
              padding-bottom: 4em;
            "
            class="mb-2"
          >
            <p>Board & Comment</p>
            <b-button href="/board" variant="outline-secondary"
              ><b-icon-cursor></b-icon-cursor
            ></b-button>
          </div>
        </b-row>

        <br />
        <br />
        <br />
        <img
          src="../assets/BeOurUser.png"
          style="
            position: absolute;
            left: 700px;
            top: 1170px;
            width: 200px;
            height: 170px; ;
          "
        />
        <b-row>
          <div
            style="
              font-family: 'Pathway Gothic One', monospace;
              font-size: 30px;
              color: #5e6472;
              padding-left: 6em;
              padding-bottom: 4em;
            "
            class="mb-2"
          >
            <p>Be our user!</p>
            <b-button href="/member" variant="outline-secondary"
              ><b-icon-cursor></b-icon-cursor
            ></b-button>
          </div>
        </b-row>

        <br />
        <br />
        <br />
        <img
          src="../assets/FindYourDreamHouse.png"
          style="
            position: absolute;
            left: 240px;
            top: 1550px;
            width: 250px;
            height: 230px;
          "
        />
        <b-row>
          <div
            style="
              font-family: 'Pathway Gothic One', monospace;
              font-size: 30px;
              color: #5e6472;
              padding-left: 4.3em;
              padding-bottom: 4em;
            "
            class="mb-2"
          >
            <p>Find your dream house!</p>
            <b-button href="/map" variant="outline-secondary"
              ><b-icon-cursor></b-icon-cursor
            ></b-button>
          </div>
        </b-row>

        <br />
        <br />
        <br />
        <img
          src="../assets/news.gif"
          style="
            position: absolute;
            width: 300px;
            height: 230px;
            left: 100px;
            top: 2150px;
          "
        />
        <b-row>
          <div style="padding-left: 3.1em" class="mb-2">
            <p
              style="
                font-family: 'Pathway Gothic One', monospace;
                font-size: 30px;
                color: #d3e7ac;
              "
            >
              LATEST NEWS
            </p>
            <b-button
              variant="outline-light"
              @click="moveLink(0)"
              style="margin-bottom: 10px"
              ><b-icon-newspaper></b-icon-newspaper
            ></b-button>
            <p>{{ news_list[0].title }}</p>
            <br />
            <b-button
              variant="outline-light"
              @click="moveLink(1)"
              style="margin-bottom: 10px"
              ><b-icon-newspaper></b-icon-newspaper
            ></b-button>
            <p>{{ news_list[1].title }}</p>
            <br />
            <b-button
              variant="outline-light"
              @click="moveLink(2)"
              style="margin-bottom: 10px"
              ><b-icon-newspaper></b-icon-newspaper
            ></b-button>
            <p>{{ news_list[2].title }}</p>
          </div>
        </b-row>
        <br /><br /><br /><br />
      </b-container>
    </b-row>
  </div>
</template>

<script>
import { getNewsList, newsInit } from "@/api/news";
import { mapState, mapActions, mapMutations } from "vuex";
// import { getImage } from "@/api/naver";

const boardStore = "boardStore";
export default {
  name: "HomeView",
  data() {
    return {
      news_list: [{ title: "" }, { title: "" }, { title: "" }],
      slide: 0,
      sliding: null,
      cimg: "",
      // fields: [
      //   { key: "subject", label: "제목" },
      //   { key: "userid", label: "글쓴이" },
      //   { key: "regtime", label: "작성일" },
      // ],
      // currentPage: 1,
      // perPage: 5,
    };
  },
  created() {
    newsInit(
      () => {
        getNewsList(
          ({ data }) => {
            this.news_list = data;
          },
          (error2) => {
            console.log(error2);
          }
        );
      },
      (error) => {
        console.log(error);
      }
    );
    this.CLEAR_POPULAR_BOARD();
    this.getPopularBoard();
  },
  methods: {
    ...mapMutations(boardStore, ["CLEAR_POPULAR_BOARD"]),
    ...mapActions(boardStore, ["getPopularBoard"]),
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
    },
    moveLink(num) {
      let url = "https://land.naver.com" + this.news_list[num].link;
      window.open(url);
    },
  },
  computed: {
    ...mapState(boardStore, ["popular_board"]),
  },
};
</script>

<style>
img {
  width: 500px;
  height: 400px;
}
</style>
