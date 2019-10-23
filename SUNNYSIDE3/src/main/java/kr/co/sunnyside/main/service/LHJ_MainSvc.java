package kr.co.sunnyside.main.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LHJ_MainSvc {

	/**공지조회*/
	public List<?> do_notice_retrieve();
	
	/**목록조회*/
	public List<?> do_banner_retrieve();
	
	/**베너이미지 저장*/
	public int do_image_save(DTO dto);
	
	/**베너이미지 삭제*/
	public int do_image_delete(DTO dto);
	
	
}
