package kr.co.sunnyside.userpage.service;

import kr.co.sunnyside.cmn.DTO;

public class KDY_MoviehistoryVO extends DTO {	
	private String ticket_code;	//예매코드
	private String kortitle;	//지점id
	private String ticket_dt;
	private String room_nm;
	
	
	

	public KDY_MoviehistoryVO() {}




	public String getTicket_code() {
		return ticket_code;
	}




	public void setTicket_code(String ticket_code) {
		this.ticket_code = ticket_code;
	}




	public String getKortitle() {
		return kortitle;
	}




	public void setKortitle(String kortitle) {
		this.kortitle = kortitle;
	}




	public String getTicket_dt() {
		return ticket_dt;
	}




	public void setTicket_dt(String ticket_dt) {
		this.ticket_dt = ticket_dt;
	}




	public String getRoom_nm() {
		return room_nm;
	}




	public void setRoom_nm(String room_nm) {
		this.room_nm = room_nm;
	}




	@Override
	public String toString() {
		return "KDY_MoviehistoryVO [ticket_code=" + ticket_code + ", kortitle=" + kortitle + ", ticket_dt=" + ticket_dt
				+ ", room_nm=" + room_nm + "]";
	}




	public KDY_MoviehistoryVO(String ticket_code, String kortitle, String ticket_dt, String room_nm) {
		super();
		this.ticket_code = ticket_code;
		this.kortitle = kortitle;
		this.ticket_dt = ticket_dt;
		this.room_nm = room_nm;
	}




		
}
