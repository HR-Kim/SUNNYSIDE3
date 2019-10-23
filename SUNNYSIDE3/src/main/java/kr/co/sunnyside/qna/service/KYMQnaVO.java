package kr.co.sunnyside.qna.service;

import kr.co.sunnyside.cmn.DTO;

public class KYMQnaVO extends DTO {

	private String userId    ; //회원id
	private String qnaNum    ; //문의번호
	private String title     ; //제목
	private String contents  ; //질문내용
	private String status    ; //질문 답변상태
	private String regDt     ; //질문등록일
	private String orgFileNm ; //원본파일명
	private String saveFileNm; //저장파일명
	private String ext       ; //확장자명
	private long fileSize  ; //용량
	private String reContents; //답변 내용
	private String reDt      ; //답변등록일
	
	public KYMQnaVO() {}

	@Override
	public String toString() {
		return "KYMQnaVO [userId=" + userId + ", qnaNum=" + qnaNum + ", title=" + title + ", contents=" + contents
				+ ", status=" + status + ", regDt=" + regDt + ", orgFileNm=" + orgFileNm + ", saveFileNm=" + saveFileNm
				+ ", ext=" + ext + ", fileSize=" + fileSize + ", reContents=" + reContents + ", reDt=" + reDt
				+ ", toString()=" + super.toString() + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQnaNum() {
		return qnaNum;
	}

	public void setQnaNum(String qnaNum) {
		this.qnaNum = qnaNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
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

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getReContents() {
		return reContents;
	}

	public void setReContents(String reContents) {
		this.reContents = reContents;
	}

	public String getReDt() {
		return reDt;
	}

	public void setReDt(String reDt) {
		this.reDt = reDt;
	}

	public KYMQnaVO(String userId, String qnaNum, String title, String contents, String status, String regDt,
			String orgFileNm, String saveFileNm, String ext, long fileSize, String reContents, String reDt) {
		super();
		this.userId = userId;
		this.qnaNum = qnaNum;
		this.title = title;
		this.contents = contents;
		this.status = status;
		this.regDt = regDt;
		this.orgFileNm = orgFileNm;
		this.saveFileNm = saveFileNm;
		this.ext = ext;
		this.fileSize = fileSize;
		this.reContents = reContents;
		this.reDt = reDt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((ext == null) ? 0 : ext.hashCode());
		result = prime * result + (int) (fileSize ^ (fileSize >>> 32));
		result = prime * result + ((orgFileNm == null) ? 0 : orgFileNm.hashCode());
		result = prime * result + ((qnaNum == null) ? 0 : qnaNum.hashCode());
		result = prime * result + ((reContents == null) ? 0 : reContents.hashCode());
		result = prime * result + ((reDt == null) ? 0 : reDt.hashCode());
		result = prime * result + ((regDt == null) ? 0 : regDt.hashCode());
		result = prime * result + ((saveFileNm == null) ? 0 : saveFileNm.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		KYMQnaVO other = (KYMQnaVO) obj;
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
		if (fileSize != other.fileSize)
			return false;
		if (orgFileNm == null) {
			if (other.orgFileNm != null)
				return false;
		} else if (!orgFileNm.equals(other.orgFileNm))
			return false;
		if (qnaNum == null) {
			if (other.qnaNum != null)
				return false;
		} else if (!qnaNum.equals(other.qnaNum))
			return false;
		if (reContents == null) {
			if (other.reContents != null)
				return false;
		} else if (!reContents.equals(other.reContents))
			return false;
		if (reDt == null) {
			if (other.reDt != null)
				return false;
		} else if (!reDt.equals(other.reDt))
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
