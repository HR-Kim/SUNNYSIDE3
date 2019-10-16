package kr.co.sunnyside.coupon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.coupon.service.SJH_CouponSvc;
import kr.co.sunnyside.login.service.impl.SJH_LoginSvcImpl;
import kr.co.sunnyside.usermypage.service.impl.SJH_MypageDao;

@Service
public class SJH_CouponSvcImpl implements SJH_CouponSvc {

	Logger LOG = LoggerFactory.getLogger(SJH_LoginSvcImpl.class);
	
	@Autowired
	private SJH_CouponDao couponDao;
	
	
	@Override
	public int do_save(DTO dto) {
		return couponDao.do_save(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return couponDao.do_delete(dto);
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return couponDao.do_selectOne(dto);
	}

	
}
