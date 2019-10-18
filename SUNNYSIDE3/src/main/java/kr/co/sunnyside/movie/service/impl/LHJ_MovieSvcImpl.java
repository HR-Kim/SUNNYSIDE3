package kr.co.sunnyside.movie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.movie.service.LHJ_MovieSvc;

@Service
public class LHJ_MovieSvcImpl implements LHJ_MovieSvc {
	@Autowired
	private LHJ_MovieDaoImpl movieDaoImpl;
	
	@Override
	public DTO do_selectOne(DTO dto) {
		return movieDaoImpl.do_selectOne(dto);
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return movieDaoImpl.do_retrieve(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return movieDaoImpl.do_save(dto);
	}

	@Override
	public int do_visitorRate_update(DTO dto) {
		return movieDaoImpl.do_visitorRate_update(dto);
	}

}
