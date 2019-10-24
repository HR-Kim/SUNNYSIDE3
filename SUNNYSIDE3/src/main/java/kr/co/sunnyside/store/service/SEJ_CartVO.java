package kr.co.sunnyside.store.service;

import kr.co.sunnyside.cmn.DTO;

public class SEJ_CartVO extends DTO {
	/**상품결제코드*/
	private String payCode     ;  
	/**장바구니코드*/
	private String cartId      ;   
	/**상품명*/
	private String productNm   ; 
	/**회원ID*/
	private String userId      ;   
	/**수량*/
	private int count          ;   
	/**수량*/
	private String strCount          ;   
	/**상품단가*/
	private int oriProductCost   ; 
	/**상품가격*/
	private int productCost    ; 
	/**상품총가격/결제금액*/
	private int totalCost    ; 
	/**상품ID*/
	private String productId   ;  
	/**원본파일명*/
	private String orgFileNm   ;
	/**저장파일명*/
	private String saveFileNm  ;  
	/**확장자명*/
	private String ext         ;   
	/**결제일*/
	private String payDt       ;  
	
	public SEJ_CartVO() {}


	public SEJ_CartVO(String payCode, String cartId, String productNm, String userId, int count, int oriProductCost,
			int productCost, int totalCost, String productId, String orgFileNm, String saveFileNm, String ext) {
		super();
		this.payCode = payCode;
		this.cartId = cartId;
		this.productNm = productNm;
		this.userId = userId;
		this.count = count;
		this.oriProductCost = oriProductCost;
		this.productCost = productCost;
		this.totalCost = totalCost;
		this.productId = productId;
		this.orgFileNm = orgFileNm;
		this.saveFileNm = saveFileNm;
		this.ext = ext;
	}


	public String getPayCode() {
		return payCode;
	}


	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}


	public String getCartId() {
		return cartId;
	}


	public void setCartId(String cartId) {
		this.cartId = cartId;
	}


	public String getProductNm() {
		return productNm;
	}


	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getOriProductCost() {
		return oriProductCost;
	}


	public void setOriProductCost(int oriProductCost) {
		this.oriProductCost = oriProductCost;
	}


	public int getProductCost() {
		return productCost;
	}


	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}


	public int getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
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


	public String getStrCount() {
		return strCount;
	}


	public void setStrCount(String strCount) {
		this.strCount = strCount;
	}


	public String getPayDt() {
		return payDt;
	}


	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}


	@Override
	public String toString() {
		return "SEJ_CartVO [payCode=" + payCode + ", cartId=" + cartId + ", productNm=" + productNm + ", userId="
				+ userId + ", count=" + count + ", oriProductCost=" + oriProductCost + ", productCost=" + productCost
				+ ", totalCost=" + totalCost + ", productId=" + productId + ", orgFileNm=" + orgFileNm + ", saveFileNm="
				+ saveFileNm + ", ext=" + ext + ", toString()=" + super.toString() + "]";
	}

	
}
