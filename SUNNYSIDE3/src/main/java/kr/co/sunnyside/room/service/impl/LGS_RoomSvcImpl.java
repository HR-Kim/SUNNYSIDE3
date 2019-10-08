package kr.co.sunnyside.room.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.room.service.LGS_RoomSvc;

@Service
public class LGS_RoomSvcImpl implements LGS_RoomSvc {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_RoomDaoImpl roomDao;
		
	@Override
	public int do_save(DTO dto) {
		return roomDao.do_save(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return roomDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {
		return roomDao.do_update(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		return roomDao.do_retrieve(dto);
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		return roomDao.do_selectOne(dto);
	}

}
