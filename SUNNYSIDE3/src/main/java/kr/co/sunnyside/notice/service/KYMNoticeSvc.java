package kr.co.sunnyside.notice.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface KYMNoticeSvc {
	
	/**수정 */
	public int do_update(DTO dto);

	/**삭제 */
	public int do_delete(DTO dto);

	/**저장 */
	public int do_save(DTO dto);

	/**단건조회 */
	public DTO do_selectOne(DTO dto);
	
	/**목록조회 */
	public List<?> do_retrieve(DTO dto);
	
	/**목록조회Two */
	public List<?> do_retrieveTwo(DTO dto);
	
	/**목록조회three */
	public List<?> do_retrieveThree(DTO dto);
	
	/**목록조회Four */
	public List<?> do_retrieveFour(DTO dto);

}
