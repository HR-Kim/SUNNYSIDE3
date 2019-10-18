package kr.co.sunnyside.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_MovieDaoImpl;
import kr.co.sunnyside.movie.service.impl.LHJ_ScreeningDaoImpl;
import kr.co.sunnyside.review.service.LHJ_ReviewSvc;

@Service
public class LHJ_ReviewSvcImpl implements LHJ_ReviewSvc {

	@Autowired
	private LHJ_ReviewDaoImpl reviewDaoImpl;
	
	@Autowired
	private LHJ_MovieDaoImpl movieDaoImpl;
	
	/**리뷰 수정*/
	@Override
	public int do_update(DTO dto) {			
		return reviewDaoImpl.do_update(dto);
	}

	/**리뷰 삭제*/
	@Override
	public int do_delete(DTO dto) {	
		return reviewDaoImpl.do_delete(dto);
	}

	/**리뷰 저장*/
	@Override
	public int do_save(DTO dto) {	
		return reviewDaoImpl.do_save(dto);
	}

	/**단건조회*/
	@Override
	public DTO do_selectOne(DTO dto) {
		return reviewDaoImpl.do_selectOne(dto);
	}

	/**목록조회*/
	@Override
	public List<?> do_retrieve(DTO dto) {
		return reviewDaoImpl.do_retrieve(dto);
	}	
}
