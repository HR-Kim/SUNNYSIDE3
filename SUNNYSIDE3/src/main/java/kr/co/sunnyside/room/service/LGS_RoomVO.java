package kr.co.sunnyside.room.service;

import kr.co.sunnyside.cmn.DTO;

public class LGS_RoomVO extends DTO {

	private String branchId;	//지점id
	private String roomId;		//상영관id
	private String roomNm;		//상영관명
	private int totalSeat;		//총좌석
	private int restSeat;		//잔여좌석
	
	public LGS_RoomVO() {}

	public LGS_RoomVO(String branchId, String roomId, String roomNm, int totalSeat, int restSeat) {
		super();
		this.branchId = branchId;
		this.roomId = roomId;
		this.roomNm = roomNm;
		this.totalSeat = totalSeat;
		this.restSeat = restSeat;
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

	public String getRoomNm() {
		return roomNm;
	}

	public void setRoomNm(String roomNm) {
		this.roomNm = roomNm;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public int getRestSeat() {
		return restSeat;
	}

	public void setRestSeat(int restSeat) {
		this.restSeat = restSeat;
	}

	@Override
	public String toString() {
		return "Room [branchId=" + branchId + ", roomId=" + roomId + ", roomNm=" + roomNm + ", totalSeat=" + totalSeat
				+ ", restSeat=" + restSeat + ", toString()=" + super.toString() + "]";
	}

}
