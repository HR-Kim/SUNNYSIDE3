package kr.co.sunnyside.file.service;

import kr.co.sunnyside.cmn.DTO;

public class File extends DTO {
	/**파일ID   */
	private String fileId		;    
	
	/**원본파일명  */
	private String orgFileNm	;	 
	
	/**새 컬럼   */
	private String division		;    
	
	/**저장파일명  */
	private String saveFileNm	;    
	
	/**사이즈    */
	private double   fSize		;    
	
	/**확장자    */
	private String ext		    ;    
	
	/**등록자ID  */
	private String regId		;    
	
	/**등록일    */
	private String regDt		;    
	
	
	public File() {}


	public File(String fileId,int num ,String orgFileNm, String division, String saveFileNm, long fSize, String ext,
			String regId, String regDt) {
		super();
		super.setNum(num);
		this.fileId = fileId;		
		this.orgFileNm = orgFileNm;
		this.division = division;
		this.saveFileNm = saveFileNm;
		this.fSize = fSize;
		this.ext = ext;
		this.regId = regId;
		this.regDt = regDt;
	}


	public String getFileId() {
		return fileId;
	}


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}


	public String getOrgFileNm() {
		return orgFileNm;
	}


	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}


	public String getDivision() {
		return division;
	}


	public void setDivision(String division) {
		this.division = division;
	}


	public String getSaveFileNm() {
		return saveFileNm;
	}


	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}


	public double getfSize() {
		return fSize;
	}


	public void setfSize(long fSize) {
		this.fSize = fSize;
	}


	public String getExt() {
		return ext;
	}


	public void setExt(String ext) {
		this.ext = ext;
	}


	public String getRegId() {
		return regId;
	}


	public void setRegId(String regId) {
		this.regId = regId;
	}


	public String getRegDt() {
		return regDt;
	}


	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}


	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", orgFileNm=" + orgFileNm + ", division=" + division + ", saveFileNm="
				+ saveFileNm + ", fSize=" + fSize + ", ext=" + ext + ", regId=" + regId + ", regDt=" + regDt
				+ ", toString()=" + super.toString() + "]";
	}
	

}