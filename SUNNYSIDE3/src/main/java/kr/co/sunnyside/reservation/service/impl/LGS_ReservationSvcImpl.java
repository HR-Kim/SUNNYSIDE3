package kr.co.sunnyside.reservation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.reservation.service.LGS_ReservationSvc;

public class LGS_ReservationSvcImpl implements LGS_ReservationSvc {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_ReservationDaoImpl reservationDao;
	
	@Override
	public int do_save(DTO dto) {
		return reservationDao.do_save(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return reservationDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {
		return reservationDao.do_update(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		return reservationDao.get_retrieve(dto);
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		return reservationDao.get_selectOne(dto);
	}

}