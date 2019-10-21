package kr.co.sunnyside.movie.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LHJ_BoxofficeSvc {
	
	/**매일 오전 9시마다 박스오피스 데이터 delete 한 뒤 insert*/
	public void do_deleteAndSave();
	
	/**박스오피스 상태값 0으로 초기화(OFF)*/	
	public int do_boxofficeOff_update(DTO dto);
	
	/**박스오피스 상태값 0으로 초기화(ON)*/	
	public int do_boxofficeOn_update(DTO dto);
	
	/**박스오피스 삭제 */	
	public int do_delete();
	
	/**박스오피스 추가*/
	public int do_save(DTO dto);
	
	/**박스오피스 단건조회*/
	public DTO do_selectOne(DTO dto);
	
	/**박스오피스 전체조회*/
	public List<?> do_retrieve();
	
	
	
	
	/**안쓰는 것들*/
	public int do_delete(DTO dto);
	
	public List<?> do_retrieve(DTO dto);
	
	public List<?> do_excelDown(DTO dto);
	
	public int do_update(DTO dto);
}
