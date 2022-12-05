package com.ssafy.finalbe.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FavoriteHouseDto : 관심주택 매물정보", description = "관심 아파트 매물 상세 정보를 나타낸다.")
public class FavoriteHouseDto {
	
	@ApiModelProperty(value = "주택번호 - auto_increament")
	private int houseid;
	@ApiModelProperty(value = "회원 아이디")
	private String userid;
	@ApiModelProperty(value = "주택 주소")
	private String houseaddress;
	@ApiModelProperty(value = "주택 가격")
	private String houseprice;
	@ApiModelProperty(value = "주택 면적")
	private String housearea;
	@ApiModelProperty(value = "주택 유형")
	private String housetype;
	
	public int getHouseid() {
		return houseid;
	}
	public void setHouseid(int houseid) {
		this.houseid = houseid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getHouseaddress() {
		return houseaddress;
	}
	public void setHouseaddress(String houseaddress) {
		this.houseaddress = houseaddress;
	}
	public String getHouseprice() {
		return houseprice;
	}
	public void setHouseprice(String houseprice) {
		this.houseprice = houseprice;
	}
	public String getHousearea() {
		return housearea;
	}
	public void setHousearea(String housearea) {
		this.housearea = housearea;
	}
	public String getHousetype() {
		return housetype;
	}
	public void setHousetype(String housetype) {
		this.housetype = housetype;
	}
	@Override
	public String toString() {
		return "FavoriteHouseDto [houseid=" + houseid + ", userid=" + userid + ", houseaddress=" + houseaddress
				+ ", houseprice=" + houseprice + ", housearea=" + housearea + ", housetype=" + housetype + "]";
	}
	
}
