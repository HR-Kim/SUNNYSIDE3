package kr.co.sunnyside.file.service;

import kr.co.sunnyside.cmn.DTO;

public class FileVO extends DTO {
	private String orgFileNm;// 원본파일명
	private String saveFileNm;// 저장파일명
	private long fileSize;// 파일사이즈
	private String ext;// 확장자
	
	public FileVO() {}

	@Override
	public String toString() {
		return "FileVO [orgFileNm=" + orgFileNm + ", saveFileNm=" + saveFileNm + ", fileSize=" + fileSize + ", ext="
				+ ext + ", toString()=" + super.toString() + "]";
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

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public FileVO(String orgFileNm, String saveFileNm, long fileSize, String ext) {
		super();
		this.orgFileNm = orgFileNm;
		this.saveFileNm = saveFileNm;
		this.fileSize = fileSize;
		this.ext = ext;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ext == null) ? 0 : ext.hashCode());
		result = prime * result + (int) (fileSize ^ (fileSize >>> 32));
		result = prime * result + ((orgFileNm == null) ? 0 : orgFileNm.hashCode());
		result = prime * result + ((saveFileNm == null) ? 0 : saveFileNm.hashCode());
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
		FileVO other = (FileVO) obj;
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
		if (saveFileNm == null) {
			if (other.saveFileNm != null)
				return false;
		} else if (!saveFileNm.equals(other.saveFileNm))
			return false;
		return true;
	}

}
