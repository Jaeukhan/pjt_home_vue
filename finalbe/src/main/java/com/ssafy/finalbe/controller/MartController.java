package com.ssafy.finalbe.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/mart")
@Api("마트 컨트롤러  API V1")
public class MartController {
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

    @ApiOperation(value = "마트 목록", response = List.class)
    @GetMapping(value = "/{gugunName}/{gugunCode}", produces = "application/json;charset=utf-8")
    public ResponseEntity<String> parkingList(@ApiParam(value = "구군이름 ", required = true) @PathVariable("gugunName") String gugunName,@ApiParam(value = "구군 코드 ", required = true) @PathVariable("gugunCode") String gugunCode) throws IOException {
        logger.info("mart - 호출");
        System.out.println(gugunName+" "+gugunCode);
        String serviceKey = "caa3a449c5c8411e910663f1df33cece";
        StringBuilder urlBuilder = new StringBuilder(
                "https://openapi.gg.go.kr/MrktStoreM");

//		serviceKey = URLDecoder.decode(serviceKey, "UTF-8");

        urlBuilder.append("?" + URLEncoder.encode("KEY", "UTF-8")
                + "=" + serviceKey);
        urlBuilder
                .append("&" + URLEncoder.encode("pIndex", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
        urlBuilder.append("&" + URLEncoder.encode("pSize", "UTF-8") + "="
                + URLEncoder.encode("1000", "UTF-8")); /* 한 페이지 결과 수 */
        urlBuilder.append(
                "&" + URLEncoder.encode("SIGUN_NM", "UTF-8") + "=" + URLEncoder.encode(gugunName, "UTF-8")); /* 구군네임 */
        urlBuilder.append(
                "&" + URLEncoder.encode("SIGUN_CD", "UTF-8") + "=" + URLEncoder.encode(gugunCode, "UTF-8")); /* 구군코드*/
        System.out.println(urlBuilder.toString());
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        //JSONObject json = XML.toJSONObject(sb.toString());
        String jsonStr = sb.toString();

        return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
    }
}
