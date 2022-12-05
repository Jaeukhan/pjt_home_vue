package com.ssafy.finalbe.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FavoriteAptDto : 관심아파트 매물정보", description = "관심 아파트 매물 상세 정보를 나타낸다.")
public class FavoriteAptDto {
	
	@ApiModelProperty(value = "아파트 아이디 - auto increament")
	private int aptid;
	@ApiModelProperty(value = "회원 아이디")
	private String userid;
	@ApiModelProperty(value="아파트 일련번호")
	private String aptcode;
	@ApiModelProperty(value = "아파트 주소")
	private String aptaddress;
	@ApiModelProperty(value = "아파트 가격")
	private String aptprice;
	@ApiModelProperty(value = "아파트 이름")
	private String aptname;
	@ApiModelProperty(value = "아파트 층수")
	private String aptfloor;
	
	public int getAptid() {
		return aptid;
	}
	public void setAptid(int aptid) {
		this.aptid = aptid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAptcode() {
		return aptcode;
	}
	public void setAptcode(String aptcode) {
		this.aptcode = aptcode;
	}
	public String getAptaddress() {
		return aptaddress;
	}
	public void setAptaddress(String aptaddress) {
		this.aptaddress = aptaddress;
	}
	public String getAptprice() {
		return aptprice;
	}
	public void setAptprice(String aptprice) {
		this.aptprice = aptprice;
	}
	public String getAptname() {
		return aptname;
	}
	public void setAptname(String aptname) {
		this.aptname = aptname;
	}
	public String getAptfloor() {
		return aptfloor;
	}
	public void setAptfloor(String aptfloor) {
		this.aptfloor = aptfloor;
	}
	@Override
	public String toString() {
		return "FavoriteAptDto [aptid=" + aptid + ", userid=" + userid + ", aptaddress=" + aptaddress + ", aptprice="
				+ aptprice + ", aptname=" + aptname + ", aptfloor=" + aptfloor + "]";
	}
	
}
