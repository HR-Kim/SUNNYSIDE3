package kr.co.sunnyside.userpage.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface KDY_UserpageSvc {


	public List<?> do_retrieve(DTO dto);
	
	public int do_delete(DTO dto);
	
	public List<?> do_coupon_retrieve(DTO dto);

	public List<?> do_membership(DTO dto);
	
	public List<?> do_qnaList(DTO dto);


}
