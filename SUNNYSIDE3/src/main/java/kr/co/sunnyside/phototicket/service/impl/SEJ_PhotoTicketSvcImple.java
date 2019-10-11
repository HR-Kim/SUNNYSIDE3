package kr.co.sunnyside.phototicket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.phototicket.service.SEJ_MovieHistoryVO;
import kr.co.sunnyside.phototicket.service.SEJ_PhotoTicketSvc;
import kr.co.sunnyside.phototicket.service.SEJ_PhotoTicketVO;

@Service
public class SEJ_PhotoTicketSvcImple implements SEJ_PhotoTicketSvc{
	@Autowired
	SEJ_PhotoTicketDaoImpl  photoTicketDaoImpl=new SEJ_PhotoTicketDaoImpl();
	@Override
	public List<SEJ_PhotoTicketVO> do_retrieve(DTO vo) {
			return (List<SEJ_PhotoTicketVO>) photoTicketDaoImpl.do_retrieve(vo);
		}

	@Override
	public int do_insert(DTO vo) {
		// TODO Auto-generated method stub
		return  photoTicketDaoImpl.do_save(vo);
	}

	@Override
	public DTO do_selectOne(DTO vo) {
		return photoTicketDaoImpl.do_selectOne(vo);
		
		
	}
	

}
