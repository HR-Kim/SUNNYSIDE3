package kr.co.sunnyside.usermypage.service;

import kr.co.sunnyside.cmn.DTO;

public class SJH_MypageVO extends DTO {

	private String userId    ;//회원ID
	private String passwd    ;//비번
	private String userName  ;//이름
	private String email     ;//이메일
	private String userLevel ;//등급
	private int	   point     ;//포인트
	private String cellphone ;//핸드폰번호
	private String birth     ;//생년월일
	private String regDt     ;//등록일
	
	/**Level to IntValue*/
	private int level;
	
	
	public SJH_MypageVO() {}


	public SJH_MypageVO(String userId, String passwd, String userName, String email, String userLevel, int point,
			String cellphone, String birth, String regDt, int level) {
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
		this.level = level;
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


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	@Override
	public String toString() {
		return "SJH_MypageVO [userId=" + userId + ", passwd=" + passwd + ", userName=" + userName + ", email=" + email
				+ ", userLevel=" + userLevel + ", point=" + point + ", cellphone=" + cellphone + ", birth=" + birth
				+ ", regDt=" + regDt + ", level=" + level + "]";
	}
	
	
	
	
	
}
