package kr.co.sunnyside.login.service;

import kr.co.sunnyside.cmn.DTO;

/**
 * 로그인 인터페이스
 * @author sist
 *
 */
public interface SJH_LoginSvc {
	
	/** 아이디/비번 체크 */
	public DTO idPassCheck(DTO dto);
	
	/** 회원가입 */
	public int do_save(DTO dto);
	
	/** 아이디 찾기 */
	public DTO id_find(DTO dto);
	
	/** 비밀번호 찾기 */
	public DTO pw_find(DTO dto) ;
	
	/** 단건조회 */
	public DTO get_selectOne(DTO dto);
	
	
}
