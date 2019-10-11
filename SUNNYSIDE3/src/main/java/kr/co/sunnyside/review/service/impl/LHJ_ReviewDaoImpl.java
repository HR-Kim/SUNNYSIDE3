package kr.co.sunnyside.review.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.review.service.LHJ_ReviewVO;

@Repository
public class LHJ_ReviewDaoImpl implements WorkDiv{
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	   
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	      
	private final String NAMESPACE = "kr.co.sunnyside.review";

	/**리뷰 수정*/
	@Override
	public int do_update(DTO dto) {
		String statement = NAMESPACE + ".do_update";
		LHJ_ReviewVO inVO = (LHJ_ReviewVO) dto;
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

	/**리뷰 삭제*/
	@Override
	public int do_delete(DTO dto) {		
		String statement = NAMESPACE + ".do_delete";
		LHJ_ReviewVO inVO = (LHJ_ReviewVO) dto;
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

	/**리뷰 저장*/
	@Override
	public int do_save(DTO dto) {
		String statement = NAMESPACE + ".do_save";
		LHJ_ReviewVO inVO = (LHJ_ReviewVO) dto;
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

	/**단건조회*/
	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = NAMESPACE + ".do_selectOne";
		LHJ_ReviewVO inVO = (LHJ_ReviewVO) dto;
		LOG.debug("=============================");
		LOG.debug("1. param:"+inVO);
		LOG.debug("2. statement:"+statement);
		LHJ_ReviewVO outVO = this.sqlSessionTemplate.selectOne(statement, inVO);		
		LOG.debug("3. outVO:"+outVO);
		LOG.debug("=============================");
		
		return outVO;
	}

	/**목록조회*/
	@Override
	public List<?> do_retrieve(DTO dto) {
		String statement = NAMESPACE + ".do_retrieve";
		SearchVO search = (SearchVO) dto;
		LOG.debug("=============================");
		LOG.debug("1. param:"+search);
		LOG.debug("2. statement:"+statement);
		List<LHJ_ReviewVO> list = this.sqlSessionTemplate.selectList(statement, search);		
		LOG.debug("3. list:"+list);
		LOG.debug("=============================");
		
		return list;
	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
}
