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
	
	public LGS_ScreenInfoVO(){}

	public LGS_ScreenInfoVO(String screenId, String roomId, String branchId, String movieId, String startTime, String endTime,
			String screenDt, int adultCost, int studentCost, int episode) {
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

	@Override
	public String toString() {
		return "ScreenInfo [screenId=" + screenId + ", roomId=" + roomId + ", branchId=" + branchId + ", movieId="
				+ movieId + ", startTime=" + startTime + ", endTime=" + endTime + ", screenDt=" + screenDt
				+ ", adultCost=" + adultCost + ", studentCost=" + studentCost + ", episode=" + episode + ", toString()="
				+ super.toString() + "]";
	}
	
}
