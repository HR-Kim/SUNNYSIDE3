package kr.co.sunnyside.review.service;

import kr.co.sunnyside.cmn.SearchVO;

public class LHJ_ReviewVO extends SearchVO{
	/**영화ID*/
	private String movieId;
	/**회원ID*/
	private String userId;
	/**내용*/
	private String contents;
	/**관람객 평점*/
	private double visitorRate;
	/**등록일*/
	private String regDt;
	
	public LHJ_ReviewVO() {}

	public LHJ_ReviewVO(String movieId, String userId, String contents, double visitorRate, String regDt) {
		super();
		this.movieId = movieId;
		this.userId = userId;
		this.contents = contents;
		this.visitorRate = visitorRate;
		this.regDt = regDt;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public double getVisitorRate() {
		return visitorRate;
	}

	public void setVisitorRate(double visitorRate) {
		this.visitorRate = visitorRate;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "LHJ_ReviewVO [movieId=" + movieId + ", userId=" + userId + ", contents=" + contents + ", visitorRate="
				+ visitorRate + ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}
	
	
}
