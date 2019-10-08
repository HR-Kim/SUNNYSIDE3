package kr.co.sunnyside.seat.service;

import kr.co.sunnyside.cmn.DTO;

public class LGS_SeatVO extends DTO {
	
	private String branchId;	//지점id
	private String roomId;	//상영관id
	private String seatNm;	//좌석명
	private String seatY;	//Y축
	private int seatX;	//X축
	private String useYN;	//사용여부
		
	public LGS_SeatVO() {}

	public LGS_SeatVO(String branchId, String roomId, String seatNm, String seatY, int seatX, String useYN) {
		super();
		this.branchId = branchId;
		this.roomId = roomId;
		this.seatNm = seatNm;
		this.seatY = seatY;
		this.seatX = seatX;
		this.useYN = useYN;
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

	public String getSeatNm() {
		return seatNm;
	}

	public void setSeatNm(String seatNm) {
		this.seatNm = seatNm;
	}

	public String getSeatY() {
		return seatY;
	}

	public void setSeatY(String seatY) {
		this.seatY = seatY;
	}

	public int getSeatX() {
		return seatX;
	}

	public void setSeatX(int seatX) {
		this.seatX = seatX;
	}

	public String getUseYN() {
		return useYN;
	}

	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}

	@Override
	public String toString() {
		return "LGS_SeatVO [branchId=" + branchId + ", roomId=" + roomId + ", seatNm=" + seatNm + ", seatY=" + seatY
				+ ", seatX=" + seatX + ", useYN=" + useYN + ", toString()=" + super.toString() + "]";
	}

}
