package kr.co.sunnyside.coupon.service;

public class SJH_CouponVO {

	
	public String couponCod	 ;//쿠폰코드
	public String userId	 ;//회원ID
	public String couponNm	 ;//쿠폰이름
	public String usable	 ;//사용여부
	public String useDt		 ;//사용기간
	public int discount		 ;//할인
	
	
	public SJH_CouponVO() {}

	public SJH_CouponVO(String couponCod, String userId, String couponNm, String usable, String useDt, int discount) {
		super();
		this.couponCod = couponCod;
		this.userId = userId;
		this.couponNm = couponNm;
		this.usable = usable;
		this.useDt = useDt;
		this.discount = discount;
	}


	public String getCouponCod() {
		return couponCod;
	}


	public void setCouponCod(String couponCod) {
		this.couponCod = couponCod;
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


	@Override
	public String toString() {
		return "SJH_CouponVO [couponCod=" + couponCod + ", userId=" + userId + ", couponNm=" + couponNm + ", usable="
				+ usable + ", useDt=" + useDt + ", discount=" + discount + "]";
	}
	
	
	
	
}
