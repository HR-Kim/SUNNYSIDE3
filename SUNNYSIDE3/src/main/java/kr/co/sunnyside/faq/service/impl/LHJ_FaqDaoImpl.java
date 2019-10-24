package kr.co.sunnyside.faq.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.faq.service.LHJ_FaqVO;


@Repository
public class LHJ_FaqDaoImpl implements WorkDiv{
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	   
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	      
	private final String NAMESPACE = "kr.co.sunnyside.faq";

	/**수정*/
	@Override
	public int do_update(DTO dto) {
		String statement = NAMESPACE + ".do_update";
		LHJ_FaqVO inVO = (LHJ_FaqVO) dto;
		LOG.debug("=============================");
		LOG.debug("1. param:"+inVO);
		LOG.debug("=============================");
		
		LOG.debug("=============================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=============================");
		
		int flag = this.sqlSessionTemplate.update(statement, inVO);		
		LOG.debug("=============================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=============================");
		
		return flag;
	}

	/**삭제*/
	@Override
	public int do_delete(DTO dto) {
		String statement = NAMESPACE + ".do_delete";
		LHJ_FaqVO inVO = (LHJ_FaqVO) dto;
		LOG.debug("=============================");
		LOG.debug("1. param:"+inVO);
		LOG.debug("=============================");
		
		LOG.debug("=============================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=============================");
		
		int flag = this.sqlSessionTemplate.delete(statement, inVO);		
		LOG.debug("=============================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=============================");
		
		return flag;
	}

	/**저장*/
	@Override
	public int do_save(DTO dto) {
		String statement = NAMESPACE + ".do_save";
		LHJ_FaqVO inVO = (LHJ_FaqVO) dto;
		LOG.debug("=============================");
		LOG.debug("1. param:"+inVO);
		LOG.debug("=============================");
		
		LOG.debug("=============================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=============================");
		
		int flag = this.sqlSessionTemplate.insert(statement, inVO);		
		LOG.debug("=============================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=============================");
		return flag;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/**전체조회*/
	
	public List<?> do_retrieve() {
		String statement = NAMESPACE + ".do_retrieve";
		LOG.debug("=============================");
		LOG.debug("1. statement:"+statement);
		List<LHJ_FaqVO> list = this.sqlSessionTemplate.selectList(statement);		
		LOG.debug("2. list:"+list);
		LOG.debug("=============================");
		
		return list;
	}
	
	@Override
	public List<?> do_retrieve(DTO dto) {
		return null;
	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
