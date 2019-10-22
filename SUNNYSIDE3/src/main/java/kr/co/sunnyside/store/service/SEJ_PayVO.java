package kr.co.sunnyside.store.service;

import kr.co.sunnyside.cmn.DTO;

public class SEJ_PayVO extends DTO {
	/**상품결제코드*/
	private String payCode     ;  
	/**회원ID*/
	private String userId      ;  
	/**결제금액*/
	private String payCost ;  
	/**수량*/
	private int count          ;  
	/**결제일*/
	private String payDt       ;  
	/**장바구니코드*/
	private String cartId      ;  
	/**상품명*/
	private String productNm   ;  
	/**상품ID*/
	private String productId   ;  
	
	public SEJ_PayVO() {}

	public SEJ_PayVO(String payCode, String userId, String payCost, int count, String payDt, String cartId,
			String productNm, String productId) {
		super();
		this.payCode = payCode;
		this.userId = userId;
		this.payCost = payCost;
		this.count = count;
		this.payDt = payDt;
		this.cartId = cartId;
		this.productNm = productNm;
		this.productId = productId;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPayCost() {
		return payCost;
	}

	public void setPayCost(String payCost) {
		this.payCost = payCost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPayDt() {
		return payDt;
	}

	public void setPayDt(String payDt) {
		this.payDt = payDt;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "SEJ_PayVO [payCode=" + payCode + ", userId=" + userId + ", payCost=" + payCost + ", count=" + count
				+ ", payDt=" + payDt + ", cartId=" + cartId + ", productNm=" + productNm + ", productId=" + productId
				+ ", toString()=" + super.toString() + "]";
	}

	

}
