package kr.co.sunnyside.usermypage.service;

import kr.co.sunnyside.cmn.DTO;

public class SJH_MypageVO extends DTO {

	private String userId		;//	회원ID
	private String passwd		;//	비번
	private String userName		;//	이름
	private String email		;//	이메일
	private String cellphone	;//	핸드폰번호
	
	
	public SJH_MypageVO() {}


	public SJH_MypageVO(String userId, String passwd, String userName, String email, String cellphone) {
		super();
		this.userId = userId;
		this.passwd = passwd;
		this.userName = userName;
		this.email = email;
		this.cellphone = cellphone;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCellphone() {
		return cellphone;
	}


	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}


	@Override
	public String toString() {
		return "SJH_MypageVO [userId=" + userId + ", passwd=" + passwd + ", userName=" + userName + ", email=" + email
				+ ", cellphone=" + cellphone + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
