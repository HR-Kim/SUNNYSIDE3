package kr.co.sunnyside.store.service;

import kr.co.sunnyside.cmn.DTO;

public class SEJ_StroreVO extends DTO {
	
	/**상품ID 20190927-분류명-001 */ 
	private String productId;
	/**상품명 */
	private String productNm;
	/**상품정보*/
	private String pruductInfo;
	/**분류  001:팝콘  002:음료 003:영화예매권*/ 
	private int category;
	/**상품가격 */
	private int productCost;
	/**이미지 */
	private String img ;

	
	public SEJ_StroreVO() {}


	public SEJ_StroreVO(String productId, String productNm, String pruductInfo, int category, int productCost,
			String img) {
		super();
		this.productId = productId;
		this.productNm = productNm;
		this.pruductInfo = pruductInfo;
		this.category = category;
		this.productCost = productCost;
		this.img = img;
	}


	@Override
	public String toString() {
		return "SEJ_StroreVO [productId=" + productId + ", productNm=" + productNm + ", pruductInfo=" + pruductInfo
				+ ", category=" + category + ", productCost=" + productCost + ", img=" + img + ", toString()="
				+ super.toString() + "]";
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


	public String getPruductInfo() {
		return pruductInfo;
	}


	public void setPruductInfo(String pruductInfo) {
		this.pruductInfo = pruductInfo;
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


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}
	
	
}
