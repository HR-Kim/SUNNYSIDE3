package kr.co.sunnyside.login.service;

import kr.co.sunnyside.cmn.DTO;

public class SJH_LoginVO extends DTO {

	private String userId		;//	회원ID
	private String passwd		;//	비번
	private String userName		;//	이름
	private String email		;//	이메일
	
	
	public SJH_LoginVO() {}


	public SJH_LoginVO(String userId, String passwd, String userName, String email) {
		super();
		this.userId = userId;
		this.passwd = passwd;
		this.userName = userName;
		this.email = email;
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


	@Override
	public String toString() {
		return "SJH_LoginVO [userId=" + userId + ", passwd=" + passwd + ", userName=" + userName + ", email=" + email
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
