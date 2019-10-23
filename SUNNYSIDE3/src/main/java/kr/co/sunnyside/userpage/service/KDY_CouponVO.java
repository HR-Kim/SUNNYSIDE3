package kr.co.sunnyside.userpage.service;

import kr.co.sunnyside.cmn.DTO;

public class KDY_CouponVO  extends DTO{
	
	private String	coupon_code ;
	private String	user_id     ;
	private String	coupon_nm   ;
	private String	usable      ;
	private String	use_dt      ;
	private int		discount        ;
	private String	reg_dt		;
	
	KDY_CouponVO(){}

	public String getCoupon_code() {
		return coupon_code;
	}

	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCoupon_nm() {
		return coupon_nm;
	}

	public void setCoupon_nm(String coupon_nm) {
		this.coupon_nm = coupon_nm;
	}

	public String getUsable() {
		return usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

	public String getUse_dt() {
		return use_dt;
	}

	public void setUse_dt(String use_dt) {
		this.use_dt = use_dt;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	@Override
	public String toString() {
		return "KDY_CouponVO [coupon_code=" + coupon_code + ", user_id=" + user_id + ", coupon_nm=" + coupon_nm
				+ ", usable=" + usable + ", use_dt=" + use_dt + ", discount=" + discount + ", reg_dt=" + reg_dt + "]";
	}

	public KDY_CouponVO(String coupon_code, String user_id, String coupon_nm, String usable, String use_dt,
			int discount, String reg_dt) {
		super();
		this.coupon_code = coupon_code;
		this.user_id = user_id;
		this.coupon_nm = coupon_nm;
		this.usable = usable;
		this.use_dt = use_dt;
		this.discount = discount;
		this.reg_dt = reg_dt;
	}

	
}
