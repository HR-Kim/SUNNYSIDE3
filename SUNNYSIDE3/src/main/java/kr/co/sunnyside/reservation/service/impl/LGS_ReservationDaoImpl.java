package kr.co.sunnyside.reservation.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.reservation.service.LGS_TicketVO;
import kr.co.sunnyside.seat.service.LGS_SeatVO;

@Repository
public class LGS_ReservationDaoImpl implements WorkDiv {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "reservation";
	
	@Override
	public int do_save(DTO dto) {
		String statement = this.NAMESPACE + ".do_save";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_TicketVO ticketVO = (LGS_TicketVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("ticketVO = " + ticketVO);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.insert(statement, ticketVO);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = this.NAMESPACE + ".do_delete";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_TicketVO ticketVO = (LGS_TicketVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("ticketVO = " + ticketVO);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.delete(statement, ticketVO);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	@Override
	public int do_update(DTO dto) {
		String statement = this.NAMESPACE + ".do_update";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_TicketVO ticketVO = (LGS_TicketVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("ticketVO = " + ticketVO);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.update(statement, ticketVO);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		String statement = this.NAMESPACE + ".do_retrieve";
	
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		SearchVO search = (SearchVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("search = " + search);
		LOG.debug("==================================");
		
		List<LGS_TicketVO> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==================================");
		LOG.debug("list = " + list);
		LOG.debug("==================================");
		
		return list;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = this.NAMESPACE + ".do_selectOne";

		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_TicketVO ticketVO = (LGS_TicketVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("ticketVO = " + ticketVO);
		LOG.debug("==================================");
		
		LGS_TicketVO outVO = (LGS_TicketVO) sqlSessionTemplate.selectOne(statement, ticketVO);
		
		LOG.debug("==================================");
		LOG.debug("outVO = " + outVO);
		LOG.debug("==================================");
		
		return outVO;
	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		return null;
	}

	public DTO do_selectOne_result(DTO dto) {
		String statement = this.NAMESPACE + ".do_selectOne_result";

		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_TicketVO ticketVO = (LGS_TicketVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("ticketVO = " + ticketVO);
		LOG.debug("==================================");
		
		LGS_TicketVO outVO = (LGS_TicketVO) sqlSessionTemplate.selectOne(statement, ticketVO);
		
		LOG.debug("==================================");
		LOG.debug("outVO = " + outVO);
		LOG.debug("==================================");
		
		return outVO;
	}
	
	public List<?> do_retrieve_seatRealTime(DTO dto) {
		String statement = this.NAMESPACE + ".do_retrieve_seatRealTime";
	
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		SearchVO search = (SearchVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("search = " + search);
		LOG.debug("==================================");
		
		List<LGS_SeatVO> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==================================");
		LOG.debug("list = " + list);
		LOG.debug("==================================");
		
		return list;
	}
}
