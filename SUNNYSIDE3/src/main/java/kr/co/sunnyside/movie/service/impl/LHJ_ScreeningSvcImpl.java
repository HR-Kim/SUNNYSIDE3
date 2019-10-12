package kr.co.sunnyside.movie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.movie.service.LHJ_ScreeningSvc;

@Service
public class LHJ_ScreeningSvcImpl implements LHJ_ScreeningSvc {

	@Autowired
	private LHJ_ScreeningDaoImpl screeningDaoImpl;
	
	/** 비 상영상태로 상태 변경(000) */
	@Override
	public int do_update_screenDown(DTO dto) {		
		return screeningDaoImpl.do_update_screenDown(dto);
	}

	/** 최신개봉으로 상태 변경(상영중으로 상태 변경)(010) */
	@Override
	public int do_update_screenUp(DTO dto) {
		return screeningDaoImpl.do_update_screenUp(dto);
	}

	/**단건조회*/
	@Override
	public DTO do_selectOne(DTO dto) {
		return screeningDaoImpl.do_selectOne(dto);
	}

	/**목록조회*/
	@Override
	public List<?> do_retrieve(DTO dto) {
		return screeningDaoImpl.do_retrieve(dto);
	}
	
	/** 최근 개봉(상영중) 등록 리스트 조회 */
	@Override
	public List<?> do_screenUp_retrieve(DTO dto) {
		return screeningDaoImpl.do_screenUp_retrieve(dto);
	}

}
