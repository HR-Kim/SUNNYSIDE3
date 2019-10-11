package kr.co.sunnyside.movie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.movie.service.LHJ_PlanedSvc;

@Service
public class LHJ_PlanedSvcImpl implements LHJ_PlanedSvc {
	@Autowired
	private LHJ_PlanedDaoImpl planedDaoImpl;
	
	/** 개봉예정으로 상태 변경(020)  */
	@Override
	public int do_update_planedUp(DTO dto) {
		return planedDaoImpl.do_update_planedUp(dto);
	}
	
	/** 기본상태로 상태 변경(000) */
	@Override
	public int do_update_planedDown(DTO dto) {
		return planedDaoImpl.do_update_planedDown(dto);
	}
	
	/** 최신개봉(상영중)으로 상태 변경(010) */
	@Override
	public int do_update_planedToScreen(DTO dto) {
		return planedDaoImpl.do_update_planedToScreen(dto);
	}

	/**단건조회*/
	@Override
	public DTO do_selectOne(DTO dto) {
		return planedDaoImpl.do_selectOne(dto);
	}

	/**목록조회*/
	@Override
	public List<?> do_retrieve(DTO dto) {
		return planedDaoImpl.do_retrieve(dto);
	}

}
