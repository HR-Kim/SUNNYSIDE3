package kr.co.sunnyside.cmn;

public class DTO {
	
	/** Locale */
	private String lang;
	/** 총 글 수 */
	private int totalCnt;
	/** 글 번호 */
	private int num;

	
	public DTO() {}

	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	

	@Override
	public String toString() {
		return "DTO [lang=" + lang + ", totalCnt=" + totalCnt + ", num=" + num + ", toString()=" + super.toString()
				+ "]";
	}

	
	
	
	

}
