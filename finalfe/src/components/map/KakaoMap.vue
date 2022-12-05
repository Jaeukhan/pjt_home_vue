<template>
  <div align-v="center" align-h="center" style="text-align: center">
    <h3 v-if="apt">{{ apt.aptName }}</h3>
    <!--맵-->
    <div id="map" style="margin-right: 0" align-h="center"></div>
    <div style="font-family: 'Titan One', monospace;">My Favorite Keyword</div>
    <table style="border-collapse: collapse" align="center">
      <td v-for="k in keyword" :key="k">
        <img
          :src="require(`../../assets/icon/${k}.png`)"
          style="width: 30px; height: 30px; margin: 5px"
        />
      </td>
    </table>
    <b-button
      style="background-color: #faf3dd; color: black;"
      class="mt-3 m-2"
      @click="getParkinglot"
      v-if="keyword.includes('자가용')"
      >주차장 찾기</b-button
    >
    <b-button
      style="background-color: #faf3dd; color: black;"
      class="mt-3 m-2"
      @click="getLibloc"
      v-if="keyword.includes('도서관')"
      >도서관 찾기</b-button
    >
    <b-button
      style="background-color: #faf3dd; color: black;"
      class="mt-3 m-2"
      @click="getMartloc"
      v-if="keyword.includes('마트')"
      >시장 및 마트 찾기</b-button
    >
    <b-button
      style="background-color: #faf3dd; color: black;"
      class="mt-3 m-2"
      @click="getBusStopList"
      v-if="keyword.includes('대중교통')"
      >버스 정류장 찾기</b-button
    >
  </div>
</template>

<script>
import { mapMutations, mapState, mapActions } from "vuex";

