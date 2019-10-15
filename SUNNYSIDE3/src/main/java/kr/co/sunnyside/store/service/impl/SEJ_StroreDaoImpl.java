package kr.co.sunnyside.store.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.file.service.FileVO;
import kr.co.sunnyside.store.service.SEJ_StroreVO;


@Repository
public class SEJ_StroreDaoImpl implements WorkDiv {
	Logger LOG=LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.store"; 
	
	/**상품 등록*/
	@Override
	public int do_save(DTO dto) {
		String statement = NAMESPACE+".do_save";
		SEJ_StroreVO store = (SEJ_StroreVO) dto;
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
	
	/**상품 삭제*/
	@Override
	public int do_delete(DTO dto) {
		String statement = NAMESPACE+".do_delete";
		SEJ_StroreVO store = (SEJ_StroreVO) dto;
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
	
	/**상품 정보 수정*/
	@Override
	public int do_update(DTO dto) {
		String statement = NAMESPACE+".do_update";
		SEJ_StroreVO store = (SEJ_StroreVO) dto;
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

	/**상품 단건조회*/
	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = NAMESPACE+".do_selectOne";
		SEJ_StroreVO store = (SEJ_StroreVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("=2. statement="+statement);
		
		SEJ_StroreVO outVO = this.sqlSessionTemplate.selectOne(statement,store); //이 역할을 해주는 것이 mybatis
		LOG.debug("=3. outVO="+outVO);
		LOG.debug("===================================");
		return outVO;
	}

	/**상품 전체조회*/
	@Override
	public List<?> do_retrieve(DTO dto) {
		String statement = NAMESPACE+".do_retrieve";
		SearchVO search = (SearchVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+search);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		List<SEJ_StroreVO> list= this.sqlSessionTemplate.selectList(statement,search);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+list);
		LOG.debug("===================================");
		
		return list;

	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		
		return null;
	}
	/**팝콘 조회*/
	public List<?> do_retrieve_popcorn(DTO dto) {
		String statement = NAMESPACE+".do_retrieve_popcorn";
		SearchVO search = (SearchVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+search);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		List<SEJ_StroreVO> list= this.sqlSessionTemplate.selectList(statement,search);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+list);
		LOG.debug("===================================");
		
		return list;
	}
	
	/**음료 조회*/
	public List<?> do_retrieve_drink(DTO dto) {
		String statement = NAMESPACE+".do_retrieve_drink";
		SearchVO search = (SearchVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+search);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		List<SEJ_StroreVO> list= this.sqlSessionTemplate.selectList(statement,search);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+list);
		LOG.debug("===================================");
		
		return list;
	}
	
	/**영화예매권 조회*/
	public List<?> do_retrieve_movieticket(DTO dto) {
		String statement = NAMESPACE+".do_retrieve_movieticket";
		SearchVO search = (SearchVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+search);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		List<SEJ_StroreVO> list= this.sqlSessionTemplate.selectList(statement,search);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+list);
		LOG.debug("===================================");
		
		return list;
	}
		
}
