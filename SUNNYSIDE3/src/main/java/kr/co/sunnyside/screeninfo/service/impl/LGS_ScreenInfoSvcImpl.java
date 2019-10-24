package kr.co.sunnyside.screeninfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.screeninfo.service.LGS_ScreenInfoSvc;

@Service
public class LGS_ScreenInfoSvcImpl implements LGS_ScreenInfoSvc {
	
	@Autowired
	LGS_ScreenInfoDaoImpl screenInfoDao;
	
	
	@Override
	public String do_save_NEW(DTO dto) {
		return screenInfoDao.do_save_NEW(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return screenInfoDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {
		return screenInfoDao.do_update(dto);
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return screenInfoDao.do_retrieve(dto);
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return screenInfoDao.do_selectOne(dto);
	}
	
	@Override
	public List<?> do_retrieve_forUser(DTO dto) {
		return screenInfoDao.do_retrieve_forUser(dto);
	}
	
	@Override
	public List<?> do_retrieve_branch(DTO dto) {
		return screenInfoDao.do_retrieve_branch(dto);
	}

}
