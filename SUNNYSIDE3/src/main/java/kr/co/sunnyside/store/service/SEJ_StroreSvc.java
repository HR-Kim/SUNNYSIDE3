package kr.co.sunnyside.store.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;

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
	

	

}
