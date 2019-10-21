package kr.co.sunnyside.cmn;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.sunnyside.usermypage.service.SJH_MypageSvc;

@Component
public class Schecduler {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SJH_MypageSvc mypageSvc;
	
	
	/**
	 * 자동등업 스케줄러 (cron 초 분 시 일 월 요일) --- 현재 돌아오는 1초마다 돌게 해놓음.
	 * @throws SQLException
	 */ 
	@Scheduled(cron = "0 0 9 * * *")
	public void tx_upgradeLevels() throws SQLException{ 
		LOG.debug("======================");
		LOG.debug("=    자동등업 스케줄러       =");
		LOG.debug("======================");
		
		mypageSvc.tx_upgradeLevels();
		
	}
	
}

