package kr.co.sunnyside.login.service;

import kr.co.sunnyside.cmn.DTO;

public class SJH_LoginVO extends DTO {

	private String userId    ;//회원ID
	private String passwd    ;//비번
	private String userName  ;//이름
	private String email     ;//이메일
	private String userLevel ;//등급
	private int	   point     ;//포인트
	private String cellphone ;//핸드폰번호
	private String birth     ;//생년월일
	private String regDt     ;//등록일
	private int	   totalPay  ;//총결제금액
	private String flag      ;//네이버로그인 플래그
	
	
	public SJH_LoginVO() {}


	public SJH_LoginVO(String userId, String passwd, String userName, String email, String userLevel, int point,
			String cellphone, String birth, String regDt, int totalPay, String flag) {
		super();
		this.userId = userId;
		this.passwd = passwd;
		this.userName = userName;
		this.email = email;
		this.userLevel = userLevel;
		this.point = point;
		this.cellphone = cellphone;
		this.birth = birth;
		this.regDt = regDt;
		this.totalPay = totalPay;
		this.flag = flag;
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


	public String getUserLevel() {
		return userLevel;
	}


	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public String getCellphone() {
		return cellphone;
	}


	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getRegDt() {
		return regDt;
	}


	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}


	public int getTotalPay() {
		return totalPay;
	}


	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	@Override
	public String toString() {
		return "SJH_LoginVO [userId=" + userId + ", passwd=" + passwd + ", userName=" + userName + ", email=" + email
				+ ", userLevel=" + userLevel + ", point=" + point + ", cellphone=" + cellphone + ", birth=" + birth
				+ ", regDt=" + regDt + ", totalPay=" + totalPay + ", flag=" + flag + "]";
	}


	
	
	
	
	
	
}
