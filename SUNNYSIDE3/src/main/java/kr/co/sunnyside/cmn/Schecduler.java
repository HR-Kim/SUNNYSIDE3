package kr.co.sunnyside.cmn;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.sunnyside.movie.service.LHJ_BoxofficeSvc;
import kr.co.sunnyside.seat.service.LGS_SeatSvc;
import kr.co.sunnyside.usermypage.service.SJH_MypageSvc;

@Component
public class Schecduler {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SJH_MypageSvc mypageSvc;
	
	@Autowired
	LHJ_BoxofficeSvc boxofficeSvc;
	
	@Autowired
	LGS_SeatSvc seatSvc;	
	
	/**
	 * 박스오피스 데이터 갱신 스케줄러 (cron 초 분 시 일 월 요일)
	 */ 
	@Scheduled(cron = "0 0 11 * * *")
	public void do_deleteAndSave() { 
		LOG.debug("======================");
		LOG.debug("=  박스오피스 데이터 갱신   =");
		LOG.debug("======================");
		
		boxofficeSvc.do_deleteAndSave();		
	}
	
	/**
	 * 자동등업 스케줄러 (cron 초 분 시 일 월 요일) --- 현재 돌아오는 1초마다 돌게 해놓음.
	 * @throws SQLException
	 */ 
	@Scheduled(cron = "0 30 5 * * *")
	public void tx_upgradeLevels() throws SQLException{ 
		LOG.debug("======================");
		LOG.debug("=    자동등업 스케줄러       =");
		LOG.debug("======================");
		
		mypageSvc.tx_upgradeLevels();
		
	}
	
	/**
	 * 상영끝난 좌석데이터 삭제 스케줄러 (cron 초 분 시 일 월 요일) --- 월요일마다
	 * @throws SQLException
	 */ 
	@Scheduled(cron = "0 0 0 * * MON")
	public void do_delete_seat_reservation() throws SQLException{ 
		LOG.debug("=================================");
		LOG.debug("=    상영끝난 좌석데이터 삭제 스케줄러       =");
		LOG.debug("=================================");
		
		seatSvc.do_deleteAll_reservation();
	}
	
}

