package kr.co.sunnyside.movie.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LHJ_MovieSvc {
	/**단건조회*/
	public DTO do_selectOne(DTO dto);
	
	/**전체조회*/
	public List<?> do_retrieve(DTO dto);
	
	/**영화정보 저장*/
	public int do_save(DTO dto);
}
