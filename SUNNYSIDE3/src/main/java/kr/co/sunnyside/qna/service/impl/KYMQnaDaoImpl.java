package kr.co.sunnyside.qna.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.notice.service.KYMBranchVO;
import kr.co.sunnyside.qna.service.KYMQnaVO;

@Repository
public class KYMQnaDaoImpl implements WorkDiv {
	
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.qna";

	@Override
	public int do_update(DTO dto) {
		String statement = NAMESPACE+".do_update";
		KYMQnaVO kymQnaVO = (KYMQnaVO) dto;
		LOG.debug("=========================");
		LOG.debug("1. param:"+kymQnaVO);
		LOG.debug("=========================");
		
		LOG.debug("=========================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=========================");	
		
		int flag = this.sqlSessionTemplate.update(statement, kymQnaVO);
		LOG.debug("=========================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=========================");			
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = NAMESPACE+".do_delete";
		KYMQnaVO kymQnaVO = (KYMQnaVO) dto;
		LOG.debug("=========================");
		LOG.debug("1. param:"+kymQnaVO);
		LOG.debug("=========================");
		
		LOG.debug("=========================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=========================");	
		
		int flag = this.sqlSessionTemplate.delete(statement, kymQnaVO);
		LOG.debug("=========================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=========================");			
		return flag;
	}

	@Override
	public int do_save(DTO dto) {
		String statement = this.NAMESPACE+".do_save";
		KYMQnaVO kymQnaVO = (KYMQnaVO) dto;
		LOG.debug("=========================");
		LOG.debug("1. param:"+kymQnaVO);
		LOG.debug("=========================");
		LOG.debug("=========================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=========================");			
		int flag = this.sqlSessionTemplate.insert(statement, kymQnaVO);
		LOG.debug("=========================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=========================");
		
		return flag;
		
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = this.NAMESPACE+".do_selectOne";
		KYMQnaVO kymQnaVO = (KYMQnaVO) dto;		
		LOG.debug("=========================");
		LOG.debug("1. param:"+kymQnaVO);
		LOG.debug("2. statement:"+statement);
		KYMQnaVO outVO =this.sqlSessionTemplate.selectOne(statement, kymQnaVO);
		LOG.debug("3. outVO:"+outVO);
		LOG.debug("=========================");
		return outVO;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		String statement = this.NAMESPACE+".do_retrieve";
		SearchVO search = (SearchVO) dto;	
		LOG.debug("=========================");
		LOG.debug("1. param:"+search);
		LOG.debug("2. statement:"+statement);
		List<KYMQnaVO> list =this.sqlSessionTemplate.selectList(statement, search);
		LOG.debug("3. list:"+list);
		LOG.debug("=========================");
		return list;	
	}
	
	public List<?> do_boardIdList(DTO dto){
		String statement = this.NAMESPACE+".do_boardIdList";
		SearchVO search = (SearchVO) dto;		
		LOG.debug("=========================");
		LOG.debug("1. param:"+search);
		LOG.debug("2. statement:"+statement);
		List<KYMQnaVO> list =this.sqlSessionTemplate.selectList(statement, search);
		LOG.debug("3. list:"+list);
		LOG.debug("=========================");
		return list;		
	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//검색구분
	public List<?> do_retrieveTwo(DTO dto){
		String statement = this.NAMESPACE+".do_retrieveTwo";
		CodeVO search = (CodeVO) dto;		
		LOG.debug("=========================");
		LOG.debug("1. param:"+search);
		LOG.debug("2. statement:"+statement);
		List<CodeVO> listQnaSearch =this.sqlSessionTemplate.selectList(statement, search);
		LOG.debug("3. list:"+listQnaSearch);
		LOG.debug("=========================");
		
		return listQnaSearch;
		
	}
	
	//관리자 수정
	public int do_updateTwo(DTO dto) {
		String statement = NAMESPACE+".do_updateTwo";
		KYMQnaVO kymQnaVO = (KYMQnaVO) dto;
		LOG.debug("=========================");
		LOG.debug("1. param:"+kymQnaVO);
		LOG.debug("=========================");
		
		LOG.debug("=========================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=========================");	
		
		int flag = this.sqlSessionTemplate.update(statement, kymQnaVO);
		LOG.debug("=========================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=========================");			
		return flag;
	}
	
}