const mapStore = "mapStore";
const parkingStore = "parkingStore";
const transportStore = "transportStore";
const libraryStore = "libraryStore";
const martStore = "martStore";
const memberStore = "memberStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      geocoder: null,
      markers: [], //매물 마커 담을 배열
      inframarkers: [], //인프라 마커 담을 배열
      infowindow: null,
      markerPositions1: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      aptAddr: "",
      temp: { lat: null, lon: null },
      imageSrc: {},
      markerImage: null,
      imageSize: null,
      tempLoc: {
        x: "",
        y: "",
      },
      circle: null,
      totalCoordinate: [],
      drawLine: null,
      isselectapart: false,
    };
  },
  props: ["cup"],
  created() {
    this.CLEAR_APT();
    this.cup.$on("move", this.searchSubmit);
  },
  computed: {
    ...mapState(mapStore, [
      "apt",
      "selectedsch",
      "aparts",
      "sidoName",
      "gugunName",
      "weatherLoc",
      "gugunCodeForInfra",
    ]), //apt.load, apt.
    ...mapState(parkingStore, ["parking_li"]),
    ...mapState(transportStore, ["busList"]),
    ...mapState(libraryStore, ["lib_li"]),
    ...mapState(martStore, ["market_li"]),
    ...mapState(memberStore, ["keyword"]),
  },
  watch: {
    market_li(val) {
      const positions = [];
      for (let i = 0; i < val.length; i++) {
        const d = {
          title: val[i].name,
          latlng: new kakao.maps.LatLng(val[i].lat, val[i].long),
          markname: "mart",
        };
        positions.push(d);
      }
      this.displayMarker(positions);
    },
    lib_li(val) {
      const positions = [];
      for (let i = 0; i < val.length; i++) {
        const d = {
          title: val[i].name,
          latlng: new kakao.maps.LatLng(val[i].lat, val[i].long),
          markname: "library",
        };
        positions.push(d);
      }
      this.displayMarker(positions);
    },
    parking_li(val) {
      const positions = [];
      for (let i = 0; i < val.length; i++) {
        const d = {
          title: val[i].name,
          latlng: new kakao.maps.LatLng(val[i].lat, val[i].long),
          markname: "parking",
        };
        positions.push(d);
      }
      this.displayMarker(positions);
    },
    busList(val) {
      const positions = [];
      for (let i = 0; i < val.length; i++) {
        const d = {
          title: val[i].stopname,
          latlng: new kakao.maps.LatLng(val[i].lat, val[i].lon),
          markname: "bus",
        };
        positions.push(d);
      }
      this.displayMarker(positions);
    },
    selectedsch(val) {
      if (val != "undefined" && val.length > 0)
        this.SchoolgeolocDisplayMarker(val);
    },
    apt(val) {
      if (val.load) {
        this.searchSubmit(val.load);
        // console.log("loc", this.tempLoc.x, this.tempLoc.y);
        this.setWeatherLoc(this.temp);
        this.apiload(this.temp);
      }
    },
    aparts(val) {
      if (val) {
        let li = [];
        for (let i = 0; i < val.length; i++) {
          const names =
            this.sidoName +
            " " +
            this.gugunName +
            " " +
            val[i].법정동 +
            " " +
            val[i].도로명;
          li.push({
            REFINE_ROADNM_ADDR: names,
            title: val[i].아파트,
            markname: "apart",
          });
        }
        this.displayMarkerAndMove(li);
      } else {
        alert("서비스 지역이 아닙니다.");
      }
    },
  },
  methods: {
    ...mapActions(mapStore, ["setWeatherLoc", "apiload"]),
    ...mapActions(parkingStore, ["getParking"]),
    ...mapActions(transportStore, ["getBusList"]),
    ...mapActions(libraryStore, ["getLibrary"]), //libraryStore
    ...mapActions(martStore, ["getMart"]), //libraryStore

    ...mapMutations(mapStore, ["CLEAR_APT"]),

    initMap() {
      const mapContainer = document.getElementById("map");
      const mapOption = {
        center: new kakao.maps.LatLng(37.8183044593, 127.3529658183), // 지도의 중심좌표
        level: 4, // 지도의 확대 레벨
      };
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      this.geocoder = new kakao.maps.services.Geocoder();
      this.imageSize = new kakao.maps.Size(30, 30);
      this.imageSrc.kindergarden = require("@/assets/kakaomap/kindergarden.png");
      this.imageSrc.element = require("@/assets/kakaomap/element.png");
      this.imageSrc.middle = require("@/assets/kakaomap/middle.png");
      this.imageSrc.high = require("@/assets/kakaomap/high.png");
      this.imageSrc.apart = require("@/assets/kakaomap/apart.png");
      this.imageSrc.buststop = require("@/assets/kakaomap/buststop.png");
      this.imageSrc.library = require("@/assets/kakaomap/library.png");
      this.imageSrc.parking = require("@/assets/kakaomap/parking.png");
      this.imageSrc.mart = require("@/assets/kakaomap/mart.png");
      this.addEventToMap();
    },
    SchoolgeolocDisplayMarker(Addr) {
      if (Addr[0].markname == "kindergarden") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.kindergarden,
          this.imageSize
        );
      } else if (Addr[0].markname == "element") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.element,
          this.imageSize
        );
      } else if (Addr[0].markname == "middle") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.middle,
          this.imageSize
        );
      } else if (Addr[0].markname == "high") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.high,
          this.imageSize
        );
      }
      let line = new kakao.maps.Polyline();
      const coords = new kakao.maps.LatLng(this.tempLoc.y, this.tempLoc.x);
      const radius = 400;
      this.markers = [];
      for (let n = 0; n < Addr.length; n++) {
        if (Addr[n].REFINE_ROADNM_ADDR != null) {
          this.geocoder.addressSearch(
            Addr[n].REFINE_ROADNM_ADDR,
            (result, status) => {
              if (status === kakao.maps.services.Status.OK) {
                const position = new kakao.maps.LatLng(
                  result[0].y,
                  result[0].x
                );
                let path = [position, coords];
                line.setPath(path);
                let dist = line.getLength();
                // console.log(Addr[n].REFINE_ROADNM_ADDR);
                if (radius > dist) {
                  const marker = new kakao.maps.Marker({
                    map: this.map,
                    position: position,
                    title: Addr[n].title,
                    clickable: true,
                    image: this.markerImage,
                  });
                  const infowindow = new kakao.maps.InfoWindow({
                    content: `<div>${Addr[n].title}</div>`,
                  });
                  let _this = this;
                  kakao.maps.event.addListener(
                    marker,
                    "mouseover",
                    function () {
                      infowindow.open(_this.map, marker);
                    }
                  );
                  kakao.maps.event.addListener(marker, "mouseout", function () {
                    infowindow.close();
                  });

                  this.markers.push(marker);
                }
              }
            }
          );
        }
      }
      this.clustererAddMark();
    },
    displayMarkerAndMove(Addr) {
      // let positions = [];
      // console.log(Addr[0]);
      this.markerImage = new kakao.maps.MarkerImage(
        this.imageSrc.apart,
        this.imageSize
      );
      let bounds = new kakao.maps.LatLngBounds();
      for (let n = 0; n < Addr.length; n++) {
        this.geocoder.addressSearch(
          Addr[n].REFINE_ROADNM_ADDR,
          (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
              const position = new kakao.maps.LatLng(result[0].y, result[0].x);

              const marker = new kakao.maps.Marker({
                map: this.map,
                position: position,
                title: Addr[n].title,
                clickable: true,
                image: this.markerImage,
              });
              const infowindow = new kakao.maps.InfoWindow({
                content: `<div>${Addr[n].title}</div>`,
              });
              let _this = this;
              kakao.maps.event.addListener(marker, "mouseover", function () {
                infowindow.open(_this.map, marker);
              });
              kakao.maps.event.addListener(marker, "mouseout", function () {
                infowindow.close();
              });

              this.markers.push(marker);

              bounds.extend(new kakao.maps.LatLng(result[0].y, result[0].x));
              this.map.setBounds(bounds);
            }
          }
        );
      }
    },
    displayMarker(positions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      // console.log(positions[0].markname);
      if (positions[0].markname == "bus") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.buststop,
          this.imageSize
        );
      } else if (positions[0].markname == "apart") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.apart,
          this.imageSize
        );
      } else if (positions[0].markname == "parking") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.parking,
          this.imageSize
        );
      } else if (positions[0].markname == "library") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.library,
          this.imageSize
        );
      } else if (positions[0].markname == "high") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.high,
          this.imageSize
        );
      } else if (positions[0].markname == "middle") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.middle,
          this.imageSize
        );
      } else if (positions[0].markname == "element") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.element,
          this.imageSize
        );
      } else if (positions[0].markname == "kindergarden") {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.kindergarden,
          this.imageSize
        );
      } else {
        this.markerImage = new kakao.maps.MarkerImage(
          this.imageSrc.mart,
          this.imageSize
        );
      }

      positions.forEach((position) => {
        const coords = new kakao.maps.LatLng(this.tempLoc.y, this.tempLoc.x);
        let line = new kakao.maps.Polyline();
        let path = [position.latlng, coords];
        line.setPath(path);
        let dist = line.getLength();
        const radius = 400;
        if (radius > dist) {
          const marker = new kakao.maps.Marker({
            map: this.map,
            position: position.latlng,
            title: position.title,
            clickable: true,
            image: this.markerImage,
          });

          const infowindow = new kakao.maps.InfoWindow({
            content: `<div>${position.title}</div>`,
          });
          let _this = this;
          kakao.maps.event.addListener(marker, "mouseover", function () {
            infowindow.open(_this.map, marker);
          });
          kakao.maps.event.addListener(marker, "mouseout", function () {
            infowindow.close();
          });

          this.markers.push(marker);
        }
      });
      this.clustererAddMark();
    },

    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(this.loc.x, this.loc.y), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
    clustererAddMark() {
      if (this.circle) {
        this.circle.setMap(null);
      }
      const coords = new kakao.maps.LatLng(this.tempLoc.y, this.tempLoc.x);
      this.map.setCenter(coords);
      this.circle = new kakao.maps.Circle({
        map: this.map,
        center: coords,
        radius: 400, // m단위
        strokeWeight: 2,
        strokeColor: "#FF00FF",
        strokeOpacity: 0.8,
        strokeStyle: "dashed",
        fillColor: "#00EEEE",
        fillOpacity: 0.1,
      });
    },
    searchSubmit(Addr) {
      let _this = this;
      this.geocoder.addressSearch(Addr, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          let bounds = new kakao.maps.LatLngBounds();
          let data = result[0];
          _this.tempLoc.x = data.x;
          _this.tempLoc.y = data.y;
          bounds.extend(new kakao.maps.LatLng(data.y, data.x));
          // console.log("loc 찍음", this.tempLoc);
          _this.map.setBounds(bounds);
        }
      });
      this.isselectapart = true;
    },
    addEventToMap() {
      let _this = this;
      kakao.maps.event.addListener(_this.map, "center_changed", function () {
        let latlng = _this.map.getCenter();
        _this.temp.lat = latlng.Ma;
        _this.temp.lon = latlng.La;
      });
    },
    ////// 주차장
    getParkinglot() {
      // console.log(this.tempLoc.lat, this.tempLoc.lon);
      if (!this.isselectapart) {
        alert("관심매물을 체크해주세요.");
        return;
      }
      const param = {
        siGunName: this.gugunName,
        siGunCode: this.gugunCodeForInfra,
      };
      this.getParking(param);
    },
    getLibloc() {
      if (!this.isselectapart) {
        alert("관심매물을 체크해주세요.");
        return;
      }
      const param = {
        siGunName: this.gugunName,
        siGunCode: this.gugunCodeForInfra,
      };
      this.getLibrary(param);
    },
    getMartloc() {
      if (!this.isselectapart) {
        alert("관심매물을 체크해주세요.");

        return;
      }
      const param = {
        siGunName: this.gugunName,
        siGunCode: this.gugunCodeForInfra,
      };
      this.getMart(param);
    },
    getBusStopList() {
      if (!this.isselectapart) {
        alert("관심매물을 체크해주세요.");
        return;
      }
      this.getBusList(11);
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /*global kakao*/
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=76f9cc9f657dbe8ff6bf3180b7a0f4db&libraries=services`;
      document.head.appendChild(script);
    }
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 500px;
}
</style>