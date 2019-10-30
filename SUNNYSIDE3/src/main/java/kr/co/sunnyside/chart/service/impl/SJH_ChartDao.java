package kr.co.sunnyside.chart.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.login.service.impl.SJH_LoginDao;
import kr.co.sunnyside.reservation.service.LGS_TicketVO;
import kr.co.sunnyside.usermypage.service.SJH_MypageVO;


@Repository
public class SJH_ChartDao {

	Logger LOG = LoggerFactory.getLogger(SJH_LoginDao.class);
	
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.chart";
	
	
	/**
	 * 일년 간 예매내역 전체조회
	 */
	public int do_getAll(DTO dto) {
		String statement = this.NAMESPACE+".do_getAll";
		LGS_TicketVO inVO = (LGS_TicketVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.no param=");
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.selectOne(statement, inVO);
	
		LOG.debug("========================");
		LOG.debug("03.list="+flag);
		LOG.debug("========================");	
		
		return flag;
	}
	
	
	
	
}
