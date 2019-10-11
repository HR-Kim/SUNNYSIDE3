package kr.co.sunnyside.phototicket.service;

import kr.co.sunnyside.cmn.DTO;

public class SEJ_MovieHistoryVO extends DTO{
	private String	kortitle;
	private String	screen_dt;
	private String	seat_num;
	public SEJ_MovieHistoryVO(){}
	@Override
	public String toString() {
		return "SEJ_MovieHistoryVO [kortitle=" + kortitle + ", screen_dt=" + screen_dt + ", seat_num=" + seat_num + "]";
	}
	public String getKortitle() {
		return kortitle;
	}
	public void setKortitle(String kortitle) {
		this.kortitle = kortitle;
	}
	public String getScreen_dt() {
		return screen_dt;
	}
	public void setScreen_dt(String screen_dt) {
		this.screen_dt = screen_dt;
	}
	public String getSeat_num() {
		return seat_num;
	}
	public void setSeat_num(String seat_num) {
		this.seat_num = seat_num;
	}
	public SEJ_MovieHistoryVO(String kortitle, String screen_dt, String seat_num) {
		super();
		this.kortitle = kortitle;
		this.screen_dt = screen_dt;
		this.seat_num = seat_num;
	}


}
