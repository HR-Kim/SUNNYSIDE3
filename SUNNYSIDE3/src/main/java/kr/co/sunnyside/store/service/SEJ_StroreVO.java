package kr.co.sunnyside.store.service;

public class SEJ_StroreVO extends FileVO {
	
	/**상품ID 20190927-분류명-001 */ 
	private String productId;
	/**상품명 */
	private String productNm;
	/**상품정보*/
	private String pruductInfo;
	/**분류  001:팝콘  002:음료 003:영화예매권*/ 
	private String category;
	/**상품가격 */
	private String productCost;
	
	public SEJ_StroreVO() {}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductCost() {
		return productCost;
	}

	public void setProductCost(String productCost) {
		this.productCost = productCost;
	}

	@Override
	public String toString() {
		return "SEJ_StroreVO [productId=" + productId + ", productNm=" + productNm + ", pruductInfo=" + pruductInfo
				+ ", category=" + category + ", productCost=" + productCost + ", toString()=" + super.toString() + "]";
	}
	
	
}
