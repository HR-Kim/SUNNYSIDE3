package kr.co.sunnyside.notice.service.impl;

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
import kr.co.sunnyside.notice.service.KYMNoticeVO;

@Repository
public class KYMNoticeDaoImpl implements WorkDiv {
	
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.notice";

	@Override
	public int do_update(DTO dto) {
		String statement = NAMESPACE+".do_update";
		KYMNoticeVO kymNoticeVO = (KYMNoticeVO) dto;
		LOG.debug("=========================");
		LOG.debug("1. param:"+kymNoticeVO);
		LOG.debug("=========================");
		
		LOG.debug("=========================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=========================");	
		
		int flag = this.sqlSessionTemplate.update(statement, kymNoticeVO);
		LOG.debug("=========================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=========================");			
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = NAMESPACE+".do_delete";
		KYMNoticeVO kymNoticeVO = (KYMNoticeVO) dto;
		LOG.debug("=========================");
		LOG.debug("1. param:"+kymNoticeVO);
		LOG.debug("=========================");
		
		LOG.debug("=========================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=========================");	
		
		int flag = this.sqlSessionTemplate.delete(statement, kymNoticeVO);
		LOG.debug("=========================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=========================");			
		return flag;
	}

	@Override
	public int do_save(DTO dto) {
		String statement = this.NAMESPACE+".do_save";
		KYMNoticeVO kymNoticeVO = (KYMNoticeVO) dto;
		LOG.debug("=========================");
		LOG.debug("1. param:"+kymNoticeVO);
		LOG.debug("=========================");
		LOG.debug("=========================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=========================");			
		int flag = this.sqlSessionTemplate.insert(statement, kymNoticeVO);
		LOG.debug("=========================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=========================");
		
		return flag;
		
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = this.NAMESPACE+".do_selectOne";
		KYMNoticeVO kymNoticeVO = (KYMNoticeVO) dto;		
		LOG.debug("=========================");
		LOG.debug("1. param:"+kymNoticeVO);
		LOG.debug("2. statement:"+statement);
		KYMNoticeVO outVO =this.sqlSessionTemplate.selectOne(statement, kymNoticeVO);
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
		List<KYMNoticeVO> list =this.sqlSessionTemplate.selectList(statement, search);
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
		List<KYMNoticeVO> list =this.sqlSessionTemplate.selectList(statement, search);
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
		List<CodeVO> listNoticeSearch =this.sqlSessionTemplate.selectList(statement, search);
		LOG.debug("3. list:"+listNoticeSearch);
		LOG.debug("=========================");
		
		return listNoticeSearch;
		
	}
	
	//지역검색 구분
	public List<?> do_retrieveThree(DTO dto){
		String statement = this.NAMESPACE+".do_retrieveThree";
		LOG.debug("=========================");
		LOG.debug("2. statement:"+statement);
		List<KYMBranchVO> Branchlist =this.sqlSessionTemplate.selectList(statement);
		LOG.debug("3. Branchlist:"+Branchlist);
		LOG.debug("=========================");
		
		return Branchlist;
		
	}
	
	//지역 조회
	public List<?> do_retrieveFour(DTO dto) {
		String statement = this.NAMESPACE+".do_retrieveFour";
		LOG.debug("=========================");
		LOG.debug("2. statement:"+statement);
		List<KYMBranchVO> list =this.sqlSessionTemplate.selectList(statement);
		LOG.debug("3. list:"+list);
		LOG.debug("=========================");
		return list;
	}

}
