package kr.co.sunnyside.movie.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LHJ_ScreeningSvc {
	/** 비 상영상태로 상태 변경 */
	public int do_update_screenDown(DTO dto);
	
	/** 최신개봉으로 상태 변경(상영중으로 상태 변경) */
	public int do_update_screenUp(DTO dto);
	
	/**단건조회*/
	public DTO do_selectOne(DTO dto);
	
	/**목록조회*/
	public List<?> do_retrieve(DTO dto);
}
