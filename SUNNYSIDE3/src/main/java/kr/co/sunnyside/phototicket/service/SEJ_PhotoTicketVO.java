package kr.co.sunnyside.phototicket.service;

import kr.co.sunnyside.cmn.DTO;

public class SEJ_PhotoTicketVO extends DTO {
	private String	kortitle;
	private String	screen_dt;
	private String	seat_num;
	private String	ticket_code;
	private String	user_id;
	private String	org_img_nm;
	private String	save_img_nm;
	private String	ext;
	private long	img_size;
	private String	reg_dt;
	private String ThisFileNm;

	
	public SEJ_PhotoTicketVO() {}


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


	public String getTicket_code() {
		return ticket_code;
	}


	public void setTicket_code(String ticket_code) {
		this.ticket_code = ticket_code;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getOrg_img_nm() {
		return org_img_nm;
	}


	public void setOrg_img_nm(String org_img_nm) {
		this.org_img_nm = org_img_nm;
	}


	public String getSave_img_nm() {
		return save_img_nm;
	}


	public void setSave_img_nm(String save_img_nm) {
		this.save_img_nm = save_img_nm;
	}


	public String getExt() {
		return ext;
	}


	public void setExt(String ext) {
		this.ext = ext;
	}


	public long getImg_size() {
		return img_size;
	}


	public void setImg_size(long img_size) {
		this.img_size = img_size;
	}


	public String getReg_dt() {
		return reg_dt;
	}


	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}


	public String getThisFileNm() {
		return ThisFileNm;
	}


	public void setThisFileNm(String thisFileNm) {
		ThisFileNm = thisFileNm;
	}


	@Override
	public String toString() {
		return "SEJ_PhotoTicketVO [kortitle=" + kortitle + ", screen_dt=" + screen_dt + ", seat_num=" + seat_num
				+ ", ticket_code=" + ticket_code + ", user_id=" + user_id + ", org_img_nm=" + org_img_nm
				+ ", save_img_nm=" + save_img_nm + ", ext=" + ext + ", img_size=" + img_size + ", reg_dt=" + reg_dt
				+ ", ThisFileNm=" + ThisFileNm + "]";
	}


	public SEJ_PhotoTicketVO(String kortitle, String screen_dt, String seat_num, String ticket_code, String user_id,
			String org_img_nm, String save_img_nm, String ext, long img_size, String reg_dt, String thisFileNm) {
		super();
		this.kortitle = kortitle;
		this.screen_dt = screen_dt;
		this.seat_num = seat_num;
		this.ticket_code = ticket_code;
		this.user_id = user_id;
		this.org_img_nm = org_img_nm;
		this.save_img_nm = save_img_nm;
		this.ext = ext;
		this.img_size = img_size;
		this.reg_dt = reg_dt;
		ThisFileNm = thisFileNm;
	}


		
}
