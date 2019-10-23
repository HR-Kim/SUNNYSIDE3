package kr.co.sunnyside.phototicket.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sunnyside.cmn.DTO;



/**
 * 사용자관리 Interface
 * @author sist
 *
 */
public interface SEJ_PhotoTicketSvc  {
	/**목록조회 */
	public List<?> do_retrieve(DTO dto);
	/**저장 */
	public int do_insert(DTO dto);

	public DTO do_selectOne(DTO dto);
	
	public List<?> do_retrieve_user(DTO dto);
}
