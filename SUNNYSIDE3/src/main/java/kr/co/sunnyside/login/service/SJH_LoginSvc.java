package kr.co.sunnyside.login.service;

import kr.co.sunnyside.cmn.DTO;

/**
 * 로그인 인터페이스
 * @author sist
 *
 */
public interface SJH_LoginSvc {
	
	/** 로그인 */
	public DTO do_login(DTO dto);
	
	/** 아이디 중복체크*/
	public DTO id_check(DTO dto);
	
	/** 회원가입 */
	public int do_save(DTO dto);
	
	/** 아이디 찾기 */
	public DTO id_find(DTO dto);
	
	/** 비밀번호 찾기 */
	public int pw_find(DTO dto) ;
	
	/** 단건조회 */
	public DTO do_selectOne(DTO dto);
	
	
}
