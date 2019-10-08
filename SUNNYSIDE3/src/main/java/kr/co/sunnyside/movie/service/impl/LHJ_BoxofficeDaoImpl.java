package kr.co.sunnyside.movie.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.movie.service.LHJ_BoxofficeVO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;


@Repository
public class LHJ_BoxofficeDaoImpl implements WorkDiv{
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	   
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	      
	private final String NAMESPACE = "kr.co.sunnyside.boxoffice";

	
//	public List<?> do_boxoffice_retrieve() {
//		String statement = NAMESPACE + ".do_boxoffice_retrieve";
//		LOG.debug("=============================");
//		LOG.debug("2. statement:"+statement);
//		List<LHJ_BoxofficeVO> list = this.sqlSessionTemplate.selectList(statement);		
//		LOG.debug("3. list:"+list);
//		LOG.debug("=============================");
//		
//		return list;
//	}
	
	/**
	 * 박스오피스 단건조회
	 */
//	public DTO do_boxoffice_selectOne(DTO dto) {
//		String statement = NAMESPACE + ".do_boxoffice_selectOne";
//		LHJ_BoxofficeVO inVO = (LHJ_BoxofficeVO) dto;
//		LOG.debug("=============================");
//		LOG.debug("1. param:"+inVO);
//		LOG.debug("2. statement:"+statement);
//		LHJ_BoxofficeVO outVO = this.sqlSessionTemplate.selectOne(statement, inVO);		
//		LOG.debug("3. outVO:"+outVO);
//		LOG.debug("=============================");
//		
//		return outVO;		
//	}
	
	/**
	 * 박스오피스 삭제
	 */	
//	public int do_boxoffice_delete() {
//		String statement = NAMESPACE + ".do_boxoffice_delete";
//		
//		LOG.debug("=============================");
//		LOG.debug("2. statement:"+statement);
//		LOG.debug("=============================");
//		
//		int flag = this.sqlSessionTemplate.delete(statement);		
//		LOG.debug("=============================");
//		LOG.debug("3. flag:"+flag);
//		LOG.debug("=============================");
//		
//		return flag;
//	}

	/**
	 * 박스오피스 순위 삽입(update)
	 */	
//	public int do_rank_update(DTO dto) {
//		String statement = NAMESPACE + ".do_rank_update";
//		LHJ_BoxofficeVO inVO = (LHJ_BoxofficeVO) dto;
//		LOG.debug("=============================");
//		LOG.debug("1. param:"+inVO);
//		LOG.debug("=============================");
//		
//		LOG.debug("=============================");
//		LOG.debug("2. statement:"+statement);
//		LOG.debug("=============================");
//		
//		int flag = this.sqlSessionTemplate.update(statement, inVO);		
//		LOG.debug("=============================");
//		LOG.debug("3. flag:"+flag);
//		LOG.debug("=============================");
//		return flag;
//	}
//	
//	/**
//	 * 영화 데이터베이스 추가(kobis)
//	 */	
//	public int do_boxoffice_insert(DTO dto) {
//		String statement = NAMESPACE + ".do_boxoffice_insert";
//		LHJ_BoxofficeVO inVO = (LHJ_BoxofficeVO) dto;
//		LOG.debug("=============================");
//		LOG.debug("1. param:"+inVO);
//		LOG.debug("=============================");
//		
//		LOG.debug("=============================");
//		LOG.debug("2. statement:"+statement);
//		LOG.debug("=============================");
//		
//		int flag = this.sqlSessionTemplate.insert(statement, inVO);		
//		LOG.debug("=============================");
//		LOG.debug("3. flag:"+flag);
//		LOG.debug("=============================");
//		return flag;
//	}
	
//	/**
//	 * 영화 데이터베이스 추가(kmdb)
//	 */	
//	public int do_movie_insert(DTO dto) {
//		String statement = NAMESPACE + ".do_movie_insert";
//		LHJ_MovieVO inVO = (LHJ_MovieVO) dto;
//		LOG.debug("=============================");
//		LOG.debug("1. param:"+inVO);
//		LOG.debug("=============================");
//		
//		LOG.debug("=============================");
//		LOG.debug("2. statement:"+statement);
//		LOG.debug("=============================");
//		
//		int flag = this.sqlSessionTemplate.insert(statement, inVO);		
//		LOG.debug("=============================");
//		LOG.debug("3. flag:"+flag);
//		LOG.debug("=============================");
//		return flag;
//	}

	/**
	 * 박스오피스 순위 삽입(update)
	 */	
	public int do_rank_update(DTO dto) {
		String statement = NAMESPACE + ".do_rank_update";
		LHJ_BoxofficeVO inVO = (LHJ_BoxofficeVO) dto;
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

	@Override
	public int do_delete(DTO dto) {
		return 0;
	}
	
	/**
	 * 박스오피스 삭제
	 */	
	public int do_delete() {
		String statement = NAMESPACE + ".do_boxoffice_delete";
		
		LOG.debug("=============================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=============================");
		
		int flag = this.sqlSessionTemplate.delete(statement);		
		LOG.debug("=============================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=============================");
		
		return flag;
	}
	
	/**
	 * 박스오피스 단건조회
	 */
	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = NAMESPACE + ".do_boxoffice_selectOne";
		LHJ_BoxofficeVO inVO = (LHJ_BoxofficeVO) dto;
		LOG.debug("=============================");
		LOG.debug("1. param:"+inVO);
		LOG.debug("2. statement:"+statement);
		LHJ_BoxofficeVO outVO = this.sqlSessionTemplate.selectOne(statement, inVO);		
		LOG.debug("3. outVO:"+outVO);
		LOG.debug("=============================");
		
		return outVO;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	/**
	 * 박스오피스 전체조회
	 */
	public List<?> get_retrieve() {
		String statement = NAMESPACE + ".do_boxoffice_retrieve";
		LOG.debug("=============================");
		LOG.debug("2. statement:"+statement);
		List<LHJ_BoxofficeVO> list = this.sqlSessionTemplate.selectList(statement);		
		LOG.debug("3. list:"+list);
		LOG.debug("=============================");
		
		return list;
	}	

	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 영화 데이터베이스 추가(kobis)
	 */	
	@Override
	public int do_save(DTO dto) {
		String statement = NAMESPACE + ".do_save";
		LHJ_BoxofficeVO inVO = (LHJ_BoxofficeVO) dto;
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
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	} 

}
