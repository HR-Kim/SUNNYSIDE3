package kr.co.sunnyside.store.service;

import kr.co.sunnyside.cmn.DTO;

public class FileVO extends DTO {
	private String orgFileNM ;//원본파일 명
	private String saveFileNM; //저장파일 명
	private long fileSize ; //파일 사이즈
	private String ext ; //확장자
	
	public FileVO() {}
	
	public String getOrgFileNM() {
		return orgFileNM;
	}
	public void setOrgFileNM(String orgFileNM) {
		this.orgFileNM = orgFileNM;
	}
	public String getSaveFileNM() {
		return saveFileNM;
	}
	public void setSaveFileNM(String saveFileNM) {
		this.saveFileNM = saveFileNM;
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
	@Override
	public String toString() {
		return "FileVO [orgFileNM=" + orgFileNM + ", saveFileNM=" + saveFileNM + ", fileSize=" + fileSize + ", ext="
				+ ext + ", toString()=" + super.toString() + "]";
	}
	
	
}
