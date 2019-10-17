package kr.co.sunnyside.cmn;

import java.util.List;

public interface WorkDiv {
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
	
	/**엑셀다운 */
	public List<?> do_excelDown(DTO dto);


}
