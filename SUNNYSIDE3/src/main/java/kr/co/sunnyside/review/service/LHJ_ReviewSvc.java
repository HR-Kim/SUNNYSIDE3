package kr.co.sunnyside.review.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LHJ_ReviewSvc {
	
	/**리뷰 수정*/
	public int do_update(DTO dto);
	
	/**리뷰 삭제*/
	public int do_delete(DTO dto);
	
	/**리뷰 저장*/
	public int do_save(DTO dto);
	
	/**단건조회*/
	public DTO do_selectOne(DTO dto);
	
	/**목록조회*/
	public List<?> do_retrieve(DTO dto);
	
}
