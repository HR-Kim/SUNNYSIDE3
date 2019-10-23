package kr.co.sunnyside.qna.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.qna.service.KYMQnaSvc;

@Service
public class KYMQnaSvcImpl implements KYMQnaSvc{
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private KYMQnaDaoImpl kymQnaDaoImpl;

	@Override
	public int do_update(DTO dto) {
		return kymQnaDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return kymQnaDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return kymQnaDaoImpl.do_save(dto);
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return kymQnaDaoImpl.do_selectOne(dto);
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return kymQnaDaoImpl.do_retrieve(dto);
	}

	@Override
	public List<?> do_retrieveTwo(DTO dto) {
		return kymQnaDaoImpl.do_retrieveTwo(dto);
	}

	@Override
	public List<?> do_retrieveThree(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> do_retrieveFour(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int do_updateTwo(DTO dto) {
		return kymQnaDaoImpl.do_updateTwo(dto);
	}

}
