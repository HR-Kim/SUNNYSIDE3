package kr.co.sunnyside.store.service;

import kr.co.sunnyside.cmn.DTO;

public class SEJ_StroreVO extends DTO {
	
	/**상품ID 20190927-분류명-001 */ 
	private String productId;
	/**상품명 */
	private String productNm;
	/**상품정보*/
	private String productInfo;
	/**분류  001:팝콘  002:음료 003:영화예매권*/ 
	private int category;
	/**상품가격 */
	private int productCost;
	/**원본이미지이름 */
	private String orgFileNm;
	/**저장이미지이름 */
	private String saveFileNm;
	/**확장자 */
	private String ext;

	
	public SEJ_StroreVO() {}


	@Override
	public String toString() {
		return "SEJ_StroreVO [productId=" + productId + ", productNm=" + productNm + ", productInfo=" + productInfo
				+ ", category=" + category + ", productCost=" + productCost + ", orgFileNm=" + orgFileNm
				+ ", saveFileNm=" + saveFileNm + ", ext=" + ext + ", toString()=" + super.toString() + "]";
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductNm() {
		return productNm;
	}


	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}


	public String getProductInfo() {
		return productInfo;
	}


	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}


	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
		this.category = category;
	}


	public int getProductCost() {
		return productCost;
	}


	public void setProductCost(int productCost) {
		this.productCost = productCost;
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


	public SEJ_StroreVO(String productId, String productNm, String productInfo, int category, int productCost,
			String orgFileNm, String saveFileNm, String ext) {
		super();
		this.productId = productId;
		this.productNm = productNm;
		this.productInfo = productInfo;
		this.category = category;
		this.productCost = productCost;
		this.orgFileNm = orgFileNm;
		this.saveFileNm = saveFileNm;
		this.ext = ext;
	}


	

	
	
}
