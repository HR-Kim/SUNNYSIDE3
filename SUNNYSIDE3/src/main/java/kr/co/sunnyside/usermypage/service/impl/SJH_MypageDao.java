package kr.co.sunnyside.usermypage.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.login.service.impl.SJH_LoginDao;
import kr.co.sunnyside.usermypage.service.SJH_MypageVO;

@Repository
public class SJH_MypageDao implements WorkDiv {

	
	Logger LOG = LoggerFactory.getLogger(SJH_LoginDao.class);
	
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.usermypage";
	
	
	
	/**
	 * 회원 전체조회
	 */
	public List<?> do_getAll() {
		String statement = this.NAMESPACE+".do_getAll";//kr.co.ehr.user.get_retrieve
		
		LOG.debug("========================");
		LOG.debug("=01.no param=");
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		List<SJH_MypageVO> list = this.sqlSessionTemplate.selectList(statement);
	
		LOG.debug("========================");
		LOG.debug("03.list="+list);
		LOG.debug("========================");	
		
		return list;
	}
	
	
	/**
	 * 회원 자동 등업
	 * @param dto
	 * @return
	 */
	public int do_levelUpdate(DTO dto) {
		String statement = this.NAMESPACE+".do_levelUpdate";
		SJH_MypageVO user = (SJH_MypageVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+user);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.update(statement, user);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");	
		
		return flag;
	}
	
	
	@Override
	public int do_update(DTO dto) {
		String statement = this.NAMESPACE+".do_update";
		SJH_MypageVO user = (SJH_MypageVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+user);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.update(statement, user);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");	
		
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = this.NAMESPACE+".do_delete";
		SJH_MypageVO user = (SJH_MypageVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+user);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.delete(statement, user);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");	
		
		return flag;
	}
	
	
	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = this.NAMESPACE+".do_selectOne";
		SJH_MypageVO inVO = (SJH_MypageVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+inVO);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		SJH_MypageVO outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
	
		LOG.debug("========================");
		LOG.debug("03.outVO="+outVO);
		LOG.debug("========================");	
		
		return outVO;
	}
	
	
	
	
	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<?> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
