package kr.co.sunnyside.login.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.login.service.SJH_LoginSvc;
import kr.co.sunnyside.login.service.SJH_LoginVO;

@Service
public class SJH_LoginSvcImpl implements SJH_LoginSvc{
	
	Logger LOG = LoggerFactory.getLogger(SJH_LoginSvcImpl.class);
	
	@Autowired
	private SJH_LoginDao loginDao;

	

	@Override
	public DTO id_find(DTO dto) {
		return loginDao.id_find(dto);
	}


	@Override
	public DTO pw_find(DTO dto) {
		return loginDao.pw_find(dto);
	}
	
	
	@Override
	public int do_save(DTO dto) {
		return loginDao.do_save(dto);
	}
	

	@Override
	public DTO get_selectOne(DTO dto) {
		return loginDao.get_selectOne(dto);
	}
	
	
	@Override
	public DTO idPassCheck(DTO dto) {
		Message outMsg = new Message();
		
		//-----------------------------
		//1.ID체크
		//-----------------------------
		int flag = loginDao.id_check(dto);
		if(flag<1) {
			outMsg.setMsgId("10");
			outMsg.setMsgMsg("아이디를 확인하세요."); //나중에 메세지 프로퍼티로 처리
			return outMsg;
		}
		
		//-----------------------------
		//2.비번체크
		//-----------------------------
		flag = loginDao.passwd_check(dto);
		if(flag<1) {
			outMsg.setMsgId("20");
			outMsg.setMsgMsg("비번을 확인하세요."); //나중에 메세지 프로퍼티로 처리
			return outMsg;
		}
		
		if(flag==1) {
			outMsg.setMsgId("30");
		}
		
		LOG.debug("=================");
		LOG.debug("=outMsg="+outMsg);
		LOG.debug("=================");
		
		return outMsg;
	}


}
