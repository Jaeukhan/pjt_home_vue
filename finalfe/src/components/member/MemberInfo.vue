<template>
  <div>
    <!-- Header Start -->
    <b-row style="padding-left: 3.3em">
      <p
        style="
          font-family: 'Abril Fatface', monospace;
          font-size: 100px;
          color: #b86565;
        "
      >
        My Page
      </p>
    </b-row>
    <b-row style="padding-bottom: 20em">
      <p
        style="
          font-family: 'Amatic SC', monospace;
          font-size: 30px;
          color: #faf3dd;
        "
      >
        THIS PAGE IS JUST FOR YOU! <br />
        CHECK OUT YOUR FAVORITE APARTMENT/HOUSE PRODUCTS.<br />
        OF COURSE, YOU CAN EDIT YOUR INFORMATION!
      </p>
      <img
        src="../../assets/mypage.gif"
        style="position: relative; right: 800px; top: 30px"
      />
    </b-row>
    <!-- Header End -->
    <!-- User Profile Start -->
    <b-row>
      <p
        style="
          font-family: 'Abril Fatface', monospace;
          font-size: 80px;
          color: #b86565;
        "
      >
        Information
      </p>
    </b-row>
    <b-row style="padding-bottom: 20em">
      <b-col
        class="m-3"
        style="background-color: rgba(225, 225, 225, 0.3); padding: 3em"
      >
        <b-card no-body class="card-profile" alt="Image placeholder" img-top>
          <b-row class="justify-content-center">
            <b-col lg="3" class="order-lg-2"> </b-col>
          </b-row>

          <b-card-header class="text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
            <div class="d-flex justify-content-between">
              <b-button
                @click="moveModify"
                variant="outline-info"
                style="float: right"
                ><b-icon-pencil-square font-scale="1.2"></b-icon-pencil-square>
                MODIFY</b-button
              >
            </div>
          </b-card-header>

          <b-card-body class="pt-0">
            <b-row>
              <b-col>
                <div
                  class="
                    card-profile-stats
                    d-flex
                    justify-content-center
                    mt-md-5
                  "
                >
                  <div style="font-size: 30px">WELCOME!</div>
                </div>
              </b-col>
            </b-row>
            <div class="text-center">
              <br />
              <b-icon icon="person-circle" font-scale="3"></b-icon>
              <h5 class="h3">
                {{ memberInfo.userid }}
              </h5>
              <div>{{ memberInfo.username }}</div>
              <div class="h5 mt-4">{{ memberInfo.email }}</div>
            </div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <!-- User Profile End -->

    <!-- Favorite Keyword Start -->
    <b-row>
      <p
        style="
          font-family: 'Abril Fatface', monospace;
          font-size: 80px;
          color: #b86565;
        "
      >
        Custom Keyword
      </p>
    </b-row>
    <b-row style="padding-bottom: 20em">
      <b-col>
        <img src="../../assets/find.gif" alt="" />
      </b-col>
      <b-col style="padding-top: 7em">
        <div style="background-color: rgba(225, 225, 225, 0.3); padding: 1.5em">
          <div style="background-color: white; padding: 3em">
            <div v-if="keyword && keyword.length > 0">
              <p>{{ memberInfo.userid }} ?????? ????????? ?????????</p>
              <table style="border-collapse: collapse">
                <td v-for="k in keyword" :key="k">
                  <img
                    :src="require(`../../assets/icon/${k}.png`)"
                    style="width: 30px; height: 30px; margin: 5px"
                  />
                </td>
              </table>
            </div>
            <div v-else>
              <p>
                ????????? ?????? ???????????? ????????????! ?????? ???????????? ???????????? ??????
                ????????? ?????????????????????!
              </p>
            </div>
            <b-button
              v-b-modal.modal-1
              style="background-color: white; color: black; margin: 1px"
              >??????</b-button
            >
            <!-- ????????? - ?????? ????????? ?????? -->
            <b-modal id="modal-1" title="SELECT YOUR KEYWORD">
              <!-- ???????????? -->
              <b-form-group
                label="?????? ??? ?????? ??????????????? ???????????????????"
                v-slot="{ ariaDescribedby }"
              >
                <b-form-checkbox-group
                  id="checkbox-group-1"
                  v-model="selected"
                  :aria-describedby="ariaDescribedby"
                  name="flavour-1"
                >
                  <b-form-checkbox value="?????????"
                    >?????????<img
                      src="../../assets/icon/?????????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="????????????"
                    >????????????<img
                      src="../../assets/icon/????????????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                </b-form-checkbox-group>
              </b-form-group>
              <!-- ?????? ?????? -->
              <b-form-group
                label="????????? ????????????????"
                v-slot="{ ariaDescribedby }"
              >
                <b-form-checkbox-group
                  id="checkbox-group-2"
                  v-model="selected"
                  :aria-describedby="ariaDescribedby"
                  name="flavour-2"
                >
                  <b-form-checkbox value="????????????"
                    >????????????<img
                      src="../../assets/icon/????????????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="????????????"
                    >????????????<img
                      src="../../assets/icon/????????????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <br />
                  <b-form-checkbox value="?????????"
                    >?????????<img
                      src="../../assets/icon/?????????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="????????????"
                    >????????????<img
                      src="../../assets/icon/????????????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                </b-form-checkbox-group>
              </b-form-group>
              <!-- ???????????? -->
              <b-form-group
                label="?????? ?????? ????????? ???????????????????"
                v-slot="{ ariaDescribedby }"
              >
                <b-form-checkbox-group
                  id="checkbox-group-2"
                  v-model="selected"
                  :aria-describedby="ariaDescribedby"
                  name="flavour-2"
                >
                  <b-form-checkbox value="?????????"
                    >?????????<img
                      src="../../assets/icon/?????????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="??????"
                    >??????<img
                      src="../../assets/icon/??????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                </b-form-checkbox-group>
              </b-form-group>
              <!-- ????????? -->
              <b-form-group
                label="?????? ?????? ????????? ????????????????"
                v-slot="{ ariaDescribedby }"
              >
                <b-form-checkbox-group
                  id="checkbox-group-2"
                  v-model="selected"
                  :aria-describedby="ariaDescribedby"
                  name="flavour-2"
                >
                  <b-form-checkbox value="?????????"
                    >?????????<img
                      src="../../assets/icon/?????????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="??????"
                    >??????<img
                      src="../../assets/icon/??????.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                </b-form-checkbox-group>
              </b-form-group>
              <div style="flow: right">
                <b-button
                  style="margin: 5px; background-color: #aed9e0; color: black"
                  class="sm"
                  @click="keywordClick()"
                  >SAVE</b-button
                >
              </div>
              <p style="font-size; 10x; text-align: right; color: red">
                ????????? ?????? ???????????? ???????????????!
              </p>
            </b-modal>
          </div>
        </div>
      </b-col>
    </b-row>
    <!-- Favorite Keyword End -->

    <!-- ?????? ?????? -->
    <b-row>
      <p
        style="
          font-family: 'Abril Fatface', monospace;
          font-size: 80px;
          color: #b86565;
        "
      >
        Favorites
      </p>
    </b-row>
    <b-row style="padding-bottom: 20em">
      <b-col
        class="m-3"
        style="background-color: rgba(225, 225, 225, 0.3); padding: 3em"
      >
        <div>
          <b-card body-class="text-center" header-tag="nav">
            <template #header>
              <b-nav card-header tabs>
                <b-nav-item active>????????? ????????? ?????? ?????????</b-nav-item>
                <b-button
                  style="margin: 5px; background-color: #aed9e0; color: black"
                  class="sm"
                  @click="toggle('a')"
                  >Apart</b-button
                >
                <b-button
                  style="margin: 5px; background-color: #aed9e0; color: black"
                  class="sm"
                  @click="toggle('h')"
                  >House</b-button
                >
              </b-nav>
            </template>
            <b-card-body v-if="isApt === 'a'">
              <b-row v-if="apartlist && apartlist.length > 0">
                <b-table
                  hover
                  :items="apartlist"
                  :fields="aptfields"
                  id="aptlist-table"
                  :per-page="perPage"
                  :current-page="currentPage"
                >
                  <template #cell(??????)="row">
                    <b-button
                      size="sm"
                      @click="removeApt(row.item.aptid)"
                      class="mr-2"
                    >
                      <b-icon icon="trash"></b-icon>
                    </b-button>
                  </template>
                </b-table>
              </b-row>
              <b-row v-else>
                <div>
                  ????????? ?????? ????????? ????????????! ?????? ????????? ???????????? ??? ??????
                  ???????????????
                </div>
              </b-row>
              <b-row class="justify-content-md-center">
                <b-pagination
                  v-model="currentPage"
                  pills
                  :total-rows="apartlist.length"
                  :per-page="perPage"
                  aria-controls="boardlist-table"
                ></b-pagination>
              </b-row>
            </b-card-body>
            <b-card-body v-else>
              <b-row v-if="houselist && houselist.length > 0">
                <b-table
                  hover
                  :items="houselist"
                  :fields="housefields"
                  id="aptlist-table"
                  :per-page="perPage"
                  :current-page="currentPage"
                >
                  <template #cell(??????)="row">
                    <b-button
                      size="sm"
                      @click="removeHouse(row.item.houseid)"
                      class="mr-2"
                    >
                      <b-icon icon="trash"></b-icon>
                    </b-button>
                  </template>
                </b-table>
              </b-row>
              <b-row v-else>
                <div>
                  ????????? ?????? ????????? ????????????! ?????? ????????? ???????????? ??? ??????
                  ???????????????
                </div>
              </b-row>
              <b-row class="justify-content-md-center">
                <b-pagination
                  v-model="currentPage"
                  pills
                  :total-rows="houselist.length"
                  :per-page="perPage"
                  aria-controls="boardlist-table"
                ></b-pagination>
              </b-row>
            </b-card-body>
            <b-button
              variant="secondary"
              @click="moveMap"
              style="background-color: #aed9e0; color: black"
              >?????? ?????? ????????????</b-button
            >
          </b-card>
        </div>
      </b-col>
    </b-row>
    <!-- ????????? ????????? ??? ?????? -->
    <b-row>
      <p
        style="
          font-family: 'Abril Fatface', monospace;
          font-size: 80px;
          color: #b86565;
        "
      >
        Your Board
      </p>
    </b-row>
    <b-row style="padding-bottom: 20em">
      <b-col
        class="m-3"
        style="background-color: rgba(225, 225, 225, 0.3); padding: 3em"
      >
        <b-card no-body class="card-profile" alt="Image placeholder" img-top>
          <b-row class="justify-content-center">
            <b-col lg="3" class="order-lg-2"> </b-col>
          </b-row>

          <b-card-body class="pt-0">
            <b-row>
              <b-col lg="20">
                <div
                  class="
                    card-profile-stats
                    d-flex
                    justify-content-center
                    mt-md-5
                  "
                >
                  <h5
                    class="h3"
                    style="
                      font-size: 20px;
                      padding-left: 16em;
                      padding-bottom: 2em;
                    "
                  >
                    {{ memberInfo.username }} ?????? ????????? ?????????
                  </h5>
                </div>
              </b-col>
            </b-row>
            <div class="text-center">
              <b-table
                hover
                :items="user_board"
                :fields="bfields"
                @row-clicked="viewArticle"
                id="boardlist-table"
                :per-page="perPage"
                :current-page="currentPage"
              >
                <template #cell(subject)="data">
                  <router-link
                    :to="{
                      name: 'boarddetail',
                      params: { articleno: data.item.articleno },
                    }"
                  >
                    {{ data.item.subject }}
                  </router-link>
                </template>
              </b-table>
            </div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <br />
    <br />
    <br />
    <br />
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const memberStore = "memberStore";
const favoriteStore = "favoriteStore";
const boardStore = "boardStore";

