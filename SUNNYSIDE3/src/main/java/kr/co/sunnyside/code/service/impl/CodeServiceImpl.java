package kr.co.sunnyside.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.code.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {
	@Autowired
	private CodeDaoImpl codeDaoImpl;
	
	@Override
	public List<?> do_retrieve(DTO dto) {
		
		return codeDaoImpl.do_retrieve(dto);
	}

}
