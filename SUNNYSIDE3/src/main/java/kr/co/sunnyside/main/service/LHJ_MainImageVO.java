package kr.co.sunnyside.main.service;

import kr.co.sunnyside.cmn.DTO;

public class LHJ_MainImageVO extends DTO{
	public String imageId;
	public String orgImgNm;
	public String saveImgNm;
	public String ext;
	public String imgSize;
	
	public LHJ_MainImageVO() {}
	
	public LHJ_MainImageVO(String imageId, String orgImgNm, String saveImgNm, String ext, String imgSize) {
		super();
		this.imageId = imageId;
		this.orgImgNm = orgImgNm;
		this.saveImgNm = saveImgNm;
		this.ext = ext;
		this.imgSize = imgSize;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getOrgImgNm() {
		return orgImgNm;
	}

	public void setOrgImgNm(String orgImgNm) {
		this.orgImgNm = orgImgNm;
	}

	public String getSaveImgNm() {
		return saveImgNm;
	}

	public void setSaveImgNm(String saveImgNm) {
		this.saveImgNm = saveImgNm;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getImgSize() {
		return imgSize;
	}

	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}

	@Override
	public String toString() {
		return "LHJ_MainImageVO [imageId=" + imageId + ", orgImgNm=" + orgImgNm + ", saveImgNm=" + saveImgNm + ", ext="
				+ ext + ", imgSize=" + imgSize + ", toString()=" + super.toString() + "]";
	}
	
	
}
