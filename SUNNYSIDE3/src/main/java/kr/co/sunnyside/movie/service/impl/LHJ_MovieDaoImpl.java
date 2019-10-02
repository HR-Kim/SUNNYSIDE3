package kr.co.sunnyside.movie.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;



@Repository
public class LHJ_MovieDaoImpl implements WorkDiv{
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	   
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	      
	private final String NAMESPACE = "kr.co.sunnyside.movie";

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		return 0;
	}

	/**
	 * 영화 데이터베이스 추가
	 */
	@Override
	public int do_save(DTO dto) {
		String statement = NAMESPACE + ".do_save";
		LHJ_MovieVO inVO = (LHJ_MovieVO) dto;
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
