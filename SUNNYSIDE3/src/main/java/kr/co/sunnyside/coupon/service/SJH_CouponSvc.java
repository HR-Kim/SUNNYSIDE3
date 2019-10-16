package kr.co.sunnyside.coupon.service;

import kr.co.sunnyside.cmn.DTO;

public interface SJH_CouponSvc {

	/** 쿠폰발급 */
	public int do_save(DTO dto);
	
	/** 쿠폰발급 */
	public int do_delete(DTO dto);
	
	
}
