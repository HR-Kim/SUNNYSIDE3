package kr.co.sunnyside.chart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.chart.service.SJH_ChartSvc;
import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.coupon.service.impl.SJH_CouponDao;
import kr.co.sunnyside.login.service.impl.SJH_LoginSvcImpl;
import kr.co.sunnyside.usermypage.service.impl.SJH_MypageDao;

@Service
public class SJH_ChartSvcImpl implements SJH_ChartSvc {

	Logger LOG = LoggerFactory.getLogger(SJH_LoginSvcImpl.class);
	
	@Autowired
	private SJH_ChartDao chartDao;
	
	
	@Override
	public int do_getAll(DTO dto) {
		return chartDao.do_getAll(dto);
	}
	
	
}
