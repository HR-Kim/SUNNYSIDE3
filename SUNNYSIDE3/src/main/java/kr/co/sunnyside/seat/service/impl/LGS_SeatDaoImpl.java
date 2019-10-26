package kr.co.sunnyside.seat.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.seat.service.LGS_SeatVO;

@Repository
public class LGS_SeatDaoImpl implements WorkDiv {
Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "seat";
	
	@Override
	public int do_save(DTO dto) {
		String statement = this.NAMESPACE + ".do_save";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_SeatVO seat = (LGS_SeatVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("seat = " + seat);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.insert(statement, seat);
		
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
		
		LGS_SeatVO seat = (LGS_SeatVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("seat = " + seat);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.delete(statement, seat);
		
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
		
		LGS_SeatVO seat = (LGS_SeatVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("seat = " + seat);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.update(statement, seat);
		
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
		
		List<LGS_SeatVO> list = sqlSessionTemplate.selectList(statement, search);
		
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
		
		LGS_SeatVO seat = (LGS_SeatVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("seat = " + seat);
		LOG.debug("==================================");
		
		LGS_SeatVO outVO = (LGS_SeatVO) sqlSessionTemplate.selectOne(statement, seat);
		
		LOG.debug("==================================");
		LOG.debug("outVO = " + outVO);
		LOG.debug("==================================");
		
		return outVO;
	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		return null;
	}
	
	public int do_save_reservation(DTO dto) {
		String statement = this.NAMESPACE + ".do_save_reservation";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_SeatVO seat = (LGS_SeatVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("seat = " + seat);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.insert(statement, seat);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	public int do_delete_reservation(DTO dto) {
		String statement = this.NAMESPACE + ".do_delete_reservation";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_SeatVO seat = (LGS_SeatVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("seat = " + seat);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.delete(statement, seat);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}
	
	public int do_update_reservation(DTO dto) {
		String statement = this.NAMESPACE + ".do_update_reservation";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_SeatVO seat = (LGS_SeatVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("seat = " + seat);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.update(statement, seat);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	public List<?> do_retrieve_reservation(DTO dto) {
		String statement = this.NAMESPACE + ".do_retrieve_reservation";
	
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

	public DTO do_selectOne_reservation(DTO dto) {
		String statement = this.NAMESPACE + ".do_selectOne_reservation";

		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_SeatVO seat = (LGS_SeatVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("seat = " + seat);
		LOG.debug("==================================");
		
		LGS_SeatVO outVO = (LGS_SeatVO) sqlSessionTemplate.selectOne(statement, seat);
		
		LOG.debug("==================================");
		LOG.debug("outVO = " + outVO);
		LOG.debug("==================================");
		
		return outVO;
	}

	public int do_deleteAll_reservation() {
		String statement = this.NAMESPACE + ".do_deleteAll_reservation";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");

		int flag = sqlSessionTemplate.delete(statement);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}
}
