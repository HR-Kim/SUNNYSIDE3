package kr.co.sunnyside.userpage.service;

import kr.co.sunnyside.cmn.DTO;

public class KDY_UserinfoVO  extends DTO{
	private int total_pay       ;
	private String user_name	;
	private String user_level   ;
	private String user_id		;
	
	KDY_UserinfoVO(){}

	public int getTotal_pay() {
		return total_pay;
	}

	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "KDY_UserinfoVO [total_pay=" + total_pay + ", user_name=" + user_name + ", user_level=" + user_level
				+ ", user_id=" + user_id + ", getNum()=" + getNum() + "]";
	}

	public KDY_UserinfoVO(int total_pay, String user_name, String user_level, String user_id) {
		super();
		this.total_pay = total_pay;
		this.user_name = user_name;
		this.user_level = user_level;
		this.user_id = user_id;
	}
	
}
