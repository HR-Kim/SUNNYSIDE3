package kr.co.sunnyside.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.main.service.LHJ_MainSvc;

@Service
public class LHJ_MainSvcImpl implements LHJ_MainSvc{
	@Autowired
	private LHJ_MainDaoImpl mainDaoImpl;
	
	@Override
	public List<?> do_banner_retrieve() {
		return mainDaoImpl.do_banner_retrieve();
	}

}
