package kr.co.sunnyside.notice.service;

import kr.co.sunnyside.cmn.DTO;

public class KYMBranchVO extends DTO{
	
	private String branchSNm;
	private String bardchId;
	
	public KYMBranchVO() {}

	@Override
	public String toString() {
		return "KYMBranchVO [branchSNm=" + branchSNm + ", bardchId=" + bardchId + ", toString()=" + super.toString()
				+ "]";
	}

	public String getbranchSNm() {
		return branchSNm;
	}

	public void setbranchSNm(String branchSNm) {
		this.branchSNm = branchSNm;
	}

	public String getBardchId() {
		return bardchId;
	}

	public void setBardchId(String bardchId) {
		this.bardchId = bardchId;
	}

	public KYMBranchVO(String branchSNm, String bardchId) {
		super();
		this.branchSNm = branchSNm;
		this.bardchId = bardchId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bardchId == null) ? 0 : bardchId.hashCode());
		result = prime * result + ((branchSNm == null) ? 0 : branchSNm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KYMBranchVO other = (KYMBranchVO) obj;
		if (bardchId == null) {
			if (other.bardchId != null)
				return false;
		} else if (!bardchId.equals(other.bardchId))
			return false;
		if (branchSNm == null) {
			if (other.branchSNm != null)
				return false;
		} else if (!branchSNm.equals(other.branchSNm))
			return false;
		return true;
	}

}
