package kr.co.sunnyside.cmn;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schecduler {
	
	/** * 1. 오후 05:50:00에 호출이 되는 스케쥴러 */ 
	@Scheduled(cron = "0 50 17 * * *")
	public void cronTest1(){ 
		System.out.println("오후 05:50:00에 호출이 됩니다 "); 

	}
	
	/** * 2. 오후 05:51:00에 호출이 되는 스케쥴러 */ 
	@Scheduled(cron = "0 51 17 * * *") 
	public void cronTest2(){ 
		System.out.println("오후 05:51:00에 호출이 됩니다 "); 
	}

}

