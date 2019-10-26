package kr.co.sunnyside.seat.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.seat.service.LGS_SeatSvc;

@Service
public class LGS_SeatSvcImpl implements LGS_SeatSvc {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_SeatDaoImpl seatDao;
	
	@Override
	public int do_save(DTO dto) {
		return seatDao.do_save(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return seatDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {
		return seatDao.do_update(dto);
	}
	
	@Override
	public List<?> do_retrieve(DTO dto) {
		return seatDao.do_retrieve(dto);
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return seatDao.do_selectOne(dto);
	}

	@Override
	public int do_save_reservation(DTO dto) {
		return seatDao.do_save_reservation(dto);
	}

	@Override
	public int do_delete_reservation(DTO dto) {
		return seatDao.do_delete_reservation(dto);
	}

	@Override
	public int do_update_reservation(DTO dto) {
		return seatDao.do_update_reservation(dto);
	}

	@Override
	public List<?> do_retrieve_reservation(DTO dto) {
		return seatDao.do_retrieve_reservation(dto);
	}

	@Override
	public DTO do_selectOne_reservation(DTO dto) {
		return seatDao.do_selectOne_reservation(dto);
	}

	@Override
	public int do_deleteAll_reservation() {
		return seatDao.do_deleteAll_reservation();
	}

}
