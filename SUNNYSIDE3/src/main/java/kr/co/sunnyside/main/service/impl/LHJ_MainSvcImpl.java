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
	
	/**베너이미지 전체조회*/
	@Override
	public List<?> do_banner_retrieve() {
		return mainDaoImpl.do_banner_retrieve();
	}

	/**베너이미지 등록*/
	@Override
	public int do_image_save(DTO dto) {
		return mainDaoImpl.do_image_save(dto);
	}

	/**베너이미지 삭제*/
	@Override
	public int do_image_delete(DTO dto) {
		return mainDaoImpl.do_image_delete(dto);
	}

}
