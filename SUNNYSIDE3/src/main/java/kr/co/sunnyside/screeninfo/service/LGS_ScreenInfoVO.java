package kr.co.sunnyside.screeninfo.service;

import kr.co.sunnyside.cmn.DTO;

public class LGS_ScreenInfoVO extends DTO {

	private String screenId;	//편성id
	private String roomId;		//상영관id
	private String branchId;	//지점id
	private String movieId;		//영화id
	private String startTime;	//상영시작시간
	private String endTime;		//상영종료시간	
	private String screenDt;	//상영날짜
	private int adultCost;		//성인
	private int studentCost;	//학생
	private int episode;		//회차
	private String korTitle;	//한글제목
	private String engTitle;	//영어제목
	private double expertRate;	//전문가평점
	private double visitorRate;	//관객평점
	private String branchNm;	//지점이름
	private String roomNm;		//상영관이름
	
	public LGS_ScreenInfoVO(){}

	public LGS_ScreenInfoVO(String screenId, String roomId, String branchId, String movieId, String startTime,
			String endTime, String screenDt, int adultCost, int studentCost, int episode, String korTitle,
			String engTitle, double expertRate, double visitorRate) {
		super();
		this.screenId = screenId;
		this.roomId = roomId;
		this.branchId = branchId;
		this.movieId = movieId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.screenDt = screenDt;
		this.adultCost = adultCost;
		this.studentCost = studentCost;
		this.episode = episode;
		this.korTitle = korTitle;
		this.engTitle = engTitle;
		this.expertRate = expertRate;
		this.visitorRate = visitorRate;
	}

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getScreenDt() {
		return screenDt;
	}

	public void setScreenDt(String screenDt) {
		this.screenDt = screenDt;
	}

	public int getAdultCost() {
		return adultCost;
	}

	public void setAdultCost(int adultCost) {
		this.adultCost = adultCost;
	}

	public int getStudentCost() {
		return studentCost;
	}

	public void setStudentCost(int studentCost) {
		this.studentCost = studentCost;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public String getKorTitle() {
		return korTitle;
	}

	public void setKorTitle(String korTitle) {
		this.korTitle = korTitle;
	}

	public String getEngTitle() {
		return engTitle;
	}

	public void setEngTitle(String engTitle) {
		this.engTitle = engTitle;
	}

	public double getExpertRate() {
		return expertRate;
	}

	public void setExpertRate(double expertRate) {
		this.expertRate = expertRate;
	}

	public double getVisitorRate() {
		return visitorRate;
	}

	public void setVisitorRate(double visitorRate) {
		this.visitorRate = visitorRate;
	}

	public String getBranchNm() {
		return branchNm;
	}

	public void setBranchNm(String branchNm) {
		this.branchNm = branchNm;
	}

	public String getRoomNm() {
		return roomNm;
	}

	public void setRoomNm(String roomNm) {
		this.roomNm = roomNm;
	}

	@Override
	public String toString() {
		return "LGS_ScreenInfoVO [screenId=" + screenId + ", roomId=" + roomId + ", branchId=" + branchId + ", movieId="
				+ movieId + ", startTime=" + startTime + ", endTime=" + endTime + ", screenDt=" + screenDt
				+ ", adultCost=" + adultCost + ", studentCost=" + studentCost + ", episode=" + episode + ", korTitle="
				+ korTitle + ", engTitle=" + engTitle + ", expertRate=" + expertRate + ", visitorRate=" + visitorRate
				+ ", branchNm=" + branchNm + ", roomNm=" + roomNm + ", toString()=" + super.toString() + "]";
	}

}
