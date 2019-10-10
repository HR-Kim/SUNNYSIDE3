package kr.co.sunnyside.movie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.movie.service.LHJ_BoxofficeSvc;

@Service
public class LHJ_BoxofficeSvcImpl implements LHJ_BoxofficeSvc {

	@Autowired
	private LHJ_BoxofficeDaoImpl boxofficeDaoImpl;

	@Override
	public int do_boxofficeOff_update(DTO dto) {
		return boxofficeDaoImpl.do_boxofficeOff_update();
	}

	@Override
	public int do_boxofficeOn_update(DTO dto) {
		return boxofficeDaoImpl.do_boxofficeOn_update(dto);
	}

	@Override
	public int do_delete() {
		return boxofficeDaoImpl.do_delete();
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return boxofficeDaoImpl.do_selectOne(dto);
	}

	@Override
	public List<?> do_retrieve() {
		return boxofficeDaoImpl.do_retrieve();
	}

	@Override
	public int do_save(DTO dto) {
		return boxofficeDaoImpl.do_save(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}



}
