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
              <p>{{ memberInfo.userid }} 님이 선택한 키워드</p>
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
                등록된 관심 키워드가 없습니다! 관심 키워드를 등록하고 맞춤
                매물을 추천받아보세요!
              </p>
            </div>
            <b-button
              v-b-modal.modal-1
              style="background-color: white; color: black; margin: 1px"
              >추가</b-button
            >
            <!-- 모달창 - 관심 키워드 체크 -->
            <b-modal id="modal-1" title="SELECT YOUR KEYWORD">
              <!-- 교통수단 -->
              <b-form-group
                label="이동 시 어떤 교통수단을 이용하시나요?"
                v-slot="{ ariaDescribedby }"
              >
                <b-form-checkbox-group
                  id="checkbox-group-1"
                  v-model="selected"
                  :aria-describedby="ariaDescribedby"
                  name="flavour-1"
                >
                  <b-form-checkbox value="자가용"
                    >자가용<img
                      src="../../assets/icon/자가용.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="대중교통"
                    >대중교통<img
                      src="../../assets/icon/대중교통.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                </b-form-checkbox-group>
              </b-form-group>
              <!-- 자녀 유무 -->
              <b-form-group
                label="자녀가 있으신가요?"
                v-slot="{ ariaDescribedby }"
              >
                <b-form-checkbox-group
                  id="checkbox-group-2"
                  v-model="selected"
                  :aria-describedby="ariaDescribedby"
                  name="flavour-2"
                >
                  <b-form-checkbox value="유치원생"
                    >유치원생<img
                      src="../../assets/icon/유치원생.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="초등학생"
                    >초등학생<img
                      src="../../assets/icon/초등학생.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <br />
                  <b-form-checkbox value="중학생"
                    >중학생<img
                      src="../../assets/icon/중학생.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="고등학생"
                    >고등학생<img
                      src="../../assets/icon/고등학생.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                </b-form-checkbox-group>
              </b-form-group>
              <!-- 주거유형 -->
              <b-form-group
                label="어떤 주거 유형을 선호하시나요?"
                v-slot="{ ariaDescribedby }"
              >
                <b-form-checkbox-group
                  id="checkbox-group-2"
                  v-model="selected"
                  :aria-describedby="ariaDescribedby"
                  name="flavour-2"
                >
                  <b-form-checkbox value="아파트"
                    >아파트<img
                      src="../../assets/icon/아파트.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="주택"
                    >주택<img
                      src="../../assets/icon/주택.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                </b-form-checkbox-group>
              </b-form-group>
              <!-- 인프라 -->
              <b-form-group
                label="자주 가는 장소가 어디인가요?"
                v-slot="{ ariaDescribedby }"
              >
                <b-form-checkbox-group
                  id="checkbox-group-2"
                  v-model="selected"
                  :aria-describedby="ariaDescribedby"
                  name="flavour-2"
                >
                  <b-form-checkbox value="도서관"
                    >도서관<img
                      src="../../assets/icon/도서관.png"
                      style="width: 30px; height: 30px"
                  /></b-form-checkbox>
                  <b-form-checkbox value="마트"
                    >마트<img
                      src="../../assets/icon/마트.png"
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
                반드시 저장 버튼까지 눌러주세요!
              </p>
            </b-modal>
          </div>
        </div>
      </b-col>
    </b-row>
    <!-- Favorite Keyword End -->

    <!-- 관심 매물 -->
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
                <b-nav-item active>좋아요 표시한 매물 리스트</b-nav-item>
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
                  <template #cell(삭제)="row">
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
                  추가한 관심 매물이 없습니다! 관심 매물을 추가하고 한 눈에
                  모아보세용
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
                  <template #cell(삭제)="row">
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
                  추가한 관심 매물이 없습니다! 관심 매물을 추가하고 한 눈에
                  모아보세용
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
              >다른 매물 둘러보기</b-button
            >
          </b-card>
        </div>
      </b-col>
    </b-row>
    <!-- 사용자 게시판 글 목록 -->
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
                    {{ memberInfo.username }} 님이 작성한 게시글
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
        { key: "aptname", label: "아파트이름" },
        { key: "aptaddress", label: "위치" },
        "삭제",
      ],
      housefields: [
        { key: "housetype", label: "주택유형" },
        { key: "houseaddress", label: "위치" },
        "삭제",
      ],
      bfields: [
        { key: "hit", label: "조회수", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
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
    //   console.log("이벤트!");
    //   event.preventDefault();
    //   this.saveKeyword(this.selected);
    // },
    keywordClick() {
      console.log("들어옴!");
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
      if (confirm("정말 삭제하시겠습니까?")) {
        this.removeFavoriteApt(aptid, this.memberInfo.userid);
        alert("삭제되었습니다!");
        this.$router.go();
      } else {
        alert("삭제가 취소되었습니다!");
      }
    },
    removeHouse(houseid) {
      if (confirm("정말 삭제하시겠습니까?")) {
        this.removeFavoriteHouse(houseid, this.memberInfo.userid);
        alert("삭제되었습니다!");
        this.$router.go();
      } else {
        alert("삭제가 취소되었습니다!");
      }
    },
    toggle(word) {
      this.isApt = word;
    },
  },
};
</script>
