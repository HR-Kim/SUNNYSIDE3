package kr.co.sunnyside.notice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.notice.service.KYMNoticeSvc;

@Service
public class KYMNoticeSvcImpl implements KYMNoticeSvc{
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private KYMNoticeDaoImpl kymNoticeDaoImpl;

	@Override
	public int do_update(DTO dto) {
		return kymNoticeDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return kymNoticeDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return kymNoticeDaoImpl.do_save(dto);
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return kymNoticeDaoImpl.do_selectOne(dto);
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return kymNoticeDaoImpl.do_retrieve(dto);
	}

	@Override
	public List<?> do_retrieveTwo(DTO dto) {
		return kymNoticeDaoImpl.do_retrieveTwo(dto);
	}

	@Override
	public List<?> do_retrieveThree(DTO dto) {
		return kymNoticeDaoImpl.do_retrieveThree(dto);
	}

	@Override
	public List<?> do_retrieveFour(DTO dto) {
		return kymNoticeDaoImpl.do_retrieveFour(dto);
	}

}
