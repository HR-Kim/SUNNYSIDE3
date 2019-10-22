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
	
	public SEJ_PayVO() {}

	public SEJ_PayVO(String payCode, String userId, String payCost, int count, String payDt, String cartId,
			String productNm) {
		super();
		this.payCode = payCode;
		this.userId = userId;
		this.payCost = payCost;
		this.count = count;
		this.payDt = payDt;
		this.cartId = cartId;
		this.productNm = productNm;
	}

	@Override
	public String toString() {
		return "SEJ_PayVO [payCode=" + payCode + ", userId=" + userId + ", payCost=" + payCost + ", count="
				+ count + ", payDt=" + payDt + ", cartId=" + cartId + ", productNm=" + productNm + ", toString()="
				+ super.toString() + "]";
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

	public String getpayCost() {
		return payCost;
	}

	public void setpayCost(String payCost) {
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

}
