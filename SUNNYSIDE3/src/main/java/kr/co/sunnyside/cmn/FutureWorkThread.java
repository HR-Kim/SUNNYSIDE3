package kr.co.sunnyside.cmn;

import java.util.Calendar;
import java.util.Date;

public class FutureWorkThread extends Thread {

	//private int mHour;
	private int mMinute;

	public FutureWorkThread( int minute){
		//mHour = hour;
		mMinute = minute;
	}
	

	@Override
	public void run() {
		try {
			sleep( timeUntil(27));
			// 작업을 수행한다.
			work();
		} catch ( InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public void work(){
		System.out.println( "i'm working!");
	}


	public long timeUntil(int minute){

		Date now = new Date();

		Calendar calUntil = Calendar.getInstance();
		//calUntil.set( Calendar.HOUR_OF_DAY, hour);
		calUntil.set( Calendar.MINUTE, minute);
		calUntil.set( Calendar.SECOND, 0);


		Date until = calUntil.getTime();
		long sleep = until.getTime() - now.getTime();

		return sleep;

	}

	
}
