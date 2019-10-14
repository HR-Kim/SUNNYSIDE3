package kr.co.sunnyside.store.service;

import java.util.List;


import kr.co.sunnyside.cmn.DTO;

public interface SEJ_StroreSvc {
	
	/**저장*/
	public int do_save(DTO dto);
	/**삭제*/
	public int do_delete(DTO dto);
	/**수정*/
	public int do_update(DTO dto);
	/**단건조회*/
	public DTO do_selectOne(DTO dto);
	/**목록조회*/
	public List<?> do_retrieve(DTO dto);
	
	/**팝콘목록조회*/
	public List<?> do_retrieve_popcorn(DTO dto);
	
	/**음료목록조회*/
	public List<?> do_retrieve_drink(DTO dto);
	
	/**영화예매권목록조회*/
	public List<?> do_retrieve_movieticket(DTO dto);
	
	/**이미지 업로드*/
	public int do_imageUpload(DTO dto);
	
	
	

	

}
