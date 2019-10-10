package kr.co.sunnyside.usermypage.service;

import java.sql.SQLException;

import kr.co.sunnyside.cmn.DTO;

public interface SJH_MypageSvc {
	
	/** 회워정보 수정 */
	public int do_update(DTO dto);
	
	/** 회워탈퇴 */
	public int do_delete(DTO dto);
	
	/** 회원선택 -- 수정파일로 데이터 이동*/
	public DTO do_selectOne(DTO dto);
	
	/** 회원 자동 등업 (트랜잭션) */
	public void tx_upgradeLevels() throws SQLException;
	
	
}
