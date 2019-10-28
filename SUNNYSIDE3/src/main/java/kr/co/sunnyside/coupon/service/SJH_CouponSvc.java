package kr.co.sunnyside.coupon.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface SJH_CouponSvc {

	/** 쿠폰발급 */
	public int do_save(DTO dto);
	
	/** 쿠폰삭제 */
	public int do_delete(DTO dto);
	
	/** 쿠폰선택 */
	public DTO do_selectOne(DTO dto);
	
	/** 쿠폰 여러장 선택 */
	public List<?> do_retrieve(DTO dto);
	
	/** 쿠폰 업데이트 */
	public int do_update(DTO dto);
	
}
