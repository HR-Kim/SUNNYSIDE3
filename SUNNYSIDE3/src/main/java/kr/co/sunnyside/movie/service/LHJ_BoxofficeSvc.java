package kr.co.sunnyside.movie.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LHJ_BoxofficeSvc {
	/**박스오피스 순위 삽입(update)*/	
	public int do_rank_update(DTO dto);
	
	/**박스오피스 삭제 */	
	public int do_delete();
	
	/**박스오피스 단건조회*/
	public DTO do_selectOne(DTO dto);
	
	/**박스오피스 전체조회*/
	public List<?> do_retrieve();
	
	/**영화 데이터베이스 추가(kobis)*/
	public int do_save(DTO dto);
	
	
	/**안쓰는 것들*/
	public int do_delete(DTO dto);
	
	public List<?> do_retrieve(DTO dto);
	
	public List<?> do_excelDown(DTO dto);
	
	public int do_update(DTO dto);
}