export default {
  name: "MemberInfo",
  data() {
    return {
      aptfields: [
        { key: "aptname", label: "???????????????" },
        { key: "aptaddress", label: "??????" },
        "??????",
      ],
      housefields: [
        { key: "housetype", label: "????????????" },
        { key: "houseaddress", label: "??????" },
        "??????",
      ],
      bfields: [
        { key: "hit", label: "?????????", tdClass: "tdClass" },
        { key: "subject", label: "??????", tdClass: "tdSubject" },
        { key: "regtime", label: "?????????", tdClass: "tdClass" },
      ],
      currentPage: 1,
      perPage: 5,
      isApt: "a",
      selected: [],
    };
  },
  create() {
    let token = sessionStorage.getItem("access-token");
    this.getMemberInfo(token);
  },

  created() {
    this.CLEAR_USER_BOARD();
    this.getHouseList(this.memberInfo.userid);
    this.getAptList(this.memberInfo.userid);
    this.getUserBoard(this.memberInfo.userid);
  },
  computed: {
    ...mapState(memberStore, ["memberInfo", "keyword"]),
    ...mapState(boardStore, ["user_board"]),
    ...mapState(favoriteStore, ["apartlist", "houselist", "rows"]),
  },
  methods: {
    ...mapMutations(boardStore, ["CLEAR_USER_BOARD"]),
    ...mapActions(memberStore, ["getMemberInfo", "saveKeyword"]),
    ...mapActions(favoriteStore, [
      "getHouseList",
      "removeFavoriteHouse",
      "getAptList",
      "removeFavoriteApt",
    ]),
    ...mapActions(boardStore, ["getUserBoard"]),
    // onSubmit(event) {
    //   console.log("?????????!");
    //   event.preventDefault();
    //   this.saveKeyword(this.selected);
    // },
    keywordClick() {
      console.log("?????????!");
      this.saveKeyword(this.selected);
      console.log(this.keyword);
    },
    moveModify() {
      this.$router.push({ name: "memberModify" });
    },
    moveMap() {
      this.$router.push({ name: "MapList" });
    },
    viewArticle(article) {
      console.log("view", article.articleno);
      this.$router.push({
        name: "boarddetail",
        params: { articleno: article.articleno },
      });
    },
    removeApt(aptid) {
      if (confirm("?????? ?????????????????????????")) {
        this.removeFavoriteApt(aptid, this.memberInfo.userid);
        alert("?????????????????????!");
        this.$router.go();
      } else {
        alert("????????? ?????????????????????!");
      }
    },
    removeHouse(houseid) {
      if (confirm("?????? ?????????????????????????")) {
        this.removeFavoriteHouse(houseid, this.memberInfo.userid);
        alert("?????????????????????!");
        this.$router.go();
      } else {
        alert("????????? ?????????????????????!");
      }
    },
    toggle(word) {
      this.isApt = word;
    },
  },
};
</script>
