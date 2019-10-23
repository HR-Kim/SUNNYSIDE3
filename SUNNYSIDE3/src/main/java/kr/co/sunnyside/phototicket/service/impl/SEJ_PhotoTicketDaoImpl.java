package kr.co.sunnyside.phototicket.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.phototicket.service.SEJ_MovieHistoryVO;
import kr.co.sunnyside.phototicket.service.SEJ_PhotoTicketVO;


@Repository
public class SEJ_PhotoTicketDaoImpl {
	static Logger LOG=LoggerFactory.getLogger(SEJ_PhotoTicketDaoImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.phototicket";
	
	public SEJ_PhotoTicketDaoImpl() {}
	
	public  List<?> do_retrieve(DTO dto) {
		String statement = this.NAMESPACE+".do_retrieve";//kr.co.ehr.user.get_retrieve
		SEJ_PhotoTicketVO MHVO = (SEJ_PhotoTicketVO) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+MHVO);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		List<SEJ_PhotoTicketVO> list = this.sqlSessionTemplate.selectList(statement, MHVO);
		
		LOG.debug("========================");
		LOG.debug("03.list="+list);
		LOG.debug("========================");		
		return list;
	}
	
	public  int do_save(DTO dto) {
		String statement = this.NAMESPACE+".do_save";//kr.co.ehr.user.get_retrieve
		SEJ_PhotoTicketVO MHVO = (SEJ_PhotoTicketVO) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+MHVO);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.insert(statement, MHVO);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");		
		return flag;
	}

	public DTO do_selectOne(DTO dto) {
		String statement = this.NAMESPACE+".do_selectOne";//kr.co.ehr.user.get_retrieve
		SEJ_PhotoTicketVO MHVO = (SEJ_PhotoTicketVO) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+MHVO);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		SEJ_PhotoTicketVO outVO = this.sqlSessionTemplate.selectOne(statement, MHVO);
		
		LOG.debug("========================");
		LOG.debug("03.outVO="+outVO);
		LOG.debug("========================");		
		return outVO;
	}
	
	
	
	public  List<?> do_retrieve_user(DTO dto) {
		String statement = this.NAMESPACE+".do_retrieve_user";//kr.co.ehr.user.get_retrieve
		SEJ_PhotoTicketVO MHVO = (SEJ_PhotoTicketVO) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+MHVO);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		List<SEJ_PhotoTicketVO> list = this.sqlSessionTemplate.selectList(statement, MHVO);
		
		LOG.debug("========================");
		LOG.debug("03.list="+list);
		LOG.debug("========================");		
		return list;
	}

}
