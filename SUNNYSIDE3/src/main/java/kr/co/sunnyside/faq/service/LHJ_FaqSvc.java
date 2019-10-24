package kr.co.sunnyside.faq.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LHJ_FaqSvc {
	/**수정*/
	public int do_update(DTO dto);
	
	/**삭제*/
	public int do_delete(DTO dto);
	
	/**저장*/
	public int do_save(DTO dto);
	
	/**전체조회*/
	public List<?> do_retrieve();
}
