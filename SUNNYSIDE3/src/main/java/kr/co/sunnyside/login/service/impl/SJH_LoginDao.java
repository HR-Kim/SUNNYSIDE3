package kr.co.sunnyside.login.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.login.service.SJH_LoginVO;

@Repository
public class SJH_LoginDao implements WorkDiv {
	
	Logger LOG = LoggerFactory.getLogger(SJH_LoginDao.class);
	
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.login";
	
	
	
	/**
	 * 아이디체크
	 * return > 1 : 성공
	 * @param dto
	 * @return
	 */
	public int id_check(DTO dto) {
		String statement = this.NAMESPACE+".id_check";
		SJH_LoginVO user = (SJH_LoginVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+user);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.selectOne(statement, user);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");	
		
		return flag;
	}
	
	
	/**
	 * 비밀번호 체크
	 * return > 1 : 성공
	 * @param dto
	 * @return
	 */
	public int passwd_check(DTO dto) {
		String statement = this.NAMESPACE+".passwd_check";
		SJH_LoginVO user = (SJH_LoginVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+user);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.selectOne(statement, user);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");	
		
		return flag;
	}
	
	
	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> get_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
