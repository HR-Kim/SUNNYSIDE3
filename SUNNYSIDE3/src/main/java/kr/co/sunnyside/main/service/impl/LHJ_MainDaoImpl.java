package kr.co.sunnyside.main.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.main.service.LHJ_MainImageVO;
import kr.co.sunnyside.notice.service.KYMNoticeVO;

@Repository
public class LHJ_MainDaoImpl implements WorkDiv{
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	   
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	      
	private final String NAMESPACE = "kr.co.sunnyside.main";
	
	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**메인 이미지 삭제*/
	public int do_image_delete(DTO dto) {
		String statement = NAMESPACE + ".do_image_delete";
		LHJ_MainImageVO inVO = (LHJ_MainImageVO) dto;
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
	
	/**메인 이미지 저장*/
	public int do_image_save(DTO dto) {
		String statement = NAMESPACE + ".do_image_save";
		LHJ_MainImageVO inVO = (LHJ_MainImageVO) dto;
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
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> do_notice_retrieve() {
		String statement = NAMESPACE + ".do_notice_retrieve";
		LOG.debug("=============================");
		LOG.debug("1. statement:"+statement);
		List<KYMNoticeVO> list = this.sqlSessionTemplate.selectList(statement);		
		LOG.debug("2. list:"+list);
		LOG.debug("=============================");
		
		return list;
	}
	
	public List<?> do_banner_retrieve() {
		String statement = NAMESPACE + ".do_banner_retrieve";
		LOG.debug("=============================");
		LOG.debug("1. statement:"+statement);
		List<LHJ_MainImageVO> list = this.sqlSessionTemplate.selectList(statement);		
		LOG.debug("2. list:"+list);
		LOG.debug("=============================");
		
		return list;
	}
	
	@Override
	public List<?> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
