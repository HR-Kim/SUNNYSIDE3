package kr.co.sunnyside.notice.service;

import kr.co.sunnyside.cmn.DTO;

public class KYMNoticeVO extends DTO {

	private String noticeId;   //공지ID(PK)
	private String title;      //제목
	private String orgFileNm;  //원본파일명
	private String saveFileNm; //저장파일명
	private String ext;        //확장자명
	private String contents;   //내용
	private String writerId;   //작성자ID
	private String modDt;      //수정일
	private String regDt;      //등록일
	private String branchSNm;  //지점명
	
	public KYMNoticeVO() {}

	@Override
	public String toString() {
		return "KYMCenterVO [noticeId=" + noticeId + ", title=" + title + ", orgFileNm=" + orgFileNm + ", saveFileNm="
				+ saveFileNm + ", ext=" + ext + ", contents=" + contents + ", writerId=" + writerId + ", modDt=" + modDt
				+ ", regDt=" + regDt + ", branchSNm=" + branchSNm + ", toString()=" + super.toString() + "]";
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrgFileNm() {
		return orgFileNm;
	}

	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}

	public String getSaveFileNm() {
		return saveFileNm;
	}

	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getBranchSNm() {
		return branchSNm;
	}

	public void setBranchSNm(String branchSNm) {
		this.branchSNm = branchSNm;
	}

	public KYMNoticeVO(String noticeId, String title, String orgFileNm, String saveFileNm, String ext, String contents,
			String writerId, String modDt, String regDt, String branchSNm) {
		super();
		this.noticeId = noticeId;
		this.title = title;
		this.orgFileNm = orgFileNm;
		this.saveFileNm = saveFileNm;
		this.ext = ext;
		this.contents = contents;
		this.writerId = writerId;
		this.modDt = modDt;
		this.regDt = regDt;
		this.branchSNm = branchSNm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchSNm == null) ? 0 : branchSNm.hashCode());
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((ext == null) ? 0 : ext.hashCode());
		result = prime * result + ((modDt == null) ? 0 : modDt.hashCode());
		result = prime * result + ((noticeId == null) ? 0 : noticeId.hashCode());
		result = prime * result + ((orgFileNm == null) ? 0 : orgFileNm.hashCode());
		result = prime * result + ((regDt == null) ? 0 : regDt.hashCode());
		result = prime * result + ((saveFileNm == null) ? 0 : saveFileNm.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((writerId == null) ? 0 : writerId.hashCode());
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
		KYMNoticeVO other = (KYMNoticeVO) obj;
		if (branchSNm == null) {
			if (other.branchSNm != null)
				return false;
		} else if (!branchSNm.equals(other.branchSNm))
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (ext == null) {
			if (other.ext != null)
				return false;
		} else if (!ext.equals(other.ext))
			return false;
		if (modDt == null) {
			if (other.modDt != null)
				return false;
		} else if (!modDt.equals(other.modDt))
			return false;
		if (noticeId == null) {
			if (other.noticeId != null)
				return false;
		} else if (!noticeId.equals(other.noticeId))
			return false;
		if (orgFileNm == null) {
			if (other.orgFileNm != null)
				return false;
		} else if (!orgFileNm.equals(other.orgFileNm))
			return false;
		if (regDt == null) {
			if (other.regDt != null)
				return false;
		} else if (!regDt.equals(other.regDt))
			return false;
		if (saveFileNm == null) {
			if (other.saveFileNm != null)
				return false;
		} else if (!saveFileNm.equals(other.saveFileNm))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (writerId == null) {
			if (other.writerId != null)
				return false;
		} else if (!writerId.equals(other.writerId))
			return false;
		return true;
	}

}
