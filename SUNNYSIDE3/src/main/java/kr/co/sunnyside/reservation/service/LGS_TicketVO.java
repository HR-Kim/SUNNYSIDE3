package kr.co.sunnyside.reservation.service;

import kr.co.sunnyside.cmn.DTO;

public class LGS_TicketVO extends DTO {	
	private String ticketCode;	//예매코드
	private String branchId;	//지점id
	private String roomId;		//상영관id
	private String screenId;	//편성id
	private String userId;		//회원id
	private String movieId;		//영화id
	private int seatNum;		//좌석번호
	private int adultCnt;		//성인유무
	private int payState;		//결제상태
	private int cost;			//가격	
	private String payDt;		//결제일
	private String ticketDt;	//예매일

	public LGS_TicketVO() {}

	public LGS_TicketVO(String ticketCode, String branchId, String roomId, String screenId, String userId, String movieId,
			int seatNum, int adultCnt, int payState, int cost, String payDt, String ticketDt) {
		super();
		this.ticketCode = ticketCode;
		this.branchId = branchId;
		this.roomId = roomId;
		this.screenId = screenId;
		this.userId = userId;
		this.movieId = movieId;
		this.seatNum = seatNum;
		this.adultCnt = adultCnt;
		this.payState = payState;
		this.cost = cost;
		this.payDt = payDt;
		this.ticketDt = ticketDt;
	}

	public String getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public int getAdultCnt() {
		return adultCnt;
	}

	public void setAdultCnt(int adultCnt) {
		this.adultCnt = adultCnt;
	}

	public int getPayState() {
		return payState;
	}

	public void setPayState(int payState) {
		this.payState = payState;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getPayDt() {
		return payDt;
	}

	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}

	public String getTicketDt() {
		return ticketDt;
	}

	public void setTicketDt(String ticketDt) {
		this.ticketDt = ticketDt;
	}

	@Override
	public String toString() {
		return "TicketVO [ticketCode=" + ticketCode + ", branchId=" + branchId + ", roomId=" + roomId + ", screenId="
				+ screenId + ", userId=" + userId + ", movieId=" + movieId + ", seatNum=" + seatNum + ", adultCnt="
				+ adultCnt + ", payState=" + payState + ", cost=" + cost + ", payDt=" + payDt + ", ticketDt=" + ticketDt
				+ ", toString()=" + super.toString() + "]";
	}
	
}
