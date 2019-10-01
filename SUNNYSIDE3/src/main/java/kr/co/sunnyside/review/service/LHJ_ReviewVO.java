package kr.co.sunnyside.review.service;

import kr.co.sunnyside.cmn.DTO;

public class LHJ_ReviewVO extends DTO{
	/**영화ID*/
	private String MOVIE_ID;
	/**회원ID*/
	private String USER_ID;
	/**내용*/
	private String CONTENTS;
	/**관람객 평점*/
	private double VISITOR_RATE;
	/**등록일*/
	private String REG_DT;
	
	public LHJ_ReviewVO() {}
	
	public LHJ_ReviewVO(String mOVIE_ID, String uSER_ID, String cONTENTS, double vISITOR_RATE, String rEG_DT) {
		super();
		MOVIE_ID = mOVIE_ID;
		USER_ID = uSER_ID;
		CONTENTS = cONTENTS;
		VISITOR_RATE = vISITOR_RATE;
		REG_DT = rEG_DT;
	}

	public String getMOVIE_ID() {
		return MOVIE_ID;
	}

	public void setMOVIE_ID(String mOVIE_ID) {
		MOVIE_ID = mOVIE_ID;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getCONTENTS() {
		return CONTENTS;
	}

	public void setCONTENTS(String cONTENTS) {
		CONTENTS = cONTENTS;
	}

	public double getVISITOR_RATE() {
		return VISITOR_RATE;
	}

	public void setVISITOR_RATE(double vISITOR_RATE) {
		VISITOR_RATE = vISITOR_RATE;
	}

	public String getREG_DT() {
		return REG_DT;
	}

	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}

	@Override
	public String toString() {
		return "LHJ_ReviewVO [MOVIE_ID=" + MOVIE_ID + ", USER_ID=" + USER_ID + ", CONTENTS=" + CONTENTS
				+ ", VISITOR_RATE=" + VISITOR_RATE + ", REG_DT=" + REG_DT + ", toString()=" + super.toString() + "]";
	}
}
