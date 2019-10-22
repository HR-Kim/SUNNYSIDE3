package kr.co.sunnyside.main.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LHJ_MainSvc {
	
	/**파일목록조회*/
	public List<?> do_banner_List(DTO dto);
	
	/**목록조회*/
	public List<?> do_banner_retrieve();
	
	/**목록조회*/
	public int do_image_save(DTO dto);
	
	
}
