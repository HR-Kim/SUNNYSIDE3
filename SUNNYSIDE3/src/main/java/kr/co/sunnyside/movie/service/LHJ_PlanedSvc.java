package kr.co.sunnyside.movie.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LHJ_PlanedSvc {
	/** 개봉예정으로 상태 변경  */
	public int do_update_planedUp(DTO dto);
	
	/** 기본상태로 상태 변경 */
	public int do_update_planedDown(DTO dto);
	
	/** 최신개봉(상영중)으로 상태 변경 */	
	public int do_update_planedToScreen(DTO dto);
	
	/**단건조회*/
	public DTO do_selectOne(DTO dto);
	
	/**목록조회*/
	public List<?> do_retrieve(DTO dto);
	
}
