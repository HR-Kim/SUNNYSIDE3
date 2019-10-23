package kr.co.sunnyside.userpage.service;

import kr.co.sunnyside.cmn.DTO;

public class KDY_QnalistVO extends DTO{
	private String title  ;
	private String status ;
	private String reg_dt ;

	KDY_QnalistVO(){}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	@Override
	public String toString() {
		return "KDY_QnalistVO [title=" + title + ", status=" + status + ", reg_dt=" + reg_dt + "]";
	}

	public KDY_QnalistVO(String title, String status, String reg_dt) {
		super();
		this.title = title;
		this.status = status;
		this.reg_dt = reg_dt;
	}

		
}
