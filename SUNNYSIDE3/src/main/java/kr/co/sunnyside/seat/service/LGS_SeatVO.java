package kr.co.sunnyside.seat.service;

import kr.co.sunnyside.cmn.DTO;

public class LGS_SeatVO extends DTO {
	
	private String branchId;	//지점id
	private String roomId;	//상영관id
	private int seatNum;	//좌석번호
	
	public LGS_SeatVO() {}

	public LGS_SeatVO(String branchId, String roomId, int seatNum) {
		super();
		this.branchId = branchId;
		this.roomId = roomId;
		this.seatNum = seatNum;
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

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	@Override
	public String toString() {
		return "Seat [branchId=" + branchId + ", roomId=" + roomId + ", seatNum=" + seatNum + ", toString()="
				+ super.toString() + "]";
	}

}
