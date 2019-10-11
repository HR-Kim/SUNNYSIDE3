package kr.co.sunnyside.cmn;

import java.util.Timer;
import java.util.TimerTask;


public class MainTimer {

	public static void main(String[] args) {

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("등업");
			}
		};
		
		//timer.schedule(task, long delay, long period);
		
		timer.schedule(task,1000);
		
		
	}//--main

}//--MainTimer
