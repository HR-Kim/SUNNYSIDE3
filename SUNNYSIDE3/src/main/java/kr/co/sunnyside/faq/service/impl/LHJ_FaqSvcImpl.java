package kr.co.sunnyside.faq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.faq.service.LHJ_FaqSvc;

@Service
public class LHJ_FaqSvcImpl implements LHJ_FaqSvc {
	@Autowired
	LHJ_FaqDaoImpl faqDaoImpl;
	
	/**수정*/
	@Override
	public int do_update(DTO dto) {
		return faqDaoImpl.do_update(dto);
	}

	/**삭제*/
	@Override
	public int do_delete(DTO dto) {
		return faqDaoImpl.do_delete(dto);
	}

	/**저장*/
	@Override
	public int do_save(DTO dto) {
		return faqDaoImpl.do_save(dto);
	}

	/**전체조회*/
	@Override
	public List<?> do_retrieve() {
		return faqDaoImpl.do_retrieve();
	}
	
}
