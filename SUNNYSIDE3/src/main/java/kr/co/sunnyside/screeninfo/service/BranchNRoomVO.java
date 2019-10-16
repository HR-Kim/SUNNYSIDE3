package kr.co.sunnyside.screeninfo.service;

public class BranchNRoomVO {
	
	private String branchNm;
	private String roomNm;
	private String branchNroom;
	
	public BranchNRoomVO() {}

	public BranchNRoomVO(String branchNm, String roomNm, String branchNroom) {
		super();
		this.branchNm = branchNm;
		this.roomNm = roomNm;
		this.branchNroom = branchNroom;
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

	public String getBranchNroom() {
		return branchNroom;
	}

	public void setBranchNroom(String branchNroom) {
		this.branchNroom = branchNroom;
	}

	@Override
	public String toString() {
		return "BranchNRoomVO [branchNm=" + branchNm + ", roomNm=" + roomNm + ", branchNroom=" + branchNroom
				+ ", toString()=" + super.toString() + "]";
	}

}
