package kr.co.sunnyside.userpage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.reservation.service.LGS_ReservationSvc;
import kr.co.sunnyside.userpage.service.KDY_UserpageSvc;

@Service
public class KDY_UserpageSvcImpl implements KDY_UserpageSvc {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	KDY_UserpageDaoImpl UserpageDao;
	
	

	@Override
	public List<?> do_retrieve(DTO dto) {
		return UserpageDao.do_retrieve(dto);
	}



	@Override
	public int do_delete(DTO dto) {
		return UserpageDao.do_delete(dto);
	}



	@Override
	public List<?> do_coupon_retrieve(DTO dto) {
		return UserpageDao.do_coupon_retrieve(dto);
	}



	@Override
	public List<?> do_membership(DTO dto) {
	
		return UserpageDao.do_membership(dto);
	}



	@Override
	public List<?> do_qnaList(DTO dto) {
		
		return UserpageDao.do_qnaList(dto);
	}



	@Override
	public List<?> do_reservationList(DTO dto) {
		return UserpageDao.do_reservationList(dto);
	}



	@Override
	public int do_delete_item(DTO dto) {
		return UserpageDao.do_delete_item(dto);
	}
	
	
}
