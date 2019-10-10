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
public class LHJ_BoxofficeDaoImpl implements WorkDiv{
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	   
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	      
	private final String NAMESPACE = "kr.co.sunnyside.boxoffice";

	/**박스오피스 상태값 0으로 초기화(OFF)*/	
	public int do_boxofficeOff_update() {
		String statement = NAMESPACE + ".do_boxofficeOff_update";
		
		LOG.debug("=============================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=============================");
		
		int flag = this.sqlSessionTemplate.update(statement);		
		LOG.debug("=============================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=============================");
		return flag;
	}
	
	/**박스오피스 상태값 0으로 초기화(On)*/	
	public int do_boxofficeOn_update(DTO dto) {
		String statement = NAMESPACE + ".do_boxofficeOn_update";
		LHJ_MovieVO inVO = (LHJ_MovieVO) dto;
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
	
	/**박스오피스 삭제 */	
	public int do_delete() {
		String statement = NAMESPACE + ".do_delete";
		
		LOG.debug("=============================");
		LOG.debug("2. statement:"+statement);
		LOG.debug("=============================");
		
		int flag = this.sqlSessionTemplate.delete(statement);		
		LOG.debug("=============================");
		LOG.debug("3. flag:"+flag);
		LOG.debug("=============================");
		
		return flag;
	}
	
	/**박스오피스 단건조회*/
	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = NAMESPACE + ".do_selectOne";
		LHJ_MovieVO inVO = (LHJ_MovieVO) dto;
		LOG.debug("=============================");
		LOG.debug("1. param:"+inVO);
		LOG.debug("2. statement:"+statement);
		LHJ_MovieVO outVO = this.sqlSessionTemplate.selectOne(statement, inVO);		
		LOG.debug("3. outVO:"+outVO);
		LOG.debug("=============================");
		
		return outVO;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	/**박스오피스 전체조회*/
	public List<?> do_retrieve() {
		String statement = NAMESPACE + ".do_retrieve";
		LOG.debug("=============================");
		LOG.debug("2. statement:"+statement);
		List<LHJ_MovieVO> list = this.sqlSessionTemplate.selectList(statement);		
		LOG.debug("3. list:"+list);
		LOG.debug("=============================");
		
		return list;
	}	

	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}


	/**영화 데이터베이스 추가(kobis)*/	
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
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	} 

}
