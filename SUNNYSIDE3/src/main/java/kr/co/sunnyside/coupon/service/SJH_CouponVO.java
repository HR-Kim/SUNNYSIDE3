package kr.co.sunnyside.coupon.service;

import kr.co.sunnyside.cmn.DTO;

public class SJH_CouponVO extends DTO {
	
	public String couponCode	 ;//쿠폰코드
	public String userId		 ;//회원ID
	public String couponNm		 ;//쿠폰이름
	public String usable		 ;//사용여부
	public String useDt			 ;//사용기간
	public int    discount		 ;//할인
	public String regDt			 ;//등록일
	
	
	public SJH_CouponVO() {}


	public SJH_CouponVO(String couponCode, String userId, String couponNm, String usable, String useDt, int discount,
			String regDt) {
		super();
		this.couponCode = couponCode;
		this.userId = userId;
		this.couponNm = couponNm;
		this.usable = usable;
		this.useDt = useDt;
		this.discount = discount;
		this.regDt = regDt;
	}


	public String getCouponCode() {
		return couponCode;
	}


	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getCouponNm() {
		return couponNm;
	}


	public void setCouponNm(String couponNm) {
		this.couponNm = couponNm;
	}


	public String getUsable() {
		return usable;
	}


	public void setUsable(String usable) {
		this.usable = usable;
	}


	public String getUseDt() {
		return useDt;
	}


	public void setUseDt(String useDt) {
		this.useDt = useDt;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public String getRegDt() {
		return regDt;
	}


	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}


	@Override
	public String toString() {
		return "SJH_CouponVO [couponCode=" + couponCode + ", userId=" + userId + ", couponNm=" + couponNm + ", usable="
				+ usable + ", useDt=" + useDt + ", discount=" + discount + ", regDt=" + regDt + "]";
	}


	
	
	
	
	
}
