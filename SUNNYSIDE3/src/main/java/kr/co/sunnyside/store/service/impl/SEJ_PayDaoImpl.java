package kr.co.sunnyside.store.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.store.service.SEJ_PayVO;

public class SEJ_PayDaoImpl implements WorkDiv {
	
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.pay"; 
	
	/**결제내역 추가*/
	@Override
	public int do_save(DTO dto) {
		String statement = NAMESPACE+".do_save";
		SEJ_PayVO store = (SEJ_PayVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		int flag = this.sqlSessionTemplate.insert(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+flag);
		LOG.debug("===================================");
		
		return flag;
	}	
	
	/**결제내역 삭제*/
	@Override
	public int do_delete(DTO dto) {
		String statement = NAMESPACE+".do_delete";
		SEJ_PayVO store = (SEJ_PayVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		int flag = this.sqlSessionTemplate.delete(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+flag);
		LOG.debug("===================================");
		
		return flag;
	}
	
	/**결제내역 조회*/
	@Override
	public List<?> do_retrieve(DTO dto) {
		String statement = NAMESPACE+".do_retrieve";
		SEJ_PayVO store = (SEJ_PayVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		List<SEJ_PayVO> list= this.sqlSessionTemplate.selectList(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+list);
		LOG.debug("===================================");
		
		return list;
	}
	
	/**사용안함*/
	@Override
	public int do_update(DTO dto) {
		return 0;
	}
	@Override
	public DTO do_selectOne(DTO dto) {
		return null;
	}
	@Override
	public List<?> do_excelDown(DTO dto) {
		return null;
	}
	
}
