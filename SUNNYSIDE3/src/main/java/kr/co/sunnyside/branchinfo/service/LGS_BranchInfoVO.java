package kr.co.sunnyside.branchinfo.service;

import kr.co.sunnyside.cmn.DTO;

public class LGS_BranchInfoVO extends DTO {
	
	private String branchId;	//지점id
	private String branchNm;	//지점명
	
	public LGS_BranchInfoVO() {}

	public LGS_BranchInfoVO(String branchId, String branchNm) {
		super();
		this.branchId = branchId;
		this.branchNm = branchNm;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchNm() {
		return branchNm;
	}

	public void setBranchNm(String branchNm) {
		this.branchNm = branchNm;
	}

	@Override
	public String toString() {
		return "BranchInfo [branchId=" + branchId + ", branchNm=" + branchNm + ", toString()=" + super.toString() + "]";
	}

}
