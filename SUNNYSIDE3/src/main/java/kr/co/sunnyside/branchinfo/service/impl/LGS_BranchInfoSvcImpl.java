package kr.co.sunnyside.branchinfo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoSvc;
import kr.co.sunnyside.cmn.DTO;

@Service
public class LGS_BranchInfoSvcImpl implements LGS_BranchInfoSvc {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_BranchInfoDaoImpl branchInfoDao;
	
	@Override
	public int do_save(DTO dto) {
		return branchInfoDao.do_save(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return branchInfoDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {
		return branchInfoDao.do_update(dto);
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return branchInfoDao.do_retrieve(dto);
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return branchInfoDao.do_selectOne(dto);
	}

}
